package io.krystof.obs.websocket;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.krystof.obs.websocket.exceptions.MessageHandlingException;
import io.krystof.obs.websocket.messages.ObsMessage;
import io.krystof.obs.websocket.messages.ObsMessageDeserializer;
import io.krystof.obs.websocket.messages.auth.Authentication;
import io.krystof.obs.websocket.messages.auth.Helo;
import io.krystof.obs.websocket.messages.auth.Identified;
import io.krystof.obs.websocket.messages.auth.Identity;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

public class ObsWebSocket extends WebSocketClient {
	private static final Logger LOG = LoggerFactory.getLogger(ObsWebSocket.class);

	ObjectMapper objectMapper = createObjectMapper();

	private String password;

	Optional<Integer> eventMask;

	CountDownLatch startSignal = new CountDownLatch(1);

	public ObsWebSocket(URI serverUri, Optional<Integer> eventsToListenFor) {
		super(serverUri);
		addHeader("Sec-WebSocket-Protocol", "obswebsocket.json");
		if (eventsToListenFor == null) {
			throw new IllegalArgumentException("Pass an empty optional in for events to listen for, not just null.");
		}
		this.eventMask = eventsToListenFor;
		try {
			connectBlocking();
		} catch (InterruptedException e) {
			Thread.interrupted();
			throw new RuntimeException("Error connecting: ", e);
		}
	}

	public ObsWebSocket(URI host, String password, Optional<Integer> eventsToListenFor) {
		this(host, eventsToListenFor);
		this.password = password;
	}

	@SuppressWarnings("serial")
	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new SimpleModule() {
			{
				addDeserializer(ObsMessage.class, new ObsMessageDeserializer());
			}
		});
		return objectMapper;
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		LOG.info("onOpen: {}", handshakedata);
	}

	@Override
	public void onMessage(String message) {
		LOG.info("onMessage: {}", message);
		try {
			ObsMessage obsMessage = objectMapper.readValue(message, ObsMessage.class);
			LOG.info("RECEIVE OBS Message: {}", obsMessage);
			if (obsMessage instanceof Helo) {
				handleHelo(Helo.class.cast(obsMessage));
			} else if (obsMessage instanceof Identified) {
				handleIdentified(Identified.class.cast(obsMessage));
			} else if (obsMessage instanceof AbstractObsEventMessage) {
				handleAbstractEventMessage(AbstractObsEventMessage.class.cast(obsMessage));
			} else {
				throw new MessageHandlingException("Unsupported message: " + obsMessage);
			}
		} catch (Exception e) {
			throw new MessageHandlingException(e);
		}
	}

	private void handleAbstractEventMessage(AbstractObsEventMessage abstractObsEventMessage) {
		LOG.info("Handle Event: {}", abstractObsEventMessage);
	}

	private void handleIdentified(Identified identified) {
		LOG.info("Identified!");
		startSignal.countDown();
	}

	private void send(ObsMessage obsMessage) {
		LOG.info("SEND: {}", obsMessage);
		try {
			send(objectMapper.writeValueAsString(obsMessage));
		} catch (JsonProcessingException e) {
			throw new MessageHandlingException("Error sending message.", e);
		}

	}

	private void handleHelo(Helo message) {
		if (message.getData().getAuthentication() != null) {
			Authentication auth = message.getData().getAuthentication();
			String authString = createAuthString(password, auth.getChallenge(), auth.getSalt());
			send(new Identity().getData().setAuthentication(authString)
					.setRpcVersion(message.getData().getRpcVersion()).setEventSubscriptions(eventMask.orElse(0)).and());
		} else {
			send(new Identity().getData().setRpcVersion(message.getData().getRpcVersion())
					.setEventSubscriptions(eventMask.orElse(0)).and());
		}
	}

	private String createAuthString(String password, String challenge, String salt) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			String secretString = this.password + salt;
			byte[] secretHash = digest.digest(secretString.getBytes(StandardCharsets.UTF_8));
			String encodedSecret = Base64.getEncoder().encodeToString(secretHash);

			String resultString = encodedSecret + challenge;
			byte[] resultHash = digest.digest(resultString.getBytes(StandardCharsets.UTF_8));

			return Base64.getEncoder().encodeToString(resultHash);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Could not find expected message digest to compute auth", e);
		}
	}

	@Override
	public void onError(Exception ex) {
		LOG.info("onError: {}", ex);

	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		LOG.info("onClose: code: {}  reason:{}  remote:{}", code, reason, remote);
	}

	public void sendRequest(AbstractObsRequestMessage request) {
		try {
			startSignal.await(10, TimeUnit.SECONDS);
			if (startSignal.getCount() > 0) {
				throw new RuntimeException("Timeout waiting for OBS Identification Response.");
			}
		} catch (InterruptedException e) {
			Thread.interrupted();
			throw new RuntimeException(e);
		}
		send(request);
	}
}

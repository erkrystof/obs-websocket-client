package io.krystof.obs.websocket;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.krystof.obs.websocket.exceptions.InvalidResponseStatusCodeException;
import io.krystof.obs.websocket.exceptions.MessageHandlingException;
import io.krystof.obs.websocket.messages.ObsMessage;
import io.krystof.obs.websocket.messages.ObsMessageDeserializer;
import io.krystof.obs.websocket.messages.auth.Authentication;
import io.krystof.obs.websocket.messages.auth.Helo;
import io.krystof.obs.websocket.messages.auth.Identified;
import io.krystof.obs.websocket.messages.auth.Identity;
import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObjectDeserializer;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObjectDeserializer;

public class ObsWebSocket extends WebSocketClient {
	private static final Logger LOG = LoggerFactory.getLogger(ObsWebSocket.class);

	ObjectMapper objectMapper = createObjectMapper();

	private String password;

	Optional<Integer> eventMask;

	CountDownLatch startSignal = new CountDownLatch(1);

	Set<ObsEventListener> obsEventListeners = ConcurrentHashMap.newKeySet();

	private ConcurrentHashMap<String, RequestResponsePair> openRequestsAndResponses = new ConcurrentHashMap<>();

	ExecutorService eventListenerExecutorService = Executors.newFixedThreadPool(10);

	public ObsWebSocket(URI serverUri, Optional<Integer> eventsToListenFor) {
		this(serverUri, null, eventsToListenFor);
	}

	public ObsWebSocket(URI serverUri, String password, Optional<Integer> eventsToListenFor) {
		super(serverUri);
		this.password = password;
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

	public void addEventListener(ObsEventListener obsEventListener) {
		obsEventListeners.add(obsEventListener);
	}

	@SuppressWarnings("serial")
	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new SimpleModule() {
			{
				addDeserializer(ObsMessage.class, new ObsMessageDeserializer());
				addDeserializer(AbstractResponseSpecificDataObject.class,
						new AbstractResponseSpecificDataObjectDeserializer());
				addDeserializer(AbstractEventSpecificDataObject.class,
						new AbstractEventSpecificDataObjectDeserializer());
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
		LOG.info("onMessage JSON: {}", message);
		try {
			ObsMessage obsMessage = objectMapper.readValue(message, ObsMessage.class);
			if (obsMessage instanceof Helo) {
				handleHelo(Helo.class.cast(obsMessage));
			} else if (obsMessage instanceof Identified) {
				handleIdentified(Identified.class.cast(obsMessage));
			} else if (obsMessage instanceof AbstractObsEventMessage) {
				handleAbstractEventMessage(AbstractObsEventMessage.class.cast(obsMessage));
			} else if (obsMessage instanceof AbstractObsResponseMessage) {
				handleAbstractRequestResponseMessage(AbstractObsResponseMessage.class.cast(obsMessage));
			} else {
				throw new MessageHandlingException("Unsupported message: " + obsMessage);
			}
		} catch (Exception e) {
			throw new MessageHandlingException(e);
		}
	}

	private void handleAbstractRequestResponseMessage(AbstractObsResponseMessage abstractObsResponseMessage) {
		LOG.info("Handle Response Object: {}", abstractObsResponseMessage);
		RequestResponsePair requestResponsePair = openRequestsAndResponses
				.get(abstractObsResponseMessage.getData().getRequestId());
		if (requestResponsePair == null) {
			LOG.warn("Unattached response encountered, could not map to open request: {}", abstractObsResponseMessage);
		}
		requestResponsePair.setResponse(abstractObsResponseMessage);
		requestResponsePair.getCountDownLatch().countDown();
	}

	// Listeners must get these on different threads from the firing thread. The
	// onMessage parent method must complete before new requests can be made.
	private void handleAbstractEventMessage(AbstractObsEventMessage abstractObsEventMessage) {
		LOG.info("handleAbstractEventMessage: {}", abstractObsEventMessage);
		obsEventListeners.forEach(listener -> {
			eventListenerExecutorService.submit(() -> listener.eventFired(abstractObsEventMessage));
		});
	}

	private void handleIdentified(Identified identified) {
		LOG.info("handleIdentified: {}", identified);
		startSignal.countDown();
	}

	private void send(ObsMessage obsMessage) {
		LOG.info("send: (Object): {}", obsMessage);
		try {
			String jsonToSend = objectMapper.writeValueAsString(obsMessage);
			LOG.info("send: (JSON): {}", jsonToSend);

			if (isOpen()) {
				send(jsonToSend);
			} else {
				LOG.warn("Not open - trying to reconnect");
				synchronized (this) {
					try {
						this.startSignal = new CountDownLatch(1);
						this.reconnect();
						startSignal.await(5, TimeUnit.SECONDS);
						send(obsMessage);
					} catch (InterruptedException e) {
						Thread.interrupted();
						throw new RuntimeException("Interrupted while trying to reconnect.", e);
					}
				}
			}
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
		LOG.info("onError: ", ex);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		LOG.info("onClose: code: {}  reason:{}  remote:{}", code, reason, remote);
	}

	@SuppressWarnings("unchecked")
	public <T> T sendRequestWaitForResponse(AbstractObsRequestMessage abstractObsRequestMessage,
			Class<T> responseClass) {
		StopWatch sw = new StopWatch();
		sw.start();
		try {
			startSignal.await(10, TimeUnit.SECONDS);
			if (startSignal.getCount() > 0) {
				this.reconnect();
				throw new RuntimeException("Timeout waiting for OBS Identification Response.");
			}
		} catch (InterruptedException e) {
			Thread.interrupted();
			throw new RuntimeException(e);
		}

		RequestResponsePair requestResponsePair = new RequestResponsePair(
				abstractObsRequestMessage);

		openRequestsAndResponses.put(abstractObsRequestMessage.getData().getRequestId(),
				requestResponsePair);

		try {
			send(abstractObsRequestMessage);
			requestResponsePair.getCountDownLatch().await(10, TimeUnit.SECONDS);
			if (startSignal.getCount() > 0) {
				throw new RuntimeException("Timeout waiting for Request Response: " + abstractObsRequestMessage);
			}
		} catch (InterruptedException e) {
			Thread.interrupted();
			throw new RuntimeException(e);
		}

		handleResponseCodeErrors(requestResponsePair,
				requestResponsePair.getResponse().getData().getRequestStatus().getCode());

		sw.stop();
		LOG.info("In/Out for Request: {}, {} ms.", abstractObsRequestMessage.getData().getRequestType(), sw.getTime());
		return (T) requestResponsePair.getResponse();

	}

	private void handleResponseCodeErrors(
			RequestResponsePair requestResponsePair, int code) {
		if (code != 100) {
			throw new InvalidResponseStatusCodeException(code, code + " - " +
					requestResponsePair.getResponse().getData().getRequestStatus().getComment());
		}

	}

}

class RequestResponsePair {

	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public RequestResponsePair(AbstractObsRequestMessage request) {
		super();
		this.request = request;
	}

	private AbstractObsRequestMessage request;
	private AbstractObsResponseMessage response;

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	public AbstractObsRequestMessage getRequest() {
		return request;
	}

	public void setRequest(AbstractObsRequestMessage request) {
		this.request = request;
	}

	public AbstractObsResponseMessage getResponse() {
		return response;
	}

	public void setResponse(AbstractObsResponseMessage response) {
		this.response = response;
	}

}

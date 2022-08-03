package io.krystof.obs.websocket;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

public class ObsClient {

	public ObsClient(URI host, Optional<String> password, Optional<Integer> eventSubscriptionMask) {
		super();
		this.host = host;
		if (password.isPresent()) {
			obsWebSocket = new ObsWebSocket(host, password.get(), eventSubscriptionMask);
		} else {
			obsWebSocket = new ObsWebSocket(host, eventSubscriptionMask);
		}
	}

	private URI host;

	private static final Logger LOG = LoggerFactory.getLogger(ObsClient.class);

	private ObsWebSocket obsWebSocket;

	public void simpleSendRequest(AbstractObsRequestMessage request) {
		obsWebSocket.sendRequest(request);

	}

}

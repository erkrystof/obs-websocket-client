package io.krystof.obs.websocket.messages.responses.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;

@AutoProperty
public class TriggerMediaInputActionResponse extends AbstractObsResponseMessage {
	public TriggerMediaInputActionResponse() {
		super(AbstractObsRequestMessage.RequestResponse.TriggerMediaInputAction);
		super.setResponseData(null);
	}

}

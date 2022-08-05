package io.krystof.obs.websocket.messages.requests.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetCurrentProgramSceneRequest extends AbstractObsRequestMessage {

	public GetCurrentProgramSceneRequest() {
		super(AbstractObsRequestMessage.RequestResponse.GetCurrentProgramScene, null);
	}


}

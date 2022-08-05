package io.krystof.obs.websocket.messages.responses.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;

@AutoProperty
public class SetCurrentProgramSceneResponse extends AbstractObsResponseMessage {
	public SetCurrentProgramSceneResponse() {
		super(AbstractObsRequestMessage.RequestResponse.SetCurrentProgramScene);
	}

}

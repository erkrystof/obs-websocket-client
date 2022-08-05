package io.krystof.obs.websocket.messages.responses.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;

@AutoProperty
public class SetCurrentPreviewSceneResponse extends AbstractObsResponseMessage {
	public SetCurrentPreviewSceneResponse() {
		super(AbstractObsRequestMessage.RequestResponse.SetCurrentPreviewScene);
	}

}

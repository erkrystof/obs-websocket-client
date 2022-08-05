package io.krystof.obs.websocket.messages.requests.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetCurrentPreviewSceneRequest extends AbstractObsRequestMessage {

	public GetCurrentPreviewSceneRequest() {
		super(AbstractObsRequestMessage.RequestResponse.GetCurrentPreviewScene, null);
	}

}

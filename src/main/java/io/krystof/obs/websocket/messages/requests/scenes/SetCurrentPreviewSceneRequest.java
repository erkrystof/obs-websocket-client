package io.krystof.obs.websocket.messages.requests.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetCurrentPreviewSceneRequest extends AbstractObsRequestMessage {

	public SetCurrentPreviewSceneRequest() {
		this(null);
	}

	public SetCurrentPreviewSceneRequest(String sceneName) {
		super(AbstractObsRequestMessage.RequestResponse.SetCurrentPreviewScene,
				new RequestData().setSceneName(sceneName));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String sceneName;

		public String getSceneName() {
			return sceneName;
		}

		public RequestData setSceneName(String sceneName) {
			this.sceneName = sceneName;
			return this;
		}

	}
}

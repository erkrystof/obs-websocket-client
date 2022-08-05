package io.krystof.obs.websocket.messages.requests.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetCurrentProgramSceneRequest extends AbstractObsRequestMessage {

	public SetCurrentProgramSceneRequest() {
		this(null);
	}

	public SetCurrentProgramSceneRequest(String sceneName) {
		super(AbstractObsRequestMessage.RequestResponse.SetCurrentProgramScene,
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

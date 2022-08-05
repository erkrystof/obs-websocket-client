package io.krystof.obs.websocket.messages.requests.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetSceneItemEnabledRequest extends AbstractObsRequestMessage {

	public GetSceneItemEnabledRequest() {
		this(null, 0);
	}

	public GetSceneItemEnabledRequest(String sceneName, int sceneItemId) {
		super(AbstractObsRequestMessage.RequestResponse.GetSceneItemEnabled,
				new RequestData().setSceneName(sceneName).setSceneItemId(sceneItemId));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String sceneName;

		private int sceneItemId;

		public String getSceneName() {
			return sceneName;
		}

		public RequestData setSceneName(String sceneName) {
			this.sceneName = sceneName;
			return this;
		}

		public int getSceneItemId() {
			return sceneItemId;
		}

		public RequestData setSceneItemId(int sceneItemId) {
			this.sceneItemId = sceneItemId;
			return this;
		}

	}
}

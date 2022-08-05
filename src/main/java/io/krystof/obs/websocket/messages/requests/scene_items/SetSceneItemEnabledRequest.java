package io.krystof.obs.websocket.messages.requests.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetSceneItemEnabledRequest extends AbstractObsRequestMessage {

	public SetSceneItemEnabledRequest() {
		this(null, 0, false);
	}

	public SetSceneItemEnabledRequest(String sceneName, int sceneItemId, boolean enabled) {
		super(AbstractObsRequestMessage.RequestResponse.SetSceneItemEnabled,
				new RequestData().setSceneName(sceneName).setSceneItemId(sceneItemId).setSceneItemEnabled(enabled));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String sceneName;

		private int sceneItemId;

		private boolean sceneItemEnabled;

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

		public boolean isSceneItemEnabled() {
			return sceneItemEnabled;
		}

		public RequestData setSceneItemEnabled(boolean sceneItemEnabled) {
			this.sceneItemEnabled = sceneItemEnabled;
			return this;
		}
	}
}

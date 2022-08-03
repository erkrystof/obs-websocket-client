package io.krystof.obs.websocket.messages.events;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;

@AutoProperty
public class SceneItemSelectedEvent extends AbstractObsEventMessage {

	public SceneItemSelectedEvent() {
		super(new EventData());
	}

	@AutoProperty
	public static class EventData extends AbstractObsDataTransferObject {

		private Integer sceneItemId;

		private String sceneName;

		public Integer getSceneItemId() {
			return sceneItemId;
		}

		public void setSceneItemId(Integer sceneItemId) {
			this.sceneItemId = sceneItemId;
		}

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}
	}

}

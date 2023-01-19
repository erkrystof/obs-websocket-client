package io.krystof.obs.websocket.messages.events.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class SceneItemEnableStateChanged extends AbstractObsEventMessage {

	public SceneItemEnableStateChanged() {
		super(AbstractObsEventMessage.EventType.SceneItemEnableStateChanged, new EventData());

	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String sceneName;

		private Integer sceneItemId;

		private boolean sceneItemEnabled;

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}

		public Integer getSceneItemId() {
			return sceneItemId;
		}

		public void setSceneItemId(Integer sceneItemId) {
			this.sceneItemId = sceneItemId;
		}

		public boolean isSceneItemEnabled() {
			return sceneItemEnabled;
		}

		public void setSceneItemEnabled(boolean sceneItemEnabled) {
			this.sceneItemEnabled = sceneItemEnabled;
		}

	}

}

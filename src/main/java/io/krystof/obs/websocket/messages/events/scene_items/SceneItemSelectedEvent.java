package io.krystof.obs.websocket.messages.events.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class SceneItemSelectedEvent extends AbstractObsEventMessage {

	public SceneItemSelectedEvent() {
		super(AbstractObsEventMessage.EventType.SceneItemSelected, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

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

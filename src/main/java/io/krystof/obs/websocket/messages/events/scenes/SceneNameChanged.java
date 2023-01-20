package io.krystof.obs.websocket.messages.events.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class SceneNameChanged extends AbstractObsEventMessage {

	public SceneNameChanged() {
		super(AbstractObsEventMessage.EventType.SceneNameChanged, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String oldSceneName;

		private String sceneName;

		public String getOldSceneName() {
			return oldSceneName;
		}

		public void setOldSceneName(String oldSceneName) {
			this.oldSceneName = oldSceneName;
		}

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}


	}

}

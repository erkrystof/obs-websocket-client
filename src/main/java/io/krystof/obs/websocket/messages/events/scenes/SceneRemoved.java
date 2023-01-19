package io.krystof.obs.websocket.messages.events.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class SceneRemoved extends AbstractObsEventMessage {

	public SceneRemoved() {
		super(AbstractObsEventMessage.EventType.SceneRemoved, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String sceneName;

		private boolean isGroup;

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}

		public boolean isGroup() {
			return isGroup;
		}

		public void setGroup(boolean isGroup) {
			this.isGroup = isGroup;
		}
	}

}

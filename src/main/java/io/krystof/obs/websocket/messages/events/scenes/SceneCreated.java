package io.krystof.obs.websocket.messages.events.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class SceneCreated extends AbstractObsEventMessage {

	public SceneCreated() {
		super(AbstractObsEventMessage.EventType.SceneCreated, new EventData());
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

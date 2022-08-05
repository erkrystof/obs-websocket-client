package io.krystof.obs.websocket.messages.events.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class CurrentProgramSceneChanged extends AbstractObsEventMessage {

	public CurrentProgramSceneChanged() {
		super(new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractObsDataTransferObject {

		private String sceneName;

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}
	}

}

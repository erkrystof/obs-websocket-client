package io.krystof.obs.websocket.messages.events.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class CurrentPreviewSceneChanged extends AbstractObsEventMessage {

	public CurrentPreviewSceneChanged() {
		super(AbstractObsEventMessage.EventType.CurrentPreviewSceneChanged, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String sceneName;

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}
	}

}

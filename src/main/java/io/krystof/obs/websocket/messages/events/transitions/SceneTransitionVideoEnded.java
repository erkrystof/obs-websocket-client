package io.krystof.obs.websocket.messages.events.transitions;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class SceneTransitionVideoEnded extends AbstractObsEventMessage {

	public SceneTransitionVideoEnded() {
		super(AbstractObsEventMessage.EventType.SceneTransitionVideoEnded, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String transitionName;

		public String getTransitionName() {
			return transitionName;
		}

		public void setTransitionName(String transitionName) {
			this.transitionName = transitionName;
		}

	}

}

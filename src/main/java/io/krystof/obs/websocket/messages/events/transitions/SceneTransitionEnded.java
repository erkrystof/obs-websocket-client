package io.krystof.obs.websocket.messages.events.transitions;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class SceneTransitionEnded extends AbstractObsEventMessage {

	public SceneTransitionEnded() {
		super(new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractObsDataTransferObject {

		private String transitionName;

		public String getTransitionName() {
			return transitionName;
		}

		public void setTransitionName(String transitionName) {
			this.transitionName = transitionName;
		}

	}

}

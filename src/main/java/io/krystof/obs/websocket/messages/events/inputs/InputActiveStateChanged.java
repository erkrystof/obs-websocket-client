package io.krystof.obs.websocket.messages.events.inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class InputActiveStateChanged extends AbstractObsEventMessage {

	public InputActiveStateChanged() {
		super(AbstractObsEventMessage.EventType.InputActiveStateChanged, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String inputName;

		private boolean videoActive;

		public String getInputName() {
			return inputName;
		}

		public void setInputName(String inputName) {
			this.inputName = inputName;
		}

		public boolean isVideoActive() {
			return videoActive;
		}

		public void setVideoActive(boolean videoActive) {
			this.videoActive = videoActive;
		}


	}

}

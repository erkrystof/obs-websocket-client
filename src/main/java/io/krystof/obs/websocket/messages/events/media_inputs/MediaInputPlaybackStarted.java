package io.krystof.obs.websocket.messages.events.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class MediaInputPlaybackStarted extends AbstractObsEventMessage {

	public MediaInputPlaybackStarted() {
		super(AbstractObsEventMessage.EventType.MediaInputPlaybackStarted, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String inputName;

		public String getInputName() {
			return inputName;
		}

		public void setInputName(String inputName) {
			this.inputName = inputName;
		}


	}

}

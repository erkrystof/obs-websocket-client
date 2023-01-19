package io.krystof.obs.websocket.messages.events.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class MediaInputPlaybackEnded extends AbstractObsEventMessage {

	public MediaInputPlaybackEnded() {
		super(AbstractObsEventMessage.EventType.MediaInputPlaybackEnded, new EventData());
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

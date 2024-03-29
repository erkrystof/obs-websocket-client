package io.krystof.obs.websocket.messages.events.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.events.AbstractEventSpecificDataObject;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@AutoProperty
public class MediaInputActionTriggered extends AbstractObsEventMessage {

	public MediaInputActionTriggered() {
		super(AbstractObsEventMessage.EventType.MediaInputActionTriggered, new EventData());
	}

	public EventData getEventSpecificData() {
		return (EventData) getPayload().getEventData();
	}

	@AutoProperty
	public static class EventData extends AbstractEventSpecificDataObject {

		private String inputName;

		private String mediaAction;

		public String getInputName() {
			return inputName;
		}

		public void setInputName(String inputName) {
			this.inputName = inputName;
		}

		public String getMediaAction() {
			return mediaAction;
		}

		public void setMediaAction(String mediaAction) {
			this.mediaAction = mediaAction;
		}

	}

}

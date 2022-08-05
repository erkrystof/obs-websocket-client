package io.krystof.obs.websocket.messages.requests.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class TriggerMediaInputActionRequest extends AbstractObsRequestMessage {

	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_NONE = "OBS_WEBSOCKET_MEDIA_INPUT_ACTION_NONE";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PLAY = "OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PLAY";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PAUSE = "OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PAUSE";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_STOP = "OBS_WEBSOCKET_MEDIA_INPUT_ACTION_STOP";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_RESTART = "OBS_WEBSOCKET_MEDIA_INPUT_ACTION_RESTART";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_NEXT = "OBS_WEBSOCKET_MEDIA_INPUT_ACTION_NEXT";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PREVIOUS = "OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PREVIOUS";

	public TriggerMediaInputActionRequest() {
		this(null, null);
	}

	public TriggerMediaInputActionRequest(String inputName, String mediaAction) {
		super(AbstractObsRequestMessage.RequestResponse.TriggerMediaInputAction,
				new RequestData().setInputName(inputName).setMediaAction(mediaAction));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String inputName;

		private String mediaAction;

		public String getInputName() {
			return inputName;
		}

		public RequestData setInputName(String sceneName) {
			this.inputName = sceneName;
			return this;
		}

		public String getMediaAction() {
			return mediaAction;
		}

		public RequestData setMediaAction(String mediaAction) {
			this.mediaAction = mediaAction;
			return this;
		}
	}
}

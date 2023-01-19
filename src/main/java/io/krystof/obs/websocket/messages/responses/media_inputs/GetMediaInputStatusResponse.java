package io.krystof.obs.websocket.messages.responses.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetMediaInputStatusResponse extends AbstractObsResponseMessage {

	public static final String OBS_WEBSOCKET_MEDIA_INPUT_STATE_NONE = "OBS_MEDIA_STATE_NONE";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PLAYING = "OBS_MEDIA_STATE_PLAYING";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_OPENING = "OBS_MEDIA_STATE_OPENING";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_BUFFERING = "OBS_MEDIA_STATE_BUFFERING";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PAUSED = "OBS_MEDIA_STATE_PAUSED";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_STOPPED = "OBS_MEDIA_STATE_STOPPED";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_ENDED = "OBS_MEDIA_STATE_ENDED";
	public static final String OBS_WEBSOCKET_MEDIA_INPUT_ACTION_ERROR = "OBS_MEDIA_STATE_ERROR";

	public GetMediaInputStatusResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetMediaInputStatus);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {

		private String mediaState;
		private Integer mediaDuration;
		private Integer mediaCursor;

		public String getMediaState() {
			return mediaState;
		}

		public void setMediaState(String mediaState) {
			this.mediaState = mediaState;
		}

		public Integer getMediaDuration() {
			return mediaDuration;
		}

		public void setMediaDuration(Integer mediaDuration) {
			this.mediaDuration = mediaDuration;
		}

		public Integer getMediaCursor() {
			return mediaCursor;
		}

		public void setMediaCursor(Integer mediaCursor) {
			this.mediaCursor = mediaCursor;
		}

	}
}

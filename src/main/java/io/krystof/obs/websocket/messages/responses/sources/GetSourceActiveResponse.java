package io.krystof.obs.websocket.messages.responses.sources;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetSourceActiveResponse extends AbstractObsResponseMessage {
	public GetSourceActiveResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetSourceActive);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {

		private boolean videoActive;

		public boolean isVideoActive() {
			return videoActive;
		}

		public void setVideoActive(boolean videoActive) {
			this.videoActive = videoActive;
		}

		public boolean isVideoShowing() {
			return videoShowing;
		}

		public void setVideoShowing(boolean videoShowing) {
			this.videoShowing = videoShowing;
		}

		private boolean videoShowing;

	}
}

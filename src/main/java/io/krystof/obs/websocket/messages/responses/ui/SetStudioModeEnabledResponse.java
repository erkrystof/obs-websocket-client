package io.krystof.obs.websocket.messages.responses.ui;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class SetStudioModeEnabledResponse extends AbstractObsResponseMessage {
	public SetStudioModeEnabledResponse() {
		super(AbstractObsRequestMessage.RequestResponse.SetStudioModeEnabled);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {
		private boolean studioModeEnabled;

		public boolean isStudioModeEnabled() {
			return studioModeEnabled;
		}

		public ResponseData setStudioModeEnabled(boolean studioModeEnabled) {
			this.studioModeEnabled = studioModeEnabled;
			return this;
		}
	}
}

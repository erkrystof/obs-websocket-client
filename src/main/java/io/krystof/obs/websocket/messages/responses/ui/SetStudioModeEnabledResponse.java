package io.krystof.obs.websocket.messages.responses.ui;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.requests.AbstractObsResponseMessage;

@AutoProperty
public class SetStudioModeEnabledResponse extends AbstractObsResponseMessage {
	public SetStudioModeEnabledResponse() {
		super(AbstractObsRequestMessage.RequestResponse.SetStudioModeEnabled);
		super.setResponseData(new Data());
	}

	@AutoProperty
	public static class ResponseData extends AbstractObsDataTransferObject {
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

package io.krystof.obs.websocket.messages.requests.ui;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetStudioModeEnabledRequest extends AbstractObsRequestMessage {

	public SetStudioModeEnabledRequest() {
		this(true);
	}

	public SetStudioModeEnabledRequest(boolean enabled) {
		super(AbstractObsRequestMessage.RequestResponse.SetStudioModeEnabled,
				new RequestData().setStudioModeEnabled(enabled));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {
		private boolean studioModeEnabled;

		public boolean isStudioModeEnabled() {
			return studioModeEnabled;
		}

		public RequestData setStudioModeEnabled(boolean studioModeEnabled) {
			this.studioModeEnabled = studioModeEnabled;
			return this;
		}
	}
}

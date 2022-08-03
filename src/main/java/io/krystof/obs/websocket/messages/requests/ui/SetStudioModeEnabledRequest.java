package io.krystof.obs.websocket.messages.requests.ui;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetStudioModeEnabledRequest extends AbstractObsRequestMessage {

	public SetStudioModeEnabledRequest() {
		super(AbstractObsRequestMessage.RequestResponse.SetStudioModeEnabled);
		super.setRequestData(new Data().setStudioModeEnabled(true));
	}

	@AutoProperty
	public static class Data extends AbstractObsDataTransferObject {
		private boolean studioModeEnabled;

		public boolean isStudioModeEnabled() {
			return studioModeEnabled;
		}

		public Data setStudioModeEnabled(boolean studioModeEnabled) {
			this.studioModeEnabled = studioModeEnabled;
			return this;
		}
	}
}

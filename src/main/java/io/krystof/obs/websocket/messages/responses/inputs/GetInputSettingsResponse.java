package io.krystof.obs.websocket.messages.responses.inputs;

import java.util.Map;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.requests.AbstractObsResponseMessage;

@AutoProperty
public class GetInputSettingsResponse extends AbstractObsResponseMessage {
	public GetInputSettingsResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetInputSettings);
		super.setResponseData(new ResponseData());
	}

	@AutoProperty
	public static class ResponseData extends AbstractObsDataTransferObject {

		private String inputKind;

		Map<String, Object> inputSettings;

		public String getInputKind() {
			return inputKind;
		}

		public void setInputKind(String inputKind) {
			this.inputKind = inputKind;
		}

		public Map<String, Object> getInputSettings() {
			return inputSettings;
		}

		public void setInputSettings(Map<String, Object> inputSettings) {
			this.inputSettings = inputSettings;
		}

	}
}

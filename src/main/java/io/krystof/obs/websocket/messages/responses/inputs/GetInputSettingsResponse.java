package io.krystof.obs.websocket.messages.responses.inputs;

import java.util.Map;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetInputSettingsResponse extends AbstractObsResponseMessage {
	public GetInputSettingsResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetInputSettings);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {

		private String inputKind;

		Map<String, Object> inputSettings;

		public String getInputKind() {
			return inputKind;
		}

		public ResponseData setInputKind(String inputKind) {
			this.inputKind = inputKind;
			return this;
		}

		public Map<String, Object> getInputSettings() {
			return inputSettings;
		}

		public void setInputSettings(Map<String, Object> inputSettings) {
			this.inputSettings = inputSettings;
		}

	}
}

package io.krystof.obs.websocket.messages.requests.inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetInputSettingsRequest extends AbstractObsRequestMessage {

	public GetInputSettingsRequest() {
		this(null);
	}

	public GetInputSettingsRequest(String inputName) {
		super(AbstractObsRequestMessage.RequestResponse.GetInputSettings,
				new RequestData().setInputName(inputName));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String inputName;

		public String getInputName() {
			return inputName;
		}

		public RequestData setInputName(String sceneName) {
			this.inputName = sceneName;
			return this;
		}
	}
}


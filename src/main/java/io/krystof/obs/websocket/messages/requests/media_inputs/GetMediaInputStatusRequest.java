package io.krystof.obs.websocket.messages.requests.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetMediaInputStatusRequest extends AbstractObsRequestMessage {

	public GetMediaInputStatusRequest() {
		this(null);
	}

	public GetMediaInputStatusRequest(String inputName) {
		super(AbstractObsRequestMessage.RequestResponse.GetMediaInputStatus,
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

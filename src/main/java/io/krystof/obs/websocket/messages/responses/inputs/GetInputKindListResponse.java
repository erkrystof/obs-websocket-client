package io.krystof.obs.websocket.messages.responses.inputs;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.requests.AbstractObsResponseMessage;

@AutoProperty
public class GetInputKindListResponse extends AbstractObsResponseMessage {
	public GetInputKindListResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetInputKindList);
		super.setResponseData(new ResponseData());
	}

	@AutoProperty
	public static class ResponseData extends AbstractObsDataTransferObject {

		private List<String> inputKinds;

		public List<String> getInputKinds() {
			return inputKinds;
		}

		public void setInputKinds(List<String> inputKinds) {
			this.inputKinds = inputKinds;
		}

	}
}

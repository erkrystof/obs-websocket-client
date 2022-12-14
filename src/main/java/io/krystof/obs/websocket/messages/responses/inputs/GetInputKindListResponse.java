package io.krystof.obs.websocket.messages.responses.inputs;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetInputKindListResponse extends AbstractObsResponseMessage {
	public GetInputKindListResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetInputKindList);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {

		private List<String> inputKinds;

		public List<String> getInputKinds() {
			return inputKinds;
		}

		public void setInputKinds(List<String> inputKinds) {
			this.inputKinds = inputKinds;
		}

	}
}

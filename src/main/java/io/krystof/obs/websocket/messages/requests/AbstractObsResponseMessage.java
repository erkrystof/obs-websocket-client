package io.krystof.obs.websocket.messages.requests;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage.RequestResponse;

@AutoProperty
public abstract class AbstractObsResponseMessage extends ObsMessage {

	public AbstractObsResponseMessage() {
		super.setOperationCode(OperationCode.RequestResponse);
	}

	public AbstractObsResponseMessage(RequestResponse requestResponseType) {
		this();
		data = new Data();
		data.requestType = requestResponseType;
	}

	@JsonProperty("d")
	private Data data;

	protected void setResponseData(
			Object responseData) {
		data.responseData = responseData;
	}

	@AutoProperty
	public static class Data extends AbstractObsDataTransferObject {

		private RequestResponse requestType;
		private String requestId;
		private RequestStatus requestStatus;
		private Object responseData;

		public RequestResponse getRequestType() {
			return requestType;
		}

		public void setRequestType(RequestResponse requestType) {
			this.requestType = requestType;
		}

		public String getRequestId() {
			return requestId;
		}

		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		public RequestStatus getRequestStatus() {
			return requestStatus;
		}

		public void setRequestStatus(RequestStatus requestStatus) {
			this.requestStatus = requestStatus;
		}

		public Object getResponseData() {
			return responseData;
		}

		public void setResponseData(Object responseData) {
			this.responseData = responseData;
		}

	}

	public Data getData() {
		return data;
	}

}

package io.krystof.obs.websocket.messages.requests;

import java.util.UUID;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;
import io.krystof.obs.websocket.messages.requests.ui.SetStudioModeEnabledRequest;
import io.krystof.obs.websocket.messages.responses.ui.SetStudioModeEnabledResponse;

@AutoProperty
public abstract class AbstractObsRequestMessage extends ObsMessage {

	public AbstractObsRequestMessage() {
		super.setOperationCode(OperationCode.Request);
		this.data = new Data();
		data.requestId = UUID.randomUUID().toString();
	}

	public AbstractObsRequestMessage(RequestResponse requestResponseType) {
		this();
		data.requestType = requestResponseType;
	}

	@JsonProperty("d")
	protected Data data;

	protected void setRequestData(
			Object requestData) {
		data.requestData = requestData;
	}

	@AutoProperty
	public static class Data extends AbstractObsDataTransferObject {

		private RequestResponse requestType;
		private String requestId;
		private Object requestData;

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

		public Object getRequestData() {
			return requestData;
		}

		public void setRequestData(Object requestData) {
			this.requestData = requestData;
		}

	}

	public enum RequestResponse {
		// General
		SetStudioModeEnabled(SetStudioModeEnabledRequest.class, SetStudioModeEnabledResponse.class);

		private final Class<? extends AbstractObsRequestMessage> requestClass;
		private final Class<? extends AbstractObsResponseMessage> responseClass;

		RequestResponse(Class<? extends AbstractObsRequestMessage> requestClass,
				Class<? extends AbstractObsResponseMessage> responseClass) {
			this.requestClass = requestClass;
			this.responseClass = responseClass;
		}

		public Class<? extends AbstractObsRequestMessage> getRequestClass() {
			return requestClass;
		}

		public Class<? extends AbstractObsResponseMessage> getResponseClass() {
			return responseClass;
		}

	}

}

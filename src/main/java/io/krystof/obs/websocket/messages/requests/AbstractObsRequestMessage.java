package io.krystof.obs.websocket.messages.requests;

import java.util.UUID;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;
import io.krystof.obs.websocket.messages.requests.inputs.GetInputKindListRequest;
import io.krystof.obs.websocket.messages.requests.inputs.GetInputSettingsRequest;
import io.krystof.obs.websocket.messages.requests.scenes.GetSceneItemListRequest;
import io.krystof.obs.websocket.messages.requests.ui.SetStudioModeEnabledRequest;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputKindListResponse;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputSettingsResponse;
import io.krystof.obs.websocket.messages.responses.scenes.GetSceneItemListResponse;
import io.krystof.obs.websocket.messages.responses.ui.SetStudioModeEnabledResponse;

@AutoProperty
public abstract class AbstractObsRequestMessage extends ObsMessage {

	public AbstractObsRequestMessage() {
		this(null, null);
	}

	public AbstractObsRequestMessage(RequestResponse requestResponse,
			Object requestData) {
		super.setOperationCode(OperationCode.Request);
		this.data = new Data();
		data.requestId = UUID.randomUUID().toString();
		this.data.setRequestType(requestResponse);
		setRequestData(requestData);
	}

	@JsonProperty("d")
	private Data data;

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
		// Scene Items Requests
		GetSceneItemList(GetSceneItemListRequest.class, GetSceneItemListResponse.class),
		//Inputs
		GetInputSettings(GetInputSettingsRequest.class, GetInputSettingsResponse.class),
		GetInputKindList(GetInputKindListRequest.class, GetInputKindListResponse.class),
		// UI
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

	public Data getData() {
		return data;
	}

}

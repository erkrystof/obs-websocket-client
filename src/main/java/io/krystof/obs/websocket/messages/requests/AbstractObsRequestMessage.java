package io.krystof.obs.websocket.messages.requests;

import java.util.UUID;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;
import io.krystof.obs.websocket.messages.requests.inputs.GetInputKindListRequest;
import io.krystof.obs.websocket.messages.requests.inputs.GetInputSettingsRequest;
import io.krystof.obs.websocket.messages.requests.inputs.SetInputSettingsRequest;
import io.krystof.obs.websocket.messages.requests.media_inputs.GetMediaInputStatusRequest;
import io.krystof.obs.websocket.messages.requests.media_inputs.SetMediaInputCursorRequest;
import io.krystof.obs.websocket.messages.requests.media_inputs.TriggerMediaInputActionRequest;
import io.krystof.obs.websocket.messages.requests.scene_items.GetSceneItemEnabledRequest;
import io.krystof.obs.websocket.messages.requests.scene_items.GetSceneItemListRequest;
import io.krystof.obs.websocket.messages.requests.scene_items.GetSceneListRequest;
import io.krystof.obs.websocket.messages.requests.scene_items.SetSceneItemEnabledRequest;
import io.krystof.obs.websocket.messages.requests.scenes.GetCurrentPreviewSceneRequest;
import io.krystof.obs.websocket.messages.requests.scenes.GetCurrentProgramSceneRequest;
import io.krystof.obs.websocket.messages.requests.scenes.SetCurrentPreviewSceneRequest;
import io.krystof.obs.websocket.messages.requests.scenes.SetCurrentProgramSceneRequest;
import io.krystof.obs.websocket.messages.requests.ui.SetStudioModeEnabledRequest;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputKindListResponse;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputSettingsResponse;
import io.krystof.obs.websocket.messages.responses.inputs.SetInputSettingsResponse;
import io.krystof.obs.websocket.messages.responses.media_inputs.GetMediaInputStatusResponse;
import io.krystof.obs.websocket.messages.responses.media_inputs.SetMediaInputCursorResponse;
import io.krystof.obs.websocket.messages.responses.media_inputs.TriggerMediaInputActionResponse;
import io.krystof.obs.websocket.messages.responses.scene_items.GetSceneItemEnabledResponse;
import io.krystof.obs.websocket.messages.responses.scene_items.GetSceneItemListResponse;
import io.krystof.obs.websocket.messages.responses.scene_items.GetSceneListResponse;
import io.krystof.obs.websocket.messages.responses.scene_items.SetSceneItemEnabledResponse;
import io.krystof.obs.websocket.messages.responses.scenes.GetCurrentPreviewSceneResponse;
import io.krystof.obs.websocket.messages.responses.scenes.GetCurrentProgramSceneResponse;
import io.krystof.obs.websocket.messages.responses.scenes.SetCurrentPreviewSceneResponse;
import io.krystof.obs.websocket.messages.responses.scenes.SetCurrentProgramSceneResponse;
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
		// Scenes
		GetCurrentPreviewScene(GetCurrentPreviewSceneRequest.class, GetCurrentPreviewSceneResponse.class),
		GetCurrentProgramScene(GetCurrentProgramSceneRequest.class, GetCurrentProgramSceneResponse.class),
		SetCurrentPreviewScene(SetCurrentPreviewSceneRequest.class, SetCurrentPreviewSceneResponse.class),
		SetCurrentProgramScene(SetCurrentProgramSceneRequest.class, SetCurrentProgramSceneResponse.class),
		// Scene Items Requests
		GetSceneItemList(GetSceneItemListRequest.class, GetSceneItemListResponse.class),
		GetSceneList(GetSceneListRequest.class, GetSceneListResponse.class),
		SetSceneItemEnabled(SetSceneItemEnabledRequest.class, SetSceneItemEnabledResponse.class),
		GetSceneItemEnabled(GetSceneItemEnabledRequest.class, GetSceneItemEnabledResponse.class),
		// Inputs
		GetInputSettings(GetInputSettingsRequest.class, GetInputSettingsResponse.class),
		GetInputKindList(GetInputKindListRequest.class, GetInputKindListResponse.class),
		SetInputSettings(SetInputSettingsRequest.class, SetInputSettingsResponse.class),
		// Media Inputs
		TriggerMediaInputAction(TriggerMediaInputActionRequest.class, TriggerMediaInputActionResponse.class),
		GetMediaInputStatus(GetMediaInputStatusRequest.class, GetMediaInputStatusResponse.class),
		SetMediaInputCursor(SetMediaInputCursorRequest.class, SetMediaInputCursorResponse.class),
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

package io.krystof.obs.websocket;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.obs.websocket.messages.requests.inputs.GetInputKindListRequest;
import io.krystof.obs.websocket.messages.requests.inputs.GetInputSettingsRequest;
import io.krystof.obs.websocket.messages.requests.inputs.GetInputVolumeRequest;
import io.krystof.obs.websocket.messages.requests.inputs.PressInputPropertiesButtonRequest;
import io.krystof.obs.websocket.messages.requests.inputs.SetInputSettingsRequest;
import io.krystof.obs.websocket.messages.requests.inputs.SetInputVolumeRequest;
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
import io.krystof.obs.websocket.messages.responses.inputs.GetInputKindListResponse;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputSettingsResponse;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputVolumeResponse;
import io.krystof.obs.websocket.messages.responses.inputs.PressInputPropertiesButtonResponse;
import io.krystof.obs.websocket.messages.responses.inputs.SetInputSettingsResponse;
import io.krystof.obs.websocket.messages.responses.inputs.SetInputVolumeResponse;
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

public class ObsClient implements Closeable {

	public static class SceneItemsRequestResponseService {

		public SceneItemsRequestResponseService(ObsWebSocket obsWebSocket) {
			super();
			this.obsWebSocket = obsWebSocket;
		}

		ObsWebSocket obsWebSocket;

		public GetSceneItemListResponse getSceneItemList(String sceneName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetSceneItemListRequest(sceneName),
							GetSceneItemListResponse.class);
		}

		public SetSceneItemEnabledResponse setSceneItemEnabled(String sceneName, int sceneItemId, boolean enabled) {
			return obsWebSocket
					.sendRequestWaitForResponse(new SetSceneItemEnabledRequest(sceneName, sceneItemId, enabled),
							SetSceneItemEnabledResponse.class);
		}

		public GetSceneItemEnabledResponse getSceneItemEnabled(String sceneName, int sceneItemId) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetSceneItemEnabledRequest(sceneName, sceneItemId),
							GetSceneItemEnabledResponse.class);
		}

		public GetSceneListResponse getSceneList() {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetSceneListRequest(),
							GetSceneListResponse.class);
		}

	}

	public static class SceneRequestResponseService {

		public SceneRequestResponseService(ObsWebSocket obsWebSocket) {
			super();
			this.obsWebSocket = obsWebSocket;
		}

		ObsWebSocket obsWebSocket;

		public GetCurrentPreviewSceneResponse getCurrentPreviewScene() {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetCurrentPreviewSceneRequest(),
							GetCurrentPreviewSceneResponse.class);
		}

		public GetCurrentProgramSceneResponse getCurrentProgramScene() {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetCurrentProgramSceneRequest(),
							GetCurrentProgramSceneResponse.class);
		}

		public SetCurrentPreviewSceneResponse setCurrentPreviewScene(String sceneName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new SetCurrentPreviewSceneRequest(sceneName),
							SetCurrentPreviewSceneResponse.class);
		}

		public SetCurrentProgramSceneResponse setCurrentProgramScene(String sceneName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new SetCurrentProgramSceneRequest(sceneName),
							SetCurrentProgramSceneResponse.class);
		}

	}

	public static class InputRequestResponseService {

		ObsWebSocket obsWebSocket;

		public InputRequestResponseService(ObsWebSocket obsWebSocket) {
			this.obsWebSocket = obsWebSocket;
		}

		public GetInputKindListResponse getInputKindList(boolean unversioned) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetInputKindListRequest(unversioned),
							GetInputKindListResponse.class);
		}

		public SetInputSettingsResponse setInputSettings(String inputName, Map<String, Object> newSettings,
				boolean overlaySettingsInsteadOfReplace) {
			return obsWebSocket
					.sendRequestWaitForResponse(
							new SetInputSettingsRequest(inputName, newSettings, overlaySettingsInsteadOfReplace),
							SetInputSettingsResponse.class);
		}

		public GetInputSettingsResponse getInputSettings(String inputName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetInputSettingsRequest(inputName),
							GetInputSettingsResponse.class);
		}

		public GetInputVolumeResponse getInputVolume(String inputName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetInputVolumeRequest(inputName),
							GetInputVolumeResponse.class);
		}

		public SetInputVolumeResponse setInputVolume(String inputName, Long inputVolumeDb) {
			return obsWebSocket
					.sendRequestWaitForResponse(new SetInputVolumeRequest(inputName, inputVolumeDb),
							SetInputVolumeResponse.class);
		}

		public PressInputPropertiesButtonResponse pressInputPropertiesButton(String inputName, String propertyName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new PressInputPropertiesButtonRequest(inputName, propertyName),
							PressInputPropertiesButtonResponse.class);
		}

	}

	public static class UIRequestResponseService {

		ObsWebSocket obsWebSocket;

		public UIRequestResponseService(ObsWebSocket obsWebSocket) {
			this.obsWebSocket = obsWebSocket;
		}

		public SetStudioModeEnabledResponse setStudioModeEnabled(boolean enabled) {
			return obsWebSocket
					.sendRequestWaitForResponse(new SetStudioModeEnabledRequest(enabled),
							SetStudioModeEnabledResponse.class);
		}
	}

	public static class MediaInputRequestResponseService {

		ObsWebSocket obsWebSocket;

		public MediaInputRequestResponseService(ObsWebSocket obsWebSocket) {
			this.obsWebSocket = obsWebSocket;
		}

		public TriggerMediaInputActionResponse triggerMediaInputAction(String inputName, String mediaAction) {
			return obsWebSocket
					.sendRequestWaitForResponse(new TriggerMediaInputActionRequest(inputName, mediaAction),
							TriggerMediaInputActionResponse.class);
		}

		public GetMediaInputStatusResponse getMediaInputStatus(String inputName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetMediaInputStatusRequest(inputName),
							GetMediaInputStatusResponse.class);
		}

		public SetMediaInputCursorResponse setMediaInputCursor(String inputName, int mediaCursor) {
			return obsWebSocket
					.sendRequestWaitForResponse(new SetMediaInputCursorRequest(inputName, mediaCursor),
							SetMediaInputCursorResponse.class);
		}
	}

	private static final Logger LOG = LoggerFactory.getLogger(ObsClient.class);

	public UIRequestResponseService ui;

	public InputRequestResponseService inputs;

	public MediaInputRequestResponseService mediaInputs;

	public SceneItemsRequestResponseService sceneItems;

	public SceneRequestResponseService scenes;

	private URI host;

	private ObsWebSocket obsWebSocket;

	public ObsClient(URI host, Optional<String> password, Optional<Integer> eventSubscriptionMask) {
		super();
		this.host = host;
		if (password.isPresent()) {
			obsWebSocket = new ObsWebSocket(host, password.get(), eventSubscriptionMask);
		} else {
			obsWebSocket = new ObsWebSocket(host, eventSubscriptionMask);
		}

		ui = new UIRequestResponseService(obsWebSocket);
		inputs = new InputRequestResponseService(obsWebSocket);
		sceneItems = new SceneItemsRequestResponseService(obsWebSocket);
		scenes = new SceneRequestResponseService(obsWebSocket);
		mediaInputs = new MediaInputRequestResponseService(obsWebSocket);
	}

	public void addEventListener(ObsEventListener obsEventListener) {
		obsWebSocket.addEventListener(obsEventListener);
	}

	@Override
	public void close() throws IOException {
		if (obsWebSocket != null) {
			obsWebSocket.close();
		}
	}

}

package io.krystof.obs.websocket;

import java.net.URI;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.krystof.obs.websocket.messages.requests.inputs.GetInputKindListRequest;
import io.krystof.obs.websocket.messages.requests.inputs.GetInputSettingsRequest;
import io.krystof.obs.websocket.messages.requests.scenes.GetSceneItemListRequest;
import io.krystof.obs.websocket.messages.requests.ui.SetStudioModeEnabledRequest;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputKindListResponse;
import io.krystof.obs.websocket.messages.responses.inputs.GetInputSettingsResponse;
import io.krystof.obs.websocket.messages.responses.scenes.GetSceneItemListResponse;
import io.krystof.obs.websocket.messages.responses.ui.SetStudioModeEnabledResponse;

public class ObsClient {

	public ObsClient(URI host, Optional<String> password, Optional<Integer> eventSubscriptionMask) {
		super();
		this.host = host;
		if (password.isPresent()) {
			obsWebSocket = new ObsWebSocket(host, password.get(), eventSubscriptionMask);
		} else {
			obsWebSocket = new ObsWebSocket(host, eventSubscriptionMask);
		}

		ui = new UIRequestResponseService(obsWebSocket);
		input = new InputRequestResponseService(obsWebSocket);
	}

	public UIRequestResponseService ui;
	public InputRequestResponseService input;

	private URI host;

	private static final Logger LOG = LoggerFactory.getLogger(ObsClient.class);

	private ObsWebSocket obsWebSocket;

	public static class UIRequestResponseService {

		ObsWebSocket obsWebSocket;

		public UIRequestResponseService(ObsWebSocket obsWebSocket) {
			this.obsWebSocket = obsWebSocket;
		}

		public SetStudioModeEnabledResponse callSetStudioModeEnabled(boolean enabled) {
			return obsWebSocket
					.sendRequestWaitForResponse(new SetStudioModeEnabledRequest(enabled),
							SetStudioModeEnabledResponse.class);
		}
	}

	public static class InputRequestResponseService {

		ObsWebSocket obsWebSocket;

		public InputRequestResponseService(ObsWebSocket obsWebSocket) {
			this.obsWebSocket = obsWebSocket;
		}

		public GetSceneItemListResponse callGetSceneItemList(String sceneName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetSceneItemListRequest(sceneName),
							GetSceneItemListResponse.class);
		}

		public GetInputSettingsResponse callGetInputSettings(String inputName) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetInputSettingsRequest(inputName),
							GetInputSettingsResponse.class);
		}

		public GetInputKindListResponse callGetInputKindList(boolean unversioned) {
			return obsWebSocket
					.sendRequestWaitForResponse(new GetInputKindListRequest(unversioned),
							GetInputKindListResponse.class);
		}
	}

}

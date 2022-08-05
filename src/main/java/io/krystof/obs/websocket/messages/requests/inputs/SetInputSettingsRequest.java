package io.krystof.obs.websocket.messages.requests.inputs;

import java.util.HashMap;
import java.util.Map;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetInputSettingsRequest extends AbstractObsRequestMessage {

	public SetInputSettingsRequest() {
		this(null);
	}

	public SetInputSettingsRequest(String inputName) {
		super(AbstractObsRequestMessage.RequestResponse.SetInputSettings,
				new RequestData().setInputName(inputName));
	}

	public SetInputSettingsRequest(String inputName, Map<String, Object> newSettings,
			boolean overlaySettingsInsteadOfReplace) {
		super(AbstractObsRequestMessage.RequestResponse.SetInputSettings,
				new RequestData().setInputName(inputName).setInputSettings(newSettings)
						.setOverlay(overlaySettingsInsteadOfReplace));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String inputName;

		private Map<String, Object> inputSettings = new HashMap<>();

		private boolean overlay;

		public String getInputName() {
			return inputName;
		}

		public RequestData setInputName(String sceneName) {
			this.inputName = sceneName;
			return this;
		}

		public Map<String, Object> getInputSettings() {
			return inputSettings;
		}

		public RequestData setInputSettings(Map<String, Object> inputSettings) {
			this.inputSettings = inputSettings;
			return this;
		}

		public boolean isOverlay() {
			return overlay;
		}

		public RequestData setOverlay(boolean overlay) {
			this.overlay = overlay;
			return this;
		}
	}
}

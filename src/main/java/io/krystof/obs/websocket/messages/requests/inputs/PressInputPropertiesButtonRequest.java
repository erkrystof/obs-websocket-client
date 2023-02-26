package io.krystof.obs.websocket.messages.requests.inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class PressInputPropertiesButtonRequest extends AbstractObsRequestMessage {

	public static final String PROPERTY_NAME_REFRESH_BROWSER_NO_CACHE = "refreshnocache";

	public PressInputPropertiesButtonRequest() {
		this(null, null);
	}

	public PressInputPropertiesButtonRequest(String inputName, String propertyName) {
		super(AbstractObsRequestMessage.RequestResponse.PressInputPropertiesButton,
				new RequestData().setInputName(inputName).setPropertyName(propertyName));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String inputName;
		private String propertyName;


		public String getInputName() {
			return inputName;
		}

		public RequestData setInputName(String sceneName) {
			this.inputName = sceneName;
			return this;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public RequestData setPropertyName(String propertyName) {
			this.propertyName = propertyName;
			return this;
		}


	}
}

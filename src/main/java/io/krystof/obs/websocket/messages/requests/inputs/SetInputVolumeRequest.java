package io.krystof.obs.websocket.messages.requests.inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetInputVolumeRequest extends AbstractObsRequestMessage {

	public SetInputVolumeRequest() {
		this(null);
	}

	public SetInputVolumeRequest(String inputName) {
		super(AbstractObsRequestMessage.RequestResponse.SetInputVolume,
				new RequestData().setInputName(inputName));
	}

	public SetInputVolumeRequest(String inputName, Long inputVolumeDb) {
		super(AbstractObsRequestMessage.RequestResponse.SetInputVolume,
				new RequestData().setInputName(inputName).setInputVolumeDb(inputVolumeDb));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String inputName;

		private Long inputVolumeDb;

		public String getInputName() {
			return inputName;
		}

		public RequestData setInputName(String sceneName) {
			this.inputName = sceneName;
			return this;
		}

		public Long getInputVolumeDb() {
			return inputVolumeDb;
		}

		public RequestData setInputVolumeDb(Long inputVolumeDb) {
			this.inputVolumeDb = inputVolumeDb;
			return this;
		}

	}
}

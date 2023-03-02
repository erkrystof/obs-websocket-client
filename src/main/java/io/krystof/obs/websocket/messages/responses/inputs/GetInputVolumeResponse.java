package io.krystof.obs.websocket.messages.responses.inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetInputVolumeResponse extends AbstractObsResponseMessage {
	public GetInputVolumeResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetInputVolume);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {

		private String inputKind;

		private long inputVolumeMul;

		private long inputVolumeDb;

		public String getInputKind() {
			return inputKind;
		}

		public ResponseData setInputKind(String inputKind) {
			this.inputKind = inputKind;
			return this;
		}

		public long getInputVolumeMul() {
			return inputVolumeMul;
		}

		public void setInputVolumeMul(long inputVolumeMul) {
			this.inputVolumeMul = inputVolumeMul;
		}

		public long getInputVolumeDb() {
			return inputVolumeDb;
		}

		public void setInputVolumeDb(long inputVolumeDb) {
			this.inputVolumeDb = inputVolumeDb;
		}


	}
}

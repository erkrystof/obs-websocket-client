package io.krystof.obs.websocket.messages.requests.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class SetMediaInputCursorRequest extends AbstractObsRequestMessage {

	public SetMediaInputCursorRequest() {
		this(null, 0);
	}

	public SetMediaInputCursorRequest(String inputName, int mediaCursor) {
		super(AbstractObsRequestMessage.RequestResponse.SetMediaInputCursor,
				new RequestData().setInputName(inputName).setMediaCursor(mediaCursor));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String inputName;

		private int mediaCursor;

		public String getInputName() {
			return inputName;
		}

		public RequestData setInputName(String sceneName) {
			this.inputName = sceneName;
			return this;
		}

		public int getMediaCursor() {
			return mediaCursor;
		}

		public RequestData setMediaCursor(int mediaCursor) {
			this.mediaCursor = mediaCursor;
			return this;
		}

	}
}

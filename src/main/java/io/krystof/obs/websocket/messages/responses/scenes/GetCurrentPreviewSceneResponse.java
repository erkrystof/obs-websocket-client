package io.krystof.obs.websocket.messages.responses.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetCurrentPreviewSceneResponse extends AbstractObsResponseMessage {
	public GetCurrentPreviewSceneResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetCurrentPreviewScene);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {
		private String currentPreviewSceneName;

		public String getCurrentPreviewSceneName() {
			return currentPreviewSceneName;
		}

		public void setCurrentPreviewSceneName(String currentProgramSceneName) {
			this.currentPreviewSceneName = currentProgramSceneName;
		}

	}
}

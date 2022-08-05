package io.krystof.obs.websocket.messages.responses.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetCurrentProgramSceneResponse extends AbstractObsResponseMessage {
	public GetCurrentProgramSceneResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetCurrentProgramScene);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {
		private String currentProgramSceneName;

		public String getCurrentProgramSceneName() {
			return currentProgramSceneName;
		}

		public void setCurrentProgramSceneName(String currentProgramSceneName) {
			this.currentProgramSceneName = currentProgramSceneName;
		}

	}
}

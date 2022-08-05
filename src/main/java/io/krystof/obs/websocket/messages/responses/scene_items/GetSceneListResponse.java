package io.krystof.obs.websocket.messages.responses.scene_items;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetSceneListResponse extends AbstractObsResponseMessage {
	public GetSceneListResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetSceneList);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {

		private String currentProgramSceneName;

		private String currentPreviewSceneName;

		private List<SceneIndexAndName> scenes;

		public String getCurrentProgramSceneName() {
			return currentProgramSceneName;
		}

		public void setCurrentProgramSceneName(String currentProgramSceneName) {
			this.currentProgramSceneName = currentProgramSceneName;
		}

		public String getCurrentPreviewSceneName() {
			return currentPreviewSceneName;
		}

		public void setCurrentPreviewSceneName(String currentPreviewSceneName) {
			this.currentPreviewSceneName = currentPreviewSceneName;
		}

		public List<SceneIndexAndName> getScenes() {
			return scenes;
		}

		public void setScenes(List<SceneIndexAndName> scenes) {
			this.scenes = scenes;
		}



	}
}

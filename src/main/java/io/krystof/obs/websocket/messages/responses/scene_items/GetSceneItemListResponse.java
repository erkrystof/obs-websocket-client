package io.krystof.obs.websocket.messages.responses.scene_items;

import java.util.List;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class GetSceneItemListResponse extends AbstractObsResponseMessage {
	public GetSceneItemListResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetSceneItemList);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {

		private List<SceneItem> sceneItems;

		public List<SceneItem> getSceneItems() {
			return sceneItems;
		}

		public void setSceneItems(List<SceneItem> sceneItems) {
			this.sceneItems = sceneItems;
		}


	}
}

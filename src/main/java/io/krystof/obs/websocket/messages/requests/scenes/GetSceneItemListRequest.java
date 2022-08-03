package io.krystof.obs.websocket.messages.requests.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetSceneItemListRequest extends AbstractObsRequestMessage {

	public GetSceneItemListRequest() {
		this(null);
	}

	public GetSceneItemListRequest(String sceneName) {
		super(AbstractObsRequestMessage.RequestResponse.GetSceneItemList,
				new RequestData().setSceneName(sceneName));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String sceneName;

		public String getSceneName() {
			return sceneName;
		}

		public RequestData setSceneName(String sceneName) {
			this.sceneName = sceneName;
			return this;
		}
	}
}


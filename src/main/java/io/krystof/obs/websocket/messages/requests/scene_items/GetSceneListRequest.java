package io.krystof.obs.websocket.messages.requests.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetSceneListRequest extends AbstractObsRequestMessage {

	public GetSceneListRequest() {
		super(AbstractObsRequestMessage.RequestResponse.GetSceneList,
				null);
	}

}

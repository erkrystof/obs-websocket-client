package io.krystof.obs.websocket.messages.responses.scenes;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.requests.AbstractObsResponseMessage;

@AutoProperty
public class GetSceneItemListResponse extends AbstractObsResponseMessage {
	public GetSceneItemListResponse() {
		super(AbstractObsRequestMessage.RequestResponse.GetSceneItemList);
		super.setResponseData(new ResponseData());
	}

	@AutoProperty
	public static class ResponseData extends AbstractObsDataTransferObject {

	}
}

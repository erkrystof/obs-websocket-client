package io.krystof.obs.websocket.messages.responses.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class SetSceneItemEnabledResponse extends AbstractObsResponseMessage {
	public SetSceneItemEnabledResponse() {
		super(AbstractObsRequestMessage.RequestResponse.SetSceneItemEnabled);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {



	}
}

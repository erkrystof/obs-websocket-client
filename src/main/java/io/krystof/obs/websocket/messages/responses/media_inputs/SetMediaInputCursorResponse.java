package io.krystof.obs.websocket.messages.responses.media_inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage;
import io.krystof.obs.websocket.messages.responses.AbstractResponseSpecificDataObject;

@AutoProperty
public class SetMediaInputCursorResponse extends AbstractObsResponseMessage {
	public SetMediaInputCursorResponse() {
		super(AbstractObsRequestMessage.RequestResponse.SetMediaInputCursor);
		super.setResponseData(new ResponseData());
	}

	public ResponseData getSpecificResponseData() {
		return (ResponseData) super.getData().getResponseData();
	}

	@AutoProperty
	public static class ResponseData extends AbstractResponseSpecificDataObject {


	}
}

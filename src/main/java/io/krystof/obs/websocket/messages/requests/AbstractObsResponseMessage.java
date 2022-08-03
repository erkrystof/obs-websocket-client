package io.krystof.obs.websocket.messages.requests;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;

@AutoProperty
public abstract class AbstractObsResponseMessage extends ObsMessage {

	public AbstractObsResponseMessage() {
		super.setOperationCode(OperationCode.RequestResponse);
	}

	@AutoProperty
	public static class Data extends AbstractObsDataTransferObject {

	}

}

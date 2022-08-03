package io.krystof.obs.websocket.messages.requests.inputs;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetInputKindListRequest extends AbstractObsRequestMessage {

	public GetInputKindListRequest() {
		this(false);
	}

	public GetInputKindListRequest(boolean unversioned) {
		super(AbstractObsRequestMessage.RequestResponse.GetInputKindList,
				new RequestData().setUnversioned(unversioned));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private boolean unversioned;

		public boolean isUnversioned() {
			return unversioned;
		}

		public RequestData setUnversioned(boolean unversioned) {
			this.unversioned = unversioned;
			return this;
		}

	}
}


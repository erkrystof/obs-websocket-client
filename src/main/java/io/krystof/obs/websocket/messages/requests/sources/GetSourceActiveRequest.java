package io.krystof.obs.websocket.messages.requests.sources;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;

@AutoProperty
public class GetSourceActiveRequest extends AbstractObsRequestMessage {

	public GetSourceActiveRequest() {
		this(null);
	}

	public GetSourceActiveRequest(String sourceName) {
		super(AbstractObsRequestMessage.RequestResponse.GetSourceActive,
				new RequestData().setSourceName(sourceName));
	}

	@AutoProperty
	public static class RequestData extends AbstractObsDataTransferObject {

		private String sourceName;

		public String getSourceName() {
			return sourceName;
		}

		public RequestData setSourceName(String sceneName) {
			this.sourceName = sceneName;
			return this;
		}
	}
}


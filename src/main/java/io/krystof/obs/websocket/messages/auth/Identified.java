package io.krystof.obs.websocket.messages.auth;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;

@AutoProperty
public class Identified extends ObsMessage {

	public Identified() {
		setOperationCode(OperationCode.Identified);
	}

	@JsonProperty("d")
	Data data = new Data();

	@AutoProperty
	public class Data extends AbstractObsDataTransferObject {

		private Integer negotiatedRpcVersion;

		public Integer getNegotiatedRpcVersion() {
			return negotiatedRpcVersion;
		}

		public void setNegotiatedRpcVersion(Integer negotiatedRpcVersion) {
			this.negotiatedRpcVersion = negotiatedRpcVersion;
		}

	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}

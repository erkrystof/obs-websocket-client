package io.krystof.obs.websocket.messages.auth;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;

@AutoProperty
public class Helo extends ObsMessage {

	public Helo() {
		setOperationCode(OperationCode.Hello);
	}

	@JsonProperty("d")
	Data data;

	@AutoProperty
	public class Data extends AbstractObsDataTransferObject {

		private String obsWebSocketVersion;
		private Integer rpcVersion;
		private Authentication authentication;

		public String getObsWebSocketVersion() {
			return obsWebSocketVersion;
		}

		public Integer getRpcVersion() {
			return rpcVersion;
		}

		public void setObsWebSocketVersion(String obsWebSocketVersion) {
			this.obsWebSocketVersion = obsWebSocketVersion;
		}

		public void setRpcVersion(Integer rpcVersion) {
			this.rpcVersion = rpcVersion;
		}

		public Authentication getAuthentication() {
			return authentication;
		}

		public void setAuthentication(Authentication authentication) {
			this.authentication = authentication;
		}

	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}

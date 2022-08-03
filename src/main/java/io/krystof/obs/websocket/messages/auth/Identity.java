package io.krystof.obs.websocket.messages.auth;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;
import io.krystof.obs.websocket.messages.ObsMessage;

@AutoProperty
public class Identity extends ObsMessage {

	public Identity() {
		setOperationCode(OperationCode.Identify);
	}

	@JsonProperty("d")
	Data data = new Data();

	@AutoProperty
	public class Data extends AbstractObsDataTransferObject {

		private Integer rpcVersion;
		private String authentication;
		private Integer eventSubscriptions;

		public Integer getRpcVersion() {
			return rpcVersion;
		}

		public Data setRpcVersion(Integer rpcVersion) {
			this.rpcVersion = rpcVersion;
			return this;
		}

		public String getAuthentication() {
			return authentication;
		}

		public Data setAuthentication(String authentication) {
			this.authentication = authentication;
			return this;
		}

		public Integer getEventSubscriptions() {
			return eventSubscriptions;
		}

		public Data setEventSubscriptions(Integer eventSubscriptions) {
			this.eventSubscriptions = eventSubscriptions;
			return this;
		}

		public Identity and() {
			return Identity.this;
		}

	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}

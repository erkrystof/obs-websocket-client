package io.krystof.obs.websocket.messages;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@AutoProperty
public abstract class ObsMessage extends AbstractObsDataTransferObject {

	@JsonProperty("op")
	private OperationCode operationCode;

	public OperationCode getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(OperationCode operationCode) {
		this.operationCode = operationCode;
	}

	public enum OperationCode {
		Hello(0),
		Identify(1),
		Identified(2),
		ReIdentify(3),
		Event(5),
		Request(6),
		RequestResponse(7),
		RequestBatch(8),
		RequestBatchResponse(9);

		private final int value;

		OperationCode(int value) {
			this.value = value;
		}

		@JsonValue
		public int getValue() {
			return value;
		}
		
		private static final Map<Integer, OperationCode> mMap = Collections.unmodifiableMap(initializeMapping());

		public static OperationCode getByValue(Integer value) {
			return mMap.get(value);
		}

		private static Map<Integer, OperationCode> initializeMapping() {
			Map<Integer, OperationCode> mMap = new HashMap<>();
			for (OperationCode s : OperationCode.values()) {
				mMap.put(s.getValue(), s);
			}
			return mMap;
		}

		
	}

}

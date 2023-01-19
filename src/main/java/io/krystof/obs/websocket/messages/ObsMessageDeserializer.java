package io.krystof.obs.websocket.messages;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import io.krystof.obs.websocket.messages.ObsMessage.OperationCode;
import io.krystof.obs.websocket.messages.auth.Helo;
import io.krystof.obs.websocket.messages.auth.Identified;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage.EventType;
import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage.RequestResponse;

public class ObsMessageDeserializer extends JsonDeserializer<ObsMessage> {

	@Override
	public ObsMessage deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {

		JsonNode node = p.getCodec().readTree(p);

		OperationCode operationCode = OperationCode.getByValue(node.get("op").asInt(-1));

		switch (operationCode) {
		case Hello:
			return p.getCodec().treeToValue(node, Helo.class);
		case Identified:
			return p.getCodec().treeToValue(node, Identified.class);
		case Event:
			return p.getCodec().treeToValue(node,
					EventType.valueOf(node.get("d").get("eventType").asText()).getEventClass());
		case RequestResponse:
			return p.getCodec().treeToValue(node,
					RequestResponse.valueOf(node.get("d").get("requestType").asText()).getResponseClass());
		default:
			throw new RuntimeException("Unsupported operation code " + operationCode + " for Node: " + node);
		}
	}

}

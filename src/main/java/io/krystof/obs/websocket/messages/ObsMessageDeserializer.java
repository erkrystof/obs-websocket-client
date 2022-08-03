package io.krystof.obs.websocket.messages;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import io.krystof.obs.websocket.messages.auth.Helo;
import io.krystof.obs.websocket.messages.auth.Identified;
import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

public class ObsMessageDeserializer extends JsonDeserializer<ObsMessage> {

	@Override
	public ObsMessage deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {

		JsonNode node = p.getCodec().readTree(p);

		int opCode = node.get("op").asInt(-1);

		switch (opCode) {
		case 0:
			return p.getCodec().treeToValue(node, Helo.class);
		case 2:
			return p.getCodec().treeToValue(node, Identified.class);
		case 5:
			return p.getCodec().treeToValue(node,
					AbstractObsEventMessage.EventType.valueOf(node.get("d").get("eventType").asText()).getEventClass());
		case -1:
		default:
			throw new RuntimeException("Invalid op code:" + opCode + " for Node: " + node);
		}
	}

}

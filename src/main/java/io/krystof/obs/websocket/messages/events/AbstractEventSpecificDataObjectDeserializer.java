package io.krystof.obs.websocket.messages.events;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class AbstractEventSpecificDataObjectDeserializer
		extends JsonDeserializer<AbstractEventSpecificDataObject> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractEventSpecificDataObjectDeserializer.class);

	private Map<AbstractObsEventMessage.EventType, Class<? extends AbstractEventSpecificDataObject>> eventTypeToSpecificEventDataClassMap = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public AbstractEventSpecificDataObject deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		JsonNode node = p.getCodec().readTree(p);

		AbstractObsEventMessage parent = (AbstractObsEventMessage) ctxt.getParser().getParsingContext().getParent()
				.getCurrentValue();

		Class<? extends AbstractEventSpecificDataObject> eventDataClass = eventTypeToSpecificEventDataClassMap
				.get(parent.getPayload().getEventType());

		if (eventDataClass == null) {
			Class<?> eventClass = parent.getPayload().getEventType().getEventClass();

			// We assume each event class has a class underneath called 'EventData'.
			// We use that to
			// Tell Jackson what class to deserialize to.
			try {
				eventDataClass = (Class<? extends AbstractEventSpecificDataObject>) Class
						.forName(eventClass.getName() + "$EventData");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Event Class " + eventClass.getName()
						+ " doesn't have a EventData inner class definition.  Go edit that class and add one.");
			}

			eventTypeToSpecificEventDataClassMap.put(parent.getPayload().getEventType(), eventDataClass);

			LOG.debug("Using class {} for response specific data based on request type: {}", eventDataClass,
					parent.getPayload().getEventType());
		}

		return p.getCodec().treeToValue(node,
				eventDataClass);
	}

}

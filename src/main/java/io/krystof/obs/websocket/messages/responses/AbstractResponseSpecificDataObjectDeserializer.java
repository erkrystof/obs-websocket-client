package io.krystof.obs.websocket.messages.responses;

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

import io.krystof.obs.websocket.messages.requests.AbstractObsRequestMessage;
import io.krystof.obs.websocket.messages.responses.AbstractObsResponseMessage.Data;

public class AbstractResponseSpecificDataObjectDeserializer
		extends JsonDeserializer<AbstractResponseSpecificDataObject> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractResponseSpecificDataObjectDeserializer.class);

	private Map<AbstractObsRequestMessage.RequestResponse, Class<? extends AbstractResponseSpecificDataObject>> requestResponseTypeToSpecificResponseDataClassMap = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public AbstractResponseSpecificDataObject deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JacksonException {
		JsonNode node = p.getCodec().readTree(p);
		AbstractObsResponseMessage.Data parent = (Data) ctxt.getParser().getParsingContext().getCurrentValue();

		Class<? extends AbstractResponseSpecificDataObject> responseDataClass = requestResponseTypeToSpecificResponseDataClassMap
				.get(parent.getRequestType());

		if (responseDataClass == null) {
			Class<?> responseClass = parent.getRequestType().getResponseClass();

			// We assume each response class has a class underneath called 'ResponseData'.
			// We use that to
			// Tell Jackson what class to deserialize to.
			try {
				responseDataClass = (Class<? extends AbstractResponseSpecificDataObject>) Class
						.forName(responseClass.getName() + "$ResponseData");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Response Class " + responseClass.getName()
						+ " doesn't have a ResponseData inner class definition.  Go edit that class and add one.");
			}

			requestResponseTypeToSpecificResponseDataClassMap.put(parent.getRequestType(), responseDataClass);

			LOG.debug("Using class {} for response specific data based on request type: {}", responseDataClass,
					parent.getRequestType());
		}

		return p.getCodec().treeToValue(node,
				responseDataClass);
	}

}

package io.krystof.obs.websocket;

import io.krystof.obs.websocket.messages.events.AbstractObsEventMessage;

@FunctionalInterface
public interface ObsEventListener {

	void eventFired(AbstractObsEventMessage abstractObsEventMessage);

}

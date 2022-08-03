package io.krystof.obs.websocket.messages.events;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;

@AutoProperty
public class CurrentProgramSceneChanged extends AbstractObsEventMessage {

	public CurrentProgramSceneChanged() {
		super(new EventData());
	}

	@AutoProperty
	public static class EventData extends AbstractObsDataTransferObject {

		private String sceneName;

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}
	}

}

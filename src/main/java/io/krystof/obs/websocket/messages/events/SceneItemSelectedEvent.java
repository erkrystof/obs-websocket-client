package io.krystof.obs.websocket.messages.events;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;

@AutoProperty
public class SceneItemSelectedEvent extends AbstractObsEventMessage {

	public SceneItemSelectedEvent() {
		super();
	}

	@JsonProperty("d")
	Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}


	@AutoProperty
	public static class Data extends AbstractObsEventMessage.Data {

		private SpecificData eventData;

		public SpecificData getEventData() {
			return eventData;
		}

		public void setEventData(SpecificData eventData) {
			this.eventData = eventData;
		}

	}

	@AutoProperty
	public static class SpecificData extends AbstractObsDataTransferObject {

		private Integer sceneItemId;

		private String sceneName;

		public Integer getSceneItemId() {
			return sceneItemId;
		}

		public void setSceneItemId(Integer sceneItemId) {
			this.sceneItemId = sceneItemId;
		}

		public String getSceneName() {
			return sceneName;
		}

		public void setSceneName(String sceneName) {
			this.sceneName = sceneName;
		}
	}

}

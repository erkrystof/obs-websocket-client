package io.krystof.obs.websocket.messages.responses.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;

@AutoProperty
public class SceneIndexAndName extends AbstractObsDataTransferObject {

	private int sceneIndex;

	private String sceneName;

	public int getSceneIndex() {
		return sceneIndex;
	}

	public void setSceneIndex(int sceneIndex) {
		this.sceneIndex = sceneIndex;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
}

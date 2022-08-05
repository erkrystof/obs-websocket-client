package io.krystof.obs.websocket.messages.responses.scene_items;

import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;

@AutoProperty
public class SceneItem extends AbstractObsDataTransferObject {
	private String inputKind;
	@JsonProperty("isGroup")
	private boolean isGroup;
	private String sceneItemBlendMode;
	private boolean sceneItemEnabled;
	private int sceneItemId;
	private int sceneItemIndex;
	private boolean sceneItemLocked;
	private SceneItemTransform sceneItemTransform;
	private String sourceName;
	private String sourceType;

	public String getInputKind() {
		return inputKind;
	}

	public void setInputKind(String inputKind) {
		this.inputKind = inputKind;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public String getSceneItemBlendMode() {
		return sceneItemBlendMode;
	}

	public void setSceneItemBlendMode(String sceneItemBlendMode) {
		this.sceneItemBlendMode = sceneItemBlendMode;
	}

	public boolean isSceneItemEnabled() {
		return sceneItemEnabled;
	}

	public void setSceneItemEnabled(boolean sceneItemEnabled) {
		this.sceneItemEnabled = sceneItemEnabled;
	}

	public int getSceneItemId() {
		return sceneItemId;
	}

	public void setSceneItemId(int sceneItemId) {
		this.sceneItemId = sceneItemId;
	}

	public int getSceneItemIndex() {
		return sceneItemIndex;
	}

	public void setSceneItemIndex(int sceneItemIndex) {
		this.sceneItemIndex = sceneItemIndex;
	}

	public boolean isSceneItemLocked() {
		return sceneItemLocked;
	}

	public void setSceneItemLocked(boolean sceneItemLocked) {
		this.sceneItemLocked = sceneItemLocked;
	}

	public SceneItemTransform getSceneItemTransform() {
		return sceneItemTransform;
	}

	public void setSceneItemTransform(SceneItemTransform sceneItemTransform) {
		this.sceneItemTransform = sceneItemTransform;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
}

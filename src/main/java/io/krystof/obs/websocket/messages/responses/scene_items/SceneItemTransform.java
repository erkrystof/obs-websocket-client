package io.krystof.obs.websocket.messages.responses.scene_items;

import org.pojomatic.annotations.AutoProperty;

import io.krystof.obs.websocket.messages.AbstractObsDataTransferObject;

@AutoProperty
public class SceneItemTransform extends AbstractObsDataTransferObject {
	private int alignment;
	private int boundsAlignment;
	private double boundsHeight;
	private String boundsType;
	private double boundsWidth;
	private int cropBottom;
	private int cropLeft;
	private int cropRight;
	private int cropTop;
	private double height;
	private double positionX;
	private double positionY;
	private double rotation;
	private double scaleX;
	private double scaleY;
	private double sourceHeight;
	private double sourceWidth;
	private double width;

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public int getBoundsAlignment() {
		return boundsAlignment;
	}

	public void setBoundsAlignment(int boundsAlignment) {
		this.boundsAlignment = boundsAlignment;
	}

	public double getBoundsHeight() {
		return boundsHeight;
	}

	public void setBoundsHeight(double boundsHeight) {
		this.boundsHeight = boundsHeight;
	}

	public String getBoundsType() {
		return boundsType;
	}

	public void setBoundsType(String boundsType) {
		this.boundsType = boundsType;
	}

	public double getBoundsWidth() {
		return boundsWidth;
	}

	public void setBoundsWidth(double boundsWidth) {
		this.boundsWidth = boundsWidth;
	}

	public int getCropBottom() {
		return cropBottom;
	}

	public void setCropBottom(int cropBottom) {
		this.cropBottom = cropBottom;
	}

	public int getCropLeft() {
		return cropLeft;
	}

	public void setCropLeft(int cropLeft) {
		this.cropLeft = cropLeft;
	}

	public int getCropRight() {
		return cropRight;
	}

	public void setCropRight(int cropRight) {
		this.cropRight = cropRight;
	}

	public int getCropTop() {
		return cropTop;
	}

	public void setCropTop(int cropTop) {
		this.cropTop = cropTop;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public double getSourceHeight() {
		return sourceHeight;
	}

	public void setSourceHeight(double sourceHeight) {
		this.sourceHeight = sourceHeight;
	}

	public double getSourceWidth() {
		return sourceWidth;
	}

	public void setSourceWidth(double sourceWidth) {
		this.sourceWidth = sourceWidth;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
}

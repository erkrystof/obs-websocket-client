package io.krystof.obs.websocket.exceptions;

public class InvalidResponseStatusCodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;

	public InvalidResponseStatusCodeException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}

package io.krystof.obs.websocket.exceptions;

public class MessageHandlingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MessageHandlingException() {
		super();
	}

	public MessageHandlingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MessageHandlingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageHandlingException(String message) {
		super(message);
	}

	public MessageHandlingException(Throwable cause) {
		super(cause);
	}

}

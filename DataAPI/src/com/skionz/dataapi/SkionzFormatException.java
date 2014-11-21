package com.skionz.dataapi;

public class SkionzFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public SkionzFormatException() {
		super();
	}

	public SkionzFormatException(String message) {
		super(message);
	}

	public SkionzFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public SkionzFormatException(Throwable cause) {
		super(cause);
	}
}

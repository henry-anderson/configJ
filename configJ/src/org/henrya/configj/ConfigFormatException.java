package org.henrya.configj;

public class ConfigFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConfigFormatException() {
		super();
	}

	public ConfigFormatException(String message) {
		super(message);
	}

	public ConfigFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigFormatException(Throwable cause) {
		super(cause);
	}
}

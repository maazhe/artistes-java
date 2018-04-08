package com.qwant.artistes_java.error;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 7205559648653867729L;

	private final String errorCode;

	public CustomException(final String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}

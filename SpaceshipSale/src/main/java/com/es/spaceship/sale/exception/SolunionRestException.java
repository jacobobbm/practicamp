package com.es.spaceship.sale.exception;


public class SolunionRestException extends RuntimeException {

	private static final long serialVersionUID = 4766837649724934235L;
	private String errorCode;
	
	public SolunionRestException(String message, Throwable cause) {
		super(message, cause);
	}

	public SolunionRestException(String message,String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

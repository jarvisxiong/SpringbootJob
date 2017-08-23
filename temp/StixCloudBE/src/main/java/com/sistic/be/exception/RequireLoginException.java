package com.sistic.be.exception;

public class RequireLoginException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4138070155421618983L;
	
	public RequireLoginException() {
		super();
	}
	
	public RequireLoginException(String message) {
		super(message);
	}
	
	public RequireLoginException(String message, Throwable cause) {
		super(message, cause);
	}
	
	 public RequireLoginException(Throwable cause) {
        super(cause);
    }

}

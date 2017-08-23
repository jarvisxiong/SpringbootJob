package com.sistic.be.exception;

public class CheckCartLoginException extends RequireLoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6169756680253975120L;
	
	public CheckCartLoginException() {
		super();
	}
	
	public CheckCartLoginException(String message) {
		super(message);
	}
	
	public CheckCartLoginException(String message, Throwable cause) {
		super(message, cause);
	}
	
	 public CheckCartLoginException(Throwable cause) {
        super(cause);
    }

}

package com.sistic.be.exception;

/**
 * Thrown when session is invalidateda
 * @author jianhong
 *
 */
public class InvalidSessionException extends IllegalStateException {

	private static final long serialVersionUID = 3171590510191074319L;
	
	public InvalidSessionException() {
		super();
	}
	
	public InvalidSessionException(String s) {
		super(s);
	}
	
	public InvalidSessionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	 public InvalidSessionException(Throwable cause) {
        super(cause);
    }

}

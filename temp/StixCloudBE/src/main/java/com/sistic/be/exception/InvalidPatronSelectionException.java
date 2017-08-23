package com.sistic.be.exception;

public class InvalidPatronSelectionException extends IllegalStateException {

	private static final long serialVersionUID = -5789540717717244348L;
	
	public InvalidPatronSelectionException() {
		super();
	}
	
	public InvalidPatronSelectionException(String s) {
		super(s);
	}
	
	public InvalidPatronSelectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	 public InvalidPatronSelectionException(Throwable cause) {
        super(cause);
    }

}

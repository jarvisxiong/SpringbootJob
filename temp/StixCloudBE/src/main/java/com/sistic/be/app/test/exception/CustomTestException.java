package com.sistic.be.app.test.exception;

public class CustomTestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6817943208473152967L;
	
	private Integer httpStatus;
	private Integer status;
	
	public CustomTestException() {
		super();
	}
	
	public CustomTestException(String s) {
		super(s);
	}
	
	public CustomTestException(String s, Integer httpStatus) {
		super(s);
		this.httpStatus = httpStatus;
	}
	
	public CustomTestException(String s, Integer httpStatus, Integer status) {
		super(s);
		this.httpStatus = httpStatus;
		this.status = status;
	}
	
	public CustomTestException(String message, Throwable cause) {
		super(message, cause);
	}
	
	 public CustomTestException(Throwable cause) {
        super(cause);
    }

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

package com.sistic.be.exception;

public class InvalidPatronLoginException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6810654834795486843L;
	
	private String gotoUrl;
	
	public InvalidPatronLoginException() {
		super();
	}
	
	public InvalidPatronLoginException(String gotoUrl) {
		super();
		this.gotoUrl = gotoUrl;
	}
	
	public InvalidPatronLoginException(StringBuffer gotoUrl) {
		super();
		this.gotoUrl = gotoUrl.toString();
	}
	
	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}

	@Override
	public String toString() {
		return "InvalidPatronLoginException [gotoUrl=" + gotoUrl + "]";
	}

}

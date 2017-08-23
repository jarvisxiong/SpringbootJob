package com.sistic.be.cart.api.model;

public class StatusResponse {
		
	private String _message;
	
	public StatusResponse() {
		this._message = "OK";
	}
	
	public StatusResponse(String message) {
		this._message = message;
	}
	
	/**
	 * @return the _message
	 */
	public String getMessage() {
		return _message;
	}

	/**
	 * @param _message the _message to set
	 */
	public void setMessage(String message) {
		this._message = message;
	}
	
}

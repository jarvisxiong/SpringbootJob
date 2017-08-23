package com.sistic.be.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.sistic.be.cart.api.model.JsonResponse;

public class ErrorJsonResponseException extends IllegalStateException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2353731266946781476L;
	
	private JsonResponse jsonErrorResponse;
	
	public ErrorJsonResponseException() {
		super();
	}
	
	public ErrorJsonResponseException(JsonResponse jsonResponse) {
		super();
		this.jsonErrorResponse = jsonResponse;
	}

	public JsonResponse getJsonErrorResponse() {
		return jsonErrorResponse;
	}

	public void setJsonErrorResponse(JsonResponse jsonErrorResponse) {
		this.jsonErrorResponse = jsonErrorResponse;
	}
	
	public HttpStatus getHttpStatus() {
		Integer httpStatusInt = jsonErrorResponse.getHttpStatus();
		if (httpStatusInt != null) {
			return HttpStatus.valueOf(jsonErrorResponse.getHttpStatus());
		} else {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@Override
	public String toString() {
		return "ErrorJsonResponseException [jsonErrorResponse=" + jsonErrorResponse + "]";
	}

}

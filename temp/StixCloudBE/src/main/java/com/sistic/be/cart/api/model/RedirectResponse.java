package com.sistic.be.cart.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedirectResponse extends JsonResponse {

	private static final long serialVersionUID = -2917460818527670471L;
	
	@JsonProperty("redirect")
	private String redirect;
	
	public RedirectResponse() {
		super();
	}
	
	public RedirectResponse(String redirectUrl) {
		this.redirect = redirectUrl;
	}
	
	public RedirectResponse(Integer httpStatus, Integer status, String statusMessage, String redirectUrl) {
		super(httpStatus, status, statusMessage);
		this.redirect = redirectUrl;
	}

	@JsonProperty("redirect")
	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	@Override
	public String toString() {
		return "RedirectResponse [redirect=" + redirect + "]";
	}

}

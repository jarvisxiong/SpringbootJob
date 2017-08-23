package com.sistic.be.cart.api.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.model.PopUpMessageJson;

public class JsonResponseBuilder {

	private static final Logger logger = LogManager.getLogger(JsonResponseBuilder.class);

	public Integer httpStatus;
	public Integer status;
	public String statusCode;
	public String errorCode;
	public String statusMessage;
	public PopUpMessageJson popUpMessage;
	public Boolean popUp;

	public JsonResponseBuilder httpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
		return this;
	}

	public JsonResponseBuilder status(Integer status) {
		this.status = status;
		return this;
	}

	public JsonResponseBuilder statusCode(String statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public JsonResponseBuilder errorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public JsonResponseBuilder statusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
		return this;
	}

	public JsonResponseBuilder popUpMessage(PopUpMessageJson popUpMessage) {
		this.popUpMessage = popUpMessage;
		return this;
	}

	public JsonResponseBuilder popUp(Boolean popUp) {
		this.popUp = popUp;
		return this;
	}

	public JsonResponse build() {
		return new JsonResponse(this);
	}
}

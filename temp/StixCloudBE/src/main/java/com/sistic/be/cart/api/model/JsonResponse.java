/**
 * 
 */
package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.money.MonetaryAmount;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistic.be.app.util.MoneyUtil;
import com.sistic.be.cart.api.builder.JsonResponseBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "httpStatus", "status", "statusCode", "errorCode", "statusMessage", "popUpMessage" })
public class JsonResponse implements Serializable {

	private static final long serialVersionUID = -2251075008439237173L;

	@JsonProperty("httpStatus")
	private Integer httpStatus;
	@JsonProperty("status")
	private Integer status;
	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("errorCode")
	private String errorCode;
	@JsonProperty("statusMessage")
	private String statusMessage;
	@JsonProperty("popUpMessage")
	private PopUpMessageJson popUpMessage;
	@JsonProperty("popUp")
	private Boolean popUp;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	public JsonResponse() {
	}

	public JsonResponse(JsonResponseBuilder builder) {
		this.httpStatus = builder.httpStatus;
		this.status = builder.status;
		this.statusCode = builder.statusCode;
		this.errorCode = builder.errorCode;
		this.statusMessage = builder.statusMessage;
		this.popUpMessage = builder.popUpMessage;
		this.popUp = builder.popUp;
	}

	// TODO: remove and change to builder
	public JsonResponse(Integer httpStatus, String statusMessage) {
		this.httpStatus = httpStatus;
		this.statusMessage = statusMessage;
	}

	// TODO: remove and change to builder
	public JsonResponse(Integer httpStatus, Integer status, String statusMessage) {
		this.httpStatus = httpStatus;
		this.status = status;
		this.statusMessage = statusMessage;
	}

	// TODO: remove and change to builder
	public JsonResponse(Integer httpStatus, Integer status, String statusCode, String statusMessage) {
		this.httpStatus = httpStatus;
		this.status = status;
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	// TODO: remove and change to builder
	public JsonResponse(Integer httpStatus, Integer status, String statusMessage, PopUpMessageJson popUpMessage,
			Boolean popUp) {
		this.httpStatus = httpStatus;
		this.status = status;
		this.statusMessage = statusMessage;
		if (popUpMessage != null) {
			this.additionalProperties.put("lightBoxTitle", "message.scb.title");
			this.additionalProperties.put("lightBox", popUp);
			this.additionalProperties.put("lightBoxMessage", popUpMessage.getMessage());
			MonetaryAmount bookingFee = (MonetaryAmount) popUpMessage.getBookingFee();
			List<String> messageDataList = new LinkedList<String>();
//			messageDataList.add(bookingFee.getCurrency().getCurrencyCode());
//			messageDataList.add(bookingFee.getNumber().toString());
			messageDataList.add(MoneyUtil.getCurrencyUnit(bookingFee));
			messageDataList.add(MoneyUtil.getFormattedMonetaryString(bookingFee));
			this.additionalProperties.put("lightBoxVariable", messageDataList);
		}

	}

	@JsonProperty("httpStatus")
	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	@JsonProperty("statusMessage")
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	@JsonProperty("status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("popUpMessage")
	public PopUpMessageJson getPopUpMessage() {
		return popUpMessage;
	}

	public void setPopUpMessage(PopUpMessageJson popUpMessage) {
		this.popUpMessage = popUpMessage;
	}

	@JsonProperty("popUp")
	public Boolean getPopUp() {
		return popUp;
	}

	public void setPopUp(Boolean popUp) {
		this.popUp = popUp;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("httpStatus", httpStatus)
				.append("status", status).append("statusCode", statusCode).append("statusMessage", statusMessage)
				.toString();
	}
}
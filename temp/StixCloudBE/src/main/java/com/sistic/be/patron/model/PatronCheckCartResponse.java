package com.sistic.be.patron.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistic.be.cart.api.model.CheckCartLineItem;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
    "patron",
    "lineItemList",
    "totalLineItems",
    "timeLeftSeconds"
})
public class PatronCheckCartResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993138045920209690L;
	
	@JsonProperty("patron")
	private CheckCartPatron checkCartPatron;
	private List<CheckCartLineItem> lineItemList;
	private Short totalLineItems;
	private Long timeLeftSeconds;
	@JsonIgnore
	private OffsetDateTime reservedTime;
	
	public PatronCheckCartResponse() {
		super();
	}

	public PatronCheckCartResponse(PatronCheckCartResponse other) {
		this.checkCartPatron = other.checkCartPatron;
		this.lineItemList = other.lineItemList;
		this.totalLineItems = other.totalLineItems;
		this.timeLeftSeconds = other.timeLeftSeconds;
		this.reservedTime = other.reservedTime;
	}

	@JsonProperty("patron")
	public CheckCartPatron getCheckCartPatron() {
		return checkCartPatron;
	}

	public void setCheckCartPatron(CheckCartPatron checkCartPatron) {
		this.checkCartPatron = checkCartPatron;
	}

	public List<CheckCartLineItem> getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(List<CheckCartLineItem> lineItemList) {
		this.lineItemList = lineItemList;
	}

	public Short getTotalLineItems() {
		return totalLineItems;
	}

	public void setTotalLineItems(Short totalLineItems) {
		this.totalLineItems = totalLineItems;
	}

	public Long getTimeLeftSeconds() {
		return timeLeftSeconds;
	}

	public void setTimeLeftSeconds(Long timeLeftSeconds) {
		this.timeLeftSeconds = timeLeftSeconds;
	}

	@JsonIgnore
	public OffsetDateTime getReservedTime() {
		return reservedTime;
	}

	public void setReservedTime(OffsetDateTime reservedTime) {
		this.reservedTime = reservedTime;
	}

}

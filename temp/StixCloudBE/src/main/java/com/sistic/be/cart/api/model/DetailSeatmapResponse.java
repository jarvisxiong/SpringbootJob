package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class DetailSeatmapResponse implements Serializable {

	private static final long serialVersionUID = -5729033707830956142L;
	
	@JsonProperty("timeLeftSeconds")
	private Long timeLeftSeconds;
	@JsonProperty("detailSeatmapResponse")
	private JsonNode detailSeatmapResponse;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	
	public Long getTimeLeftSeconds() {
		return timeLeftSeconds;
	}
	public void setTimeLeftSeconds(Long timeLeftSeconds) {
		this.timeLeftSeconds = timeLeftSeconds;
	}
	public JsonNode getDetailSeatmapResponse() {
		return detailSeatmapResponse;
	}
	public void setDetailSeatmapResponse(JsonNode detailSeatmapResponse) {
		this.detailSeatmapResponse = detailSeatmapResponse;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}
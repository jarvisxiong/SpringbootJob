package com.sistic.be.cart.seatmap;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
	"topLeftCoordinates",
	"seatAngle"
})
public class SeatsUnavailable {

	@JsonProperty("topLeftCoordinates")
	private String topLeftCoordinates;
	@JsonProperty("seatAngle")
	private String seatAngle;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The topLeftCoordinates
	 */
	@JsonProperty("topLeftCoordinates")
	public String getTopLeftCoordinates() {
		return topLeftCoordinates;
	}

	/**
	 * 
	 * @param topLeftCoordinates
	 * The topLeftCoordinates
	 */
	@JsonProperty("topLeftCoordinates")
	public void setTopLeftCoordinates(String topLeftCoordinates) {
		this.topLeftCoordinates = topLeftCoordinates;
	}

	/**
	 * 
	 * @return
	 * The seatAngle
	 */
	@JsonProperty("seatAngle")
	public String getSeatAngle() {
		return seatAngle;
	}

	/**
	 * 
	 * @param seatAngle
	 * The seatAngle
	 */
	@JsonProperty("seatAngle")
	public void setSeatAngle(String seatAngle) {
		this.seatAngle = seatAngle;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
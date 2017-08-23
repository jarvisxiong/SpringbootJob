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
	"inventoryId",
	"seatRowAlias",
	"seatAlias",
	"seatType",
	"topLeftCoordinates",
	"seatAngle",
	"coordinates"
})
public class SetsReserved {

	@JsonProperty("inventoryId")
	private Integer inventoryId;
	@JsonProperty("seatRowAlias")
	private String seatRowAlias;
	@JsonProperty("seatAlias")
	private String seatAlias;
	@JsonProperty("seatType")
	private Integer seatType;
	@JsonProperty("topLeftCoordinates")
	private String topLeftCoordinates;
	@JsonProperty("seatAngle")
	private String seatAngle;
	@JsonProperty("coordinates")
	private String coordinates;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 * The inventoryId
	 */
	@JsonProperty("inventoryId")
	public Integer getInventoryId() {
		return inventoryId;
	}

	/**
	 * 
	 * @param inventoryId
	 * The inventoryId
	 */
	@JsonProperty("inventoryId")
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * 
	 * @return
	 * The seatRowAlias
	 */
	@JsonProperty("seatRowAlias")
	public String getSeatRowAlias() {
		return seatRowAlias;
	}

	/**
	 * 
	 * @param seatRowAlias
	 * The seatRowAlias
	 */
	@JsonProperty("seatRowAlias")
	public void setSeatRowAlias(String seatRowAlias) {
		this.seatRowAlias = seatRowAlias;
	}

	/**
	 * 
	 * @return
	 * The seatAlias
	 */
	@JsonProperty("seatAlias")
	public String getSeatAlias() {
		return seatAlias;
	}

	/**
	 * 
	 * @param seatAlias
	 * The seatAlias
	 */
	@JsonProperty("seatAlias")
	public void setSeatAlias(String seatAlias) {
		this.seatAlias = seatAlias;
	}

	/**
	 * 
	 * @return
	 * The seatType
	 */
	@JsonProperty("seatType")
	public Integer getSeatType() {
		return seatType;
	}

	/**
	 * 
	 * @param seatType
	 * The seatType
	 */
	@JsonProperty("seatType")
	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

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

	/**
	 * 
	 * @return
	 * The coordinates
	 */
	@JsonProperty("coordinates")
	public String getCoordinates() {
		return coordinates;
	}

	/**
	 * 
	 * @param coordinates
	 * The coordinates
	 */
	@JsonProperty("coordinates")
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
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
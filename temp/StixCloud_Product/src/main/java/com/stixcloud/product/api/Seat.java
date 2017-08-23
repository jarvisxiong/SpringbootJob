package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Generated;

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
public class Seat {

  @JsonProperty("inventoryId")
  private Long inventoryId;
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

  /**
   * No args constructor for use in serialization
   */
  public Seat() {
  }

  /**
   *
   * @param seatAlias
   * @param seatType
   * @param seatAngle
   * @param inventoryId
   * @param seatRowAlias
   * @param coordinates
   * @param topLeftCoordinates
   */
  public Seat(Long inventoryId, String seatRowAlias, String seatAlias, Integer seatType,
              String topLeftCoordinates, String seatAngle, String coordinates) {
    this.inventoryId = inventoryId;
    this.seatRowAlias = seatRowAlias;
    this.seatAlias = seatAlias;
    this.seatType = seatType;
    this.topLeftCoordinates = topLeftCoordinates;
    this.seatAngle = seatAngle;
    this.coordinates = coordinates;
  }

  /**
   * @return The inventoryId
   */
  @JsonProperty("inventoryId")
  public Long getInventoryId() {
    return inventoryId;
  }

  /**
   * @param inventoryId The inventoryId
   */
  @JsonProperty("inventoryId")
  public void setInventoryId(Long inventoryId) {
    this.inventoryId = inventoryId;
  }

  /**
   * @return The seatRowAlias
   */
  @JsonProperty("seatRowAlias")
  public String getSeatRowAlias() {
    return seatRowAlias;
  }

  /**
   * @param seatRowAlias The seatRowAlias
   */
  @JsonProperty("seatRowAlias")
  public void setSeatRowAlias(String seatRowAlias) {
    this.seatRowAlias = seatRowAlias;
  }

  /**
   * @return The seatAlias
   */
  @JsonProperty("seatAlias")
  public String getSeatAlias() {
    return seatAlias;
  }

  /**
   * @param seatAlias The seatAlias
   */
  @JsonProperty("seatAlias")
  public void setSeatAlias(String seatAlias) {
    this.seatAlias = seatAlias;
  }

  /**
   * @return The seatType
   */
  @JsonProperty("seatType")
  public Integer getSeatType() {
    return seatType;
  }

  /**
   * @param seatType The seatType
   */
  @JsonProperty("seatType")
  public void setSeatType(Integer seatType) {
    this.seatType = seatType;
  }

  /**
   * @return The topLeftCoordinates
   */
  @JsonProperty("topLeftCoordinates")
  public String getTopLeftCoordinates() {
    return topLeftCoordinates;
  }

  /**
   * @param topLeftCoordinates The topLeftCoordinates
   */
  @JsonProperty("topLeftCoordinates")
  public void setTopLeftCoordinates(String topLeftCoordinates) {
    this.topLeftCoordinates = topLeftCoordinates;
  }

  /**
   * @return The seatAngle
   */
  @JsonProperty("seatAngle")
  public String getSeatAngle() {
    return seatAngle;
  }

  /**
   * @param seatAngle The seatAngle
   */
  @JsonProperty("seatAngle")
  public void setSeatAngle(String seatAngle) {
    this.seatAngle = seatAngle;
  }

  /**
   * @return The coordinates
   */
  @JsonProperty("coordinates")
  public String getCoordinates() {
    return coordinates;
  }

  /**
   * @param coordinates The coordinates
   */
  @JsonProperty("coordinates")
  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Seat seat = (Seat) o;

    return new EqualsBuilder()
        .append(inventoryId, seat.inventoryId)
        .append(seatRowAlias, seat.seatRowAlias)
        .append(seatAlias, seat.seatAlias)
        .append(seatType, seat.seatType)
        .append(topLeftCoordinates, seat.topLeftCoordinates)
        .append(seatAngle, seat.seatAngle)
        .append(coordinates, seat.coordinates)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(inventoryId)
        .append(seatRowAlias)
        .append(seatAlias)
        .append(seatType)
        .append(topLeftCoordinates)
        .append(seatAngle)
        .append(coordinates)
        .toHashCode();
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("inventoryId", inventoryId)
        .append("seatRowAlias", seatRowAlias)
        .append("seatAlias", seatAlias)
        .append("seatType", seatType)
        .append("topLeftCoordinates", topLeftCoordinates)
        .append("seatAngle", seatAngle)
        .append("coordinates", coordinates)
        .toString();
  }
}

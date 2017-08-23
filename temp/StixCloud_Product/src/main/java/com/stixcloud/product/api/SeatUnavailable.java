package com.stixcloud.product.api;

import com.google.common.base.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "topLeftCoordinates",
    "seatAngle",
    "coordinates"
})
public class SeatUnavailable {

  @JsonProperty("topLeftCoordinates")
  private String topLeftCoordinates;
  @JsonProperty("seatAngle")
  private String seatAngle;
  @JsonProperty("coordinates")
  private String coordinates;


  /**
   * No args constructor for use in serialization
   */
  public SeatUnavailable() {
  }

  /**
   *
   * @param seatAngle
   * @param topLeftCoordinates
   */
  public SeatUnavailable(String topLeftCoordinates, String coordinates, String seatAngle) {
    this.topLeftCoordinates = topLeftCoordinates;
    this.coordinates = coordinates;
    this.seatAngle = seatAngle;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SeatUnavailable that = (SeatUnavailable) o;
    return Objects.equal(topLeftCoordinates, that.topLeftCoordinates) &&
        Objects.equal(coordinates, that.coordinates) &&
        Objects.equal(seatAngle, that.seatAngle);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(topLeftCoordinates, coordinates, seatAngle);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("topLeftCoordinates", topLeftCoordinates)
        .append("coordinates", coordinates)
        .append("seatAngle", seatAngle)
        .toString();
  }
}

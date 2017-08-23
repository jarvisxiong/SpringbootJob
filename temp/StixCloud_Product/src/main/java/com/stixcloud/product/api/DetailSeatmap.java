package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "reservedTime",
    "imageAvailable",
    "viewFromSeatAvailable",
    "seatSelectedType",
    "imageURL",
    "overlayImagePath",
    "setsReservedList",
    "seatsAvailableList",
    "seatsUnavailableList",
    "sectionAlias"
})
public class DetailSeatmap {

  @JsonProperty("reservedTime")
  private OffsetDateTime reservedTime;
  @JsonProperty("imageAvailable")
  private Integer imageAvailable;
  @JsonProperty("viewFromSeatAvailable")
  private Integer viewFromSeatAvailable;
  @JsonProperty("seatSelectedType")
  private String seatSelectedType;
  @JsonProperty("imageURL")
  private String imageURL;
  @JsonProperty("overlayImagePath")
  private String overlayImagePath;
  @JsonProperty("setsReservedList")
  private List<SetsReservedList> setsReservedList = new ArrayList<SetsReservedList>();
  @JsonProperty("seatsAvailableList")
  private List<Seat> seatsAvailableList = new ArrayList<Seat>();
  @JsonProperty("seatsUnavailableList")
  private List<SeatUnavailable> seatsUnavailableList = new ArrayList<SeatUnavailable>();
  @JsonProperty("sectionAlias")
  private String sectionAlias;

  /**
   * No args constructor for use in serialization
   */
  public DetailSeatmap() {
  }

  /**
   *
   * @param overlayImagePath
   * @param sectionAlias
   * @param seatsAvailableList
   * @param seatsUnavailableList
   * @param viewFromSeatAvailable
   * @param setsReservedList
   * @param seatSelectedType
   * @param imageURL
   * @param imageAvailable
   */
  public DetailSeatmap(Integer imageAvailable, Integer viewFromSeatAvailable,
                       String seatSelectedType, String imageURL, String overlayImagePath,
                       List<SetsReservedList> setsReservedList, List<Seat> seatsAvailableList,
                       List<SeatUnavailable> seatsUnavailableList, String sectionAlias) {
    this.imageAvailable = imageAvailable;
    this.viewFromSeatAvailable = viewFromSeatAvailable;
    this.seatSelectedType = seatSelectedType;
    this.imageURL = imageURL;
    this.overlayImagePath = overlayImagePath;
    this.setsReservedList = setsReservedList;
    this.seatsAvailableList = seatsAvailableList;
    this.seatsUnavailableList = seatsUnavailableList;
    this.sectionAlias = sectionAlias;
  }

  /**
   * @return The reservedTime
   */
  @JsonProperty("reservedTime")
  public OffsetDateTime getReservedTime() {
    return reservedTime;
  }

  /**
   * @param reservedTime The reservedTime
   */
  @JsonProperty("reservedTime")
  public void setReservedTime(OffsetDateTime reservedTime) {
    this.reservedTime = reservedTime;
  }

  /**
   * @return The imageAvailable
   */
  @JsonProperty("imageAvailable")
  public Integer getImageAvailable() {
    return imageAvailable;
  }

  /**
   * @param imageAvailable The imageAvailable
   */
  @JsonProperty("imageAvailable")
  public void setImageAvailable(Integer imageAvailable) {
    this.imageAvailable = imageAvailable;
  }

  /**
   * @return The viewFromSeatAvailable
   */
  @JsonProperty("viewFromSeatAvailable")
  public Integer getViewFromSeatAvailable() {
    return viewFromSeatAvailable;
  }

  /**
   * @param viewFromSeatAvailable The viewFromSeatAvailable
   */
  @JsonProperty("viewFromSeatAvailable")
  public void setViewFromSeatAvailable(Integer viewFromSeatAvailable) {
    this.viewFromSeatAvailable = viewFromSeatAvailable;
  }

  /**
   * @return The seatSelectedType
   */
  @JsonProperty("seatSelectedType")
  public String getSeatSelectedType() {
    return seatSelectedType;
  }

  /**
   * @param seatSelectedType The seatSelectedType
   */
  @JsonProperty("seatSelectedType")
  public void setSeatSelectedType(String seatSelectedType) {
    this.seatSelectedType = seatSelectedType;
  }

  /**
   * @return The imageURL
   */
  @JsonProperty("imageURL")
  public String getImageURL() {
    return imageURL;
  }

  /**
   * @param imageURL The imageURL
   */
  @JsonProperty("imageURL")
  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  /**
   * @return The overlayImagePath
   */
  @JsonProperty("overlayImagePath")
  public String getOverlayImagePath() {
    return overlayImagePath;
  }

  /**
   * @param overlayImagePath The overlayImagePath
   */
  @JsonProperty("overlayImagePath")
  public void setOverlayImagePath(String overlayImagePath) {
    this.overlayImagePath = overlayImagePath;
  }

  /**
   * @return The setsReservedList
   */
  @JsonProperty("setsReservedList")
  public List<SetsReservedList> getSetsReservedList() {
    return setsReservedList;
  }

  /**
   * @param setsReservedList The setsReservedList
   */
  @JsonProperty("setsReservedList")
  public void setSetsReservedList(List<SetsReservedList> setsReservedList) {
    this.setsReservedList = setsReservedList;
  }

  /**
   * @return The seatsAvailableList
   */
  @JsonProperty("seatsAvailableList")
  public List<Seat> getSeatsAvailableList() {
    return seatsAvailableList;
  }

  /**
   * @param seatsAvailableList The seatsAvailableList
   */
  @JsonProperty("seatsAvailableList")
  public void setSeatsAvailableList(List<Seat> seatsAvailableList) {
    this.seatsAvailableList = seatsAvailableList;
  }

  /**
   * @return The seatsUnavailableList
   */
  @JsonProperty("seatsUnavailableList")
  public List<SeatUnavailable> getSeatsUnavailableList() {
    return seatsUnavailableList;
  }

  /**
   * @param seatsUnavailableList The seatsUnavailableList
   */
  @JsonProperty("seatsUnavailableList")
  public void setSeatsUnavailableList(List<SeatUnavailable> seatsUnavailableList) {
    this.seatsUnavailableList = seatsUnavailableList;
  }

  /**
   * @return The sectionAlias
   */
  @JsonProperty("sectionAlias")
  public String getSectionAlias() {
    return sectionAlias;
  }

  /**
   * @param sectionAlias The sectionAlias
   */
  @JsonProperty("sectionAlias")
  public void setSectionAlias(String sectionAlias) {
    this.sectionAlias = sectionAlias;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DetailSeatmap that = (DetailSeatmap) o;

    return new EqualsBuilder()
        .append(reservedTime, that.reservedTime)
        .append(imageAvailable, that.imageAvailable)
        .append(viewFromSeatAvailable, that.viewFromSeatAvailable)
        .append(seatSelectedType, that.seatSelectedType)
        .append(imageURL, that.imageURL)
        .append(overlayImagePath, that.overlayImagePath)
        .append(setsReservedList, that.setsReservedList)
        .append(seatsAvailableList, that.seatsAvailableList)
        .append(seatsUnavailableList, that.seatsUnavailableList)
        .append(sectionAlias, that.sectionAlias)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(reservedTime)
        .append(imageAvailable)
        .append(viewFromSeatAvailable)
        .append(seatSelectedType)
        .append(imageURL)
        .append(overlayImagePath)
        .append(setsReservedList)
        .append(seatsAvailableList)
        .append(seatsUnavailableList)
        .append(sectionAlias)
        .toHashCode();
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("reservedTime", reservedTime)
        .append("imageAvailable", imageAvailable)
        .append("viewFromSeatAvailable", viewFromSeatAvailable)
        .append("seatSelectedType", seatSelectedType)
        .append("imageURL", imageURL)
        .append("overlayImagePath", overlayImagePath)
        .append("setsReservedList", setsReservedList)
        .append("seatsAvailableList", seatsAvailableList)
        .append("seatsUnavailableList", seatsUnavailableList)
        .append("sectionAlias", sectionAlias)
        .toString();
  }
}

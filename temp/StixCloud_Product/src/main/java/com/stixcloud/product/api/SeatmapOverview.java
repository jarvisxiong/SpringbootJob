package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "hideStandardPrice",
    "imageAvailable",
    "interactive",
    "mode",
    "imageURL",
    "seatSectionList"
})
public class SeatmapOverview {

  @JsonProperty("hideStandardPrice")
  private Integer hideStandardPrice;
  @JsonProperty("imageAvailable")
  private Integer imageAvailable;
  @JsonProperty("interactive")
  private Integer interactive;
  @JsonProperty("mode")
  private String mode;
  @JsonProperty("imageURL")
  private String imageURL;
  @JsonProperty("seatSectionList")
  private List<SeatSectionList> seatSectionList = new ArrayList<SeatSectionList>();

  /**
   * No args constructor for use in serialization
   */
  public SeatmapOverview() {
  }

  /**
   *
   * @param interactive
   * @param hideStandardPrice
   * @param imageURL
   * @param imageAvailable
   * @param seatSectionList
   * @param mode
   */
  public SeatmapOverview(Integer hideStandardPrice, Integer imageAvailable, Integer interactive,
                         String mode, String imageURL, List<SeatSectionList> seatSectionList) {
    this.hideStandardPrice = hideStandardPrice;
    this.imageAvailable = imageAvailable;
    this.interactive = interactive;
    this.mode = mode;
    this.imageURL = imageURL;
    this.seatSectionList = seatSectionList;
  }

  /**
   * @return The hideStandardPrice
   */
  @JsonProperty("hideStandardPrice")
  public Integer getHideStandardPrice() {
    return hideStandardPrice;
  }

  /**
   * @param hideStandardPrice The hideStandardPrice
   */
  @JsonProperty("hideStandardPrice")
  public void setHideStandardPrice(Integer hideStandardPrice) {
    this.hideStandardPrice = hideStandardPrice;
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
   * @return The interactive
   */
  @JsonProperty("interactive")
  public Integer getInteractive() {
    return interactive;
  }

  /**
   * @param interactive The interactive
   */
  @JsonProperty("interactive")
  public void setInteractive(Integer interactive) {
    this.interactive = interactive;
  }

  /**
   * @return The mode
   */
  @JsonProperty("mode")
  public String getMode() {
    return mode;
  }

  /**
   * @param mode The mode
   */
  @JsonProperty("mode")
  public void setMode(String mode) {
    this.mode = mode;
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
   * @return The seatSectionList
   */
  @JsonProperty("seatSectionList")
  public List<SeatSectionList> getSeatSectionList() {
    return seatSectionList;
  }

  /**
   * @param seatSectionList The seatSectionList
   */
  @JsonProperty("seatSectionList")
  public void setSeatSectionList(List<SeatSectionList> seatSectionList) {
    this.seatSectionList = seatSectionList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof SeatmapOverview)) {
      return false;
    }

    SeatmapOverview overview = (SeatmapOverview) o;

    return new EqualsBuilder()
        .append(getHideStandardPrice(), overview.getHideStandardPrice())
        .append(getImageAvailable(), overview.getImageAvailable())
        .append(getInteractive(), overview.getInteractive())
        .append(getMode(), overview.getMode())
        .append(getImageURL(), overview.getImageURL())
        .append(getSeatSectionList(), overview.getSeatSectionList())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getHideStandardPrice())
        .append(getImageAvailable())
        .append(getInteractive())
        .append(getMode())
        .append(getImageURL())
        .append(getSeatSectionList())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("hideStandardPrice", hideStandardPrice)
        .append("imageAvailable", imageAvailable)
        .append("interactive", interactive)
        .append("mode", mode)
        .append("imageURL", imageURL)
        .append("seatSectionList", seatSectionList)
        .toString();
  }
}

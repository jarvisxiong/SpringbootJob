package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.Valid;

/**
 * Created by chongye on 30-Aug-16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "summaryImagePath",
    "icAttributesList",
    "promotionStatus",
    "showTimingList"
})
public class ShowTimes implements Serializable {

  private static final long serialVersionUID = 8800481729365380500L;
  @JsonProperty("summaryImagePath")
  private String summaryImagePath;
  @JsonProperty("icAttributesList")
  @Valid
  private List<String> icAttributesList = new ArrayList<>();
  @JsonProperty("promotionStatus")
  private String promoStatus;
  @JsonProperty("showTimingList")
  @Valid
  private List<ShowTimingList> showTimingList = new ArrayList<ShowTimingList>();

  /**
   * No args constructor for use in serialization
   */
  public ShowTimes() {
  }

  /**
   *
   * @param showTimingList
   * @param icAttributesList
   * @param promotion
   * @param isValidPromotion
   * @param summaryImagePath
   */
  public ShowTimes(String summaryImagePath, List<String> icAttributesList, String promoStatus,
                   List<ShowTimingList> showTimingList) {
    this.summaryImagePath = summaryImagePath;
    this.icAttributesList = icAttributesList;
    this.promoStatus = promoStatus;
    this.showTimingList = showTimingList;
  }

  /**
   * @return The summaryImagePath
   */
  @JsonProperty("summaryImagePath")
  public String getSummaryImagePath() {
    return summaryImagePath;
  }

  /**
   * @param summaryImagePath The summaryImagePath
   */
  @JsonProperty("summaryImagePath")
  public void setSummaryImagePath(String summaryImagePath) {
    this.summaryImagePath = summaryImagePath;
  }

  /**
   * @return The icAttributesList
   */
  @JsonProperty("icAttributesList")
  public List<String> getIcAttributesList() {
    return icAttributesList;
  }

  /**
   * @param icAttributesList The icAttributesList
   */
  @JsonProperty("icAttributesList")
  public void setIcAttributesList(List<String> icAttributesList) {
    this.icAttributesList = icAttributesList;
  }

  /**
   * @return The promotionStatus
   */
  @JsonProperty("promotionStatus")
  public String getPromoStatus() {
    return promoStatus;
  }

  /**
   * @param promoStatus The promotionStatus
   */
  public void setPromoStatus(String promoStatus) {
    this.promoStatus = promoStatus;
  }

  /**
   * @return The showTimingList
   */
  @JsonProperty("showTimingList")
  public List<ShowTimingList> getShowTimingList() {
    return showTimingList;
  }

  /**
   * @param showTimingList The showTimingList
   */
  @JsonProperty("showTimingList")
  public void setShowTimingList(List<ShowTimingList> showTimingList) {
    this.showTimingList = showTimingList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ShowTimes)) {
      return false;
    }

    ShowTimes showTimes = (ShowTimes) o;

    return new EqualsBuilder()
        .append(getSummaryImagePath(), showTimes.getSummaryImagePath())
        .append(getIcAttributesList(), showTimes.getIcAttributesList())
        .append(getPromoStatus(), showTimes.getPromoStatus())
        .append(getShowTimingList(), showTimes.getShowTimingList())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getSummaryImagePath())
        .append(getIcAttributesList())
        .append(getPromoStatus())
        .append(getShowTimingList())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("summaryImagePath", summaryImagePath)
        .append("icAttributesList", icAttributesList)
        .append("promotionStatus", promoStatus)
        .append("showTimingList", showTimingList)
        .toString();
  }
}

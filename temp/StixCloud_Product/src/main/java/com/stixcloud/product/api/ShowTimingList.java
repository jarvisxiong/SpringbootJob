package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.annotation.Generated;

/**
 * Created by chongye on 04-Aug-16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "productId",
    "showTitle",
    "showDateTime",
    "seatmap",
    "availabilityStatus",
    "isPromo"
})
public class ShowTimingList implements Serializable {
  private static final long serialVersionUID = -1811636500648203390L;
  @JsonProperty("productId")
  private long productId;
  @JsonProperty("showTitle")
  private String showTitle;
  @JsonProperty("showDateTime")
  private OffsetDateTime showDateTime;
  @JsonProperty("venue")
  private String venue;
  @JsonProperty("availabilityStatus")
  private int availabilityStatus;
  @JsonProperty("isPromo")
  private boolean isPromo;
  
  public ShowTimingList() {
  }

  public ShowTimingList(long productId, String showTitle, OffsetDateTime showDateTime, String venue,
                        int availabilityStatus, boolean isPromo) {
    this.productId = productId;
    this.showTitle = showTitle;
    this.showDateTime = showDateTime;
    this.venue = venue;
    this.availabilityStatus = availabilityStatus;
    this.isPromo = isPromo;
  }

  @JsonProperty("productId")
  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  @JsonProperty("showTitle")
  public String getShowTitle() {
    return showTitle;
  }

  public void setShowTitle(String showTitle) {
    this.showTitle = showTitle;
  }

  @JsonProperty("showDateTime")
  public OffsetDateTime getShowDateTime() {
    return showDateTime;
  }

  public void setShowDateTime(OffsetDateTime showDateTime) {
    this.showDateTime = showDateTime;
  }

  @JsonProperty("venue")
  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  @JsonProperty("availabilityStatus")
  public int getAvailabilityStatus() {
    return availabilityStatus;
  }

  public void setAvailabilityStatus(int availabilityStatus) {
    this.availabilityStatus = availabilityStatus;
  }

  @JsonProperty("isPromo")
  public boolean isPromo() {
    return isPromo;
  }

  public void setPromo(boolean isPromo) {
    this.isPromo = isPromo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ShowTimingList)) {
      return false;
    }

    ShowTimingList that = (ShowTimingList) o;

    return new EqualsBuilder()
        .append(getProductId(), that.getProductId())
        .append(getAvailabilityStatus(), that.getAvailabilityStatus())
        .append(getShowTitle(), that.getShowTitle())
        .append(getShowDateTime(), that.getShowDateTime())
        .append(getVenue(), that.getVenue())
        .append(isPromo(), that.isPromo())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getProductId())
        .append(getShowTitle())
        .append(getShowDateTime())
        .append(getVenue())
        .append(getAvailabilityStatus())
        .append(isPromo())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productId)
        .append("showTitle", showTitle)
        .append("showDateTime", showDateTime)
        .append("venue", venue)
        .append("availabilityStatus", availabilityStatus)
        .append("isPromo", isPromo)
        .toString();
  }
}

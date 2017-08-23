package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "seatSectionId",
    "priceCategoryId",
    "seatSectionAvailability"
})
public class SeatmapAvailability implements Serializable {

  private static final long serialVersionUID = 7101366887997912670L;
  @JsonProperty("seatSectionId")
  private Long seatSectionId;
  @JsonProperty("priceCategoryId")
  private Long priceCategoryId;
  @JsonProperty("seatSectionAvailability")
  private Integer seatSectionAvailability;

  public SeatmapAvailability() {
  }

  public SeatmapAvailability(Long seatSectionId, Long priceCategoryId,
                             Integer seatSectionAvailability) {
    this.seatSectionId = seatSectionId;
    this.priceCategoryId = priceCategoryId;
    this.seatSectionAvailability = seatSectionAvailability;
  }

  @JsonProperty("seatSectionId")
  public Long getSeatSectionId() {
    return seatSectionId;
  }

  public void setSeatSectionId(Long seatSectionId) {
    this.seatSectionId = seatSectionId;
  }

  @JsonProperty("priceCategoryId")
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  @JsonProperty("seatSectionAvailability")
  public Integer getSeatSectionAvailability() {
    return seatSectionAvailability;
  }

  public void setSeatSectionAvailability(Integer seatSectionAvailability) {
    this.seatSectionAvailability = seatSectionAvailability;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("seatSectionId", seatSectionId)
        .append("priceCategoryId", priceCategoryId)
        .append("seatSectionAvailability", seatSectionAvailability)
        .toString();
  }
}

package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 25-Aug-16.
 */
@Entity
@Table(name = "SEATMAP_AVAILABLITY_PRODUCTS")
public class SeatmapAvailablityProducts implements Serializable {
  @Id
  private Long productid;
  @Id
  private Long seatsectionid;
  @Id
  private Long priceCatId;
  @Id
  private Long profileInfoId;
  @Id
  private Integer seatSectionAvailability;

  public SeatmapAvailablityProducts() {
  }

  public SeatmapAvailablityProducts(Long productid, Long seatsectionid, Long priceCatId, Long profileInfoId,
                                    Integer seatSectionAvailability) {
    this.productid = productid;
    this.seatsectionid = seatsectionid;
    this.priceCatId = priceCatId;
    this.profileInfoId = profileInfoId;
    this.seatSectionAvailability = seatSectionAvailability;
  }

  @Column(name = "PRODUCTID", nullable = false, precision = 0)
  public Long getProductid() {
    return productid;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }

  @Column(name = "SEATSECTIONID", nullable = true, precision = 0)
  public Long getSeatsectionid() {
    return seatsectionid;
  }

  public void setSeatsectionid(Long seatsectionid) {
    this.seatsectionid = seatsectionid;
  }

  @Column(name = "PRICE_CAT_ID", nullable = true, precision = 0)
  public Long getPriceCatId() {
    return priceCatId;
  }

  public void setPriceCatId(Long priceCatId) {
    this.priceCatId = priceCatId;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Column(name = "SEAT_SECTION_AVAILABILITY", nullable = true, precision = 0)
  public Integer getSeatSectionAvailability() {
    return seatSectionAvailability;
  }

  public void setSeatSectionAvailability(Integer seatSectionAvailability) {
    this.seatSectionAvailability = seatSectionAvailability;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SeatmapAvailablityProducts that = (SeatmapAvailablityProducts) o;

    return new EqualsBuilder()
        .append(productid, that.productid)
        .append(seatsectionid, that.seatsectionid)
        .append(priceCatId, that.priceCatId)
        .append(profileInfoId, that.profileInfoId)
        .append(seatSectionAvailability, that.seatSectionAvailability)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(productid)
        .append(seatsectionid)
        .append(priceCatId)
        .append(profileInfoId)
        .append(seatSectionAvailability)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productid", productid)
        .append("seatsectionid", seatsectionid)
        .append("priceCatId", priceCatId)
        .append("profileInfoId", profileInfoId)
        .append("seatSectionAvailability", seatSectionAvailability)
        .toString();
  }
}

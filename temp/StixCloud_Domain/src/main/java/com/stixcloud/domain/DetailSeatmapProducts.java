package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DETAIL_SEATMAP")
public class DetailSeatmapProducts implements Serializable {
  @Id
  private long productId;
  @Id
  private long priceCatId;
  @Id
  private long sectionId;
  private int imageAvailable;
  private int viewFromSeatAvailable;
  private Long seatMappingId;
  private String detailImage;
  private String sectionAlias;
  private boolean interactive;
  private Integer rank;
  private int seatSalesStatus;
  private int ticketable;
  @Id
  private Long inventoryId;
  private String seatRowAlias;
  private String seatAlias;
  private Integer seatType;
  private String coordinates;
  private String topLeftCoordinates;
  private String seatAngle;
  private String seatSectionAlias;
  private long profileInfoId;

  public DetailSeatmapProducts() {
  }

  public DetailSeatmapProducts(long productId, long priceCatId, long sectionId, int imageAvailable,
                               int viewFromSeatAvailable, Long seatMappingId, String detailImage,
                               String sectionAlias, boolean interactive, Integer rank,
                               int seatSalesStatus, int ticketable, Long inventoryId, String seatRowAlias,
                               String seatAlias, Integer seatType, String coordinates,
                               String topLeftCoordinates, String seatAngle, String seatSectionAlias,
                               long profileInfoId) {
    this.productId = productId;
    this.priceCatId = priceCatId;
    this.sectionId = sectionId;
    this.imageAvailable = imageAvailable;
    this.viewFromSeatAvailable = viewFromSeatAvailable;
    this.seatMappingId = seatMappingId;
    this.detailImage = detailImage;
    this.sectionAlias = sectionAlias;
    this.interactive = interactive;
    this.rank = rank;
    this.seatSalesStatus = seatSalesStatus;
    this.ticketable = ticketable;
    this.inventoryId = inventoryId;
    this.seatRowAlias = seatRowAlias;
    this.seatAlias = seatAlias;
    this.seatType = seatType;
    this.coordinates = coordinates;
    this.topLeftCoordinates = topLeftCoordinates;
    this.seatAngle = seatAngle;
    this.seatSectionAlias = seatSectionAlias;
    this.profileInfoId = profileInfoId;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public long getPriceCatId() {
    return priceCatId;
  }

  public void setPriceCatId(long priceCatId) {
    this.priceCatId = priceCatId;
  }

  public long getSectionId() {
    return sectionId;
  }

  public void setSectionId(long sectionId) {
    this.sectionId = sectionId;
  }

  public int getImageAvailable() {
    return imageAvailable;
  }

  public void setImageAvailable(int imageAvailable) {
    this.imageAvailable = imageAvailable;
  }

  public int getViewFromSeatAvailable() {
    return viewFromSeatAvailable;
  }

  public void setViewFromSeatAvailable(int viewFromSeatAvailable) {
    this.viewFromSeatAvailable = viewFromSeatAvailable;
  }

  public Long getSeatMappingId() {
    return seatMappingId;
  }

  public void setSeatMappingId(Long seatMappingId) {
    this.seatMappingId = seatMappingId;
  }

  public String getDetailImage() {
    return detailImage;
  }

  public void setDetailImage(String detailImage) {
    this.detailImage = detailImage;
  }

  public String getSectionAlias() {
    return sectionAlias;
  }

  public void setSectionAlias(String sectionAlias) {
    this.sectionAlias = sectionAlias;
  }

  public boolean isInteractive() {
    return interactive;
  }

  public void setInteractive(boolean interactive) {
    this.interactive = interactive;
  }

  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }

  public int getSeatSalesStatus() {
    return seatSalesStatus;
  }

  public void setSeatSalesStatus(int seatSalesStatus) {
    this.seatSalesStatus = seatSalesStatus;
  }
  
  public int getTicketable() {
    return ticketable;
  }

  public void setTicketable(int ticketable) {
    this.ticketable = ticketable;
  }

  public Long getInventoryId() {
    return inventoryId;
  }

  public void setInventoryId(Long inventoryId) {
    this.inventoryId = inventoryId;
  }

  public String getSeatRowAlias() {
    return seatRowAlias;
  }

  public void setSeatRowAlias(String seatRowAlias) {
    this.seatRowAlias = seatRowAlias;
  }

  public String getSeatAlias() {
    return seatAlias;
  }

  public void setSeatAlias(String seatAlias) {
    this.seatAlias = seatAlias;
  }

  public Integer getSeatType() {
    return seatType;
  }

  public void setSeatType(Integer seatType) {
    this.seatType = seatType;
  }

  public String getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  public String getTopLeftCoordinates() {
    return topLeftCoordinates;
  }

  public void setTopLeftCoordinates(String topLeftCoordinates) {
    this.topLeftCoordinates = topLeftCoordinates;
  }

  public String getSeatAngle() {
    return seatAngle;
  }

  public void setSeatAngle(String seatAngle) {
    this.seatAngle = seatAngle;
  }

  public String getSeatSectionAlias() {
    return seatSectionAlias;
  }

  public void setSeatSectionAlias(String seatSectionAlias) {
    this.seatSectionAlias = seatSectionAlias;
  }

  public long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DetailSeatmapProducts that = (DetailSeatmapProducts) o;

    return new EqualsBuilder()
        .append(productId, that.productId)
        .append(priceCatId, that.priceCatId)
        .append(sectionId, that.sectionId)
        .append(imageAvailable, that.imageAvailable)
        .append(viewFromSeatAvailable, that.viewFromSeatAvailable)
        .append(interactive, that.interactive)
        .append(rank, that.rank)
        .append(seatSalesStatus, that.seatSalesStatus)
        .append(ticketable, that.ticketable)
        .append(inventoryId, that.inventoryId)
        .append(seatType, that.seatType)
        .append(seatAngle, that.seatAngle)
        .append(seatMappingId, that.seatMappingId)
        .append(detailImage, that.detailImage)
        .append(sectionAlias, that.sectionAlias)
        .append(seatRowAlias, that.seatRowAlias)
        .append(seatAlias, that.seatAlias)
        .append(coordinates, that.coordinates)
        .append(topLeftCoordinates, that.topLeftCoordinates)
        .append(seatSectionAlias, that.seatSectionAlias)
        .append(profileInfoId, that.profileInfoId)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(productId)
        .append(priceCatId)
        .append(sectionId)
        .append(imageAvailable)
        .append(viewFromSeatAvailable)
        .append(seatMappingId)
        .append(detailImage)
        .append(sectionAlias)
        .append(interactive)
        .append(rank)
        .append(seatSalesStatus)
        .append(ticketable)
        .append(inventoryId)
        .append(seatRowAlias)
        .append(seatAlias)
        .append(seatType)
        .append(coordinates)
        .append(topLeftCoordinates)
        .append(seatAngle)
        .append(seatSectionAlias)
        .append(profileInfoId)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productId", productId)
        .append("priceCatId", priceCatId)
        .append("sectionId", sectionId)
        .append("imageAvailable", imageAvailable)
        .append("viewFromSeatAvailable", viewFromSeatAvailable)
        .append("seatMappingId", seatMappingId)
        .append("detailImage", detailImage)
        .append("sectionAlias", sectionAlias)
        .append("interactive", interactive)
        .append("rank", rank)
        .append("seatSalesStatus", seatSalesStatus)
        .append("ticketable", ticketable)
        .append("inventoryId", inventoryId)
        .append("seatRowAlias", seatRowAlias)
        .append("seatAlias", seatAlias)
        .append("seatType", seatType)
        .append("coordinates", coordinates)
        .append("topLeftCoordinates", topLeftCoordinates)
        .append("seatAngle", seatAngle)
        .append("seatSectionAlias", seatSectionAlias)
        .append("profileInfoId", profileInfoId)
        .toString();
  }
}

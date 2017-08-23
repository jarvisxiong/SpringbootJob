package com.stixcloud.barcode.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "BARCODE_FIELD_VALUES_DATA_VIEW")
public class BarcodeFieldValuesDataView implements Serializable {

  private static final long serialVersionUID = 7879096776413161769L;
  @Id
  private Long seatInventoryId;
  @Id
  private Long productId;
  @Id
  private Long priceClassId;
  @Id
  private String sectionCode;
  @Id
  private String priceClassCode;
  @Id
  private Long venueId;
  @Id
  private Long externalProductId;
  @Id
  private Long eventId;

  public BarcodeFieldValuesDataView() {

  }

  public BarcodeFieldValuesDataView(Long seatInventoryId, Long productId, Long priceClassId,
      String sectionCode, String priceClassCode, Long venueId, Long externalProductId,
      Long eventId) {
    this.seatInventoryId = seatInventoryId;
    this.productId = productId;
    this.priceClassId = priceClassId;
    this.sectionCode = sectionCode;
    this.priceClassCode = priceClassCode;
    this.venueId = venueId;
    this.externalProductId = externalProductId;
    this.eventId = eventId;
  }


  /**
   * @return the seatInventoryId
   */
  @Column(name = "SEAT_INVENTORY_ID", nullable = false, length = 10)
  public Long getSeatInventoryId() {
    return seatInventoryId;
  }

  /**
   * @param seatInventoryId the seatInventoryId to set
   */
  public void setSeatInventoryId(Long seatInventoryId) {
    this.seatInventoryId = seatInventoryId;
  }

  /**
   * @return the productId
   */
  @Column(name = "PRODUCT_ID", nullable = false, length = 10)
  public Long getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * @return the priceClassId
   */
  @Column(name = "PRICE_CLASS_ID", nullable = false, length = 10)
  public Long getPriceClassId() {
    return priceClassId;
  }

  /**
   * @param priceClassId the priceClassId to set
   */
  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  /**
   * @return the sectionCode
   */
  @Column(name = "SECTION_CODE", nullable = true, length = 50)
  public String getSectionCode() {
    return sectionCode;
  }

  /**
   * @param sectionCode the sectionCode to set
   */
  public void setSectionCode(String sectionCode) {
    this.sectionCode = sectionCode;
  }

  /**
   * @return the priceClassCode
   */
  @Column(name = "PRICE_CLASS_CODE", nullable = false, length = 10)
  public String getPriceClassCode() {
    return priceClassCode;
  }

  /**
   * @param priceClassCode the priceClassCode to set
   */
  public void setPriceClassCode(String priceClassCode) {
    this.priceClassCode = priceClassCode;
  }

  /**
   * @return the venueId
   */
  @Column(name = "VENUE_ID", nullable = false, length = 10)
  public Long getVenueId() {
    return venueId;
  }

  /**
   * @param venueId the venueId to set
   */
  public void setVenueId(Long venueId) {
    this.venueId = venueId;
  }

  /**
   * @return the externalProductId
   */
  @Column(name = "EXTERNAL_PRODUCT_ID", nullable = false, length = 10)
  public Long getExternalProductId() {
    return externalProductId;
  }

  /**
   * @param externalProductId the externalProductId to set
   */
  public void setExternalProductId(Long externalProductId) {
    this.externalProductId = externalProductId;
  }

  /**
   * @return the eventId
   */
  @Column(name = "EVENT_ID", nullable = false, length = 10)
  public Long getEventId() {
    return eventId;
  }

  /**
   * @param eventId the eventId to set
   */
  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BarcodeFieldValuesDataView that = (BarcodeFieldValuesDataView) o;
    return new EqualsBuilder().append(seatInventoryId, that.getSeatInventoryId())
        .append(productId, that.getProductId()).append(priceClassId, that.getPriceClassId())
        .append(sectionCode, that.getSectionCode()).append(priceClassCode, that.getPriceClassCode())
        .append(venueId, that.getVenueId()).append(externalProductId, that.getExternalProductId())
        .append(eventId, that.getEventId()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(seatInventoryId).append(productId)
        .append(priceClassId).append(sectionCode).append(priceClassCode).append(venueId)
        .append(externalProductId).append(eventId).toHashCode();
  }

}

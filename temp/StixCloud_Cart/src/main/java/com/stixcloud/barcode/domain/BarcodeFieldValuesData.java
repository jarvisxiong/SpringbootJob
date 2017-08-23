package com.stixcloud.barcode.domain;

import java.io.Serializable;
import java.util.Objects;

public class BarcodeFieldValuesData implements Serializable {
  private static final long serialVersionUID = -5449950764617813468L;
  protected Long eventId;
  protected String sectionCode;
  protected Long externalProductId;
  protected String priceClassCode;
  protected Long venueId;

  public BarcodeFieldValuesData() {}

  public BarcodeFieldValuesData(Long eventId, String sectionCode, Long externalProductId,
      String priceClassCode, Long venueId) {
    this.eventId = eventId;
    this.sectionCode = sectionCode;
    this.externalProductId = externalProductId;
    this.priceClassCode = priceClassCode;
    this.venueId = venueId;
  }


  /**
   * @return the eventId
   */
  public Long getEventId() {
    return eventId;
  }

  /**
   * @param eventId the eventId to set
   */
  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  /**
   * @return the sectionCode
   */
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
   * @return the externalProductId
   */
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
   * @return the priceClassCode
   */
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
  public Long getVenueId() {
    return venueId;
  }

  /**
   * @param venueId the venueId to set
   */
  public void setVenueId(Long venueId) {
    this.venueId = venueId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BarcodeFieldValuesData that = (BarcodeFieldValuesData) o;
    return Objects.equals(eventId, that.eventId) && Objects.equals(sectionCode, that.sectionCode)
        && Objects.equals(externalProductId, that.externalProductId)
        && Objects.equals(priceClassCode, that.priceClassCode)
        && Objects.equals(venueId, that.venueId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventId, sectionCode, externalProductId, priceClassCode, venueId);
  }

}

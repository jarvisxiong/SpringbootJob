package com.stixcloud.barcode.domain;

import com.stixcloud.barcode.constant.BarcodeConstant;

public class BarcodeFieldValues extends BarcodeFieldValuesData {
  private String patronId;
  private String transactionId;
  private Long acsBarcodeRegenCount;
  private Long reprintedCount;
  private Long ticketId;

  /**
   * @return The patronId.
   */
  public String getPatronId() {
    return patronId;
  }

  /**
   * @param patronId The patronId.
   */
  public void setPatronId(String patronId) {
    this.patronId = patronId;
  }

  /**
   * @return The transactionId.
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * @param transactionId The transactionId.
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }


  /**
   * @return the acsBarcodeRegenCount
   */
  public Long getAcsBarcodeRegenCount() {
    return acsBarcodeRegenCount;
  }

  public void setVenueId(Long venueId) {
    if (venueId == null) {
      this.venueId = -1L;
    } else {
      this.venueId = venueId;
    }
  }

  public void setAcsBarcodeRegenCount(Long acsBarcodeRegenCount) {
    if (acsBarcodeRegenCount == null) {
      this.acsBarcodeRegenCount = 0L;
    } else {
      this.acsBarcodeRegenCount = acsBarcodeRegenCount;
    }
  }


  /**
   * @return the reprintedCount
   */
  public Long getReprintedCount() {
    return reprintedCount;
  }

  /**
   * @param reprintedCount the reprintedCount to set
   */
  public void setReprintedCount(Long reprintedCount) {
    this.reprintedCount = reprintedCount;
  }

  /**
   * @return the ticketId
   */
  public Long getTicketId() {
    return ticketId;
  }

  /**
   * @param ticketId the ticketId to set
   */
  public void setTicketId(Long ticketId) {
    this.ticketId = ticketId;
  }

  public String getFieldValue(String dataType) {
    switch (dataType) {
      case BarcodeConstant.EVENT_ID:
        return getEventId().toString();
      case BarcodeConstant.SECTION_CODE:
        return getSectionCode();
      case BarcodeConstant.TICKET_ID:
        return getTicketId().toString();
      case BarcodeConstant.PRICE_CLASS_CODE:
        return getPriceClassCode();
      case BarcodeConstant.PATRON_ID:
        return getPatronId();
      case BarcodeConstant.TRANSACTION_ID:
        return getTransactionId();
      case BarcodeConstant.REPRINTED_COUNT:
        return getReprintedCount().toString();
      case BarcodeConstant.VENUE_ID:
        return getVenueId().toString();
      case BarcodeConstant.ACSBARCODEREGENCOUNT:
        return getAcsBarcodeRegenCount().toString();
      default:
        return null;
    }
  }
}

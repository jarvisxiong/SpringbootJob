package com.stixcloud.barcode.domain;

public class BarcodeFieldValuesBuilder {

  private Long eventId;
  private String sectionCode;
  private Long ticketId;
  private String priceClassCode;
  private String patronId;
  private String transactionId;
  private Long reprintedCount;
  private Long venueId;
  private Long acsBarcodeRegenCount;

  private BarcodeFieldValuesBuilder() {

  }

  public static BarcodeFieldValuesBuilder aBarcodeFieldValues() {
    return new BarcodeFieldValuesBuilder();
  }

  /**
   * @return The eventId.
   */
  public Long getEventId() {
    return eventId;
  }

  /**
   * @param eventId The eventId.
   */
  public BarcodeFieldValuesBuilder setEventId(Long eventId) {
    this.eventId = eventId;
    return this;
  }

  /**
   * @return The sectionCode.
   */
  public String getSectionCode() {
    return sectionCode;
  }

  /**
   * @param sectionCode The sectionCode.
   */
  public BarcodeFieldValuesBuilder setSectionCode(String sectionCode) {
    this.sectionCode = sectionCode;
    return this;
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
  public BarcodeFieldValuesBuilder setTicketId(Long ticketId) {
    this.ticketId = ticketId;
    return this;
  }

  /**
   * @return The priceClassCode.
   */
  public String getPriceClassCode() {
    return priceClassCode;
  }

  /**
   * @param priceClassCode The priceClassCode.
   */
  public BarcodeFieldValuesBuilder setPriceClassCode(String priceClassCode) {
    this.priceClassCode = priceClassCode;
    return this;
  }

  /**
   * @return The patronId.
   */
  public String getPatronId() {
    return patronId;
  }

  /**
   * @param patronId The patronId.
   */
  public BarcodeFieldValuesBuilder setPatronId(String patronId) {
    this.patronId = patronId;
    return this;
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
  public BarcodeFieldValuesBuilder setTransactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * @return The reprintedCount.
   */
  public Long getReprintedCount() {
    return reprintedCount;
  }

  /**
   * @param reprintedCount The reprintedCount.
   */
  public BarcodeFieldValuesBuilder setReprintedCount(Long reprintedCount) {
    this.reprintedCount = reprintedCount;
    return this;
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
  public BarcodeFieldValuesBuilder setVenueId(Long venueId) {
    this.venueId = venueId;
    return this;
  }

  /**
   * @return the acsBarcodeRegenCount
   */
  public Long getAcsBarcodeRegenCount() {
    return acsBarcodeRegenCount;
  }

  /**
   * @param acsBarcodeRegenCount the acsBarcodeRegenCount to set
   */
  public BarcodeFieldValuesBuilder setAcsBarcodeRegenCount(Long acsBarcodeRegenCount) {
    this.acsBarcodeRegenCount = acsBarcodeRegenCount;
    return this;
  }

  public BarcodeFieldValues build() {
    BarcodeFieldValues barcodeFieldValues = new BarcodeFieldValues();
    barcodeFieldValues.setEventId(eventId);
    barcodeFieldValues.setAcsBarcodeRegenCount(acsBarcodeRegenCount);
    barcodeFieldValues.setPatronId(patronId.toString());
    barcodeFieldValues.setPriceClassCode(priceClassCode);
    barcodeFieldValues.setReprintedCount(reprintedCount);
    barcodeFieldValues.setSectionCode(sectionCode);
    barcodeFieldValues.setTicketId(ticketId);
    barcodeFieldValues.setTransactionId(transactionId);
    barcodeFieldValues.setVenueId(venueId);
    return barcodeFieldValues;
  }
}

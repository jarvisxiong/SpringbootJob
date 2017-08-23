package com.stixcloud.patron.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION_REFERENCE_VIEW")
public class TransactionReferenceView implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1361708952511198688L;

  @Id
  private String lineItemType;
  @Id
  private Long transactionid;
  @Id
  private String transactionRefNumber;
  @Id
  private String transactionType;
  @Id
  private OffsetDateTime transactedTime;
  @Id
  private String paymentType;
  @Id
  private Long txnLineItemId;
  @Id
  private Long txnproductid;
  private String productName;
  private OffsetDateTime startDate;
  private OffsetDateTime endDate;
  private String priceCategoryName;
  private String venueName;
  private String sectionAlias;
  private String sectionType;
  private String levelAlias;
  private String rowAlias;
  private Long seatNo;
  @Id
  private String description;
  private Long quantity;
  private BigDecimal unitPrice;
  @Id
  private BigDecimal bookingFee;
  @Id
  private Long patronid;
  @Id
  private Long ownerprofile;
  private String lineone;
  private String linetwo;
  private String linethree;
  private String postalcode;
  private String city;
  private String state;
  private String country;
  private String deliverymethodname;
  private String deliverymethodcode;
  private Boolean addressrequired;

  public TransactionReferenceView() {
    super();
  }

  public TransactionReferenceView(String lineItemType, String transactionRefNumber,
      String transactionType, OffsetDateTime transactedTime, String paymentType, Long txnLineItemId,
      String productName, OffsetDateTime startDate, OffsetDateTime endDate,
      String priceCategoryName, String venueName, String sectionAlias, String sectionType,
      String levelAlias, String rowAlias, Long seatNo, String description, Long quantity,
      BigDecimal unitPrice, BigDecimal bookingFee, String lineone, String linetwo, String linethree,
      String postalcode, String city, String state, String country, String deliverymethodname,
      String deliverymethodcode, Boolean addressrequired) {
    super();
    this.lineItemType = lineItemType;
    this.transactionRefNumber = transactionRefNumber;
    this.transactionType = transactionType;
    this.transactedTime = transactedTime;
    this.paymentType = paymentType;
    this.txnLineItemId = txnLineItemId;
    this.productName = productName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priceCategoryName = priceCategoryName;
    this.venueName = venueName;
    this.sectionAlias = sectionAlias;
    this.sectionType = sectionType;
    this.levelAlias = levelAlias;
    this.rowAlias = rowAlias;
    this.seatNo = seatNo;
    this.description = description;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.bookingFee = bookingFee;
    this.lineone = lineone;
    this.linetwo = linetwo;
    this.linethree = linethree;
    this.postalcode = postalcode;
    this.city = city;
    this.state = state;
    this.country = country;
    this.deliverymethodname = deliverymethodname;
    this.deliverymethodcode = deliverymethodcode;
    this.addressrequired = addressrequired;
  }



  /**
   * @return the type
   */
  @Column(name = "LINE_ITEM_TYPE", nullable = false, length = 14)
  public String getLineItemType() {
    return lineItemType;
  }

  /**
   * @param type the type to set
   */
  public void setLineItemType(String lineItemType) {
    this.lineItemType = lineItemType;
  }

  /**
   * @return the transactionId
   */
  @Column(name = "TRANSACTIONID", nullable = false, length = 14)
  public Long getTransactionId() {
    return transactionid;
  }

  /**
   * @param transactionId the transactionId to set
   */
  public void setTransactionId(Long transactionid) {
    this.transactionid = transactionid;
  }

  /**
   * @return the transactionRefNumber
   */
  @Column(name = "TRANSACTION_REF_NUMBER", nullable = false, length = 40)
  public String getTransactionRefNumber() {
    return transactionRefNumber;
  }

  /**
   * @param transactionRefNumber the transactionRefNumber to set
   */
  public void setTransactionRefNumber(String transactionRefNumber) {
    this.transactionRefNumber = transactionRefNumber;
  }

  /**
   * @return the transactionType
   */
  @Column(name = "TRANSACTION_TYPE", nullable = false, length = 255)
  public String getTransactionType() {
    return transactionType;
  }

  /**
   * @param transactionType the transactionType to set
   */
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * @return the transactedTime
   */
  @Column(name = "TRANSACTED_TIME", nullable = false, length = 100)
  public OffsetDateTime getTransactedTime() {
    return transactedTime;
  }

  /**
   * @param transactedTime the transactedTime to set
   */
  public void setTransactedTime(OffsetDateTime transactedTime) {
    this.transactedTime = transactedTime;
  }

  /**
   * @return the paymentType
   */
  @Column(name = "PAYMENT_TYPE", nullable = false, length = 255)
  public String getPaymentType() {
    return paymentType;
  }

  /**
   * @param paymentType the paymentType to set
   */
  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  /**
   * @return the txnproductid
   */
  @Column(name = "TXNPRODUCTID", nullable = true)
  public Long getTxnproductid() {
    return txnproductid;
  }

  /**
   * @param txnproductid the txnproductid to set
   */
  public void setTxnproductid(Long txnproductid) {
    this.txnproductid = txnproductid;
  }

  /**
   * @return the txnLineItemId
   */
  @Column(name = "TXN_LINE_ITEM_ID", nullable = false, length = 10)
  public Long getTxnLineItemId() {
    return txnLineItemId;
  }


  /**
   * @param txnLineItemId the txnLineItemId to set
   */
  public void setTxnLineItemId(Long txnLineItemId) {
    this.txnLineItemId = txnLineItemId;
  }

  /**
   * @return the productName
   */
  @Column(name = "PRODUCT_NAME", nullable = true, length = 255)
  public String getProductName() {
    return productName;
  }

  /**
   * @param productName the productName to set
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return the startDate
   */
  @Column(name = "START_DATE", nullable = true)
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  /**
   * @return the endDate
   */
  @Column(name = "END_DATE", nullable = true)
  public OffsetDateTime getEndDate() {
    return endDate;
  }

  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  /**
   * @return the priceCateroryName
   */
  @Column(name = "PRICE_CATEGORY_NAME", nullable = true, length = 150)
  public String getPriceCategoryName() {
    return priceCategoryName;
  }

  /**
   * @param priceCateroryName the priceCateroryName to set
   */
  public void setPriceCategoryName(String priceCateroryName) {
    this.priceCategoryName = priceCateroryName;
  }

  /**
   * @return the venueName
   */
  @Column(name = "VENUE_NAME", nullable = true, length = 255)
  public String getVenueName() {
    return venueName;
  }

  /**
   * @param venueName the venueName to set
   */
  public void setVenueName(String venueName) {
    this.venueName = venueName;
  }

  /**
   * @return the sectionAlias
   */
  @Column(name = "SECTION_ALIAS", nullable = true, length = 50)
  public String getSectionAlias() {
    return sectionAlias;
  }

  /**
   * @param sectionAlias the sectionAlias to set
   */
  public void setSectionAlias(String sectionAlias) {
    this.sectionAlias = sectionAlias;
  }

  /**
   * @return the sectionType
   */
  @Column(name = "SECTION_TYPE", nullable = true, length = 2)
  public String getSectionType() {
    return sectionType;
  }

  /**
   * @param sectionType the sectionType to set
   */
  public void setSectionType(String sectionType) {
    this.sectionType = sectionType;
  }

  /**
   * @return the levelAlias
   */
  @Column(name = "LEVEL_ALIAS", nullable = true, length = 50)
  public String getLevelAlias() {
    return levelAlias;
  }

  /**
   * @param levelAlias the levelAlias to set
   */
  public void setLevelAlias(String levelAlias) {
    this.levelAlias = levelAlias;
  }

  /**
   * @return the rowAlias
   */
  @Column(name = "ROW_ALIAS", nullable = true, length = 50)
  public String getRowAlias() {
    return rowAlias;
  }

  /**
   * @param rowAlias the rowAlias to set
   */
  public void setRowAlias(String rowAlias) {
    this.rowAlias = rowAlias;
  }

  /**
   * @return the seatNo
   */
  @Column(name = "SEAT_NO", nullable = true, length = 10)
  public Long getSeatNo() {
    return seatNo;
  }

  /**
   * @param seatNo the seatNo to set
   */
  public void setSeatNo(Long seatNo) {
    this.seatNo = seatNo;
  }

  /**
   * @return the description
   */
  @Column(name = "DESCRIPTION", nullable = false, length = 500)
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the quantity
   */
  @Column(name = "QUANTITY", nullable = true, length = 10)
  public Long getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  /**
   * @return the unitPrice
   */
  @Column(name = "UNIT_PRICE", nullable = true, length = 20, scale = 5)
  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  /**
   * @param unitPrice the unitPrice to set
   */
  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * @return the bookingFee
   */
  @Column(name = "BOOKING_FEE", nullable = false, length = 20, scale = 5)
  public BigDecimal getBookingFee() {
    return bookingFee;
  }

  /**
   * @param bookingFee the bookingFee to set
   */
  public void setBookingFee(BigDecimal bookingFee) {
    this.bookingFee = bookingFee;
  }

  /**
   * @return the patronid
   */
  @Column(name = "PATRONID", nullable = false, length = 10)
  public Long getPatronid() {
    return patronid;
  }

  /**
   * @param patronid the patronid to set
   */
  public void setPatronid(Long patronid) {
    this.patronid = patronid;
  }

  /**
   * @return the ownerprofile
   */
  @Column(name = "OWNERPROFILE", nullable = false, length = 10)
  public Long getOwnerprofile() {
    return ownerprofile;
  }

  /**
   * @param ownerprofile the ownerprofile to set
   */
  public void setOwnerprofile(Long ownerprofile) {
    this.ownerprofile = ownerprofile;
  }

  /**
   * @return the transactionid
   */
  public Long getTransactionid() {
    return transactionid;
  }

  /**
   * @param transactionid the transactionid to set
   */
  public void setTransactionid(Long transactionid) {
    this.transactionid = transactionid;
  }

  /**
   * @return the lineone
   */
  @Column(name = "LINEONE", nullable = true)
  public String getLineone() {
    return lineone;
  }

  /**
   * @param lineone the lineone to set
   */
  public void setLineone(String lineone) {
    this.lineone = lineone;
  }

  /**
   * @return the linetwo
   */
  @Column(name = "LINETWO", nullable = true)
  public String getLinetwo() {
    return linetwo;
  }

  /**
   * @param linetwo the linetwo to set
   */
  public void setLinetwo(String linetwo) {
    this.linetwo = linetwo;
  }

  /**
   * @return the linethree
   */
  @Column(name = "LINETHREE", nullable = true)
  public String getLinethree() {
    return linethree;
  }

  /**
   * @param linethree the linethree to set
   */
  public void setLinethree(String linethree) {
    this.linethree = linethree;
  }

  /**
   * @return the postalcode
   */
  @Column(name = "POSTALCODE", nullable = true)
  public String getPostalcode() {
    return postalcode;
  }

  /**
   * @param postalcode the postalcode to set
   */
  public void setPostalcode(String postalcode) {
    this.postalcode = postalcode;
  }

  /**
   * @return the city
   */
  @Column(name = "CITY", nullable = true)
  public String getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the state
   */
  @Column(name = "STATE", nullable = true)
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the country
   */
  @Column(name = "COUNTRY", nullable = true)
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return the deliverymethodname
   */
  @Column(name = "DELIVERYMETHODNAME", nullable = true)
  public String getDeliverymethodname() {
    return deliverymethodname;
  }

  /**
   * @param deliverymethodname the deliverymethodname to set
   */
  public void setDeliverymethodname(String deliverymethodname) {
    this.deliverymethodname = deliverymethodname;
  }



  /**
   * @return the deliverymethodcode
   */
  @Column(name = "DELIVERYMETHODCODE", nullable = true)
  public String getDeliverymethodcode() {
    return deliverymethodcode;
  }

  /**
   * @param deliverymethodcode the deliverymethodcode to set
   */
  public void setDeliverymethodcode(String deliverymethodcode) {
    this.deliverymethodcode = deliverymethodcode;
  }

  /**
   * @return the addressrequired
   */
  @Column(name = "ADDRESSREQUIRED", nullable = true)
  public Boolean getAddressrequired() {
    return addressrequired;
  }

  /**
   * @param addressrequired the addressrequired to set
   */
  public void setAddressrequired(Boolean addressrequired) {
    this.addressrequired = addressrequired;
  }
}

package com.stixcloud.cart;

import java.io.Serializable;

public class TransactionProductDTO implements Serializable {

  private static final long serialVersionUID = -1872505690241791594L;
  private int isReturned;
  private String prdtType;
  private String prdtDescription;
  private String showDateTime;
  private String prdtDetails;
  private String venueAlias;
  private String priceCat;
  private String priceClass;
  private String packageCode;
  private String packagename;
  private String unitPriceCurrencySign;
  private float unitPriceValue;
  private String datePrinted;
  private Long seatNum;
  private String datereprinted;
  private int qty;
  private double bookingFee;
  private double deliveryFee;
  private String eticketLink;

  //for sepang time range email confirmation
  private String sepangStartDate;
  private String sepangEndDate;
  private String sepangRangeTime;


  /**
   * Gets the checks if is returned.
   * @return the checks if is returned
   */
  public int getIsReturned() {
    return isReturned;
  }

  /**
   * Sets the checks if is returned.
   * @param isReturned the new checks if is returned
   */
  public void setIsReturned(int isReturned) {
    this.isReturned = isReturned;
  }

  /**
   * Gets the prdt type.
   * @return the prdt type
   */
  public String getPrdtType() {
    return prdtType;
  }

  /**
   * Sets the prdt type.
   * @param prdtType the new prdt type
   */
  public void setPrdtType(String prdtType) {
    this.prdtType = prdtType;
  }

  /**
   * Gets the prdt description.
   * @return the prdt description
   */
  public String getPrdtDescription() {
    return prdtDescription;
  }

  /**
   * Sets the prdt description.
   * @param prdtDescription the new prdt description
   */
  public void setPrdtDescription(String prdtDescription) {
    this.prdtDescription = prdtDescription;
  }

  /**
   * Gets the show date time.
   * @return the show date time
   */
  public String getShowDateTime() {
    return showDateTime;
  }

  /**
   * Sets the show date time.
   * @param showDateTime the new show date time
   */
  public void setShowDateTime(String showDateTime) {
    this.showDateTime = showDateTime;
  }

  /**
   * Gets the prdt details.
   * @return the prdt details
   */
  public String getPrdtDetails() {
    return prdtDetails;
  }

  /**
   * Sets the prdt details.
   * @param prdtDetails the new prdt details
   */
  public void setPrdtDetails(String prdtDetails) {
    this.prdtDetails = prdtDetails;
  }

  /**
   * Gets the price cat.
   * @return the price cat
   */
  public String getPriceCat() {
    return priceCat;
  }

  /**
   * Sets the price cat.
   * @param priceCat the new price cat
   */
  public void setPriceCat(String priceCat) {
    this.priceCat = priceCat;
  }

  /**
   * Gets the price class.
   * @return the price class
   */
  public String getPriceClass() {
    return priceClass;
  }

  /**
   * Sets the price class.
   * @param priceClass the new price class
   */
  public void setPriceClass(String priceClass) {
    this.priceClass = priceClass;
  }

  /**
   * Gets the package code.
   * @return the package code
   */
  public String getPackageCode() {
    return packageCode;
  }

  /**
   * Sets the package code.
   * @param packageCode the new package code
   */
  public void setPackageCode(String packageCode) {
    this.packageCode = packageCode;
  }

  /**
   * Gets the packagename.
   * @return the packagename
   */
  public String getPackagename() {
    return packagename;
  }

  /**
   * Sets the packagename.
   * @param packagename the new packagename
   */
  public void setPackagename(String packagename) {
    this.packagename = packagename;
  }

  /**
   * Gets the unit price currency sign.
   * @return the unit price currency sign
   */
  public String getUnitPriceCurrencySign() {
    return unitPriceCurrencySign;
  }

  /**
   * Sets the unit price currency sign.
   * @param unitPriceCurrencySign the new unit price currency sign
   */
  public void setUnitPriceCurrencySign(String unitPriceCurrencySign) {
    this.unitPriceCurrencySign = unitPriceCurrencySign;
  }

  /**
   * Gets the unit price value.
   * @return the unit price value
   */
  public float getUnitPriceValue() {
    return unitPriceValue;
  }

  /**
   * Sets the unit price value.
   * @param unitPriceValue the new unit price value
   */
  public void setUnitPriceValue(float unitPriceValue) {
    this.unitPriceValue = unitPriceValue;
  }

  /**
   * Gets the date printed.
   * @return the date printed
   */
  public String getDatePrinted() {
    return datePrinted;
  }

  /**
   * Sets the date printed.
   * @param datePrinted the new date printed
   */
  public void setDatePrinted(String datePrinted) {
    this.datePrinted = datePrinted;
  }

  /**
   * Gets the datereprinted.
   * @return the datereprinted
   */
  public String getDatereprinted() {
    return datereprinted;
  }

  /**
   * Sets the datereprinted.
   * @param datereprinted the new datereprinted
   */
  public void setDatereprinted(String datereprinted) {
    this.datereprinted = datereprinted;
  }

  public String getVenueAlias() {
    return venueAlias;
  }

  public void setVenueAlias(String venueAlias) {
    this.venueAlias = venueAlias;
  }

  public int getQty() {
    return qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }

  public double getBookingFee() {
    return bookingFee;
  }

  public void setBookingFee(double bookingFee) {
    this.bookingFee = bookingFee;
  }

  public double getDeliveryFee() {
    return deliveryFee;
  }

  public void setDeliveryFee(double deliveryFee) {
    this.deliveryFee = deliveryFee;
  }

  public Long getSeatNum() {
    return seatNum;
  }

  public void setSeatNum(Long seatNum) {
    this.seatNum = seatNum;
  }

  public String getEticketLink() {
    return eticketLink;
  }

  public void setEticketLink(String eticketLink) {
    this.eticketLink = eticketLink;
  }

  public String getSepangStartDate() {
    return sepangStartDate;
  }

  public void setSepangStartDate(String sepangStartDate) {
    this.sepangStartDate = sepangStartDate;
  }

  public String getSepangEndDate() {
    return sepangEndDate;
  }

  public void setSepangEndDate(String sepangEndDate) {
    this.sepangEndDate = sepangEndDate;
  }

  public String getSepangRangeTime() {
    return sepangRangeTime;
  }

  public void setSepangRangeTime(String sepangRangeTime) {
    this.sepangRangeTime = sepangRangeTime;
  }


}

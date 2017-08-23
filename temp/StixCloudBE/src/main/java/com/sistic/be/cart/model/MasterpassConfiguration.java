package com.sistic.be.cart.model;

public class MasterpassConfiguration {

  private String consumerKey;
  private String checkoutIdentifier;
  private String p12CertFileName;
  private String keyPassword;
  private String keyAlias;
  private String checkOutCallbackUrl;
  private String pairingCallbackUrl;

  public MasterpassConfiguration() {

  }

  public MasterpassConfiguration( String consumerKey, String checkoutIdentifier,
      String p12CertFileName, String keyPassword, String keyAlias, String checkOutCallbackUrl,
      String pairingCallbackUrl) {

    this.consumerKey = consumerKey;
    this.checkoutIdentifier = checkoutIdentifier;
    this.p12CertFileName = p12CertFileName;
    this.keyPassword = keyPassword;
    this.keyAlias = keyAlias;
    this.checkOutCallbackUrl = checkOutCallbackUrl;
    this.pairingCallbackUrl = pairingCallbackUrl;
  }
  
  /**
   * @return The consumerKey.
   */
  public String getConsumerKey() {
    return consumerKey;
  }

  /**
   * @param consumerKey The consumerKey
   */
  public void setConsumerKey(String consumerKey) {
    this.consumerKey = consumerKey;
  }

  /**
   * @return The checkoutIdentifier.
   */
  public String getCheckoutIdentifier() {
    return checkoutIdentifier;
  }

  /**
   * @param checkoutIdentifier The checkoutIdentifier
   */
  public void setCheckoutIdentifier(String checkoutIdentifier) {
    this.checkoutIdentifier = checkoutIdentifier;
  }

  /**
   * @return The p12CertFileName.
   */
  public String getP12CertFileName() {
    return p12CertFileName;
  }

  /**
   * @param p12CertFileName The p12CertFileName
   */
  public void setP12CertFileName(String p12CertFileName) {
    this.p12CertFileName = p12CertFileName;
  }

  /**
   * @return The keyPassword.
   */
  public String getKeyPassword() {
    return keyPassword;
  }

  /**
   * @param keyPassword The keyPassword
   */
  public void setKeyPassword(String keyPassword) {
    this.keyPassword = keyPassword;
  }

  /**
   * @return The keyAlias.
   */
  public String getKeyAlias() {
    return keyAlias;
  }

  /**
   * @param keyAlias The keyAlias
   */
  public void setKeyAlias(String keyAlias) {
    this.keyAlias = keyAlias;
  }

  /**
   * @return The checkOutCallbackUrl.
   */
  public String getCheckOutCallbackUrl() {
    return checkOutCallbackUrl;
  }

  /**
   * @param checkOutCallbackUrl The checkOutCallbackUrl
   */
  public void setCheckOutCallbackUrl(String checkOutCallbackUrl) {
    this.checkOutCallbackUrl = checkOutCallbackUrl;
  }

  /**
   * @return The pairingCallbackUrl.
   */
  public String getPairingCallbackUrl() {
    return pairingCallbackUrl;
  }

  /**
   * @param pairingCallbackUrl The pairingCallbackUrl
   */
  public void setPairingCallbackUrl(String pairingCallbackUrl) {
    this.pairingCallbackUrl = pairingCallbackUrl;
  }

}
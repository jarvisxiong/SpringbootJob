package com.stixcloud.masterpass.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"consumerKey", "checkoutIdentifier", "p12CertFileName", "keyAlias",
    "keyPassword", "checkOutCallbackUrl", "pairingCallbackUrl"})
public class MasterpassConfigurationResponse {

  @JsonProperty("consumerKey")
  private String consumerKey;
  @JsonProperty("checkoutIdentifier")
  private String checkoutIdentifier;
  @JsonProperty("p12CertFileName")
  private String p12CertFileName;
  @JsonProperty("keyPassword")
  private String keyPassword;
  @JsonProperty("keyAlias")
  private String keyAlias;
  @JsonProperty("checkOutCallbackUrl")
  private String checkOutCallbackUrl;
  @JsonProperty("pairingCallbackUrl")
  private String pairingCallbackUrl;

  public MasterpassConfigurationResponse() {

  }

  public MasterpassConfigurationResponse(String consumerKey, String checkoutIdentifier,
                                         String p12CertFileName, String keyPassword,
                                         String keyAlias, String checkOutCallbackUrl,
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("consumerKey", consumerKey)
        .append("checkoutIdentifier", checkoutIdentifier).append("p12CertFileName", p12CertFileName)
        .append("keyPassword", keyPassword).append("keyAlias", keyAlias)
        .append("checkOutCallbackUrl", checkOutCallbackUrl)
        .append("pairingCallbackUrl", pairingCallbackUrl).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MasterpassConfigurationResponse that = (MasterpassConfigurationResponse) o;
    return new EqualsBuilder().append(checkoutIdentifier, that.getCheckoutIdentifier())
        .append(consumerKey, that.getConsumerKey())
        .append(p12CertFileName, that.getP12CertFileName())
        .append(keyPassword, that.getKeyPassword()).append(keyAlias, that.getKeyAlias())
        .append(checkOutCallbackUrl, that.getCheckOutCallbackUrl())
        .append(pairingCallbackUrl, that.getPairingCallbackUrl()).isEquals();
  }
}

package com.stixcloud.masterpass.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MASTERPASS_CONFIGURATION")
public class MasterpassConfiguration implements Serializable {

  private static final long serialVersionUID = -3105169519596126964L;

  private Long masterpassconfigurationid;
  private String tenantName;
  private String consumerKey;
  private String merchantIdentifierId;
  private String certificateFileName;
  private String keystorePassword;
  private String keystoreAlias;
  private String checkoutCallbackUrl;
  private String pairingCallbackUrl;
  private Date createdate;
  private Date updatedate;
  private Integer createdBy;
  private Integer updatedBy;

  public MasterpassConfiguration() {

  }

  public MasterpassConfiguration(Long masterpassconfigurationid, String tenantName,
                                 String consumerKey, String merchantIdentifierId,
                                 String certificateFileName,
                                 String keystorePassword, String keystoreAlias,
                                 String checkoutCallbackUrl,
                                 String pairingCallbackUrl, Date createdate, Date updatedate,
                                 Integer createdBy,
                                 Integer updatedBy) {
    this.masterpassconfigurationid = masterpassconfigurationid;
    this.tenantName = tenantName;
    this.consumerKey = consumerKey;
    this.merchantIdentifierId = merchantIdentifierId;
    this.certificateFileName = certificateFileName;
    this.keystorePassword = keystorePassword;
    this.keystoreAlias = keystoreAlias;
    this.checkoutCallbackUrl = checkoutCallbackUrl;
    this.pairingCallbackUrl = pairingCallbackUrl;
    this.createdate = createdate;
    this.updatedate = updatedate;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;

  }

  /**
   * @return The masterpassconfigurationid.
   */
  @Id
  @Column(name = "MASTERPASSCONFIGURATIONID", nullable = false, length = 10)
  public Long getMasterpassconfigurationid() {
    return masterpassconfigurationid;
  }

  /**
   * @param masterpassconfigurationid The masterpassconfigurationid
   */
  public void setMasterpassconfigurationid(Long masterpassconfigurationid) {
    this.masterpassconfigurationid = masterpassconfigurationid;
  }

  /**
   * @return The tenantName.
   */
  @Column(name = "TENANT_NAME", nullable = false, length = 100)
  public String getTenantName() {
    return tenantName;
  }

  /**
   * @param tenantName The tenantName
   */
  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  /**
   * @return The consumerKey.
   */
  @Column(name = "CONSUMER_KEY", nullable = false, length = 500)
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
   * @return The merchantIdentifierId.
   */
  @Column(name = "MERCHANT_IDENTIFIER_ID", nullable = false, length = 200)
  public String getMerchantIdentifierId() {
    return merchantIdentifierId;
  }

  /**
   * @param merchantIdentifierId The merchantIdentifierId
   */
  public void setMerchantIdentifierId(String merchantIdentifierId) {
    this.merchantIdentifierId = merchantIdentifierId;
  }

  /**
   * @return The certificateFileName.
   */
  @Column(name = "CERTIFICATE_FILE_NAME", nullable = false, length = 200)
  public String getCertificateFileName() {
    return certificateFileName;
  }

  /**
   * @param certificateFileName The certificateFileName
   */
  public void setCertificateFileName(String certificateFileName) {
    this.certificateFileName = certificateFileName;
  }

  /**
   * @return The keystorePassword.
   */
  @Column(name = "KEY_STORE_PASSWORD", nullable = false, length = 200)
  public String getKeystorePassword() {
    return keystorePassword;
  }

  /**
   * @param keystorePassword The keystorePassword
   */
  public void setKeystorePassword(String keystorePassword) {
    this.keystorePassword = keystorePassword;
  }

  /**
   * @return The keystoreAlias.
   */
  @Column(name = "KEY_STORE_ALIAS", nullable = false, length = 200)
  public String getKeystoreAlias() {
    return keystoreAlias;
  }

  /**
   * @param keystoreAlias The keystoreAlias
   */
  public void setKeystoreAlias(String keystoreAlias) {
    this.keystoreAlias = keystoreAlias;
  }

  /**
   * @return The checkoutCallbackUrl.
   */
  @Column(name = "CHECKOUT_CALLBACK_URL", nullable = false, length = 500)
  public String getCheckoutCallbackUrl() {
    return checkoutCallbackUrl;
  }

  /**
   * @param checkoutCallbackUrl The checkoutCallbackUrl
   */
  public void setCheckoutCallbackUrl(String checkoutCallbackUrl) {
    this.checkoutCallbackUrl = checkoutCallbackUrl;
  }

  /**
   * @return The pairingCallbackUrl.
   */
  @Column(name = "PAIRING_CALLBACK_URL", nullable = false, length = 500)
  public String getPairingCallbackUrl() {
    return pairingCallbackUrl;
  }

  /**
   * @param pairingCallbackUrl The pairingCallbackUrl
   */
  public void setPairingCallbackUrl(String pairingCallbackUrl) {
    this.pairingCallbackUrl = pairingCallbackUrl;
  }

  /**
   * @return The createdate.
   */
  @Column(name = "CREATEDATE", nullable = false)
  public Date getCreatedate() {
    return createdate;
  }

  /**
   * @param createdate The createdate
   */
  public void setCreatedate(Date createdate) {
    this.createdate = createdate;
  }

  /**
   * @return The updatedate.
   */
  @Column(name = "UPDATEDATE", nullable = false)
  public Date getUpdatedate() {
    return updatedate;
  }

  /**
   * @param updatedate The updatedate
   */
  public void setUpdatedate(Date updatedate) {
    this.updatedate = updatedate;
  }

  /**
   * @return The createdBy.
   */
  @Column(name = "CREATED_BY", nullable = false, precision = 10)
  public Integer getCreatedBy() {
    return createdBy;
  }

  /**
   * @param createdBy The createdBy
   */
  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * @return The updatedBy.
   */
  @Column(name = "UPDATED_BY", nullable = false, precision = 10)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  /**
   * @param The updatedBy.
   */
  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("masterpassconfigurationid", masterpassconfigurationid)
        .append("tenantName", tenantName).append("consumerKey", consumerKey)
        .append("merchantIdentifierId", merchantIdentifierId)
        .append("certificateFileName", certificateFileName)
        .append("keystorePassword", keystorePassword).append("keystoreAlias", keystoreAlias)
        .append("checkoutCallbackUrl", checkoutCallbackUrl)
        .append("pairingCallbackUrl", pairingCallbackUrl).append("createdate", createdate)
        .append("updatedate", updatedate).append("createdBy", createdBy)
        .append("updatedBy", updatedBy).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MasterpassConfiguration that = (MasterpassConfiguration) o;
    return new EqualsBuilder()
        .append(masterpassconfigurationid, that.getMasterpassconfigurationid())
        .append(tenantName, that.getTenantName())
        .append(merchantIdentifierId, that.getMerchantIdentifierId())
        .append(consumerKey, that.getConsumerKey())
        .append(certificateFileName, that.getCertificateFileName())
        .append(keystorePassword, that.getKeystorePassword())
        .append(keystoreAlias, that.getKeystoreAlias())
        .append(checkoutCallbackUrl, that.getCheckoutCallbackUrl())
        .append(pairingCallbackUrl, that.getPairingCallbackUrl())
        .append(updatedate, that.getUpdatedate()).append(createdate, that.getCreatedate())
        .append(createdBy, that.getCreatedBy()).append(updatedBy, that.getUpdatedBy()).isEquals();
  }
}

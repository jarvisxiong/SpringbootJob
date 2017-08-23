package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Cacheable
@Entity
@Table(name = "FEE_ATTRIBUTES")
public class FeeAttributes implements Serializable {

  private static final long serialVersionUID = 5364980219496201598L;
  private Long feeattributeid;
  private Integer chargetype;
  private Boolean isproductprice;
  private String channel;
  private Integer taxstatus;
  private Boolean includeticketprice;
  private Long methodCode;
  private Long feeVersionHistoryId;
  private Long levyByMctId;
  private Long payerMctId;
  private Long payeeMctId;
  private Set<FeeChargeRule> feeChargeRules = new HashSet<>(0);

  public FeeAttributes() {
  }

  @Id
  @Column(name = "FEEATTRIBUTEID", nullable = false, precision = 0)
  public Long getFeeattributeid() {
    return feeattributeid;
  }

  public void setFeeattributeid(Long feeattributeid) {
    this.feeattributeid = feeattributeid;
  }

  @Column(name = "CHARGETYPE", nullable = false, precision = 0)
  public Integer getChargetype() {
    return chargetype;
  }

  public void setChargetype(Integer chargetype) {
    this.chargetype = chargetype;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "ISPRODUCTPRICE", nullable = true, precision = 0)
  public Boolean getIsproductprice() {
    return isproductprice;
  }

  public void setIsproductprice(Boolean isproductprice) {
    this.isproductprice = isproductprice;
  }

  @Column(name = "CHANNEL", nullable = true, length = 25)
  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  @Column(name = "TAXSTATUS", nullable = true, precision = 0)
  public Integer getTaxstatus() {
    return taxstatus;
  }

  public void setTaxstatus(Integer taxstatus) {
    this.taxstatus = taxstatus;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "INCLUDETICKETPRICE", nullable = true, precision = 0)
  public Boolean getIncludeticketprice() {
    return includeticketprice;
  }

  public void setIncludeticketprice(Boolean includeticketprice) {
    this.includeticketprice = includeticketprice;
  }

  @Column(name = "METHOD_CODE", nullable = true, precision = 0)
  public Long getMethodCode() {
    return methodCode;
  }

  public void setMethodCode(Long methodCode) {
    this.methodCode = methodCode;
  }

  @Column(name = "FEE_VERSION_HISTORY_ID", nullable = false, precision = 0)
  public Long getFeeVersionHistoryId() {
    return feeVersionHistoryId;
  }

  public void setFeeVersionHistoryId(Long feeVersionHistoryId) {
    this.feeVersionHistoryId = feeVersionHistoryId;
  }

  @Column(name = "LEVY_BY_MCT_ID", nullable = false, precision = 0)
  public Long getLevyByMctId() {
    return levyByMctId;
  }

  public void setLevyByMctId(Long levyByMctId) {
    this.levyByMctId = levyByMctId;
  }

  @Column(name = "PAYER_MCT_ID", nullable = false, precision = 0)
  public Long getPayerMctId() {
    return payerMctId;
  }

  public void setPayerMctId(Long payerMctId) {
    this.payerMctId = payerMctId;
  }

  @Column(name = "PAYEE_MCT_ID", nullable = false, precision = 0)
  public Long getPayeeMctId() {
    return payeeMctId;
  }

  public void setPayeeMctId(Long payeeMctId) {
    this.payeeMctId = payeeMctId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeAttributes that = (FeeAttributes) o;

    return Objects.equals(this.feeattributeid, that.feeattributeid) &&
        Objects.equals(this.chargetype, that.chargetype) &&
        Objects.equals(this.isproductprice, that.isproductprice) &&
        Objects.equals(this.channel, that.channel) &&
        Objects.equals(this.taxstatus, that.taxstatus) &&
        Objects.equals(this.includeticketprice, that.includeticketprice) &&
        Objects.equals(this.methodCode, that.methodCode) &&
        Objects.equals(this.feeVersionHistoryId, that.feeVersionHistoryId) &&
        Objects.equals(this.levyByMctId, that.levyByMctId) &&
        Objects.equals(this.payerMctId, that.payerMctId) &&
        Objects.equals(this.payeeMctId, that.payeeMctId) &&
        Objects.equals(this.feeChargeRules, that.feeChargeRules);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(feeattributeid, chargetype, isproductprice, channel, taxstatus, includeticketprice,
            methodCode, feeVersionHistoryId, levyByMctId, payerMctId, payeeMctId,
            feeChargeRules);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feeattributeid", feeattributeid)
        .append("chargetype", chargetype)
        .append("isproductprice", isproductprice)
        .append("channel", channel)
        .append("taxstatus", taxstatus)
        .append("includeticketprice", includeticketprice)
        .append("methodCode", methodCode)
        .append("feeVersionHistoryId", feeVersionHistoryId)
        .append("levyByMctId", levyByMctId)
        .append("payerMctId", payerMctId)
        .append("payeeMctId", payeeMctId)
        .append("feeChargeRules", feeChargeRules)
        .toString();
  }
}

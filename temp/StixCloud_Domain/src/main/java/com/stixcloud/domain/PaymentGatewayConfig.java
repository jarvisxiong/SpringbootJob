package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 3/1/2017.
 */
@Entity
@Table(name = "PAYMENT_GATEWAY_CONFIG")
public class PaymentGatewayConfig implements Serializable {
  private Long configid;
  private String paymentgatewayname;
  private String paymentGatewayStatus;
  private Long revenuecenterid;
  private String revenuecentercode;
  private String configurationname;
  private String configurationvalue;
  private String paymentmethodcode;
  private Long profileInfoId;
  
  @Id
  @Column(name = "CONFIGID", nullable = false, precision = 0)
  public Long getConfigid() {
    return configid;
  }

  public void setConfigid(Long rownum) {
    this.configid = rownum;
  }

  @Column(name = "PAYMENTGATEWAYNAME", nullable = true, length = 50)
  public String getPaymentgatewayname() {
    return paymentgatewayname;
  }

  public void setPaymentgatewayname(String paymentgatewayname) {
    this.paymentgatewayname = paymentgatewayname;
  }

  @Column(name = "PAYMENTGATEWAYSTATUS", nullable = true, length = 50)
  public String getPaymentGatewayStatus() {
    return paymentGatewayStatus;
  }

  public void setPaymentGatewayStatus(String paymentGatewayStatus) {
    this.paymentGatewayStatus = paymentGatewayStatus;
  }

  @Column(name = "REVENUECENTERID", nullable = true, precision = 0)
  public Long getRevenuecenterid() {
    return revenuecenterid;
  }

  public void setRevenuecenterid(Long revenuecenterid) {
    this.revenuecenterid = revenuecenterid;
  }

  @Column(name = "REVENUECENTERCODE", nullable = true, length = 50)
  public String getRevenuecentercode() {
    return revenuecentercode;
  }

  public void setRevenuecentercode(String revenuecentercode) {
    this.revenuecentercode = revenuecentercode;
  }

  @Column(name = "CONFIGURATIONNAME", nullable = true, length = 128)
  public String getConfigurationname() {
    return configurationname;
  }

  public void setConfigurationname(String configurationname) {
    this.configurationname = configurationname;
  }

  @Column(name = "CONFIGURATIONVALUE", nullable = true, length = 128)
  public String getConfigurationvalue() {
    return configurationvalue;
  }

  public void setConfigurationvalue(String configurationvalue) {
    this.configurationvalue = configurationvalue;
  }

  @Column(name = "PAYMENTMETHODCODE", nullable = true, length = 128)
  public String getPaymentmethodcode() {
    return paymentmethodcode;
  }

  public void setPaymentmethodcode(String paymentmethodcode) {
    this.paymentmethodcode = paymentmethodcode;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0) 
  public Long getProfileInfoId() {
	return profileInfoId;
}

public void setProfileInfoId(Long profileInfoId) {
	this.profileInfoId = profileInfoId;
}

@Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentGatewayConfig that = (PaymentGatewayConfig) o;

    return new EqualsBuilder()
        .append(paymentgatewayname, that.paymentgatewayname)
        .append(paymentGatewayStatus, that.paymentGatewayStatus)
        .append(revenuecenterid, that.revenuecenterid)
        .append(revenuecentercode, that.revenuecentercode)
        .append(configurationname, that.configurationname)
        .append(configurationvalue, that.configurationvalue)
        .append(paymentmethodcode, that.paymentmethodcode)
        .append(profileInfoId, that.profileInfoId)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(paymentgatewayname)
        .append(paymentGatewayStatus)
        .append(revenuecenterid)
        .append(revenuecentercode)
        .append(configurationname)
        .append(configurationvalue)
        .append(paymentmethodcode)
        .append(profileInfoId)
        .toHashCode();
  }
}

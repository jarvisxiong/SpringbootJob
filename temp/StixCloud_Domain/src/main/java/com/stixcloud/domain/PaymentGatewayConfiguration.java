package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 11/16/2016.
 */
@Entity
@Table(name = "PAYMENT_GATEWAY_CONFIGURATION")
public class PaymentGatewayConfiguration implements Serializable {

  private static final long serialVersionUID = -2995456334406809815L;
  private Long configurationid;
  private String configurationvalue;
  private Long configurationId;
  private Long paymentGatewayId;

  public PaymentGatewayConfiguration() {
  }

  public PaymentGatewayConfiguration(Long configurationid, String configurationvalue,
                                     Long configurationId, Long paymentGatewayId) {
    this.configurationid = configurationid;
    this.configurationvalue = configurationvalue;
    this.configurationId = configurationId;
    this.paymentGatewayId = paymentGatewayId;
  }

  @Id
  @Column(name = "CONFIGURATIONID", nullable = false, precision = 0)
  public Long getConfigurationid() {
    return configurationid;
  }

  public void setConfigurationid(Long configurationid) {
    this.configurationid = configurationid;
  }

  @Column(name = "CONFIGURATIONVALUE", nullable = true, length = 128)
  public String getConfigurationvalue() {
    return configurationvalue;
  }

  public void setConfigurationvalue(String configurationvalue) {
    this.configurationvalue = configurationvalue;
  }

  @Column(name = "CONFIGURATION_ID", nullable = false, precision = 0)
  public Long getConfigurationId() {
    return configurationId;
  }

  public void setConfigurationId(Long configurationId) {
    this.configurationId = configurationId;
  }

  @Column(name = "PAYMENT_GATEWAY_ID", nullable = false, precision = 0)
  public Long getPaymentGatewayId() {
    return paymentGatewayId;
  }

  public void setPaymentGatewayId(Long paymentGatewayId) {
    this.paymentGatewayId = paymentGatewayId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentGatewayConfiguration that = (PaymentGatewayConfiguration) o;
    return Objects.equals(configurationid, that.configurationid) &&
        Objects.equals(configurationvalue, that.configurationvalue) &&
        Objects.equals(configurationId, that.configurationId) &&
        Objects.equals(paymentGatewayId, that.paymentGatewayId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(configurationid, configurationvalue, configurationId, paymentGatewayId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("configurationid", configurationid)
        .append("configurationvalue", configurationvalue)
        .append("configurationId", configurationId)
        .append("paymentGatewayId", paymentGatewayId)
        .toString();
  }
}

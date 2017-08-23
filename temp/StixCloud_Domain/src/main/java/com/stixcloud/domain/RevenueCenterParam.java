package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 11/17/2016.
 */
@Entity
@Table(name = "REVENUE_CENTER_PARAM")
public class RevenueCenterParam {
  private Long revenuecenterparamid;
  private String configurationvalue;
  private Long revenueCenterId;
  private Long configurationId;

  public RevenueCenterParam() {
  }

  public RevenueCenterParam(Long revenuecenterparamid, String configurationvalue,
                            Long revenueCenterId, Long configurationId) {
    this.revenuecenterparamid = revenuecenterparamid;
    this.configurationvalue = configurationvalue;
    this.revenueCenterId = revenueCenterId;
    this.configurationId = configurationId;
  }

  @Id
  @Column(name = "REVENUECENTERPARAMID", nullable = false, precision = 0)
  public Long getRevenuecenterparamid() {
    return revenuecenterparamid;
  }

  public void setRevenuecenterparamid(Long revenuecenterparamid) {
    this.revenuecenterparamid = revenuecenterparamid;
  }

  @Column(name = "CONFIGURATIONVALUE", nullable = true, length = 128)
  public String getConfigurationvalue() {
    return configurationvalue;
  }

  public void setConfigurationvalue(String configurationvalue) {
    this.configurationvalue = configurationvalue;
  }

  @Column(name = "REVENUE_CENTER_ID", nullable = false, precision = 0)
  public Long getRevenueCenterId() {
    return revenueCenterId;
  }

  public void setRevenueCenterId(Long revenueCenterId) {
    this.revenueCenterId = revenueCenterId;
  }

  @Column(name = "CONFIGURATION_ID", nullable = false, precision = 0)
  public Long getConfigurationId() {
    return configurationId;
  }

  public void setConfigurationId(Long configurationId) {
    this.configurationId = configurationId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RevenueCenterParam that = (RevenueCenterParam) o;
    return Objects.equals(revenuecenterparamid, that.revenuecenterparamid) &&
        Objects.equals(configurationvalue, that.configurationvalue) &&
        Objects.equals(revenueCenterId, that.revenueCenterId) &&
        Objects.equals(configurationId, that.configurationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(revenuecenterparamid, configurationvalue, revenueCenterId, configurationId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("revenuecenterparamid", revenuecenterparamid)
        .append("configurationvalue", configurationvalue)
        .append("revenueCenterId", revenueCenterId)
        .append("configurationId", configurationId)
        .toString();
  }
}

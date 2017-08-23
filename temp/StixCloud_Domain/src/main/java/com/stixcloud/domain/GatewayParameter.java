package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 11/16/2016.
 */
@Entity
@Table(name = "GATEWAY_PARAMETER")
public class GatewayParameter implements Serializable {

  private static final long serialVersionUID = 8783912668601761189L;
  private Long gatewayparameterid;
  private String configurationname;
  private Boolean parametertype;
  private Integer createdBy;
  private OffsetDateTime createddate;
  private Integer updatedBy;
  private OffsetDateTime updateddate;

  public GatewayParameter() {
  }

  public GatewayParameter(Long gatewayparameterid, String configurationname,
                          Boolean parametertype, Integer createdBy,
                          OffsetDateTime createddate, Integer updatedBy,
                          OffsetDateTime updateddate) {
    this.gatewayparameterid = gatewayparameterid;
    this.configurationname = configurationname;
    this.parametertype = parametertype;
    this.createdBy = createdBy;
    this.createddate = createddate;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
  }

  @Id
  @Column(name = "GATEWAYPARAMETERID", nullable = false, precision = 0)
  public Long getGatewayparameterid() {
    return gatewayparameterid;
  }

  public void setGatewayparameterid(Long gatewayparameterid) {
    this.gatewayparameterid = gatewayparameterid;
  }

  @Column(name = "CONFIGURATIONNAME", nullable = false, length = 128)
  public String getConfigurationname() {
    return configurationname;
  }

  public void setConfigurationname(String configurationname) {
    this.configurationname = configurationname;
  }

  @Column(name = "PARAMETERTYPE", nullable = false, precision = 0)
  public Boolean getParametertype() {
    return parametertype;
  }

  public void setParametertype(Boolean parametertype) {
    this.parametertype = parametertype;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GatewayParameter that = (GatewayParameter) o;
    return Objects.equals(gatewayparameterid, that.gatewayparameterid) &&
        Objects.equals(configurationname, that.configurationname) &&
        Objects.equals(parametertype, that.parametertype) &&
        Objects.equals(createdBy, that.createdBy) &&
        Objects.equals(createddate, that.createddate) &&
        Objects.equals(updatedBy, that.updatedBy) &&
        Objects.equals(updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(gatewayparameterid, configurationname, parametertype, createdBy, createddate,
            updatedBy,
            updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("gatewayparameterid", gatewayparameterid)
        .append("configurationname", configurationname)
        .append("parametertype", parametertype)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}

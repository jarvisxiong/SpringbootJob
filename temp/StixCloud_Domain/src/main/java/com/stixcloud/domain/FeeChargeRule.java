package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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
@Table(name = "FEE_CHARGE_RULE")
public class FeeChargeRule implements Serializable {

  private static final long serialVersionUID = -9055506573750856335L;
  private Long feechargeruleid;
  private Integer ruletype;
  private BigDecimal operandone;
  private BigDecimal operandtwo;
  private String operatorone;
  private String operatortwo;
  private Boolean striptaxstatus;
  private Long feeAttributeId;
  private Long taxClassId;
  private Boolean chargeruleattribute;
  private BigDecimal chargedvalue;

  public FeeChargeRule() {
  }

  @Id
  @Column(name = "FEECHARGERULEID", nullable = false, precision = 0)
  public Long getFeechargeruleid() {
    return feechargeruleid;
  }

  public void setFeechargeruleid(Long feechargeruleid) {
    this.feechargeruleid = feechargeruleid;
  }

  @Column(name = "RULETYPE", nullable = true, precision = 0)
  public Integer getRuletype() {
    return ruletype;
  }

  public void setRuletype(Integer ruletype) {
    this.ruletype = ruletype;
  }

  @Column(name = "OPERANDONE", nullable = true, precision = 2)
  public BigDecimal getOperandone() {
    return operandone;
  }

  public void setOperandone(BigDecimal operandone) {
    this.operandone = operandone;
  }

  @Column(name = "OPERANDTWO", nullable = true, precision = 2)
  public BigDecimal getOperandtwo() {
    return operandtwo;
  }

  public void setOperandtwo(BigDecimal operandtwo) {
    this.operandtwo = operandtwo;
  }

  @Column(name = "OPERATORONE", nullable = true, length = 2)
  public String getOperatorone() {
    return operatorone;
  }

  public void setOperatorone(String operatorone) {
    this.operatorone = operatorone;
  }

  @Column(name = "OPERATORTWO", nullable = true, length = 2)
  public String getOperatortwo() {
    return operatortwo;
  }

  public void setOperatortwo(String operatortwo) {
    this.operatortwo = operatortwo;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "STRIPTAXSTATUS", nullable = true, precision = 0)
  public Boolean getStriptaxstatus() {
    return striptaxstatus;
  }

  public void setStriptaxstatus(Boolean striptaxstatus) {
    this.striptaxstatus = striptaxstatus;
  }

  @Column(name = "FEE_ATTRIBUTE_ID", nullable = false, precision = 0)
  public Long getFeeAttributeId() {
    return feeAttributeId;
  }

  public void setFeeAttributeId(Long feeAttributeId) {
    this.feeAttributeId = feeAttributeId;
  }

  @Column(name = "TAX_CLASS_ID", nullable = true, precision = 0)
  public Long getTaxClassId() {
    return taxClassId;
  }

  public void setTaxClassId(Long taxClassId) {
    this.taxClassId = taxClassId;
  }

  @Column(name = "CHARGERULEATTRIBUTE", nullable = true, precision = 0)
  public Boolean getChargeruleattribute() {
    return chargeruleattribute;
  }

  public void setChargeruleattribute(Boolean chargeruleattribute) {
    this.chargeruleattribute = chargeruleattribute;
  }

  @Column(name = "CHARGEDVALUE", nullable = true, precision = 20, scale = 10)
  public BigDecimal getChargedvalue() {
    return chargedvalue;
  }

  public void setChargedvalue(BigDecimal chargedvalue) {
    this.chargedvalue = chargedvalue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeChargeRule that = (FeeChargeRule) o;

    return Objects.equals(this.feechargeruleid, that.feechargeruleid) &&
        Objects.equals(this.ruletype, that.ruletype) &&
        Objects.equals(this.operandone, that.operandone) &&
        Objects.equals(this.operandtwo, that.operandtwo) &&
        Objects.equals(this.operatorone, that.operatorone) &&
        Objects.equals(this.operatortwo, that.operatortwo) &&
        Objects.equals(this.striptaxstatus, that.striptaxstatus) &&
        Objects.equals(this.feeAttributeId, that.feeAttributeId) &&
        Objects.equals(this.taxClassId, that.taxClassId) &&
        Objects.equals(this.chargeruleattribute, that.chargeruleattribute) &&
        Objects.equals(this.chargedvalue, that.chargedvalue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feechargeruleid, ruletype, operandone, operandtwo, operatorone, operatortwo,
        striptaxstatus, feeAttributeId, taxClassId, chargeruleattribute, chargedvalue);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feechargeruleid", feechargeruleid)
        .append("ruletype", ruletype)
        .append("operandone", operandone)
        .append("operandtwo", operandtwo)
        .append("operatorone", operatorone)
        .append("operatortwo", operatortwo)
        .append("striptaxstatus", striptaxstatus)
        .append("feeAttributeId", feeAttributeId)
        .append("taxClassId", taxClassId)
        .append("chargeruleattribute", chargeruleattribute)
        .append("chargedvalue", chargedvalue)
        .toString();
  }
}

package com.stixcloud.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@Entity
public class PromoPassword implements Serializable {
  @Id
  private Long rownum;

  private static final long serialVersionUID = -1526878084202839239L;
  private Long priceClassId;
  private Long promotionPasswordId;
  private Long passwordRegexId;
  private Integer maxUsageFrequency;
  private Integer numberUsage;
  private String regexPattern;
  private Integer status;
  private String passwordUsage;
  private String priceClassName;

  public String getPriceClassName() {
	return priceClassName;
  }

  public void setPriceClassName(String priceClassName) {
	this.priceClassName = priceClassName;
  }

  public Long getPriceClassId() {
	return priceClassId;
  }

  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  public Long getPromotionPasswordId() {
    return promotionPasswordId;
  }

  public void setPromotionPasswordId(Long promotionPasswordId) {
    this.promotionPasswordId = promotionPasswordId;
  }

  public Long getPasswordRegexId() {
    return passwordRegexId;
  }

  public void setPasswordRegexId(Long passwordRegexId) {
    this.passwordRegexId = passwordRegexId;
  }

  public Integer getMaxUsageFrequency() {
    return maxUsageFrequency;
  }

  public void setMaxUsageFrequency(Integer maxUsageFrequency) {
    this.maxUsageFrequency = maxUsageFrequency;
  }

  public Integer getNumberUsage() {
    return numberUsage;
  }

  public void setNumberUsage(Integer numberUsage) {
    this.numberUsage = numberUsage;
  }

  public String getRegexPattern() {
    return regexPattern;
  }

  public void setRegexPattern(String regexPattern) {
    this.regexPattern = regexPattern;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getPasswordUsage() {
    return passwordUsage;
  }

  public void setPasswordUsage(String passwordUsage) {
    this.passwordUsage = passwordUsage;
  }
  
  public PromoPassword() {
    
  }
  
  public PromoPassword(Long priceClassId, Long promotionPasswordId, Long passwordRegexId, Integer maxUsageFrequency, Integer numberUsage,
      String regexPattern, Integer status, String passwordUsage, String priceClassName) {
    this.priceClassId = priceClassId;
    this.promotionPasswordId = promotionPasswordId;
    this.passwordRegexId = passwordRegexId;
    this.maxUsageFrequency = maxUsageFrequency;
    this.numberUsage = numberUsage;
    this.regexPattern = regexPattern;
    this.status = status;
    this.passwordUsage = passwordUsage;
    this.priceClassName = priceClassName;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PromoPassword that = (PromoPassword) o;
    return Objects.equals(priceClassId, that.priceClassId)
        && Objects.equals(maxUsageFrequency, that.maxUsageFrequency)
        && Objects.equals(promotionPasswordId, that.promotionPasswordId)
        && Objects.equals(numberUsage, that.numberUsage)
        && Objects.equals(regexPattern, that.regexPattern) 
        && Objects.equals(status, that.status)
        && Objects.equals(passwordUsage, that.passwordUsage)
        && Objects.equals(priceClassName, that.priceClassName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priceClassId, promotionPasswordId, maxUsageFrequency, numberUsage,
        regexPattern, status, passwordUsage, priceClassName);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("priceClassId", priceClassId)
        .append("promotionPasswordId", promotionPasswordId)
        .append("maxUsageFrequency", maxUsageFrequency).append("numberUsage", numberUsage)
        .append("regexPattern", regexPattern).append("status", status)
        .append("passwordUsageId", passwordUsage)
        .append("priceClassName", priceClassName)
        .toString();
  }
}

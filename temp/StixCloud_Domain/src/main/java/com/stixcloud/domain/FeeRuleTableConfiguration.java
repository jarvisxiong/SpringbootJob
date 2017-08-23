package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
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
@Table(name = "FEE_RULE_TABLE_CONFIGURATION")
public class FeeRuleTableConfiguration implements Serializable {

  private static final long serialVersionUID = 7603349758390442218L;
  private Long feeruletableconfigid;
  private Long feerulesequence;
  private Long feeRuleTableId;
  private Long feeRuleId;

  public FeeRuleTableConfiguration() {
  }

  @Id
  @Column(name = "FEERULETABLECONFIGID", nullable = false, precision = 0)
  public Long getFeeruletableconfigid() {
    return feeruletableconfigid;
  }

  public void setFeeruletableconfigid(Long feeruletableconfigid) {
    this.feeruletableconfigid = feeruletableconfigid;
  }

  @Column(name = "FEERULESEQUENCE", nullable = false, precision = 0)
  public Long getFeerulesequence() {
    return feerulesequence;
  }

  public void setFeerulesequence(Long feerulesequence) {
    this.feerulesequence = feerulesequence;
  }

  @Column(name = "FEE_RULE_TABLE_ID", nullable = false, precision = 0)
  public Long getFeeRuleTableId() {
    return feeRuleTableId;
  }

  public void setFeeRuleTableId(Long feeRuleTableId) {
    this.feeRuleTableId = feeRuleTableId;
  }

  @Column(name = "FEE_RULE_ID", nullable = false, precision = 0)
  public Long getFeeRuleId() {
    return feeRuleId;
  }

  public void setFeeRuleId(Long feeRuleId) {
    this.feeRuleId = feeRuleId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeRuleTableConfiguration that = (FeeRuleTableConfiguration) o;

    return Objects.equals(this.feeruletableconfigid, that.feeruletableconfigid) &&
        Objects.equals(this.feerulesequence, that.feerulesequence) &&
        Objects.equals(this.feeRuleTableId, that.feeRuleTableId) &&
        Objects.equals(this.feeRuleId, that.feeRuleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feeruletableconfigid, feerulesequence, feeRuleTableId, feeRuleId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feeruletableconfigid", feeruletableconfigid)
        .append("feerulesequence", feerulesequence)
        .append("feeRuleTableId", feeRuleTableId)
        .append("feeRuleId", feeRuleId)
        .toString();
  }
}

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
@Table(name = "FEE_RULE_FEE_MAPPING")
public class FeeRuleFeeMapping implements Serializable {

  private static final long serialVersionUID = 8082636870096303672L;
  private Long feerulefeemappingid;
  private Long feeRuleId;
  private Long feeId;

  public FeeRuleFeeMapping() {
  }

  @Id
  @Column(name = "FEERULEFEEMAPPINGID", nullable = false, precision = 0)
  public Long getFeerulefeemappingid() {
    return feerulefeemappingid;
  }

  public void setFeerulefeemappingid(Long feerulefeemappingid) {
    this.feerulefeemappingid = feerulefeemappingid;
  }

  @Column(name = "FEE_RULE_ID", nullable = false, precision = 0)
  public Long getFeeRuleId() {
    return feeRuleId;
  }

  public void setFeeRuleId(Long feeRuleId) {
    this.feeRuleId = feeRuleId;
  }

  @Column(name = "FEE_ID", nullable = false, precision = 0)
  public Long getFeeId() {
    return feeId;
  }

  public void setFeeId(Long feeId) {
    this.feeId = feeId;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feerulefeemappingid", feerulefeemappingid)
        .append("feeRuleId", feeRuleId)
        .append("feeId", feeId)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeRuleFeeMapping that = (FeeRuleFeeMapping) o;

    return Objects.equals(this.feerulefeemappingid, that.feerulefeemappingid) &&
        Objects.equals(this.feeRuleId, that.feeRuleId) &&
        Objects.equals(this.feeId, that.feeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feerulefeemappingid, feeRuleId, feeId);
  }

}

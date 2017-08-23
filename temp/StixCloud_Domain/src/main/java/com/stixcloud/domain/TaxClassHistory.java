package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 6/1/2017.
 */
@Entity
@Table(name = "TAX_CLASS_HISTORY")
public class TaxClassHistory {
  private Long taxclasshistoryid;
  private BigDecimal value;
  private OffsetDateTime effectivedate;
  private OffsetDateTime expirydate;
  private BigDecimal version;
  private Long taxClassId;

  @Id
  @Column(name = "TAXCLASSHISTORYID", nullable = false, precision = 0)
  public Long getTaxclasshistoryid() {
    return taxclasshistoryid;
  }

  public void setTaxclasshistoryid(Long taxclasshistoryid) {
    this.taxclasshistoryid = taxclasshistoryid;
  }

  @Column(name = "VALUE", nullable = true, precision = 2)
  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  @Column(name = "EFFECTIVEDATE", nullable = false)
  public OffsetDateTime getEffectivedate() {
    return effectivedate;
  }

  public void setEffectivedate(OffsetDateTime effectivedate) {
    this.effectivedate = effectivedate;
  }

  @Column(name = "EXPIRYDATE", nullable = false)
  public OffsetDateTime getExpirydate() {
    return expirydate;
  }

  public void setExpirydate(OffsetDateTime expirydate) {
    this.expirydate = expirydate;
  }

  @Column(name = "VERSION", nullable = false, precision = 0)
  public BigDecimal getVersion() {
    return version;
  }

  public void setVersion(BigDecimal version) {
    this.version = version;
  }

  @Column(name = "TAX_CLASS_ID", nullable = false, precision = 0)
  public Long getTaxClassId() {
    return taxClassId;
  }

  public void setTaxClassId(Long taxClassId) {
    this.taxClassId = taxClassId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TaxClassHistory that = (TaxClassHistory) o;

    return Objects.equals(this.effectivedate, that.effectivedate) &&
        Objects.equals(this.expirydate, that.expirydate) &&
        Objects.equals(this.taxclasshistoryid, that.taxclasshistoryid) &&
        Objects.equals(this.taxClassId, that.taxClassId) &&
        Objects.equals(this.value, that.value) &&
        Objects.equals(this.version, that.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(effectivedate, expirydate, taxclasshistoryid, taxClassId, value, version);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("taxclasshistoryid", taxclasshistoryid)
        .append("value", value)
        .append("effectivedate", effectivedate)
        .append("expirydate", expirydate)
        .append("version", version)
        .append("taxClassId", taxClassId)
        .toString();
  }
}

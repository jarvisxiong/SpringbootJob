package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
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
@Table(name = "FEE_VERSION_HISTORY")
public class FeeVersionHistory implements Serializable {

  private static final long serialVersionUID = -3803307381640849428L;
  private Long feeversionhistoryid;
  private Long version;
  private OffsetDateTime effectivestartdate;
  private OffsetDateTime effectiveenddate;
  private Long feeId;

  public FeeVersionHistory() {
  }

  @Id
  @Column(name = "FEEVERSIONHISTORYID", nullable = false, precision = 0)
  public Long getFeeversionhistoryid() {
    return feeversionhistoryid;
  }

  public void setFeeversionhistoryid(Long feeversionhistoryid) {
    this.feeversionhistoryid = feeversionhistoryid;
  }

  @Column(name = "VERSION", nullable = false, precision = 0)
  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  @Column(name = "EFFECTIVESTARTDATE", nullable = false)
  public OffsetDateTime getEffectivestartdate() {
    return effectivestartdate;
  }

  public void setEffectivestartdate(OffsetDateTime effectivestartdate) {
    this.effectivestartdate = effectivestartdate;
  }

  @Column(name = "EFFECTIVEENDDATE", nullable = false)
  public OffsetDateTime getEffectiveenddate() {
    return effectiveenddate;
  }

  public void setEffectiveenddate(OffsetDateTime effectiveenddate) {
    this.effectiveenddate = effectiveenddate;
  }

  @Column(name = "FEE_ID", nullable = false, precision = 0)
  public Long getFeeId() {
    return feeId;
  }

  public void setFeeId(Long feeId) {
    this.feeId = feeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FeeVersionHistory that = (FeeVersionHistory) o;

    return Objects.equals(this.feeversionhistoryid, that.feeversionhistoryid) &&
        Objects.equals(this.version, that.version) &&
        Objects.equals(this.effectivestartdate, that.effectivestartdate) &&
        Objects.equals(this.effectiveenddate, that.effectiveenddate) &&
        Objects.equals(this.feeId, that.feeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feeversionhistoryid, version, effectivestartdate, effectiveenddate, feeId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("feeversionhistoryid", feeversionhistoryid)
        .append("version", version)
        .append("effectivestartdate", effectivestartdate)
        .append("effectiveenddate", effectiveenddate)
        .append("feeId", feeId)
        .toString();
  }
}

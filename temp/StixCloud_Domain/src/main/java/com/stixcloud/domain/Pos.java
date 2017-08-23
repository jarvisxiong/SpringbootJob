package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chongye on 21/12/2016.
 */
@Entity
public class Pos {
  private Long posid;
  private String posname;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private Long organizationId;
  private Long areaId;
  private String isagent;

  @Id
  @Column(name = "POSID", nullable = false, precision = 0)
  public Long getPosid() {
    return posid;
  }

  public void setPosid(Long posid) {
    this.posid = posid;
  }

  @Column(name = "POSNAME", nullable = true, length = 255)
  public String getPosname() {
    return posname;
  }

  public void setPosname(String posname) {
    this.posname = posname;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = true)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "ORGANIZATION_ID", nullable = false, precision = 0)
  public Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  @Column(name = "AREA_ID", nullable = false, precision = 0)
  public Long getAreaId() {
    return areaId;
  }

  public void setAreaId(Long areaId) {
    this.areaId = areaId;
  }

  @Column(name = "ISAGENT", nullable = true, length = 1)
  public String getIsagent() {
    return isagent;
  }

  public void setIsagent(String isagent) {
    this.isagent = isagent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Pos pos = (Pos) o;

    return new EqualsBuilder()
        .append(posid, pos.posid)
        .append(posname, pos.posname)
        .append(createdBy, pos.createdBy)
        .append(createddate, pos.createddate)
        .append(updatedBy, pos.updatedBy)
        .append(updateddate, pos.updateddate)
        .append(organizationId, pos.organizationId)
        .append(areaId, pos.areaId)
        .append(isagent, pos.isagent)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(posid)
        .append(posname)
        .append(createdBy)
        .append(createddate)
        .append(updatedBy)
        .append(updateddate)
        .append(organizationId)
        .append(areaId)
        .append(isagent)
        .toHashCode();
  }
}

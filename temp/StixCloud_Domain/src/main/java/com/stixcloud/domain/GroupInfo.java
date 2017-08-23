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
 * Created by chongye on 20/10/2016.
 */
@Cacheable
@Entity
@Table(name = "GROUP_INFO")
public class GroupInfo implements Serializable {

  private static final long serialVersionUID = -8408280465146898174L;
  private Long groupinfoid;
  private String groupname;
  private String groupcode;
  private Integer status;
  private Long groupTypeId;
  private String isdefault;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;

  public GroupInfo() {
  }

  @Id
  @Column(name = "GROUPINFOID", nullable = false, precision = 0)
  public Long getGroupinfoid() {
    return groupinfoid;
  }

  public void setGroupinfoid(Long groupinfoid) {
    this.groupinfoid = groupinfoid;
  }

  @Column(name = "GROUPNAME", nullable = false, length = 255)
  public String getGroupname() {
    return groupname;
  }

  public void setGroupname(String groupname) {
    this.groupname = groupname;
  }

  @Column(name = "GROUPCODE", nullable = false, length = 255)
  public String getGroupcode() {
    return groupcode;
  }

  public void setGroupcode(String groupcode) {
    this.groupcode = groupcode;
  }

  @Column(name = "STATUS", nullable = false, precision = 0)
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Column(name = "GROUP_TYPE_ID", nullable = false, precision = 0)
  public Long getGroupTypeId() {
    return groupTypeId;
  }

  public void setGroupTypeId(Long groupTypeId) {
    this.groupTypeId = groupTypeId;
  }

  @Column(name = "ISDEFAULT", nullable = true, length = 1)
  public String getIsdefault() {
    return isdefault;
  }

  public void setIsdefault(String isdefault) {
    this.isdefault = isdefault;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GroupInfo that = (GroupInfo) o;

    return Objects.equals(this.groupinfoid, that.groupinfoid) &&
        Objects.equals(this.groupname, that.groupname) &&
        Objects.equals(this.groupcode, that.groupcode) &&
        Objects.equals(this.status, that.status) &&
        Objects.equals(this.groupTypeId, that.groupTypeId) &&
        Objects.equals(this.isdefault, that.isdefault) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupinfoid, groupname, groupcode, status, groupTypeId, isdefault,
        createdBy, createddate, updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("groupinfoid", groupinfoid)
        .append("groupname", groupname)
        .append("groupcode", groupcode)
        .append("status", status)
        .append("groupTypeId", groupTypeId)
        .append("isdefault", isdefault)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .toString();
  }
}

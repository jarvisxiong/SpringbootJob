package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

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
@Table(name = "MASTER_CODE_TABLE")
public class MasterCodeTable implements Serializable {

  private static final long serialVersionUID = -2289501007747818648L;
  private Long mastercodeid;
  private String categorycode;
  private Boolean status;
  private String description1;
  private String description2;

  public MasterCodeTable() {
  }

  public MasterCodeTable(Long mastercodeid, String categorycode, Boolean status,
      String description1, String description2) {
    this.mastercodeid = mastercodeid;
    this.categorycode = categorycode;
    this.status = status;
    this.description1 = description1;
    this.description2 = description2;
  }
  
  @Id
  @Column(name = "MASTERCODEID", nullable = false, precision = 0)
  public Long getMastercodeid() {
    return mastercodeid;
  }

  public void setMastercodeid(Long mastercodeid) {
    this.mastercodeid = mastercodeid;
  }

  @Column(name = "CATEGORYCODE", nullable = false, length = 50)
  public String getCategorycode() {
    return categorycode;
  }

  public void setCategorycode(String categorycode) {
    this.categorycode = categorycode;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "STATUS", nullable = false, precision = 0)
  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Column(name = "DESCRIPTION1", nullable = true, length = 255)
  public String getDescription1() {
    return description1;
  }

  public void setDescription1(String description1) {
    this.description1 = description1;
  }

  @Column(name = "DESCRIPTION2", nullable = true, length = 255)
  public String getDescription2() {
    return description2;
  }

  public void setDescription2(String description2) {
    this.description2 = description2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MasterCodeTable that = (MasterCodeTable) o;

    return Objects.equals(this.mastercodeid, that.mastercodeid) &&
        Objects.equals(this.categorycode, that.categorycode) &&
        Objects.equals(this.status, that.status) &&
        Objects.equals(this.description1, that.description1) &&
        Objects.equals(this.description2, that.description2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mastercodeid, categorycode, status, description1, description2);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("mastercodeid", mastercodeid)
        .append("categorycode", categorycode)
        .append("status", status)
        .append("description1", description1)
        .append("description2", description2)
        .toString();
  }
}

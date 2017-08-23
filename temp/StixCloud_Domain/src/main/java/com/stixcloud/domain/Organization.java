package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 12/6/2016.
 */
@Entity
@Table(name = "ORGANIZATION")
public class Organization {
  private Long organizationid;
  private String organizationname;
  private String registrationno;
  private String istenant;
  private String keyalias;
  private String encryptionalgorithm;
  private String organizationurl;
  private OffsetDateTime createddate;
  private Integer createdBy;
  private OffsetDateTime updateddate;
  private Integer updatedBy;

  public Organization() {
  }

  public Organization(Long organizationid, String organizationname, String registrationno,
                      String istenant, String keyalias, String encryptionalgorithm,
                      String organizationurl, OffsetDateTime createddate, Integer createdBy,
                      OffsetDateTime updateddate, Integer updatedBy) {
    this.organizationid = organizationid;
    this.organizationname = organizationname;
    this.registrationno = registrationno;
    this.istenant = istenant;
    this.keyalias = keyalias;
    this.encryptionalgorithm = encryptionalgorithm;
    this.organizationurl = organizationurl;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "ORGANIZATIONID", nullable = false, precision = 0)
  public Long getOrganizationid() {
    return organizationid;
  }

  public void setOrganizationid(Long organizationid) {
    this.organizationid = organizationid;
  }

  @Column(name = "ORGANIZATIONNAME", nullable = false, length = 255)
  public String getOrganizationname() {
    return organizationname;
  }

  public void setOrganizationname(String organizationname) {
    this.organizationname = organizationname;
  }

  @Column(name = "REGISTRATIONNO", nullable = true, length = 50)
  public String getRegistrationno() {
    return registrationno;
  }

  public void setRegistrationno(String registrationno) {
    this.registrationno = registrationno;
  }

  @Column(name = "ISTENANT", nullable = true, length = 1)
  public String getIstenant() {
    return istenant;
  }

  public void setIstenant(String istenant) {
    this.istenant = istenant;
  }

  @Column(name = "KEYALIAS", nullable = true, length = 255)
  public String getKeyalias() {
    return keyalias;
  }

  public void setKeyalias(String keyalias) {
    this.keyalias = keyalias;
  }

  @Column(name = "ENCRYPTIONALGORITHM", nullable = true, length = 255)
  public String getEncryptionalgorithm() {
    return encryptionalgorithm;
  }

  public void setEncryptionalgorithm(String encryptionalgorithm) {
    this.encryptionalgorithm = encryptionalgorithm;
  }

  @Column(name = "ORGANIZATIONURL", nullable = true, length = 500)
  public String getOrganizationurl() {
    return organizationurl;
  }

  public void setOrganizationurl(String organizationurl) {
    this.organizationurl = organizationurl;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Organization that = (Organization) o;
    return Objects.equals(organizationid, that.organizationid) &&
        Objects.equals(organizationname, that.organizationname) &&
        Objects.equals(registrationno, that.registrationno) &&
        Objects.equals(istenant, that.istenant) &&
        Objects.equals(keyalias, that.keyalias) &&
        Objects.equals(encryptionalgorithm, that.encryptionalgorithm) &&
        Objects.equals(organizationurl, that.organizationurl) &&
        Objects.equals(createddate, that.createddate) &&
        Objects.equals(createdBy, that.createdBy) &&
        Objects.equals(updateddate, that.updateddate) &&
        Objects.equals(updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organizationid, organizationname, registrationno, istenant, keyalias,
        encryptionalgorithm, organizationurl, createddate, createdBy, updateddate, updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("organizationid", organizationid)
        .append("organizationname", organizationname)
        .append("registrationno", registrationno)
        .append("istenant", istenant)
        .append("keyalias", keyalias)
        .append("encryptionalgorithm", encryptionalgorithm)
        .append("organizationurl", organizationurl)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}

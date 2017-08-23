package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 2/10/2017.
 */
@Entity
@Table(name = "LOCATION")
public class Location implements Serializable {
  private Long locationid;
  private Long organizationId;
  private String locationname;
  private String printablelocationname;
  private String displayalias;
  private String address;
  private String remarks;
  private String locationalias;
  private String mailaddress;
  private String status;
  private OffsetDateTime updateddate;
  private Integer updatedBy;
  private OffsetDateTime createddate;
  private Integer createdBy;
  private Long latitude;
  private Long longitude;

  public Location() {
  }

  public Location(Long locationid, Long organizationId, String locationname,
                  String printablelocationname, String displayalias, String address,
                  String remarks, String locationalias, String mailaddress, String status,
                  OffsetDateTime updateddate, Integer updatedBy, OffsetDateTime createddate,
                  Integer createdBy, Long latitude, Long longitude) {
    this.locationid = locationid;
    this.organizationId = organizationId;
    this.locationname = locationname;
    this.printablelocationname = printablelocationname;
    this.displayalias = displayalias;
    this.address = address;
    this.remarks = remarks;
    this.locationalias = locationalias;
    this.mailaddress = mailaddress;
    this.status = status;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @Id
  @Column(name = "LOCATIONID", nullable = false, precision = 0)
  public Long getLocationid() {
    return locationid;
  }

  public void setLocationid(Long locationid) {
    this.locationid = locationid;
  }

  @Column(name = "ORGANIZATION_ID", nullable = true, precision = 0)
  public Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  @Column(name = "LOCATIONNAME", nullable = true, length = 255)
  public String getLocationname() {
    return locationname;
  }

  public void setLocationname(String locationname) {
    this.locationname = locationname;
  }

  @Column(name = "PRINTABLELOCATIONNAME", nullable = true, length = 255)
  public String getPrintablelocationname() {
    return printablelocationname;
  }

  public void setPrintablelocationname(String printablelocationname) {
    this.printablelocationname = printablelocationname;
  }

  @Column(name = "DISPLAYALIAS", nullable = true, length = 255)
  public String getDisplayalias() {
    return displayalias;
  }

  public void setDisplayalias(String displayalias) {
    this.displayalias = displayalias;
  }

  @Column(name = "ADDRESS", nullable = true, length = 1000)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "REMARKS", nullable = true, length = 4000)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "LOCATIONALIAS", nullable = true, length = 255)
  public String getLocationalias() {
    return locationalias;
  }

  public void setLocationalias(String locationalias) {
    this.locationalias = locationalias;
  }

  @Column(name = "MAILADDRESS", nullable = true, length = 255)
  public String getMailaddress() {
    return mailaddress;
  }

  public void setMailaddress(String mailaddress) {
    this.mailaddress = mailaddress;
  }

  @Column(name = "STATUS", nullable = true, length = 1)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  @Column(name = "CREATEDDATE", nullable = true)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "CREATED_BY", nullable = true, precision = 0)
  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "LATITUDE", nullable = true, precision = 6)
  public Long getLatitude() {
    return latitude;
  }

  public void setLatitude(Long latitude) {
    this.latitude = latitude;
  }

  @Column(name = "LONGITUDE", nullable = true, precision = 6)
  public Long getLongitude() {
    return longitude;
  }

  public void setLongitude(Long longitude) {
    this.longitude = longitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(locationid, location.locationid) &&
        Objects.equals(organizationId, location.organizationId) &&
        Objects.equals(locationname, location.locationname) &&
        Objects.equals(printablelocationname, location.printablelocationname) &&
        Objects.equals(displayalias, location.displayalias) &&
        Objects.equals(address, location.address) &&
        Objects.equals(remarks, location.remarks) &&
        Objects.equals(locationalias, location.locationalias) &&
        Objects.equals(mailaddress, location.mailaddress) &&
        Objects.equals(status, location.status) &&
        Objects.equals(updateddate, location.updateddate) &&
        Objects.equals(updatedBy, location.updatedBy) &&
        Objects.equals(createddate, location.createddate) &&
        Objects.equals(createdBy, location.createdBy) &&
        Objects.equals(latitude, location.latitude) &&
        Objects.equals(longitude, location.longitude);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(locationid, organizationId, locationname, printablelocationname, displayalias,
            address,
            remarks, locationalias, mailaddress, status, updateddate, updatedBy, createddate,
            createdBy, latitude, longitude);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("locationid", locationid)
        .append("organizationId", organizationId)
        .append("locationname", locationname)
        .append("printablelocationname", printablelocationname)
        .append("displayalias", displayalias)
        .append("address", address)
        .append("remarks", remarks)
        .append("locationalias", locationalias)
        .append("mailaddress", mailaddress)
        .append("status", status)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("latitude", latitude)
        .append("longitude", longitude)
        .toString();
  }
}

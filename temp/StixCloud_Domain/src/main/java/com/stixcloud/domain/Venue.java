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
 * Created by sengkai on 2/9/2017.
 */
@Entity
@Table(name = "VENUE")
public class Venue implements Serializable {
  private Long venueid;
  private Long locationId;
  private String venuename;
  private String venuealias;
  private Integer venuecapacity;
  private String remarks;
  private Integer updatedBy;
  private OffsetDateTime updateddate;
  private OffsetDateTime createddate;
  private Integer createdBy;
  private Boolean status;
  private String venueurl;

  public Venue() {
  }

  public Venue(Long venueid, Long locationId, String venuename, String venuealias,
               Integer venuecapacity, String remarks, Integer updatedBy,
               OffsetDateTime updateddate, OffsetDateTime createddate, Integer createdBy,
               Boolean status, String venueurl) {

    this.venueid = venueid;
    this.locationId = locationId;
    this.venuename = venuename;
    this.venuealias = venuealias;
    this.venuecapacity = venuecapacity;
    this.remarks = remarks;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
    this.createddate = createddate;
    this.createdBy = createdBy;
    this.status = status;
    this.venueurl = venueurl;
  }

  @Id
  @Column(name = "VENUEID", nullable = false, precision = 0)
  public Long getVenueid() {
    return venueid;
  }

  public void setVenueid(Long venueid) {
    this.venueid = venueid;
  }

  @Column(name = "LOCATION_ID", nullable = false, precision = 0)
  public Long getLocationId() {
    return locationId;
  }

  public void setLocationId(Long locationId) {
    this.locationId = locationId;
  }

  @Column(name = "VENUENAME", nullable = true, length = 255)
  public String getVenuename() {
    return venuename;
  }

  public void setVenuename(String venuename) {
    this.venuename = venuename;
  }

  @Column(name = "VENUEALIAS", nullable = true, length = 255)
  public String getVenuealias() {
    return venuealias;
  }

  public void setVenuealias(String venuealias) {
    this.venuealias = venuealias;
  }

  @Column(name = "VENUECAPACITY", nullable = true, precision = 0)
  public Integer getVenuecapacity() {
    return venuecapacity;
  }

  public void setVenuecapacity(Integer venuecapacity) {
    this.venuecapacity = venuecapacity;
  }

  @Column(name = "REMARKS", nullable = true, length = 4000)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
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

  @Column(name = "STATUS", nullable = true, precision = 0)
  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Column(name = "VENUEURL", nullable = true, length = 500)
  public String getVenueurl() {
    return venueurl;
  }

  public void setVenueurl(String venueurl) {
    this.venueurl = venueurl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Venue venue = (Venue) o;
    return Objects.equals(venueid, venue.venueid) &&
        Objects.equals(locationId, venue.locationId) &&
        Objects.equals(venuename, venue.venuename) &&
        Objects.equals(venuealias, venue.venuealias) &&
        Objects.equals(venuecapacity, venue.venuecapacity) &&
        Objects.equals(remarks, venue.remarks) &&
        Objects.equals(updatedBy, venue.updatedBy) &&
        Objects.equals(updateddate, venue.updateddate) &&
        Objects.equals(createddate, venue.createddate) &&
        Objects.equals(createdBy, venue.createdBy) &&
        Objects.equals(status, venue.status) &&
        Objects.equals(venueurl, venue.venueurl);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(venueid, locationId, venuename, venuealias, venuecapacity, remarks, updatedBy,
            updateddate, createddate, createdBy, status, venueurl);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venueid", venueid)
        .append("locationId", locationId)
        .append("venuename", venuename)
        .append("venuealias", venuealias)
        .append("venuecapacity", venuecapacity)
        .append("remarks", remarks)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .append("createddate", createddate)
        .append("createdBy", createdBy)
        .append("status", status)
        .append("venueurl", venueurl)
        .toString();
  }
}

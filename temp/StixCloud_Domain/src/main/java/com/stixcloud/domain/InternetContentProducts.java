package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 12-Aug-16.
 */
@Entity
@Table(name = "INTERNET_CONTENT_PRODUCTS")
public class InternetContentProducts implements Serializable {
  @Id
  private String code;
  @Id
  private long productid;
  @Id
  private String productname;
  @Id
  private OffsetDateTime startdate;
  @Id
  private String venuename;
  @Id
  private Long profileInfoId;
  @Id
  private int availablityStatus;
  private String summaryimagepath;
  private String icattributes;

  public InternetContentProducts() {
  }

  public InternetContentProducts(String code, long productid, String productname,
                                 OffsetDateTime startdate, String venuename, Long profileInfoId,
                                 int availablityStatus, String summaryimagepath,
                                 String icattributes) {
    this.code = code;
    this.productid = productid;
    this.productname = productname;
    this.startdate = startdate;
    this.venuename = venuename;
    this.profileInfoId = profileInfoId;
    this.availablityStatus = availablityStatus;
    this.summaryimagepath = summaryimagepath;
    this.icattributes = icattributes;
  }

  @Column(name = "CODE", nullable = false, length = 50)
  public String getCode() {
    return code;
  }

  @Column(name = "PRODUCTID", nullable = false, length = 50)
  public long getProductid() {
    return productid;
  }

  @Column(name = "PRODUCTNAME", nullable = false, length = 255)
  public String getProductname() {
    return productname;
  }

  @Column(name = "STARTDATE", nullable = false)
  public OffsetDateTime getStartdate() {
    return startdate;
  }

  @Column(name = "VENUENAME", nullable = false, length = 255)
  public String getVenuename() {
    return venuename;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  @Column(name = "AVAILABLITY_STATUS", nullable = false, precision = 0)
  public int getAvailablityStatus() {
    return availablityStatus;
  }

  @Column(name = "SUMMARYIMAGEPATH", nullable = true, length = 255)
  public String getSummaryimagepath() {
    return summaryimagepath;
  }

  @Column(name = "ICATTRIBUTES", nullable = true, length = 4000)
  public String getIcattributes() {
    return icattributes;
  }

  public void setAvailablityStatus(int availablityStatus) {
    this.availablityStatus = availablityStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof InternetContentProducts)) {
      return false;
    }

    InternetContentProducts that = (InternetContentProducts) o;

    return new EqualsBuilder()
        .append(getProductid(), that.getProductid())
        .append(getAvailablityStatus(), that.getAvailablityStatus())
        .append(getCode(), that.getCode())
        .append(getProductname(), that.getProductname())
        .append(getStartdate(), that.getStartdate())
        .append(getVenuename(), that.getVenuename())
        .append(getProfileInfoId(), that.getProfileInfoId())
        .append(getSummaryimagepath(), that.getSummaryimagepath())
        .append(getIcattributes(), that.getIcattributes())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getCode())
        .append(getProductid())
        .append(getProductname())
        .append(getStartdate())
        .append(getVenuename())
        .append(getProfileInfoId())
        .append(getAvailablityStatus())
        .append(getSummaryimagepath())
        .append(getIcattributes())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("code", code)
        .append("productid", productid)
        .append("productname", productname)
        .append("startdate", startdate)
        .append("venuename", venuename)
        .append("profileInfoId", profileInfoId)
        .append("availablityStatus", availablityStatus)
        .append("summaryImagePath", summaryimagepath)
        .append("icAttributes", icattributes)
        .toString();
  }

}

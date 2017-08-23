package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "PROFILE_INFO")
public class ProfileInfo {
  private Long profileinfoid;
  private String profilecode;
  private String profilename;
  private Boolean sellerstatus;
  private Long profileChannelId;
  private String ispaymentoffline;
  private Integer paymentmode;
  private String immediateprintingstatus;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private String usethermalprinter;

  public ProfileInfo() {
  }

  @Id
  @Column(name = "PROFILEINFOID", nullable = false, precision = 0)
  public Long getProfileinfoid() {
    return profileinfoid;
  }

  public void setProfileinfoid(Long profileinfoid) {
    this.profileinfoid = profileinfoid;
  }

  @Column(name = "PROFILECODE", nullable = true, length = 50)
  public String getProfilecode() {
    return profilecode;
  }

  public void setProfilecode(String profilecode) {
    this.profilecode = profilecode;
  }

  @Column(name = "PROFILENAME", nullable = false, length = 50)
  public String getProfilename() {
    return profilename;
  }

  public void setProfilename(String profilename) {
    this.profilename = profilename;
  }

  @Type(type = "yes_no")
  @Column(name = "SELLERSTATUS", nullable = false, length = 1)
  public Boolean getSellerstatus() {
    return sellerstatus;
  }

  public void setSellerstatus(Boolean sellerstatus) {
    this.sellerstatus = sellerstatus;
  }

  @Column(name = "PROFILE_CHANNEL_ID", nullable = true, precision = 0)
  public Long getProfileChannelId() {
    return profileChannelId;
  }

  public void setProfileChannelId(Long profileChannelId) {
    this.profileChannelId = profileChannelId;
  }

  @Column(name = "ISPAYMENTOFFLINE", nullable = false, length = 1)
  public String getIspaymentoffline() {
    return ispaymentoffline;
  }

  public void setIspaymentoffline(String ispaymentoffline) {
    this.ispaymentoffline = ispaymentoffline;
  }

  @Column(name = "PAYMENTMODE", nullable = true, precision = 0)
  public Integer getPaymentmode() {
    return paymentmode;
  }

  public void setPaymentmode(Integer paymentmode) {
    this.paymentmode = paymentmode;
  }

  @Type(type = "yes_no")
  @Column(name = "IMMEDIATEPRINTINGSTATUS", nullable = true, length = 1)
  public String getImmediateprintingstatus() {
    return immediateprintingstatus;
  }

  public void setImmediateprintingstatus(String immediateprintingstatus) {
    this.immediateprintingstatus = immediateprintingstatus;
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

  @Column(name = "USETHERMALPRINTER", nullable = false, length = 1)
  public String getUsethermalprinter() {
    return usethermalprinter;
  }

  public void setUsethermalprinter(String usethermalprinter) {
    this.usethermalprinter = usethermalprinter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProfileInfo that = (ProfileInfo) o;

    return Objects.equals(this.profileinfoid, that.profileinfoid) &&
        Objects.equals(this.profilecode, that.profilecode) &&
        Objects.equals(this.profilename, that.profilename) &&
        Objects.equals(this.sellerstatus, that.sellerstatus) &&
        Objects.equals(this.profileChannelId, that.profileChannelId) &&
        Objects.equals(this.ispaymentoffline, that.ispaymentoffline) &&
        Objects.equals(this.paymentmode, that.paymentmode) &&
        Objects.equals(this.immediateprintingstatus, that.immediateprintingstatus) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.usethermalprinter, that.usethermalprinter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profileinfoid, profilecode, profilename, sellerstatus, profileChannelId,
        ispaymentoffline,
        paymentmode, immediateprintingstatus, createdBy, createddate, updatedBy,
        updateddate, usethermalprinter);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("profileinfoid", profileinfoid)
        .append("profilecode", profilecode)
        .append("profilename", profilename)
        .append("sellerstatus", sellerstatus)
        .append("profileChannelId", profileChannelId)
        .append("ispaymentoffline", ispaymentoffline)
        .append("paymentmode", paymentmode)
        .append("immediateprintingstatus", immediateprintingstatus)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .append("usethermalprinter", usethermalprinter)
        .toString();
  }
}

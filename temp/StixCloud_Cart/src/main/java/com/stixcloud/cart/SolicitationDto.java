package com.stixcloud.cart;

import com.stixcloud.domain.PatronProfile;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * Created by sengkai on 12/6/2016.
 */
public class SolicitationDto {

  private PatronProfile patronProfile;
  private String solicitationType;
  private Long organizationid;
  private String organizationname;
  private String organizationurl;
  private Boolean subscriptionstatus;

  public SolicitationDto() {
  }

  public SolicitationDto(PatronProfile patronProfile, String solicitationType,
                         Long organizationid, String organizationname,
                         String organizationurl, Boolean subscriptionstatus) {
    this.patronProfile = patronProfile;
    this.solicitationType = solicitationType;
    this.organizationid = organizationid;
    this.organizationname = organizationname;
    this.organizationurl = organizationurl;
    this.subscriptionstatus = subscriptionstatus;
  }

  public SolicitationDto(String solicitationType, Long organizationid,
                         String organizationname, String organizationurl,
                         Boolean subscriptionstatus) {
    this.solicitationType = solicitationType;
    this.organizationid = organizationid;
    this.organizationname = organizationname;
    this.organizationurl = organizationurl;
    this.subscriptionstatus = subscriptionstatus;
  }

  public PatronProfile getPatronProfile() {
    return patronProfile;
  }

  public void setPatronProfile(PatronProfile patronProfile) {
    this.patronProfile = patronProfile;
  }

  public String getSolicitationType() {
    return solicitationType;
  }

  public void setSolicitationType(String solicitationType) {
    this.solicitationType = solicitationType;
  }

  public Long getOrganizationid() {
    return organizationid;
  }

  public void setOrganizationid(Long organizationid) {
    this.organizationid = organizationid;
  }

  public String getOrganizationname() {
    return organizationname;
  }

  public void setOrganizationname(String organizationname) {
    this.organizationname = organizationname;
  }

  public String getOrganizationurl() {
    return organizationurl;
  }

  public void setOrganizationurl(String organizationurl) {
    this.organizationurl = organizationurl;
  }

  public Boolean getSubscriptionstatus() {
    return subscriptionstatus;
  }

  public void setSubscriptionstatus(Boolean subscriptionstatus) {
    this.subscriptionstatus = subscriptionstatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SolicitationDto that = (SolicitationDto) o;
    return Objects.equals(patronProfile, that.patronProfile) &&
        Objects.equals(solicitationType, that.solicitationType) &&
        Objects.equals(organizationid, that.organizationid) &&
        Objects.equals(organizationname, that.organizationname) &&
        Objects.equals(organizationurl, that.organizationurl) &&
        Objects.equals(subscriptionstatus, that.subscriptionstatus);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(patronProfile, solicitationType, organizationid, organizationname, organizationurl,
            subscriptionstatus);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("patronProfile", patronProfile)
        .append("solicitationType", solicitationType)
        .append("organizationid", organizationid)
        .append("organizationname", organizationname)
        .append("organizationurl", organizationurl)
        .append("subscriptionstatus", subscriptionstatus)
        .toString();
  }
}

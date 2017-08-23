package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "PROFILE_DELIVERY_METHOD")
public class ProfileDeliveryMethod {
  @Id
  private Long profiledeliverymethodid;
  private Long deliveryMethodId;
  private Long profileInfoId;

  public ProfileDeliveryMethod() {
  }

  @Id
  @Column(name = "PROFILEDELIVERYMETHODID", nullable = false, precision = 0)
  public Long getProfiledeliverymethodid() {
    return profiledeliverymethodid;
  }

  public void setProfiledeliverymethodid(Long profiledeliverymethodid) {
    this.profiledeliverymethodid = profiledeliverymethodid;
  }

  @Column(name = "DELIVERY_METHOD_ID", nullable = false, precision = 0)
  public Long getDeliveryMethodId() {
    return deliveryMethodId;
  }

  public void setDeliveryMethodId(Long deliveryMethodId) {
    this.deliveryMethodId = deliveryMethodId;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProfileDeliveryMethod that = (ProfileDeliveryMethod) o;

    return Objects.equals(this.profiledeliverymethodid, that.profiledeliverymethodid) &&
        Objects.equals(this.deliveryMethodId, that.deliveryMethodId) &&
        Objects.equals(this.profileInfoId, that.profileInfoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profiledeliverymethodid, deliveryMethodId, profileInfoId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("profiledeliverymethodid", profiledeliverymethodid)
        .append("deliveryMethodId", deliveryMethodId)
        .append("profileInfoId", profileInfoId)
        .toString();
  }
}

package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 21/12/2016.
 */
@Entity
@Table(name = "USER_POS")
public class UserPos {
  private Long userposid;
  private Long userInfoId;
  private Long profileInfoId;
  private Long posId;

  @Id
  @Column(name = "USERPOSID", nullable = false, precision = 0)
  public Long getUserposid() {
    return userposid;
  }

  public void setUserposid(Long userposid) {
    this.userposid = userposid;
  }

  @Column(name = "USER_INFO_ID", nullable = false, precision = 0)
  public Long getUserInfoId() {
    return userInfoId;
  }

  public void setUserInfoId(Long userInfoId) {
    this.userInfoId = userInfoId;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Column(name = "POS_ID", nullable = false, precision = 0)
  public Long getPosId() {
    return posId;
  }

  public void setPosId(Long posId) {
    this.posId = posId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserPos userPos = (UserPos) o;

    return new EqualsBuilder()
        .append(userposid, userPos.userposid)
        .append(userInfoId, userPos.userInfoId)
        .append(profileInfoId, userPos.profileInfoId)
        .append(posId, userPos.posId)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(userposid)
        .append(userInfoId)
        .append(profileInfoId)
        .append(posId)
        .toHashCode();
  }
}

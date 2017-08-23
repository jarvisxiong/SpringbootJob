package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
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
@Table(name = "USER_GROUP")
public class UserGroup implements Serializable {
  private Long usergroupid;
  private Long groupInfoId;
  private Long profileInfoId;
  private Long userInfoId;

  public UserGroup() {
  }

  public UserGroup(Long profileInfoId, Long userInfoId) {
    this.profileInfoId = profileInfoId;
    this.userInfoId = userInfoId;
  }

  @Id
  @Column(name = "USERGROUPID", nullable = false, precision = 0)
  public Long getUsergroupid() {
    return usergroupid;
  }

  public void setUsergroupid(Long usergroupid) {
    this.usergroupid = usergroupid;
  }

  @Column(name = "GROUP_INFO_ID", nullable = false, precision = 0)
  public Long getGroupInfoId() {
    return groupInfoId;
  }

  public void setGroupInfoId(Long groupInfoId) {
    this.groupInfoId = groupInfoId;
  }

  @Column(name = "PROFILE_INFO_ID", nullable = false, precision = 0)
  public Long getProfileInfoId() {
    return profileInfoId;
  }

  public void setProfileInfoId(Long profileInfoId) {
    this.profileInfoId = profileInfoId;
  }

  @Column(name = "USER_INFO_ID", nullable = false, precision = 0)
  public Long getUserInfoId() {
    return userInfoId;
  }

  public void setUserInfoId(Long userInfoId) {
    this.userInfoId = userInfoId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserGroup that = (UserGroup) o;

    return Objects.equals(this.profileInfoId, that.profileInfoId) &&
        Objects.equals(this.userInfoId, that.userInfoId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(usergroupid, groupInfoId, profileInfoId, userInfoId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("usergroupid", usergroupid)
        .append("groupInfoId", groupInfoId)
        .append("profileInfoId", profileInfoId)
        .append("userInfoId", userInfoId)
        .toString();
  }
}
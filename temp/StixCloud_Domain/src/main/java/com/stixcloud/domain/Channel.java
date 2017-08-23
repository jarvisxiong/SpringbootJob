package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chongye on 21/12/2016.
 */
@Entity
public class Channel {
  private Long channelid;
  private String channeldescription;
  private String channelname;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private Long organizationId;

  @Id
  @Column(name = "CHANNELID", nullable = false, precision = 0)
  public Long getChannelid() {
    return channelid;
  }

  public void setChannelid(Long channelid) {
    this.channelid = channelid;
  }

  @Column(name = "CHANNELDESCRIPTION", nullable = false, length = 128)
  public String getChanneldescription() {
    return channeldescription;
  }

  public void setChanneldescription(String channeldescription) {
    this.channeldescription = channeldescription;
  }

  @Column(name = "CHANNELNAME", nullable = false, length = 100)
  public String getChannelname() {
    return channelname;
  }

  public void setChannelname(String channelname) {
    this.channelname = channelname;
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

  @Column(name = "ORGANIZATION_ID", nullable = true, precision = 0)
  public Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Channel channel = (Channel) o;

    return new EqualsBuilder()
        .append(channelid, channel.channelid)
        .append(channeldescription, channel.channeldescription)
        .append(channelname, channel.channelname)
        .append(createdBy, channel.createdBy)
        .append(createddate, channel.createddate)
        .append(updatedBy, channel.updatedBy)
        .append(updateddate, channel.updateddate)
        .append(organizationId, channel.organizationId)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(channelid)
        .append(channeldescription)
        .append(channelname)
        .append(createdBy)
        .append(createddate)
        .append(updatedBy)
        .append(updateddate)
        .append(organizationId)
        .toHashCode();
  }
}

package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "vpc_3DSECI",
    "vpc_3DSXID",
    "vpc_3DSenrolled",
    "vpc_3DSstatus",
    "vpc_VerToken",
    "vpc_VerType",
    "vpc_VerSecurityLevel",
    "vpc_VerStatus"
})
public class PostCommitRequest {

  @JsonProperty("vpc_3DSECI")
  public String vpc3DSECI;
  @JsonProperty("vpc_3DSXID")
  public String vpc3DSXID;
  @JsonProperty("vpc_3DSenrolled")
  public String vpc3DSenrolled;
  @JsonProperty("vpc_3DSstatus")
  public String vpc3DSstatus;
  @JsonProperty("vpc_VerToken")
  public String vpcVerToken;
  @JsonProperty("vpc_VerType")
  public String vpcVerType;
  @JsonProperty("vpc_VerSecurityLevel")
  public String vpcVerSecurityLevel;
  @JsonProperty("vpc_VerStatus")
  public String vpcVerStatus;

  /**
   * No args constructor for use in serialization
   */
  public PostCommitRequest() {
  }

  private PostCommitRequest(Builder builder) {
    vpc3DSECI = builder.vpc3DSECI;
    vpc3DSXID = builder.vpc3DSXID;
    vpc3DSenrolled = builder.vpc3DSenrolled;
    vpc3DSstatus = builder.vpc3DSstatus;
    vpcVerToken = builder.vpcVerToken;
    vpcVerType = builder.vpcVerType;
    vpcVerSecurityLevel = builder.vpcVerSecurityLevel;
    vpcVerStatus = builder.vpcVerStatus;
  }

  public String getVpc3DSECI() {
    return vpc3DSECI;
  }

  public String getVpc3DSXID() {
    return vpc3DSXID;
  }

  public String getVpc3DSenrolled() {
    return vpc3DSenrolled;
  }

  public String getVpc3DSstatus() {
    return vpc3DSstatus;
  }

  public String getVpcVerToken() {
    return vpcVerToken;
  }

  public String getVpcVerType() {
    return vpcVerType;
  }

  public String getVpcVerSecurityLevel() {
    return vpcVerSecurityLevel;
  }

  public String getVpcVerStatus() {
    return vpcVerStatus;
  }

  @Override
  public String toString() {

    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("vpc3DSECI", vpc3DSECI)
        .append("vpc3DSXID", vpc3DSXID)
        .append("vpc3DSenrolled", vpc3DSenrolled)
        .append("vpc3DSstatus", vpc3DSstatus)
        .append("vpcVerToken", vpcVerToken)
        .append("vpcVerType", vpcVerType)
        .append("vpcVerSecurityLevel", vpcVerSecurityLevel)
        .append("vpcVerStatus", vpcVerStatus)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PostCommitRequest that = (PostCommitRequest) o;

    return Objects.equals(this.vpc3DSECI, that.vpc3DSECI) &&
        Objects.equals(this.vpc3DSXID, that.vpc3DSXID) &&
        Objects.equals(this.vpc3DSenrolled, that.vpc3DSenrolled) &&
        Objects.equals(this.vpc3DSstatus, that.vpc3DSstatus) &&
        Objects.equals(this.vpcVerToken, that.vpcVerToken) &&
        Objects.equals(this.vpcVerType, that.vpcVerType) &&
        Objects.equals(this.vpcVerSecurityLevel, that.vpcVerSecurityLevel) &&
        Objects.equals(this.vpcVerStatus, that.vpcVerStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vpc3DSECI, vpc3DSXID, vpc3DSenrolled, vpc3DSstatus,
        vpcVerToken,
        vpcVerType, vpcVerSecurityLevel, vpcVerStatus);
  }

  public static final class Builder {
    public String vpc3DSECI;
    public String vpc3DSXID;
    public String vpc3DSenrolled;
    public String vpc3DSstatus;
    public String vpcVerToken;
    public String vpcVerType;
    public String vpcVerSecurityLevel;
    public String vpcVerStatus;

    public Builder() {
    }

    public Builder(PostCommitRequest copy) {
      this.vpc3DSECI = copy.vpc3DSECI;
      this.vpc3DSXID = copy.vpc3DSXID;
      this.vpc3DSenrolled = copy.vpc3DSenrolled;
      this.vpc3DSstatus = copy.vpc3DSstatus;
      this.vpcVerToken = copy.vpcVerToken;
      this.vpcVerType = copy.vpcVerType;
      this.vpcVerSecurityLevel = copy.vpcVerSecurityLevel;
      this.vpcVerStatus = copy.vpcVerStatus;
    }

    public Builder vpc3DSECI(String vpc3DSECI) {
      this.vpc3DSECI = vpc3DSECI;
      return this;
    }

    public Builder vpc3DSXID(String vpc3DSXID) {
      this.vpc3DSXID = vpc3DSXID;
      return this;
    }

    public Builder vpc3DSenrolled(String vpc3DSenrolled) {
      this.vpc3DSenrolled = vpc3DSenrolled;
      return this;
    }

    public Builder vpc3DSstatus(String vpc3DSstatus) {
      this.vpc3DSstatus = vpc3DSstatus;
      return this;
    }

    public Builder vpcVerToken(String vpcVerToken) {
      this.vpcVerToken = vpcVerToken;
      return this;
    }

    public Builder vpcVerType(String vpcVerType) {
      this.vpcVerType = vpcVerType;
      return this;
    }

    public Builder vpcVerSecurityLevel(String vpcVerSecurityLevel) {
      this.vpcVerSecurityLevel = vpcVerSecurityLevel;
      return this;
    }

    public Builder vpcVerStatus(String vpcVerStatus) {
      this.vpcVerStatus = vpcVerStatus;
      return this;
    }

    public PostCommitRequest build() {
      return new PostCommitRequest(this);
    }
  }
}
package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "solicitationType",
    "organizationID",
    "organizationName",
    "organizationUrl",
    "subscribed"
})
public class Solicitation implements Serializable {

  private static final long serialVersionUID = -2229039200710227281L;
  @JsonProperty("solicitationType")
  private String solicitationType;
  @JsonProperty("organizationID")
  private Long organizationID;
  @JsonProperty("organizationName")
  private String organizationName;
  @JsonProperty("organizationUrl")
  private String organizationUrl;
  @JsonProperty("subscribed")
  private Boolean subscribed;

  public Solicitation() {
  }

  private Solicitation(Builder builder) {
    solicitationType = builder.solicitationType;
    organizationID = builder.organizationID;
    organizationName = builder.organizationName;
    organizationUrl = builder.organizationUrl;
    subscribed = builder.subscribed;
  }

  /**
   * @return The solicitationType
   */
  @JsonProperty("solicitationType")
  public String getSolicitationType() {
    return solicitationType;
  }


  /**
   * @return The organizationID
   */
  @JsonProperty("organizationID")
  public Long getOrganizationID() {
    return organizationID;
  }


  /**
   * @return The organizationName
   */
  @JsonProperty("organizationName")
  public String getOrganizationName() {
    return organizationName;
  }


  /**
   * @return The organizationUrl
   */
  @JsonProperty("organizationUrl")
  public String getOrganizationUrl() {
    return organizationUrl;
  }

  /**
   * @return The subscribed
   */
  @JsonProperty("subscribed")
  public Boolean getSubscribed() {
    return subscribed;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Solicitation that = (Solicitation) o;

    return Objects.equals(this.solicitationType, that.solicitationType) &&
        Objects.equals(this.organizationID, that.organizationID) &&
        Objects.equals(this.organizationName, that.organizationName) &&
        Objects.equals(this.organizationUrl, that.organizationUrl) &&
        Objects.equals(this.subscribed, that.subscribed);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(solicitationType, organizationID, organizationName, organizationUrl, subscribed);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("solicitationType", solicitationType)
        .append("organizationID", organizationID)
        .append("organizationName", organizationName)
        .append("organizationUrl", organizationUrl)
        .append("subscribed", subscribed)
        .toString();
  }

  public static final class Builder {
    private String solicitationType;
    private Long organizationID;
    private String organizationName;
    private String organizationUrl;
    private Boolean subscribed;

    public Builder() {
    }

    public Builder(Solicitation copy) {
      this.solicitationType = copy.solicitationType;
      this.organizationID = copy.organizationID;
      this.organizationName = copy.organizationName;
      this.organizationUrl = copy.organizationUrl;
      this.subscribed = copy.subscribed;
    }

    public Builder solicitationType(String solicitationType) {
      this.solicitationType = solicitationType;
      return this;
    }

    public Builder organizationID(Long organizationID) {
      this.organizationID = organizationID;
      return this;
    }

    public Builder organizationName(String organizationName) {
      this.organizationName = organizationName;
      return this;
    }

    public Builder organizationUrl(String organizationUrl) {
      this.organizationUrl = organizationUrl;
      return this;
    }

    public Builder subscribed(Boolean subscribed) {
      this.subscribed = subscribed;
      return this;
    }

    public Solicitation build() {
      return new Solicitation(this);
    }
  }
}
package com.stixcloud.patron.api.json;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"solicitationType", "organizationID", "subscribed"})
public class SolicitationJson implements Serializable {

  private static final long serialVersionUID = -8546028927410144959L;

  @JsonProperty("solicitationType")
  protected String solicitationType;
  @JsonProperty("organizationID")
  protected Long organizationID;
  @JsonProperty("subscribed")
  protected Boolean subscribed;

  public SolicitationJson() {

  }

  public SolicitationJson(String solicitationType, Long organizationID, Boolean subscribed) {
    this.solicitationType = solicitationType;
    this.organizationID = organizationID;
    this.subscribed = subscribed;
  }


  /**
   * @return the solicitationType
   */
  @JsonProperty("solicitationType")
  public String getSolicitationType() {
    return solicitationType;
  }


  /**
   * @param solicitationType the solicitationType to set
   */
  @JsonProperty("solicitationType")
  public void setSolicitationType(String solicitationType) {
    this.solicitationType = solicitationType;
  }


  /**
   * @return the organizationID
   */
  @JsonProperty("organizationID")
  public Long getOrganizationID() {
    return organizationID;
  }


  /**
   * @param organizationID the organizationID to set
   */
  @JsonProperty("organizationID")
  public void setOrganizationID(Long organizationID) {
    this.organizationID = organizationID;
  }


  /**
   * @return the subscribed
   */
  @JsonProperty("subscribed")
  public Boolean getSubscribed() {
    return subscribed;
  }


  /**
   * @param subscribed the subscribed to set
   */
  @JsonProperty("subscribed")
  public void setSubscribed(Boolean subscribed) {
    this.subscribed = subscribed;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronSolicitationJson that = (PatronSolicitationJson) o;
    return new EqualsBuilder().append(solicitationType, that.getSolicitationType())
        .append(organizationID, that.getOrganizationID()).append(subscribed, that.getSubscribed())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(solicitationType).append(organizationID)
        .append(subscribed).toHashCode();
  }
}

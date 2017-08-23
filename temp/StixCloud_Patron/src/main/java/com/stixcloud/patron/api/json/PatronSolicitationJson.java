package com.stixcloud.patron.api.json;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"organizationName", "organizationUrl", "order"})
public class PatronSolicitationJson extends SolicitationJson implements Serializable {

  private static final long serialVersionUID = -1645634042643511780L;

  @JsonProperty("organizationName")
  private String organizationName;
  @JsonProperty("organizationUrl")
  private String organizationUrl;
  @JsonProperty("order")
  private Integer order;
  
  public PatronSolicitationJson() {

  }

  public PatronSolicitationJson(String solicitationType, Long organizationID,
      String organizationName, String organizationUrl, Boolean subscribed, Integer order) {
    this.solicitationType = solicitationType;
    this.organizationID = organizationID;
    this.organizationName = organizationName;
    this.organizationUrl = organizationUrl;
    this.subscribed = subscribed;
    this.order = order;
  }


  /**
   * @return the organizationName
   */
  @JsonProperty("organizationName")
  public String getOrganizationName() {
    return organizationName;
  }


  /**
   * @param organizationName the organizationName to set
   */
  @JsonProperty("organizationName")
  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }


  /**
   * @return the organizationUrl
   */
  @JsonProperty("organizationUrl")
  public String getOrganizationUrl() {
    return organizationUrl;
  }


  /**
   * @param organizationUrl the organizationUrl to set
   */
  @JsonProperty("organizationUrl")
  public void setOrganizationUrl(String organizationUrl) {
    this.organizationUrl = organizationUrl;
  }


  /**
   * @return the order
   */
  @JsonProperty("order")
  public Integer getOrder() {
    return order;
  }

  /**
   * @param order the order to set
   */
  @JsonProperty("order")
  public void setOrder(Integer order) {
    this.order = order;
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
        .append(organizationID, that.getOrganizationID())
        .append(organizationName, that.getOrganizationName())
        .append(organizationUrl, that.getOrganizationUrl()).append(subscribed, that.getSubscribed())
        .append(order, that.getOrder()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(solicitationType).append(organizationID)
        .append(organizationName).append(organizationUrl).append(subscribed).append(order)
        .toHashCode();
  }

}

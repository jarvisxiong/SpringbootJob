package com.stixcloud.patron.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "PATRON_SOLICITATION_VIEW")
public class PatronSolicitationView implements Serializable {

  private static final long serialVersionUID = 4807839042111963166L;
  
  @Id
  private String solicitationType;
  @Id
  private Long organizationId;
  @Id
  private String organizationName;
  @Id
  private String organizationUrl;
  @Id
  private Boolean subcribed;
  @Id
  private Long patronProfileId;
  @Id
  private String description2;
  
  public PatronSolicitationView() {

  }

  public PatronSolicitationView(String solicitationType, Long organizationId,
      String organizationName, String organizationUrl, Boolean subcribed, Long patronProfileId, String description2) {
    this.solicitationType = solicitationType;
    this.organizationId = organizationId;
    this.organizationName = organizationName;
    this.organizationUrl = organizationUrl;
    this.subcribed = subcribed;
    this.patronProfileId = patronProfileId;
    this.description2 = description2;
  }

  /**
   * @return the solicitationType
   */
  @Column(name = "SOLICITATION_TYPE", nullable = true, length = 255)
  public String getSolicitationType() {
    return solicitationType;
  }

  /**
   * @param solicitationType the solicitationType to set
   */
  public void setSolicitationType(String solicitationType) {
    this.solicitationType = solicitationType;
  }

  /**
   * @return the organizationId
   */
  @Column(name = "ORGANIZATION_ID", nullable = true, length = 10)
  public Long getOrganizationId() {
    return organizationId;
  }

  /**
   * @param organizationId the organizationId to set
   */
  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  /**
   * @return the organizationName
   */
  @Column(name = "ORGANIZATION_NAME", nullable = true, length = 255)
  public String getOrganizationName() {
    return organizationName;
  }

  /**
   * @param organizationName the organizationName to set
   */
  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  /**
   * @return the organizationUrl
   */
  @Column(name = "ORGANIZATION_URL", nullable = true, length = 500)
  public String getOrganizationUrl() {
    return organizationUrl;
  }

  /**
   * @param organizationUrl the organizationUrl to set
   */
  public void setOrganizationUrl(String organizationUrl) {
    this.organizationUrl = organizationUrl;
  }

  /**
   * @return the subcribed
   */
  @Column(name = "SUBCRIBED", nullable = false, length = 1)
  @Type(type = "true_false")
  public Boolean getSubcribed() {
    return subcribed;
  }

  /**
   * @param subcribed the subcribed to set
   */
  public void setSubcribed(Boolean subcribed) {
    this.subcribed = subcribed;
  }

  /**
   * @return the patronProfileId
   */
  @Column(name = "PATRON_PROFILE_ID", nullable = true, length = 255)
  public Long getPatronProfileId() {
    return patronProfileId;
  }

  /**
   * @param patronProfileId the patronProfileId to set
   */
  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  
  /**
   * @return the description2
   */
  @Column(name = "DESCRIPTION2", nullable = true, length = 255)
  public String getDescription2() {
    return description2;
  }

  /**
   * @param description2 the description2 to set
   */
  public void setDescription2(String description2) {
    this.description2 = description2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronSolicitationView that = (PatronSolicitationView) o;
    return new EqualsBuilder().append(solicitationType, that.getSolicitationType())
        .append(subcribed, that.getSubcribed()).append(organizationId, that.getOrganizationId())
        .append(organizationName, that.getOrganizationName())
        .append(organizationUrl, that.getOrganizationUrl())
        .append(patronProfileId, that.getPatronProfileId())
        .append(description2, that.getDescription2()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(solicitationType).append(subcribed)
        .append(organizationId).append(organizationName).append(organizationUrl)
        .append(patronProfileId).append(description2).toHashCode();

  }

}

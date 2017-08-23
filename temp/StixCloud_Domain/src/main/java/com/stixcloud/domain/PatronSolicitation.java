package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 12/6/2016.
 */
@Entity
@Table(name = "PATRON_SOLICITATION")
public class PatronSolicitation {
  private Long patronsolicitationid;
  private Long patronProfileId;
  private Long solicitationTypeMctId;
  private Long solicitationLabelMctId;
  private Long organizationId;
  private Boolean subscriptionstatus;
  private OffsetDateTime updateddate;
  private Long updatedBy;

  public PatronSolicitation() {
  }

  public PatronSolicitation(Long patronProfileId,
                            Long solicitationTypeMctId, Long solicitationLabelMctId,
                            Long organizationId, Boolean subscriptionstatus,
                            OffsetDateTime updateddate, Long updatedBy) {
    this.patronProfileId = patronProfileId;
    this.solicitationTypeMctId = solicitationTypeMctId;
    this.solicitationLabelMctId = solicitationLabelMctId;
    this.organizationId = organizationId;
    this.subscriptionstatus = subscriptionstatus;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  public PatronSolicitation(Long patronsolicitationid, Long patronProfileId,
                            Long solicitationTypeMctId, Long solicitationLabelMctId,
                            Long organizationId, Boolean subscriptionstatus,
                            OffsetDateTime updateddate, Long updatedBy) {
    this.patronsolicitationid = patronsolicitationid;
    this.patronProfileId = patronProfileId;
    this.solicitationTypeMctId = solicitationTypeMctId;
    this.solicitationLabelMctId = solicitationLabelMctId;
    this.organizationId = organizationId;
    this.subscriptionstatus = subscriptionstatus;
    this.updateddate = updateddate;
    this.updatedBy = updatedBy;
  }

  @Id
  @Column(name = "PATRONSOLICITATIONID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATRON_SOLICITATION_ID_SEQ")
  @GenericGenerator(name = "PATRON_SOLICITATION_ID_SEQ", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "PATRON_SOLICITATION_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getPatronsolicitationid() {
    return patronsolicitationid;
  }

  public void setPatronsolicitationid(Long patronsolicitationid) {
    this.patronsolicitationid = patronsolicitationid;
  }

  @Column(name = "PATRON_PROFILE_ID", nullable = false, precision = 0)
  public Long getPatronProfileId() {
    return patronProfileId;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  @Column(name = "SOLICITATION_TYPE_MCT_ID", nullable = false, precision = 0)
  public Long getSolicitationTypeMctId() {
    return solicitationTypeMctId;
  }

  public void setSolicitationTypeMctId(Long solicitationTypeMctId) {
    this.solicitationTypeMctId = solicitationTypeMctId;
  }

  @Column(name = "SOLICITATION_LABEL_MCT_ID", nullable = true, precision = 0)
  public Long getSolicitationLabelMctId() {
    return solicitationLabelMctId;
  }

  public void setSolicitationLabelMctId(Long solicitationLabelMctId) {
    this.solicitationLabelMctId = solicitationLabelMctId;
  }

  @Column(name = "ORGANIZATION_ID", nullable = true, precision = 0)
  public Long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  @Column(name = "SUBSCRIPTIONSTATUS", nullable = false, precision = 0)
  public Boolean getSubscriptionstatus() {
    return subscriptionstatus;
  }

  public void setSubscriptionstatus(Boolean subscriptionstatus) {
    this.subscriptionstatus = subscriptionstatus;
  }

  @Column(name = "UPDATEDDATE", nullable = false)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatronSolicitation that = (PatronSolicitation) o;
    return Objects.equals(patronsolicitationid, that.patronsolicitationid) &&
        Objects.equals(patronProfileId, that.patronProfileId) &&
        Objects.equals(solicitationTypeMctId, that.solicitationTypeMctId) &&
        Objects.equals(solicitationLabelMctId, that.solicitationLabelMctId) &&
        Objects.equals(organizationId, that.organizationId) &&
        Objects.equals(subscriptionstatus, that.subscriptionstatus) &&
        Objects.equals(updateddate, that.updateddate) &&
        Objects.equals(updatedBy, that.updatedBy);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(patronsolicitationid, patronProfileId, solicitationTypeMctId, solicitationLabelMctId,
            organizationId, subscriptionstatus, updateddate, updatedBy);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("patronsolicitationid", patronsolicitationid)
        .append("patronProfileId", patronProfileId)
        .append("solicitationTypeMctId", solicitationTypeMctId)
        .append("solicitationLabelMctId", solicitationLabelMctId)
        .append("organizationId", organizationId)
        .append("subscriptionstatus", subscriptionstatus)
        .append("updateddate", updateddate)
        .append("updatedBy", updatedBy)
        .toString();
  }
}

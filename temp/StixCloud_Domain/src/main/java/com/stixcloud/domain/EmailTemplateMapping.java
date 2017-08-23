package com.stixcloud.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "EMAIL_TEMPLATE_MAPPING")
public class EmailTemplateMapping implements Serializable {
  private Long emailTemplateMappingId;
  private Long entityId;
  private Long type;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private String languageCode;
  private Long emailTemplateId;

  /**
   * Gets the emailTemplateMappingId.
   * @return emailTemplateMappingId
   */
  @Id
  @Column(name = "EMAILTEMPLATEMAPPINGID", unique = true, nullable = false, precision = 10, scale = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "EmailTemplateMappingIdSeq")
  @GenericGenerator(name = "EmailTemplateMappingIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "EMAIL_TEMPLATE_MAPPING_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getEmailTemplateMappingId() {
    return emailTemplateMappingId;
  }

  /**
   *
   * @param emailTemplateMappingId
   */
  public void setEmailTemplateMappingId(Long emailTemplateMappingId) {
    this.emailTemplateMappingId = emailTemplateMappingId;
  }

  /**
   * Gets the entityId.
   * @return entityId
   */
  @Column(name = "ENTITY_ID")
  public Long getEntityId() {
    return entityId;
  }

  /**
   *
   * @param entityId
   */
  public void setEntityId(Long entityId) {
    this.entityId = entityId;
  }

  /**
   * Gets the type.
   */
  @Column(name = "TYPE", nullable = false, precision = 1, scale = 0)
  public Long getType() {
    return type;
  }

  /**
   *
   * @param type
   */
  public void setType(Long type) {
    this.type = type;
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

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = false)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  /**
   * @return emailTemplates
   */
  @Column(name = "EMAIL_TEMPLATE_ID", nullable = false)
  public Long getEmailTemplateId() {
    return emailTemplateId;
  }

  /**
   *
   * @param emailTemplates
   */
  public void setEmailTemplateId(Long emailTemplateId) {
    this.emailTemplateId = emailTemplateId;
  }


}

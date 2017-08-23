package com.stixcloud.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_TEMPLATE")
public class EmailTemplate implements Serializable {
  private Long emailTemplateId;
  private String type;
  private boolean isDefault;
  private String path;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private String languageCode;

  /**
   * Gets the emailTemplateId.
   */
  @Id
  @Column(name = "EMAILTEMPLATEID", unique = true, nullable = false, precision = 10, scale = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "EmailTemplateIdSeq")
  @GenericGenerator(name = "EmailTemplateIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "EMAIL_TEMPLATE_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getEmailTemplateId() {
    return emailTemplateId;
  }

  /**
   *
   * @param emailTemplateId
   */
  public void setEmailTemplateId(Long emailTemplateId) {
    this.emailTemplateId = emailTemplateId;
  }

  /**
   *
   * @return
   */
  @Column(name = "TYPE", length = 128)
  public String getType() {
    return type;
  }

  /**
   * Gets the type.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the isDefault
   */
  @Type(type = "yes_no")
  @Column(name = "IS_DEFAULT", nullable = false, length = 1)
  public boolean getIsDefault() {
    return isDefault;
  }

  /**
   *
   * @param isDefault
   */
  public void setIsDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }

  /**
   * get file path
   */
  @Column(name = "PATH", length = 1000)
  public String getPath() {
    return path;
  }

  /**
   * set file path
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * get language code
   */
  @Column(name = "LANGUAGE_CODE", nullable = false, length = 5)
  public String getLanguageCode() {
    return languageCode;
  }

  /**
   * set language code
   */
  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
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

}

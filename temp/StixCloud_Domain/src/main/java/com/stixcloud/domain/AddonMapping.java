package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chetan on 05/12/2016.
 */
@Entity
@Table(name = "ADDON_MAPPING")
public class AddonMapping implements Serializable {
  private static final long serialVersionUID = 7174480126624044501L;
  private Long addOnMappingID;
  private String mappingName;
  private String description;
  private Boolean is_applicable;
  private Long addonProductID;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private String message;
  private Boolean showLightbox;
  private String lightboxContent;
  private String addonType;

  @Id
  @Column(name = "ADDONMAPPINGID", nullable = false, precision = 0)
  public Long getAddOnMappingID() {
    return addOnMappingID;
  }

  public void setAddOnMappingID(Long addOnMappingID) {
    this.addOnMappingID = addOnMappingID;
  }

  @Column(name = "MAPPING_NAME", nullable = false, precision = 200)
  public String getMappingName() {
    return mappingName;
  }

  public void setMappingName(String mappingName) {
    this.mappingName = mappingName;
  }

  @Column(name = "DESCRIPTION", nullable = true, precision = 200)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "ADDON_PRODUCT_ID", nullable = false, precision = 0)
  public Long getAddonProductID() {
    return addonProductID;
  }

  public void setAddonProductID(Long addonProductID) {
    this.addonProductID = addonProductID;
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

  @Column(name = "MESSAGE", nullable = false, precision = 500)
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  @Column(name = "SHOW_LIGHTBOX", nullable = false)
  public Boolean getShowLightbox() {
    return showLightbox;
  }

  public void setShowLightbox(Boolean showLightbox) {
    this.showLightbox = showLightbox;
  }

  @Column(name = "LIGHTBOX_CONTENT", nullable = false)
  public String getLightboxContent() {
    return lightboxContent;
  }

  @Column(name = "LIGHTBOX_CONTENT", nullable = false)
  public void setLightboxContent(String lightboxContent) {
    this.lightboxContent = lightboxContent;
  }
  
  @Column(name = "ADDON_TYPE", nullable = false)
  public String getAddonType() {
    return addonType;
  }

  public void setAddonType(String addonType) {
    this.addonType = addonType;
  }
  
  @Type(type = "numeric_boolean")
  @Column(name = "IS_APPLICABLE", nullable = false, precision = 0)
  public Boolean getIs_applicable() {
    return is_applicable;
  }

  public void setIs_applicable(Boolean is_applicable) {
    this.is_applicable = is_applicable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddonMapping that = (AddonMapping) o;
    return Objects.equals(this.addOnMappingID, that.addOnMappingID) &&
        Objects.equals(this.addonProductID, that.addonProductID) &&
        Objects.equals(this.description, that.description) &&
        Objects.equals(this.mappingName, that.mappingName) &&
        Objects.equals(this.is_applicable, that.is_applicable) &&
        Objects.equals(this.message, that.message) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.description, that.description)&&
        Objects.equals(this.showLightbox, that.showLightbox)&&
        Objects.equals(this.addonType, that.addonType)&&
        Objects.equals(this.lightboxContent, that.lightboxContent);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(addOnMappingID, addonProductID, description, mappingName, is_applicable, message, createdBy,
            createddate, updatedBy, updateddate, description, showLightbox, lightboxContent, addonType);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("addOnMappingID", addOnMappingID)
        .append("addonProductID", addonProductID)
        .append("description", description)
        .append("mappingName", mappingName)
        .append("status", is_applicable)
        .append("message", message)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .append("description", description)
        .append("showLightbox", showLightbox)
        .append("lightBoxContent", lightboxContent)
        .append("addonType", addonType)
        .toString();
  }
}

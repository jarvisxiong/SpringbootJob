package com.stixcloud.seatmap.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by chongye on 08-Sep-16.
 */
public class PricingSection implements Serializable {
  private Long priceCatID = -1L;
  private Integer priceCatNumber = -1;
  private Long parentSectionID = -1L;
  private String isFull;
  private Long sectionID;
  private Long levelID;
  private String areaName;
  private String levelAlias;
  private String sectionAlias;
  private String sectionType;
  private Long componentID;

  public PricingSection() {
  }

  public PricingSection(Long priceCatID, Integer priceCatNumber, Long parentSectionID,
                        String isFull,
                        Long sectionID, Long levelID, String areaName, String levelAlias,
                        String sectionAlias, String sectionType) {
    this.priceCatID = priceCatID;
    this.priceCatNumber = priceCatNumber;
    this.parentSectionID = parentSectionID;
    this.isFull = isFull;
    this.sectionID = sectionID;
    this.levelID = levelID;
    this.areaName = areaName;
    this.levelAlias = levelAlias;
    this.sectionAlias = sectionAlias;
    this.sectionType = sectionType;
  }

  public Long getPriceCatID() {
    return priceCatID;
  }

  public void setPriceCatID(Long priceCatID) {
    this.priceCatID = priceCatID;
  }

  public Integer getPriceCatNumber() {
    return priceCatNumber;
  }

  public void setPriceCatNumber(Integer priceCatNumber) {
    this.priceCatNumber = priceCatNumber;
  }

  public Long getParentSectionID() {
    return parentSectionID;
  }

  public void setParentSectionID(Long parentSectionID) {
    this.parentSectionID = parentSectionID;
  }

  public String getFull() {
    return isFull;
  }

  public void setFull(String full) {
    isFull = full;
  }

  public Long getSectionID() {
    return sectionID;
  }

  public void setSectionID(Long sectionID) {
    this.sectionID = sectionID;
  }

  public Long getLevelID() {
    return levelID;
  }

  public void setLevelID(Long levelID) {
    this.levelID = levelID;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public String getLevelAlias() {
    return levelAlias;
  }

  public void setLevelAlias(String levelAlias) {
    this.levelAlias = levelAlias;
  }

  public String getSectionAlias() {
    return sectionAlias;
  }

  public void setSectionAlias(String sectionAlias) {
    this.sectionAlias = sectionAlias;
  }

  public String getSectionType() {
    return sectionType;
  }

  public void setSectionType(String sectionType) {
    this.sectionType = sectionType;
  }

  public Long getComponentID() {
    return componentID;
  }

  public void setComponentID(Long componentID) {
    this.componentID = componentID;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceCatID", priceCatID)
        .append("priceCatNumber", priceCatNumber)
        .append("parentSectionID", parentSectionID)
        .append("isFull", isFull)
        .append("sectionID", sectionID)
        .append("levelID", levelID)
        .append("areaName", areaName)
        .append("levelAlias", levelAlias)
        .append("sectionAlias", sectionAlias)
        .append("sectionType", sectionType)
        .append("componentID", componentID)
        .toString();
  }
}

package com.stixcloud.seatmap.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by chongye on 13-Sep-16.
 */
public class SectionRow {
  private Long seatInvId;
  private Long sectionID;
  private Long levelID;
  private String sectionAlias;
  private String areaAlias;
  private String levelAlias;
  private Integer rank;
  private Long rowID;
  private String rowAlias;

  public SectionRow() {
  }

  public SectionRow(Long seatInvId, Long sectionID, Long levelID, String sectionAlias,
                    String areaAlias, String levelAlias, Integer rank, Long rowID,
                    String rowAlias) {
    this.seatInvId = seatInvId;
    this.sectionID = sectionID;
    this.levelID = levelID;
    this.sectionAlias = sectionAlias;
    this.areaAlias = areaAlias;
    this.levelAlias = levelAlias;
    this.rank = rank;
    this.rowID = rowID;
    this.rowAlias = rowAlias;
  }

  public Long getSeatInvId() {

    return seatInvId;
  }

  public void setSeatInvId(Long seatInvId) {
    this.seatInvId = seatInvId;
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

  public String getSectionAlias() {
    return sectionAlias;
  }

  public void setSectionAlias(String sectionAlias) {
    this.sectionAlias = sectionAlias;
  }

  public String getAreaAlias() {
    return areaAlias;
  }

  public void setAreaAlias(String areaAlias) {
    this.areaAlias = areaAlias;
  }

  public String getLevelAlias() {
    return levelAlias;
  }

  public void setLevelAlias(String levelAlias) {
    this.levelAlias = levelAlias;
  }

  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }

  public Long getRowID() {
    return rowID;
  }

  public void setRowID(Long rowID) {
    this.rowID = rowID;
  }

  public String getRowAlias() {
    return rowAlias;
  }

  public void setRowAlias(String rowAlias) {
    this.rowAlias = rowAlias;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("seatInvId", seatInvId)
        .append("sectionID", sectionID)
        .append("levelID", levelID)
        .append("sectionAlias", sectionAlias)
        .append("areaAlias", areaAlias)
        .append("levelAlias", levelAlias)
        .append("rank", rank)
        .append("rowID", rowID)
        .append("rowAlias", rowAlias)
        .toString();
  }
}

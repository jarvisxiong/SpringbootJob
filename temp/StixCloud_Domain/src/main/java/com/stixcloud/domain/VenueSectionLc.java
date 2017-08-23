package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 07-Sep-16.
 */
@Entity
@Table(name = "VENUE_SECTION_LC")
public class VenueSectionLc implements Serializable {

  private static final long serialVersionUID = -2376178744230883227L;
  private Long venuesectionlcid;
  private Long lcId;
  private Long levelId;
  private String sectionalias;
  private String areaalias;
  private Integer noofseats;
  private String sectiontype;
  private Long nearestEntranceId;
  private Integer rank;
  private String isseatnumpreview;

  public VenueSectionLc() {
  }

  public VenueSectionLc(Long venuesectionlcid, Long lcId, Long levelId, String sectionalias,
                        String areaalias, Integer noofseats, String sectiontype,
                        Long nearestEntranceId,
                        Integer rank, String isseatnumpreview) {
    this.venuesectionlcid = venuesectionlcid;
    this.lcId = lcId;
    this.levelId = levelId;
    this.sectionalias = sectionalias;
    this.areaalias = areaalias;
    this.noofseats = noofseats;
    this.sectiontype = sectiontype;
    this.nearestEntranceId = nearestEntranceId;
    this.rank = rank;
    this.isseatnumpreview = isseatnumpreview;
  }

  @Id
  @Column(name = "VENUESECTIONLCID", nullable = false, precision = 0)
  public Long getVenuesectionlcid() {
    return venuesectionlcid;
  }

  public void setVenuesectionlcid(Long venuesectionlcid) {
    this.venuesectionlcid = venuesectionlcid;
  }


  @Column(name = "LC_ID", nullable = false, precision = 0)
  public Long getLcId() {
    return lcId;
  }

  public void setLcId(Long lcId) {
    this.lcId = lcId;
  }


  @Column(name = "LEVEL_ID", nullable = false, precision = 0)
  public Long getLevelId() {
    return levelId;
  }

  public void setLevelId(Long levelId) {
    this.levelId = levelId;
  }


  @Column(name = "SECTIONALIAS", nullable = true, length = 50)
  public String getSectionalias() {
    return sectionalias;
  }

  public void setSectionalias(String sectionalias) {
    this.sectionalias = sectionalias;
  }


  @Column(name = "AREAALIAS", nullable = true, length = 255)
  public String getAreaalias() {
    return areaalias;
  }

  public void setAreaalias(String areaalias) {
    this.areaalias = areaalias;
  }


  @Column(name = "NOOFSEATS", nullable = true, precision = 0)
  public Integer getNoofseats() {
    return noofseats;
  }

  public void setNoofseats(Integer noofseats) {
    this.noofseats = noofseats;
  }


  @Column(name = "SECTIONTYPE", nullable = true, length = 2)
  public String getSectiontype() {
    return sectiontype;
  }

  public void setSectiontype(String sectiontype) {
    this.sectiontype = sectiontype;
  }


  @Column(name = "NEAREST_ENTRANCE_ID", nullable = true, precision = 0)
  public Long getNearestEntranceId() {
    return nearestEntranceId;
  }

  public void setNearestEntranceId(Long nearestEntranceId) {
    this.nearestEntranceId = nearestEntranceId;
  }


  @Column(name = "RANK", nullable = true, precision = 0)
  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }


  @Column(name = "ISSEATNUMPREVIEW", nullable = true, length = 1)
  public String getIsseatnumpreview() {
    return isseatnumpreview;
  }

  public void setIsseatnumpreview(String isseatnumpreview) {
    this.isseatnumpreview = isseatnumpreview;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenueSectionLc that = (VenueSectionLc) o;

    return new EqualsBuilder()
        .append(venuesectionlcid, that.venuesectionlcid)
        .append(lcId, that.lcId)
        .append(levelId, that.levelId)
        .append(sectionalias, that.sectionalias)
        .append(areaalias, that.areaalias)
        .append(noofseats, that.noofseats)
        .append(sectiontype, that.sectiontype)
        .append(nearestEntranceId, that.nearestEntranceId)
        .append(rank, that.rank)
        .append(isseatnumpreview, that.isseatnumpreview)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(venuesectionlcid)
        .append(lcId)
        .append(levelId)
        .append(sectionalias)
        .append(areaalias)
        .append(noofseats)
        .append(sectiontype)
        .append(nearestEntranceId)
        .append(rank)
        .append(isseatnumpreview)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venuesectionlcid", venuesectionlcid)
        .append("lcId", lcId)
        .append("levelId", levelId)
        .append("sectionalias", sectionalias)
        .append("areaalias", areaalias)
        .append("noofseats", noofseats)
        .append("sectiontype", sectiontype)
        .append("nearestEntranceId", nearestEntranceId)
        .append("rank", rank)
        .append("isseatnumpreview", isseatnumpreview)
        .toString();
  }
}

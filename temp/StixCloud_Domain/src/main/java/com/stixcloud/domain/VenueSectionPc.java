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
@Table(name = "VENUE_SECTION_PC")
public class VenueSectionPc implements Serializable {

  private static final long serialVersionUID = -2488567713305377737L;
  private Long venuesectionpcid;
  private Long pcId;
  private Long levelId;
  private String sectionalias;
  private String areaalias;
  private Long parentSectionId;
  private String isfull;
  private Long priceCatId;
  private Integer pricecatnumber;

  public VenueSectionPc() {
  }

  public VenueSectionPc(Long venuesectionpcid, Long pcId, Long levelId, String sectionalias,
                        String areaalias, Long parentSectionId, String isfull, Long priceCatId,
                        Integer pricecatnumber) {
    this.venuesectionpcid = venuesectionpcid;
    this.pcId = pcId;
    this.levelId = levelId;
    this.sectionalias = sectionalias;
    this.areaalias = areaalias;
    this.parentSectionId = parentSectionId;
    this.isfull = isfull;
    this.priceCatId = priceCatId;
    this.pricecatnumber = pricecatnumber;
  }

  @Id
  @Column(name = "VENUESECTIONPCID", nullable = false, precision = 0)
  public Long getVenuesectionpcid() {
    return venuesectionpcid;
  }

  public void setVenuesectionpcid(Long venuesectionpcid) {
    this.venuesectionpcid = venuesectionpcid;
  }

  @Column(name = "PC_ID", nullable = true, precision = 0)
  public Long getPcId() {
    return pcId;
  }

  public void setPcId(Long pcId) {
    this.pcId = pcId;
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

  @Column(name = "PARENT_SECTION_ID", nullable = true, precision = 0)
  public Long getParentSectionId() {
    return parentSectionId;
  }

  public void setParentSectionId(Long parentSectionId) {
    this.parentSectionId = parentSectionId;
  }

  @Column(name = "ISFULL", nullable = true, length = 1)
  public String getIsfull() {
    return isfull;
  }

  public void setIsfull(String isfull) {
    this.isfull = isfull;
  }

  @Column(name = "PRICE_CAT_ID", nullable = true, precision = 0)
  public Long getPriceCatId() {
    return priceCatId;
  }

  public void setPriceCatId(Long priceCatId) {
    this.priceCatId = priceCatId;
  }

  @Column(name = "PRICECATNUMBER", nullable = true, precision = 0)
  public Integer getPricecatnumber() {
    return pricecatnumber;
  }

  public void setPricecatnumber(Integer pricecatnumber) {
    this.pricecatnumber = pricecatnumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenueSectionPc that = (VenueSectionPc) o;

    return new EqualsBuilder()
        .append(venuesectionpcid, that.venuesectionpcid)
        .append(pcId, that.pcId)
        .append(levelId, that.levelId)
        .append(sectionalias, that.sectionalias)
        .append(areaalias, that.areaalias)
        .append(parentSectionId, that.parentSectionId)
        .append(isfull, that.isfull)
        .append(priceCatId, that.priceCatId)
        .append(pricecatnumber, that.pricecatnumber)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(venuesectionpcid)
        .append(pcId)
        .append(levelId)
        .append(sectionalias)
        .append(areaalias)
        .append(parentSectionId)
        .append(isfull)
        .append(priceCatId)
        .append(pricecatnumber)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venuesectionpcid", venuesectionpcid)
        .append("pcId", pcId)
        .append("levelId", levelId)
        .append("sectionalias", sectionalias)
        .append("areaalias", areaalias)
        .append("parentSectionId", parentSectionId)
        .append("isfull", isfull)
        .append("priceCatId", priceCatId)
        .append("pricecatnumber", pricecatnumber)
        .toString();
  }
}

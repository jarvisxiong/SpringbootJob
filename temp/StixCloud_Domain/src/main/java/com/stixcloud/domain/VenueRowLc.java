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
 * Created by chongye on 13-Sep-16.
 */
@Entity
@Table(name = "VENUE_ROW_LC")
public class VenueRowLc implements Serializable {
  private Long venuerowlcid;
  private Long lcId;
  private Long levelId;
  private Long sectionId;
  private Long blockId;
  private String rowalias;
  private Integer rowno;

  public VenueRowLc() {
  }

  public VenueRowLc(Long venuerowlcid, Long lcId, Long levelId, Long sectionId,
                    Long blockId, String rowalias, Integer rowno) {
    this.venuerowlcid = venuerowlcid;
    this.lcId = lcId;
    this.levelId = levelId;
    this.sectionId = sectionId;
    this.blockId = blockId;
    this.rowalias = rowalias;
    this.rowno = rowno;
  }

  @Id
  @Column(name = "VENUEROWLCID", nullable = false, precision = 0)
  public Long getVenuerowlcid() {
    return venuerowlcid;
  }

  public void setVenuerowlcid(Long venuerowlcid) {
    this.venuerowlcid = venuerowlcid;
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


  @Column(name = "SECTION_ID", nullable = false, precision = 0)
  public Long getSectionId() {
    return sectionId;
  }

  public void setSectionId(Long sectionId) {
    this.sectionId = sectionId;
  }


  @Column(name = "BLOCK_ID", nullable = false, precision = 0)
  public Long getBlockId() {
    return blockId;
  }

  public void setBlockId(Long blockId) {
    this.blockId = blockId;
  }


  @Column(name = "ROWALIAS", nullable = true, length = 50)
  public String getRowalias() {
    return rowalias;
  }

  public void setRowalias(String rowalias) {
    this.rowalias = rowalias;
  }


  @Column(name = "ROWNO", nullable = true, precision = 0)
  public Integer getRowno() {
    return rowno;
  }

  public void setRowno(Integer rowno) {
    this.rowno = rowno;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenueRowLc that = (VenueRowLc) o;

    return new EqualsBuilder()
        .append(venuerowlcid, that.venuerowlcid)
        .append(lcId, that.lcId)
        .append(levelId, that.levelId)
        .append(sectionId, that.sectionId)
        .append(blockId, that.blockId)
        .append(rowalias, that.rowalias)
        .append(rowno, that.rowno)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(venuerowlcid)
        .append(lcId)
        .append(levelId)
        .append(sectionId)
        .append(blockId)
        .append(rowalias)
        .append(rowno)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venuerowlcid", venuerowlcid)
        .append("lcId", lcId)
        .append("levelId", levelId)
        .append("sectionId", sectionId)
        .append("blockId", blockId)
        .append("rowalias", rowalias)
        .append("rowno", rowno)
        .toString();
  }
}

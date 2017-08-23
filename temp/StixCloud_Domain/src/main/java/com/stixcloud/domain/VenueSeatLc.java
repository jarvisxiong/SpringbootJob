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
@Table(name = "VENUE_SEAT_LC")
public class VenueSeatLc implements Serializable {

  private static final long serialVersionUID = 4130918184863795497L;
  private Long venueseatid;
  private Long lcId;
  private Long blockId;
  private Long levelId;
  private Long sectionId;
  private Long rowId;
  private Integer seatno;
  private String seatalias;
  private Integer seattype;
  private Integer rank;
  private String seatmaplocation;
  private String seatstatus;
  private Integer ticketable;
  private String nearestentrance;
  private String tickettype;
  private Integer seatattributes;
  private String areaalias;

  public VenueSeatLc() {
  }

  public VenueSeatLc(Long venueseatid, Long lcId, Long blockId, Long levelId, Long sectionId,
                     Long rowId, Integer seatno, String seatalias, Integer seattype, Integer rank,
                     String seatmaplocation, String seatstatus, Integer ticketable,
                     String nearestentrance, String tickettype, Integer seatattributes,
                     String areaalias) {
    this.venueseatid = venueseatid;
    this.lcId = lcId;
    this.blockId = blockId;
    this.levelId = levelId;
    this.sectionId = sectionId;
    this.rowId = rowId;
    this.seatno = seatno;
    this.seatalias = seatalias;
    this.seattype = seattype;
    this.rank = rank;
    this.seatmaplocation = seatmaplocation;
    this.seatstatus = seatstatus;
    this.ticketable = ticketable;
    this.nearestentrance = nearestentrance;
    this.tickettype = tickettype;
    this.seatattributes = seatattributes;
    this.areaalias = areaalias;
  }

  @Id
  @Column(name = "VENUESEATID", nullable = false, precision = 0)
  public Long getVenueseatid() {
    return venueseatid;
  }

  public void setVenueseatid(Long venueseatid) {
    this.venueseatid = venueseatid;
  }


  @Column(name = "LC_ID", nullable = false, precision = 0)
  public Long getLcId() {
    return lcId;
  }

  public void setLcId(Long lcId) {
    this.lcId = lcId;
  }


  @Column(name = "BLOCK_ID", nullable = false, precision = 0)
  public Long getBlockId() {
    return blockId;
  }

  public void setBlockId(Long blockId) {
    this.blockId = blockId;
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


  @Column(name = "ROW_ID", nullable = false, precision = 0)
  public Long getRowId() {
    return rowId;
  }

  public void setRowId(Long rowId) {
    this.rowId = rowId;
  }


  @Column(name = "SEATNO", nullable = true, precision = 0)
  public Integer getSeatno() {
    return seatno;
  }

  public void setSeatno(Integer seatno) {
    this.seatno = seatno;
  }


  @Column(name = "SEATALIAS", nullable = true, length = 50)
  public String getSeatalias() {
    return seatalias;
  }

  public void setSeatalias(String seatalias) {
    this.seatalias = seatalias;
  }


  @Column(name = "SEATTYPE", nullable = true, precision = 0)
  public Integer getSeattype() {
    return seattype;
  }

  public void setSeattype(Integer seattype) {
    this.seattype = seattype;
  }


  @Column(name = "RANK", nullable = true, precision = 0)
  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }


  @Column(name = "SEATMAPLOCATION", nullable = true, length = 50)
  public String getSeatmaplocation() {
    return seatmaplocation;
  }

  public void setSeatmaplocation(String seatmaplocation) {
    this.seatmaplocation = seatmaplocation;
  }


  @Column(name = "SEATSTATUS", nullable = true, length = 1)
  public String getSeatstatus() {
    return seatstatus;
  }

  public void setSeatstatus(String seatstatus) {
    this.seatstatus = seatstatus;
  }


  @Column(name = "TICKETABLE", nullable = true, precision = 0)
  public Integer getTicketable() {
    return ticketable;
  }

  public void setTicketable(Integer ticketable) {
    this.ticketable = ticketable;
  }


  @Column(name = "NEARESTENTRANCE", nullable = true, length = 50)
  public String getNearestentrance() {
    return nearestentrance;
  }

  public void setNearestentrance(String nearestentrance) {
    this.nearestentrance = nearestentrance;
  }


  @Column(name = "TICKETTYPE", nullable = true, length = 2)
  public String getTickettype() {
    return tickettype;
  }

  public void setTickettype(String tickettype) {
    this.tickettype = tickettype;
  }


  @Column(name = "SEATATTRIBUTES", nullable = true, precision = 0)
  public Integer getSeatattributes() {
    return seatattributes;
  }

  public void setSeatattributes(Integer seatattributes) {
    this.seatattributes = seatattributes;
  }


  @Column(name = "AREAALIAS", nullable = true, length = 255)
  public String getAreaalias() {
    return areaalias;
  }

  public void setAreaalias(String areaalias) {
    this.areaalias = areaalias;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenueSeatLc that = (VenueSeatLc) o;

    return new EqualsBuilder()
        .append(venueseatid, that.venueseatid)
        .append(lcId, that.lcId)
        .append(blockId, that.blockId)
        .append(levelId, that.levelId)
        .append(sectionId, that.sectionId)
        .append(rowId, that.rowId)
        .append(seatno, that.seatno)
        .append(seatalias, that.seatalias)
        .append(seattype, that.seattype)
        .append(rank, that.rank)
        .append(seatmaplocation, that.seatmaplocation)
        .append(seatstatus, that.seatstatus)
        .append(ticketable, that.ticketable)
        .append(nearestentrance, that.nearestentrance)
        .append(tickettype, that.tickettype)
        .append(seatattributes, that.seatattributes)
        .append(areaalias, that.areaalias)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(venueseatid)
        .append(lcId)
        .append(blockId)
        .append(levelId)
        .append(sectionId)
        .append(rowId)
        .append(seatno)
        .append(seatalias)
        .append(seattype)
        .append(rank)
        .append(seatmaplocation)
        .append(seatstatus)
        .append(ticketable)
        .append(nearestentrance)
        .append(tickettype)
        .append(seatattributes)
        .append(areaalias)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venueseatid", venueseatid)
        .append("lcId", lcId)
        .append("blockId", blockId)
        .append("levelId", levelId)
        .append("sectionId", sectionId)
        .append("rowId", rowId)
        .append("seatno", seatno)
        .append("seatalias", seatalias)
        .append("seattype", seattype)
        .append("rank", rank)
        .append("seatmaplocation", seatmaplocation)
        .append("seatstatus", seatstatus)
        .append("ticketable", ticketable)
        .append("nearestentrance", nearestentrance)
        .append("tickettype", tickettype)
        .append("seatattributes", seatattributes)
        .append("areaalias", areaalias)
        .toString();
  }
}

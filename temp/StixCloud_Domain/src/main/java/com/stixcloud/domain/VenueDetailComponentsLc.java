package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 09-Sep-16.
 */
@Entity
@Table(name = "VENUE_DETAIL_COMPONENTS_LC")
public class VenueDetailComponentsLc implements Serializable {
  private Long venuedetailcomponentslcid;
  private Long lcId;
  private Long blockId;
  private Integer type;
  private String displayvalue;
  private String shape;
  private String x;
  private String y;
  private Integer width;
  private Integer height;
  private Boolean isvertical;
  private Boolean isvisible;
  private Integer bgcolor;
  private Integer fgcolor;
  private Integer fontsize;
  private Integer rotation;

  public VenueDetailComponentsLc() {
  }

  public VenueDetailComponentsLc(Long venuedetailcomponentslcid, Long lcId, Long blockId,
                                 Integer type, String displayvalue, String shape, String x,
                                 String y, Integer width, Integer height,
                                 Boolean isvertical, Boolean isvisible, Integer bgcolor,
                                 Integer fgcolor, Integer fontsize, Integer rotation) {
    this.venuedetailcomponentslcid = venuedetailcomponentslcid;
    this.lcId = lcId;
    this.blockId = blockId;
    this.type = type;
    this.displayvalue = displayvalue;
    this.shape = shape;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.isvertical = isvertical;
    this.isvisible = isvisible;
    this.bgcolor = bgcolor;
    this.fgcolor = fgcolor;
    this.fontsize = fontsize;
    this.rotation = rotation;
  }

  @Id
  @Column(name = "VENUEDETAILCOMPONENTSLCID", nullable = false, precision = 0)
  public Long getVenuedetailcomponentslcid() {
    return venuedetailcomponentslcid;
  }

  public void setVenuedetailcomponentslcid(Long venuedetailcomponentslcid) {
    this.venuedetailcomponentslcid = venuedetailcomponentslcid;
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


  @Column(name = "TYPE", nullable = false, precision = 0)
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  @Column(name = "DISPLAYVALUE", nullable = true, length = 50)
  public String getDisplayvalue() {
    return displayvalue;
  }

  public void setDisplayvalue(String displayvalue) {
    this.displayvalue = displayvalue;
  }


  @Column(name = "SHAPE", nullable = true, length = 25)
  public String getShape() {
    return shape;
  }

  public void setShape(String shape) {
    this.shape = shape;
  }


  @Column(name = "X", nullable = true, length = 512)
  public String getX() {
    return x;
  }

  public void setX(String x) {
    this.x = x;
  }


  @Column(name = "Y", nullable = true, length = 512)
  public String getY() {
    return y;
  }

  public void setY(String y) {
    this.y = y;
  }


  @Column(name = "WIDTH", nullable = true, precision = 0)
  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }


  @Column(name = "HEIGHT", nullable = true, precision = 0)
  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }


  @Column(name = "ISVERTICAL", nullable = true, length = 1)
  @Type(type = "true_false")
  public Boolean getIsvertical() {
    return isvertical;
  }

  public void setIsvertical(Boolean isvertical) {
    this.isvertical = isvertical;
  }


  @Column(name = "ISVISIBLE", nullable = true, length = 1)
  @Type(type = "true_false")
  public Boolean getIsvisible() {
    return isvisible;
  }

  public void setIsvisible(Boolean isvisible) {
    this.isvisible = isvisible;
  }


  @Column(name = "BGCOLOR", nullable = true, precision = 0)
  public Integer getBgcolor() {
    return bgcolor;
  }

  public void setBgcolor(Integer bgcolor) {
    this.bgcolor = bgcolor;
  }


  @Column(name = "FGCOLOR", nullable = true, precision = 0)
  public Integer getFgcolor() {
    return fgcolor;
  }

  public void setFgcolor(Integer fgcolor) {
    this.fgcolor = fgcolor;
  }


  @Column(name = "FONTSIZE", nullable = true, precision = 0)
  public Integer getFontsize() {
    return fontsize;
  }

  public void setFontsize(Integer fontsize) {
    this.fontsize = fontsize;
  }


  @Column(name = "ROTATION", nullable = true, precision = 0)
  public Integer getRotation() {
    return rotation;
  }

  public void setRotation(Integer rotation) {
    this.rotation = rotation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenueDetailComponentsLc that = (VenueDetailComponentsLc) o;

    return new EqualsBuilder()
        .append(venuedetailcomponentslcid, that.venuedetailcomponentslcid)
        .append(lcId, that.lcId)
        .append(blockId, that.blockId)
        .append(type, that.type)
        .append(displayvalue, that.displayvalue)
        .append(shape, that.shape)
        .append(x, that.x)
        .append(y, that.y)
        .append(width, that.width)
        .append(height, that.height)
        .append(isvertical, that.isvertical)
        .append(isvisible, that.isvisible)
        .append(bgcolor, that.bgcolor)
        .append(fgcolor, that.fgcolor)
        .append(fontsize, that.fontsize)
        .append(rotation, that.rotation)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(venuedetailcomponentslcid)
        .append(lcId)
        .append(blockId)
        .append(type)
        .append(displayvalue)
        .append(shape)
        .append(x)
        .append(y)
        .append(width)
        .append(height)
        .append(isvertical)
        .append(isvisible)
        .append(bgcolor)
        .append(fgcolor)
        .append(fontsize)
        .append(rotation)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venuedetailcomponentslcid", venuedetailcomponentslcid)
        .append("lcId", lcId)
        .append("blockId", blockId)
        .append("type", type)
        .append("displayvalue", displayvalue)
        .append("shape", shape)
        .append("x", x)
        .append("y", y)
        .append("width", width)
        .append("height", height)
        .append("isvertical", isvertical)
        .append("isvisible", isvisible)
        .append("bgcolor", bgcolor)
        .append("fgcolor", fgcolor)
        .append("fontsize", fontsize)
        .append("rotation", rotation)
        .toString();
  }
}

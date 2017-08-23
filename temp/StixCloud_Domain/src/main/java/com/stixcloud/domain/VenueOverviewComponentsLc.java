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
 * Created by chongye on 07-Sep-16.
 */
@Entity
@Table(name = "VENUE_OVERVIEW_COMPONENTS_LC")
public class VenueOverviewComponentsLc implements Serializable {

  private static final long serialVersionUID = -5938028069906537834L;
  @Id
  private Long venueoverviewcomponentslcid;
  private Long lcId;
  private Integer type;
  private Long linkId;
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
  private String sectionalias;
  private String levelalias;

  public VenueOverviewComponentsLc() {
  }

  public VenueOverviewComponentsLc(Long venueoverviewcomponentslcid, Long lcId, Integer type,
                                   Long linkId, String displayvalue, String shape, String x,
                                   String y, Integer width, Integer height, Boolean isvertical,
                                   Boolean isvisible, Integer bgcolor, Integer fgcolor,
                                   Integer fontsize, String sectionalias, String levelalias) {
    this.venueoverviewcomponentslcid = venueoverviewcomponentslcid;
    this.lcId = lcId;
    this.type = type;
    this.linkId = linkId;
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
    this.sectionalias = sectionalias;
    this.levelalias = levelalias;
  }

  @Id
  @Column(name = "VENUEOVERVIEWCOMPONENTSLCID", nullable = false, precision = 0)
  public Long getVenueoverviewcomponentslcid() {
    return venueoverviewcomponentslcid;
  }

  public void setVenueoverviewcomponentslcid(Long venueoverviewcomponentslcid) {
    this.venueoverviewcomponentslcid = venueoverviewcomponentslcid;
  }

  @Column(name = "LC_ID", nullable = false, precision = 0)
  public Long getLcId() {
    return lcId;
  }

  public void setLcId(Long lcId) {
    this.lcId = lcId;
  }

  @Column(name = "TYPE", nullable = true, precision = 0)
  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  @Column(name = "LINK_ID", nullable = true, precision = 0)
  public Long getLinkId() {
    return linkId;
  }

  public void setLinkId(Long linkId) {
    this.linkId = linkId;
  }

  @Column(name = "DISPLAYVALUE", nullable = true, length = 256)
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

  @Column(name = "SECTIONALIAS", nullable = true, length = 50)
  public String getSectionalias() {
    return sectionalias;
  }

  public void setSectionalias(String sectionalias) {
    this.sectionalias = sectionalias;
  }

  @Column(name = "LEVELALIAS", nullable = true, length = 50)
  public String getLevelalias() {
    return levelalias;
  }

  public void setLevelalias(String levelalias) {
    this.levelalias = levelalias;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenueOverviewComponentsLc that = (VenueOverviewComponentsLc) o;

    return new EqualsBuilder()
        .append(venueoverviewcomponentslcid, that.venueoverviewcomponentslcid)
        .append(lcId, that.lcId)
        .append(type, that.type)
        .append(linkId, that.linkId)
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
        .append(sectionalias, that.sectionalias)
        .append(levelalias, that.levelalias)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(venueoverviewcomponentslcid)
        .append(lcId)
        .append(type)
        .append(linkId)
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
        .append(sectionalias)
        .append(levelalias)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venueoverviewcomponentslcid", venueoverviewcomponentslcid)
        .append("lcId", lcId)
        .append("type", type)
        .append("linkId", linkId)
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
        .append("sectionalias", sectionalias)
        .append("levelalias", levelalias)
        .toString();
  }
}

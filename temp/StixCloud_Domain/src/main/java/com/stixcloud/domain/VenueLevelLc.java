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
 * Created by chongye on 08-Sep-16.
 */
@Entity
@Table(name = "VENUE_LEVEL_LC")
public class VenueLevelLc implements Serializable {
  private Long venuelevellcid;
  private Long lcId;
  private String levelalias;
  private String leveltype;

  public VenueLevelLc() {
  }

  public VenueLevelLc(Long venuelevellcid, Long lcId, String levelalias, String leveltype) {
    this.venuelevellcid = venuelevellcid;
    this.lcId = lcId;
    this.levelalias = levelalias;
    this.leveltype = leveltype;
  }

  @Id
  @Column(name = "VENUELEVELLCID", nullable = false, precision = 0)
  public Long getVenuelevellcid() {
    return venuelevellcid;
  }

  public void setVenuelevellcid(Long venuelevellcid) {
    this.venuelevellcid = venuelevellcid;
  }


  @Column(name = "LC_ID", nullable = false, precision = 0)
  public Long getLcId() {
    return lcId;
  }

  public void setLcId(Long lcId) {
    this.lcId = lcId;
  }


  @Column(name = "LEVELALIAS", nullable = true, length = 50)
  public String getLevelalias() {
    return levelalias;
  }

  public void setLevelalias(String levelalias) {
    this.levelalias = levelalias;
  }


  @Column(name = "LEVELTYPE", nullable = false, length = 255)
  public String getLeveltype() {
    return leveltype;
  }

  public void setLeveltype(String leveltype) {
    this.leveltype = leveltype;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VenueLevelLc that = (VenueLevelLc) o;

    return new EqualsBuilder()
        .append(venuelevellcid, that.venuelevellcid)
        .append(lcId, that.lcId)
        .append(levelalias, that.levelalias)
        .append(leveltype, that.leveltype)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(venuelevellcid)
        .append(lcId)
        .append(levelalias)
        .append(leveltype)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("venuelevellcid", venuelevellcid)
        .append("lcId", lcId)
        .append("levelalias", levelalias)
        .append("leveltype", leveltype)
        .toString();
  }
}

package com.stixcloud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 01-Sep-16.
 */
@Entity
@Table(name = "SALES_SEAT_INVENTORY")
public class SalesSeatInventory implements Serializable {
  @Id
  private Long salesseatinventoryid;
  private Long productId;
  private Integer rank;
  private Integer seatsalesstatus;
  private Boolean ticketable;
  private Long priceCatId;
  private Long priceClassId;
  private Long patronId;
  private OffsetDateTime reserveexpirydate;
  private Long systemSaleCodeId;
  private Long holdBy;
  private OffsetDateTime holddate;
  private Long soldBy;
  private OffsetDateTime solddate;
  private OffsetDateTime updatedtime;
  private Long updatedBy;
  private String holdreason;
  private OffsetDateTime holdexpirynotified;
  private Integer sectionrank;
  private String spankey;
  private Long venueSeatLcId;
  private Long venueSectionLcId;
  private OffsetDateTime salescyclestarttime;
  private Long packageId;
  private Long packagerequirementId;
  private Long pricevalue;
  private String sessionid;
  private OffsetDateTime holdexpirydate;

  public SalesSeatInventory() {
  }

  public SalesSeatInventory(Long salesseatinventoryid, Long productId, Integer rank,
                            Integer seatsalesstatus, Boolean ticketable, Long priceCatId,
                            Long priceClassId, Long patronId, OffsetDateTime reserveexpirydate,
                            Long systemSaleCodeId, Long holdBy, OffsetDateTime holddate,
                            Long soldBy, OffsetDateTime solddate, OffsetDateTime updatedtime,
                            Long updatedBy, String holdreason, OffsetDateTime holdexpirynotified,
                            Integer sectionrank, String spankey, Long venueSeatLcId,
                            Long venueSectionLcId, OffsetDateTime salescyclestarttime,
                            Long packageId,
                            Long packagerequirementId, Long pricevalue, String sessionid,
                            OffsetDateTime holdexpirydate) {
    this.salesseatinventoryid = salesseatinventoryid;
    this.productId = productId;
    this.rank = rank;
    this.seatsalesstatus = seatsalesstatus;
    this.ticketable = ticketable;
    this.priceCatId = priceCatId;
    this.priceClassId = priceClassId;
    this.patronId = patronId;
    this.reserveexpirydate = reserveexpirydate;
    this.systemSaleCodeId = systemSaleCodeId;
    this.holdBy = holdBy;
    this.holddate = holddate;
    this.soldBy = soldBy;
    this.solddate = solddate;
    this.updatedtime = updatedtime;
    this.updatedBy = updatedBy;
    this.holdreason = holdreason;
    this.holdexpirynotified = holdexpirynotified;
    this.sectionrank = sectionrank;
    this.spankey = spankey;
    this.venueSeatLcId = venueSeatLcId;
    this.venueSectionLcId = venueSectionLcId;
    this.salescyclestarttime = salescyclestarttime;
    this.packageId = packageId;
    this.packagerequirementId = packagerequirementId;
    this.pricevalue = pricevalue;
    this.sessionid = sessionid;
    this.holdexpirydate = holdexpirydate;
  }

  @Id
  @Column(name = "SALESSEATINVENTORYID", nullable = false, precision = 0)
  public Long getSalesseatinventoryid() {
    return salesseatinventoryid;
  }

  public void setSalesseatinventoryid(Long salesseatinventoryid) {
    this.salesseatinventoryid = salesseatinventoryid;
  }

  @Column(name = "PRODUCT_ID", nullable = true, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Column(name = "RANK", nullable = true, precision = 0)
  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }

  @Column(name = "SEATSALESSTATUS", nullable = true, precision = 0)
  public Integer getSeatsalesstatus() {
    return seatsalesstatus;
  }

  public void setSeatsalesstatus(Integer seatsalesstatus) {
    this.seatsalesstatus = seatsalesstatus;
  }

  @Column(name = "TICKETABLE", nullable = true, precision = 0)
  @Type(type = "numeric_boolean")
  public Boolean getTicketable() {
    return ticketable;
  }

  public void setTicketable(Boolean ticketable) {
    this.ticketable = ticketable;
  }

  @Column(name = "PRICE_CAT_ID", nullable = true, precision = 0)
  public Long getPriceCatId() {
    return priceCatId;
  }

  public void setPriceCatId(Long priceCatId) {
    this.priceCatId = priceCatId;
  }

  @Column(name = "PRICE_CLASS_ID", nullable = true, precision = 0)
  public Long getPriceClassId() {
    return priceClassId;
  }

  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  @Column(name = "PATRON_ID", nullable = true, precision = 0)
  public Long getPatronId() {
    return patronId;
  }

  public void setPatronId(Long patronId) {
    this.patronId = patronId;
  }

  @Column(name = "RESERVEEXPIRYDATE", nullable = true)
  public OffsetDateTime getReserveexpirydate() {
    return reserveexpirydate;
  }

  public void setReserveexpirydate(OffsetDateTime reserveexpirydate) {
    this.reserveexpirydate = reserveexpirydate;
  }

  @Column(name = "SYSTEM_SALE_CODE_ID", nullable = true, precision = 0)
  public Long getSystemSaleCodeId() {
    return systemSaleCodeId;
  }

  public void setSystemSaleCodeId(Long systemSaleCodeId) {
    this.systemSaleCodeId = systemSaleCodeId;
  }

  @Column(name = "HOLD_BY", nullable = true, precision = 0)
  public Long getHoldBy() {
    return holdBy;
  }

  public void setHoldBy(Long holdBy) {
    this.holdBy = holdBy;
  }

  @Column(name = "HOLDDATE", nullable = true)
  public OffsetDateTime getHolddate() {
    return holddate;
  }

  public void setHolddate(OffsetDateTime holddate) {
    this.holddate = holddate;
  }

  @Column(name = "SOLD_BY", nullable = true, precision = 0)
  public Long getSoldBy() {
    return soldBy;
  }

  public void setSoldBy(Long soldBy) {
    this.soldBy = soldBy;
  }

  @Column(name = "SOLDDATE", nullable = true)
  public OffsetDateTime getSolddate() {
    return solddate;
  }

  public void setSolddate(OffsetDateTime solddate) {
    this.solddate = solddate;
  }

  @Column(name = "UPDATEDTIME", nullable = true)
  public OffsetDateTime getUpdatedtime() {
    return updatedtime;
  }

  public void setUpdatedtime(OffsetDateTime updatedtime) {
    this.updatedtime = updatedtime;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "HOLDREASON", nullable = true, length = 500)
  public String getHoldreason() {
    return holdreason;
  }

  public void setHoldreason(String holdreason) {
    this.holdreason = holdreason;
  }

  @Column(name = "HOLDEXPIRYNOTIFIED", nullable = true)
  public OffsetDateTime getHoldexpirynotified() {
    return holdexpirynotified;
  }

  public void setHoldexpirynotified(OffsetDateTime holdexpirynotified) {
    this.holdexpirynotified = holdexpirynotified;
  }

  @Column(name = "SECTIONRANK", nullable = true, precision = 0)
  public Integer getSectionrank() {
    return sectionrank;
  }

  public void setSectionrank(Integer sectionrank) {
    this.sectionrank = sectionrank;
  }

  @Column(name = "SPANKEY", nullable = true, length = 512)
  public String getSpankey() {
    return spankey;
  }

  public void setSpankey(String spankey) {
    this.spankey = spankey;
  }

  @Column(name = "VENUE_SEAT_LC_ID", nullable = true, precision = 0)
  public Long getVenueSeatLcId() {
    return venueSeatLcId;
  }

  public void setVenueSeatLcId(Long venueSeatLcId) {
    this.venueSeatLcId = venueSeatLcId;
  }

  @Column(name = "VENUE_SECTION_LC_ID", nullable = true, precision = 0)
  public Long getVenueSectionLcId() {
    return venueSectionLcId;
  }

  public void setVenueSectionLcId(Long venueSectionLcId) {
    this.venueSectionLcId = venueSectionLcId;
  }

  @Column(name = "SALESCYCLESTARTTIME", nullable = true)
  public OffsetDateTime getSalescyclestarttime() {
    return salescyclestarttime;
  }

  public void setSalescyclestarttime(OffsetDateTime salescyclestarttime) {
    this.salescyclestarttime = salescyclestarttime;
  }

  @Column(name = "PACKAGE_ID", nullable = true, precision = 0)
  public Long getPackageId() {
    return packageId;
  }

  public void setPackageId(Long packageId) {
    this.packageId = packageId;
  }

  @Column(name = "PACKAGEREQUIREMENT_ID", nullable = true, precision = 0)
  public Long getPackagerequirementId() {
    return packagerequirementId;
  }

  public void setPackagerequirementId(Long packagerequirementId) {
    this.packagerequirementId = packagerequirementId;
  }

  @Column(name = "PRICEVALUE", nullable = true, precision = 5)
  public Long getPricevalue() {
    return pricevalue;
  }

  public void setPricevalue(Long pricevalue) {
    this.pricevalue = pricevalue;
  }

  @Column(name = "SESSIONID", nullable = true, length = 100)
  public String getSessionid() {
    return sessionid;
  }

  public void setSessionid(String sessionid) {
    this.sessionid = sessionid;
  }

  @Column(name = "HOLDEXPIRYDATE", nullable = true)
  public OffsetDateTime getHoldexpirydate() {
    return holdexpirydate;
  }

  public void setHoldexpirydate(OffsetDateTime holdexpirydate) {
    this.holdexpirydate = holdexpirydate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SalesSeatInventory that = (SalesSeatInventory) o;

    return new EqualsBuilder()
        .append(salesseatinventoryid, that.salesseatinventoryid)
        .append(productId, that.productId)
        .append(rank, that.rank)
        .append(seatsalesstatus, that.seatsalesstatus)
        .append(ticketable, that.ticketable)
        .append(priceCatId, that.priceCatId)
        .append(priceClassId, that.priceClassId)
        .append(patronId, that.patronId)
        .append(reserveexpirydate, that.reserveexpirydate)
        .append(systemSaleCodeId, that.systemSaleCodeId)
        .append(holdBy, that.holdBy)
        .append(holddate, that.holddate)
        .append(soldBy, that.soldBy)
        .append(solddate, that.solddate)
        .append(updatedtime, that.updatedtime)
        .append(updatedBy, that.updatedBy)
        .append(holdreason, that.holdreason)
        .append(holdexpirynotified, that.holdexpirynotified)
        .append(sectionrank, that.sectionrank)
        .append(spankey, that.spankey)
        .append(venueSeatLcId, that.venueSeatLcId)
        .append(venueSectionLcId, that.venueSectionLcId)
        .append(salescyclestarttime, that.salescyclestarttime)
        .append(packageId, that.packageId)
        .append(packagerequirementId, that.packagerequirementId)
        .append(pricevalue, that.pricevalue)
        .append(sessionid, that.sessionid)
        .append(holdexpirydate, that.holdexpirydate)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(salesseatinventoryid)
        .append(productId)
        .append(rank)
        .append(seatsalesstatus)
        .append(ticketable)
        .append(priceCatId)
        .append(priceClassId)
        .append(patronId)
        .append(reserveexpirydate)
        .append(systemSaleCodeId)
        .append(holdBy)
        .append(holddate)
        .append(soldBy)
        .append(solddate)
        .append(updatedtime)
        .append(updatedBy)
        .append(holdreason)
        .append(holdexpirynotified)
        .append(sectionrank)
        .append(spankey)
        .append(venueSeatLcId)
        .append(venueSectionLcId)
        .append(salescyclestarttime)
        .append(packageId)
        .append(packagerequirementId)
        .append(pricevalue)
        .append(sessionid)
        .append(holdexpirydate)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("salesseatinventoryid", salesseatinventoryid)
        .append("productId", productId)
        .append("rank", rank)
        .append("seatsalesstatus", seatsalesstatus)
        .append("ticketable", ticketable)
        .append("priceCatId", priceCatId)
        .append("priceClassId", priceClassId)
        .append("patronId", patronId)
        .append("reserveexpirydate", reserveexpirydate)
        .append("systemSaleCodeId", systemSaleCodeId)
        .append("holdBy", holdBy)
        .append("holddate", holddate)
        .append("soldBy", soldBy)
        .append("solddate", solddate)
        .append("updatedtime", updatedtime)
        .append("updatedBy", updatedBy)
        .append("holdreason", holdreason)
        .append("holdexpirynotified", holdexpirynotified)
        .append("sectionrank", sectionrank)
        .append("spankey", spankey)
        .append("venueSeatLcId", venueSeatLcId)
        .append("venueSectionLcId", venueSectionLcId)
        .append("salescyclestarttime", salescyclestarttime)
        .append("packageId", packageId)
        .append("packagerequirementId", packagerequirementId)
        .append("pricevalue", pricevalue)
        .append("sessionid", sessionid)
        .append("holdexpirydate", holdexpirydate)
        .toString();
  }
}

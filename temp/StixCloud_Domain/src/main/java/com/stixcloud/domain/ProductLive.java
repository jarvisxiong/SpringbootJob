package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 19/10/2016.
 */
@Entity
@Table(name = "PRODUCT_LIVE")
public class ProductLive {
  private Long productid;
  private Long productGroupId;
  private String productname;
  private String productcode;
  private String glcode;
  private String description;
  private String remarks;
  private String keywordsearch;
  private Boolean reportvisibility;
  private Boolean financialreportvisibility;
  private String productowner;
  private Long weight;
  private Integer salesstatus;
  private Boolean activationstatus;
  private Integer maxticketsappl;
  private OffsetDateTime startdate;
  private OffsetDateTime enddate;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private Long stopSalesReasonMctId;
  private Long festivalCodeId;
  private Long spanCodeMctId;
  private Long venueId;
  private Long venuelayoutconfigId;
  private String salespic;
  private String eventbuilder;

  public ProductLive() {
  }

  @Id
  @Column(name = "PRODUCTID", nullable = false, precision = 0)
  public Long getProductid() {
    return productid;
  }

  public void setProductid(Long productid) {
    this.productid = productid;
  }

  @Column(name = "PRODUCT_GROUP_ID", nullable = false, precision = 0)
  public Long getProductGroupId() {
    return productGroupId;
  }

  public void setProductGroupId(Long productGroupId) {
    this.productGroupId = productGroupId;
  }

  @Column(name = "PRODUCTNAME", nullable = false, length = 255)
  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

  @Column(name = "PRODUCTCODE", nullable = false, length = 50)
  public String getProductcode() {
    return productcode;
  }

  public void setProductcode(String productcode) {
    this.productcode = productcode;
  }

  @Column(name = "GLCODE", nullable = false, length = 50)
  public String getGlcode() {
    return glcode;
  }

  public void setGlcode(String glcode) {
    this.glcode = glcode;
  }

  @Column(name = "DESCRIPTION", nullable = true, length = 500)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "REMARKS", nullable = true, length = 1000)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "KEYWORDSEARCH", nullable = true, length = 255)
  public String getKeywordsearch() {
    return keywordsearch;
  }

  public void setKeywordsearch(String keywordsearch) {
    this.keywordsearch = keywordsearch;
  }

  @Type(type = "true_false")
  @Column(name = "REPORTVISIBILITY", nullable = false, length = 1)
  public Boolean getReportvisibility() {
    return reportvisibility;
  }

  public void setReportvisibility(Boolean reportvisibility) {
    this.reportvisibility = reportvisibility;
  }

  @Type(type = "true_false")
  @Column(name = "FINANCIALREPORTVISIBILITY", nullable = false, length = 1)
  public Boolean getFinancialreportvisibility() {
    return financialreportvisibility;
  }

  public void setFinancialreportvisibility(Boolean financialreportvisibility) {
    this.financialreportvisibility = financialreportvisibility;
  }

  @Column(name = "PRODUCTOWNER", nullable = true, length = 128)
  public String getProductowner() {
    return productowner;
  }

  public void setProductowner(String productowner) {
    this.productowner = productowner;
  }

  @Column(name = "WEIGHT", nullable = true, precision = 2)
  public Long getWeight() {
    return weight;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
  }

  @Column(name = "SALESSTATUS", nullable = false, precision = 0)
  public Integer getSalesstatus() {
    return salesstatus;
  }

  public void setSalesstatus(Integer salesstatus) {
    this.salesstatus = salesstatus;
  }

  @Type(type = "numeric_boolean")
  @Column(name = "ACTIVATIONSTATUS", nullable = false, precision = 0)
  public Boolean getActivationstatus() {
    return activationstatus;
  }

  public void setActivationstatus(Boolean activationstatus) {
    this.activationstatus = activationstatus;
  }

  @Column(name = "MAXTICKETSAPPL", nullable = true, precision = 0)
  public Integer getMaxticketsappl() {
    return maxticketsappl;
  }

  public void setMaxticketsappl(Integer maxticketsappl) {
    this.maxticketsappl = maxticketsappl;
  }

  @Column(name = "STARTDATE", nullable = false)
  public OffsetDateTime getStartdate() {
    return startdate;
  }

  public void setStartdate(OffsetDateTime startdate) {
    this.startdate = startdate;
  }

  @Column(name = "ENDDATE", nullable = true)
  public OffsetDateTime getEnddate() {
    return enddate;
  }

  public void setEnddate(OffsetDateTime enddate) {
    this.enddate = enddate;
  }

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "STOP_SALES_REASON_MCT_ID", nullable = true, precision = 0)
  public Long getStopSalesReasonMctId() {
    return stopSalesReasonMctId;
  }

  public void setStopSalesReasonMctId(Long stopSalesReasonMctId) {
    this.stopSalesReasonMctId = stopSalesReasonMctId;
  }

  @Column(name = "FESTIVAL_CODE_ID", nullable = true, precision = 0)
  public Long getFestivalCodeId() {
    return festivalCodeId;
  }

  public void setFestivalCodeId(Long festivalCodeId) {
    this.festivalCodeId = festivalCodeId;
  }

  @Column(name = "SPAN_CODE_MCT_ID", nullable = true, precision = 0)
  public Long getSpanCodeMctId() {
    return spanCodeMctId;
  }

  public void setSpanCodeMctId(Long spanCodeMctId) {
    this.spanCodeMctId = spanCodeMctId;
  }

  @Column(name = "VENUE_ID", nullable = false, precision = 0)
  public Long getVenueId() {
    return venueId;
  }

  public void setVenueId(Long venueId) {
    this.venueId = venueId;
  }

  @Column(name = "VENUELAYOUTCONFIG_ID", nullable = true, precision = 0)
  public Long getVenuelayoutconfigId() {
    return venuelayoutconfigId;
  }

  public void setVenuelayoutconfigId(Long venuelayoutconfigId) {
    this.venuelayoutconfigId = venuelayoutconfigId;
  }

  @Column(name = "SALESPIC", nullable = true, length = 128)
  public String getSalespic() {
    return salespic;
  }

  public void setSalespic(String salespic) {
    this.salespic = salespic;
  }

  @Column(name = "EVENTBUILDER", nullable = true, length = 255)
  public String getEventbuilder() {
    return eventbuilder;
  }

  public void setEventbuilder(String eventbuilder) {
    this.eventbuilder = eventbuilder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductLive that = (ProductLive) o;

    return Objects.equals(this.productid, that.productid) &&
        Objects.equals(this.productGroupId, that.productGroupId) &&
        Objects.equals(this.productname, that.productname) &&
        Objects.equals(this.productcode, that.productcode) &&
        Objects.equals(this.glcode, that.glcode) &&
        Objects.equals(this.description, that.description) &&
        Objects.equals(this.remarks, that.remarks) &&
        Objects.equals(this.keywordsearch, that.keywordsearch) &&
        Objects.equals(this.reportvisibility, that.reportvisibility) &&
        Objects.equals(this.financialreportvisibility, that.financialreportvisibility) &&
        Objects.equals(this.productowner, that.productowner) &&
        Objects.equals(this.weight, that.weight) &&
        Objects.equals(this.salesstatus, that.salesstatus) &&
        Objects.equals(this.activationstatus, that.activationstatus) &&
        Objects.equals(this.maxticketsappl, that.maxticketsappl) &&
        Objects.equals(this.startdate, that.startdate) &&
        Objects.equals(this.enddate, that.enddate) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.stopSalesReasonMctId, that.stopSalesReasonMctId) &&
        Objects.equals(this.festivalCodeId, that.festivalCodeId) &&
        Objects.equals(this.spanCodeMctId, that.spanCodeMctId) &&
        Objects.equals(this.venueId, that.venueId) &&
        Objects.equals(this.venuelayoutconfigId, that.venuelayoutconfigId) &&
        Objects.equals(this.salespic, that.salespic) &&
        Objects.equals(this.eventbuilder, that.eventbuilder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productid, productGroupId, productname, productcode, glcode, description,
        remarks, keywordsearch, reportvisibility, financialreportvisibility, productowner,
        weight, salesstatus, activationstatus, maxticketsappl, startdate,
        enddate, createdBy, createddate, updatedBy, updateddate,
        stopSalesReasonMctId, festivalCodeId, spanCodeMctId, venueId, venuelayoutconfigId,
        salespic, eventbuilder);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productid", productid)
        .append("productGroupId", productGroupId)
        .append("productname", productname)
        .append("productcode", productcode)
        .append("glcode", glcode)
        .append("description", description)
        .append("remarks", remarks)
        .append("keywordsearch", keywordsearch)
        .append("reportvisibility", reportvisibility)
        .append("financialreportvisibility", financialreportvisibility)
        .append("productowner", productowner)
        .append("weight", weight)
        .append("salesstatus", salesstatus)
        .append("activationstatus", activationstatus)
        .append("maxticketsappl", maxticketsappl)
        .append("startdate", startdate)
        .append("enddate", enddate)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .append("stopSalesReasonMctId", stopSalesReasonMctId)
        .append("festivalCodeId", festivalCodeId)
        .append("spanCodeMctId", spanCodeMctId)
        .append("venueId", venueId)
        .append("venuelayoutconfigId", venuelayoutconfigId)
        .append("salespic", salespic)
        .append("eventbuilder", eventbuilder)
        .toString();
  }
}

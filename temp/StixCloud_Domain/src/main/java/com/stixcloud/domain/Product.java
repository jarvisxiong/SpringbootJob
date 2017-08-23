package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 3/9/2017.
 */
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

  private static final long serialVersionUID = 2662032419232337082L;
  private Long productid;
  private Long productGroupId;
  private String productname;
  private String productcode;
  private String glcode;
  private String description;
  private String remarks;
  private String keywordsearch;
  private String reportvisibility;
  private String financialreportvisibility;
  private String productowner;
  private Long weight;
  private Boolean activationstatus;
  private Boolean salesstatus;
  private Byte maxticketsappl;
  private Boolean status;
  private Boolean versionstatus;
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
  private Boolean publishtype;
  private Integer workingId;
  private String salespic;
  private String eventbuilder;

  public Product() {
  }

  public Product(Long productid, Long productGroupId, String productname,
                 String productcode, String glcode, String description, String remarks,
                 String keywordsearch, String reportvisibility,
                 String financialreportvisibility, String productowner, Long weight,
                 Boolean activationstatus, Boolean salesstatus, Byte maxticketsappl,
                 Boolean status, Boolean versionstatus, OffsetDateTime startdate,
                 OffsetDateTime enddate, Long createdBy, OffsetDateTime createddate,
                 Long updatedBy, OffsetDateTime updateddate, Long stopSalesReasonMctId,
                 Long festivalCodeId, Long spanCodeMctId, Long venueId,
                 Long venuelayoutconfigId, Boolean publishtype, Integer workingId,
                 String salespic, String eventbuilder) {

    this.productid = productid;
    this.productGroupId = productGroupId;
    this.productname = productname;
    this.productcode = productcode;
    this.glcode = glcode;
    this.description = description;
    this.remarks = remarks;
    this.keywordsearch = keywordsearch;
    this.reportvisibility = reportvisibility;
    this.financialreportvisibility = financialreportvisibility;
    this.productowner = productowner;
    this.weight = weight;
    this.activationstatus = activationstatus;
    this.salesstatus = salesstatus;
    this.maxticketsappl = maxticketsappl;
    this.status = status;
    this.versionstatus = versionstatus;
    this.startdate = startdate;
    this.enddate = enddate;
    this.createdBy = createdBy;
    this.createddate = createddate;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
    this.stopSalesReasonMctId = stopSalesReasonMctId;
    this.festivalCodeId = festivalCodeId;
    this.spanCodeMctId = spanCodeMctId;
    this.venueId = venueId;
    this.venuelayoutconfigId = venuelayoutconfigId;
    this.publishtype = publishtype;
    this.workingId = workingId;
    this.salespic = salespic;
    this.eventbuilder = eventbuilder;
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

  @Column(name = "REPORTVISIBILITY", nullable = false, length = 1)
  public String getReportvisibility() {
    return reportvisibility;
  }

  public void setReportvisibility(String reportvisibility) {
    this.reportvisibility = reportvisibility;
  }

  @Column(name = "FINANCIALREPORTVISIBILITY", nullable = false, length = 1)
  public String getFinancialreportvisibility() {
    return financialreportvisibility;
  }

  public void setFinancialreportvisibility(String financialreportvisibility) {
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

  @Column(name = "ACTIVATIONSTATUS", nullable = false, precision = 0)
  public Boolean getActivationstatus() {
    return activationstatus;
  }

  public void setActivationstatus(Boolean activationstatus) {
    this.activationstatus = activationstatus;
  }

  @Column(name = "SALESSTATUS", nullable = false, precision = 0)
  public Boolean getSalesstatus() {
    return salesstatus;
  }

  public void setSalesstatus(Boolean salesstatus) {
    this.salesstatus = salesstatus;
  }

  @Column(name = "MAXTICKETSAPPL", nullable = true, precision = 0)
  public Byte getMaxticketsappl() {
    return maxticketsappl;
  }

  public void setMaxticketsappl(Byte maxticketsappl) {
    this.maxticketsappl = maxticketsappl;
  }

  @Column(name = "STATUS", nullable = false, precision = 0)
  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Column(name = "VERSIONSTATUS", nullable = true, precision = 0)
  public Boolean getVersionstatus() {
    return versionstatus;
  }

  public void setVersionstatus(Boolean versionstatus) {
    this.versionstatus = versionstatus;
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

  @Column(name = "PUBLISHTYPE", nullable = false, precision = 0)
  public Boolean getPublishtype() {
    return publishtype;
  }

  public void setPublishtype(Boolean publishtype) {
    this.publishtype = publishtype;
  }

  @Column(name = "WORKING_ID", nullable = true, precision = 0)
  public Integer getWorkingId() {
    return workingId;
  }

  public void setWorkingId(Integer workingId) {
    this.workingId = workingId;
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
    Product product = (Product) o;
    return Objects.equals(productid, product.productid) &&
        Objects.equals(productGroupId, product.productGroupId) &&
        Objects.equals(productname, product.productname) &&
        Objects.equals(productcode, product.productcode) &&
        Objects.equals(glcode, product.glcode) &&
        Objects.equals(description, product.description) &&
        Objects.equals(remarks, product.remarks) &&
        Objects.equals(keywordsearch, product.keywordsearch) &&
        Objects.equals(reportvisibility, product.reportvisibility) &&
        Objects.equals(financialreportvisibility, product.financialreportvisibility) &&
        Objects.equals(productowner, product.productowner) &&
        Objects.equals(weight, product.weight) &&
        Objects.equals(activationstatus, product.activationstatus) &&
        Objects.equals(salesstatus, product.salesstatus) &&
        Objects.equals(maxticketsappl, product.maxticketsappl) &&
        Objects.equals(status, product.status) &&
        Objects.equals(versionstatus, product.versionstatus) &&
        Objects.equals(startdate, product.startdate) &&
        Objects.equals(enddate, product.enddate) &&
        Objects.equals(createdBy, product.createdBy) &&
        Objects.equals(createddate, product.createddate) &&
        Objects.equals(updatedBy, product.updatedBy) &&
        Objects.equals(updateddate, product.updateddate) &&
        Objects.equals(stopSalesReasonMctId, product.stopSalesReasonMctId) &&
        Objects.equals(festivalCodeId, product.festivalCodeId) &&
        Objects.equals(spanCodeMctId, product.spanCodeMctId) &&
        Objects.equals(venueId, product.venueId) &&
        Objects.equals(venuelayoutconfigId, product.venuelayoutconfigId) &&
        Objects.equals(publishtype, product.publishtype) &&
        Objects.equals(workingId, product.workingId) &&
        Objects.equals(salespic, product.salespic) &&
        Objects.equals(eventbuilder, product.eventbuilder);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(productid, productGroupId, productname, productcode, glcode, description, remarks,
            keywordsearch, reportvisibility, financialreportvisibility, productowner, weight,
            activationstatus, salesstatus, maxticketsappl, status, versionstatus, startdate,
            enddate,
            createdBy, createddate, updatedBy, updateddate, stopSalesReasonMctId, festivalCodeId,
            spanCodeMctId, venueId, venuelayoutconfigId, publishtype, workingId, salespic,
            eventbuilder);
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
        .append("activationstatus", activationstatus)
        .append("salesstatus", salesstatus)
        .append("maxticketsappl", maxticketsappl)
        .append("status", status)
        .append("versionstatus", versionstatus)
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
        .append("publishtype", publishtype)
        .append("workingId", workingId)
        .append("salespic", salespic)
        .append("eventbuilder", eventbuilder)
        .toString();
  }
}

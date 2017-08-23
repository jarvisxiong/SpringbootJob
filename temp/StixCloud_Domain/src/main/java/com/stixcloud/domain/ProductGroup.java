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
@Table(name = "PRODUCT_GROUP")
public class ProductGroup implements Serializable {

  private static final long serialVersionUID = 6436308816194374088L;
  private Long productgroupid;
  private String productgroupname;
  private String productgroupcode;
  private String glcode;
  private String description;
  private String remarks;
  private String istaxinclusive;
  private Long taxPercentId;
  private String keywordsearch;
  private String portalurl;
  private String reportvisibility;
  private String financialreportvisibility;
  private String productowner;
  private String internetcontentcode;
  private Long createdBy;
  private OffsetDateTime createddate;
  private Long updatedBy;
  private OffsetDateTime updateddate;
  private Long weight;
  private Long festivalCodeId;
  private Long categoryCodeMctId;
  private Long spanCodeMctId;
  private Boolean status;
  private Boolean versionstatus;
  private Integer workingId;
  private String salespic;
  private String eventbuilder;

  public ProductGroup() {
  }

  public ProductGroup(Long productgroupid, String productgroupname, String productgroupcode,
                      String glcode, String description, String remarks,
                      String istaxinclusive, Long taxPercentId, String keywordsearch,
                      String portalurl, String reportvisibility,
                      String financialreportvisibility, String productowner,
                      String internetcontentcode, Long createdBy,
                      OffsetDateTime createddate, Long updatedBy,
                      OffsetDateTime updateddate, Long weight, Long festivalCodeId,
                      Long categoryCodeMctId, Long spanCodeMctId, Boolean status,
                      Boolean versionstatus, Integer workingId, String salespic,
                      String eventbuilder) {

    this.productgroupid = productgroupid;
    this.productgroupname = productgroupname;
    this.productgroupcode = productgroupcode;
    this.glcode = glcode;
    this.description = description;
    this.remarks = remarks;
    this.istaxinclusive = istaxinclusive;
    this.taxPercentId = taxPercentId;
    this.keywordsearch = keywordsearch;
    this.portalurl = portalurl;
    this.reportvisibility = reportvisibility;
    this.financialreportvisibility = financialreportvisibility;
    this.productowner = productowner;
    this.internetcontentcode = internetcontentcode;
    this.createdBy = createdBy;
    this.createddate = createddate;
    this.updatedBy = updatedBy;
    this.updateddate = updateddate;
    this.weight = weight;
    this.festivalCodeId = festivalCodeId;
    this.categoryCodeMctId = categoryCodeMctId;
    this.spanCodeMctId = spanCodeMctId;
    this.status = status;
    this.versionstatus = versionstatus;
    this.workingId = workingId;
    this.salespic = salespic;
    this.eventbuilder = eventbuilder;
  }

  @Id
  @Column(name = "PRODUCTGROUPID", nullable = false, precision = 0)
  public Long getProductgroupid() {
    return productgroupid;
  }

  public void setProductgroupid(Long productgroupid) {
    this.productgroupid = productgroupid;
  }

  @Column(name = "PRODUCTGROUPNAME", nullable = false, length = 255)
  public String getProductgroupname() {
    return productgroupname;
  }

  public void setProductgroupname(String productgroupname) {
    this.productgroupname = productgroupname;
  }

  @Column(name = "PRODUCTGROUPCODE", nullable = false, length = 50)
  public String getProductgroupcode() {
    return productgroupcode;
  }

  public void setProductgroupcode(String productgroupcode) {
    this.productgroupcode = productgroupcode;
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

  @Column(name = "ISTAXINCLUSIVE", nullable = false, length = 1)
  public String getIstaxinclusive() {
    return istaxinclusive;
  }

  public void setIstaxinclusive(String istaxinclusive) {
    this.istaxinclusive = istaxinclusive;
  }

  @Column(name = "TAX_PERCENT_ID", nullable = true, precision = 0)
  public Long getTaxPercentId() {
    return taxPercentId;
  }

  public void setTaxPercentId(Long taxPercentId) {
    this.taxPercentId = taxPercentId;
  }

  @Column(name = "KEYWORDSEARCH", nullable = true, length = 255)
  public String getKeywordsearch() {
    return keywordsearch;
  }

  public void setKeywordsearch(String keywordsearch) {
    this.keywordsearch = keywordsearch;
  }

  @Column(name = "PORTALURL", nullable = true, length = 500)
  public String getPortalurl() {
    return portalurl;
  }

  public void setPortalurl(String portalurl) {
    this.portalurl = portalurl;
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

  @Column(name = "INTERNETCONTENTCODE", nullable = true, length = 50)
  public String getInternetcontentcode() {
    return internetcontentcode;
  }

  public void setInternetcontentcode(String internetcontentcode) {
    this.internetcontentcode = internetcontentcode;
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

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
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

  @Column(name = "WEIGHT", nullable = true, precision = 2)
  public Long getWeight() {
    return weight;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
  }

  @Column(name = "FESTIVAL_CODE_ID", nullable = true, precision = 0)
  public Long getFestivalCodeId() {
    return festivalCodeId;
  }

  public void setFestivalCodeId(Long festivalCodeId) {
    this.festivalCodeId = festivalCodeId;
  }

  @Column(name = "CATEGORY_CODE_MCT_ID", nullable = false, precision = 0)
  public Long getCategoryCodeMctId() {
    return categoryCodeMctId;
  }

  public void setCategoryCodeMctId(Long categoryCodeMctId) {
    this.categoryCodeMctId = categoryCodeMctId;
  }

  @Column(name = "SPAN_CODE_MCT_ID", nullable = true, precision = 0)
  public Long getSpanCodeMctId() {
    return spanCodeMctId;
  }

  public void setSpanCodeMctId(Long spanCodeMctId) {
    this.spanCodeMctId = spanCodeMctId;
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
    ProductGroup that = (ProductGroup) o;
    return Objects.equals(productgroupid, that.productgroupid) &&
        Objects.equals(productgroupname, that.productgroupname) &&
        Objects.equals(productgroupcode, that.productgroupcode) &&
        Objects.equals(glcode, that.glcode) &&
        Objects.equals(description, that.description) &&
        Objects.equals(remarks, that.remarks) &&
        Objects.equals(istaxinclusive, that.istaxinclusive) &&
        Objects.equals(taxPercentId, that.taxPercentId) &&
        Objects.equals(keywordsearch, that.keywordsearch) &&
        Objects.equals(portalurl, that.portalurl) &&
        Objects.equals(reportvisibility, that.reportvisibility) &&
        Objects.equals(financialreportvisibility, that.financialreportvisibility) &&
        Objects.equals(productowner, that.productowner) &&
        Objects.equals(internetcontentcode, that.internetcontentcode) &&
        Objects.equals(createdBy, that.createdBy) &&
        Objects.equals(createddate, that.createddate) &&
        Objects.equals(updatedBy, that.updatedBy) &&
        Objects.equals(updateddate, that.updateddate) &&
        Objects.equals(weight, that.weight) &&
        Objects.equals(festivalCodeId, that.festivalCodeId) &&
        Objects.equals(categoryCodeMctId, that.categoryCodeMctId) &&
        Objects.equals(spanCodeMctId, that.spanCodeMctId) &&
        Objects.equals(status, that.status) &&
        Objects.equals(versionstatus, that.versionstatus) &&
        Objects.equals(workingId, that.workingId) &&
        Objects.equals(salespic, that.salespic) &&
        Objects.equals(eventbuilder, that.eventbuilder);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(productgroupid, productgroupname, productgroupcode, glcode, description, remarks,
            istaxinclusive, taxPercentId, keywordsearch, portalurl, reportvisibility,
            financialreportvisibility, productowner, internetcontentcode, createdBy, createddate,
            updatedBy, updateddate, weight, festivalCodeId, categoryCodeMctId, spanCodeMctId,
            status,
            versionstatus, workingId, salespic, eventbuilder);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("productgroupid", productgroupid)
        .append("productgroupname", productgroupname)
        .append("productgroupcode", productgroupcode)
        .append("glcode", glcode)
        .append("description", description)
        .append("remarks", remarks)
        .append("istaxinclusive", istaxinclusive)
        .append("taxPercentId", taxPercentId)
        .append("keywordsearch", keywordsearch)
        .append("portalurl", portalurl)
        .append("reportvisibility", reportvisibility)
        .append("financialreportvisibility", financialreportvisibility)
        .append("productowner", productowner)
        .append("internetcontentcode", internetcontentcode)
        .append("createdBy", createdBy)
        .append("createddate", createddate)
        .append("updatedBy", updatedBy)
        .append("updateddate", updateddate)
        .append("weight", weight)
        .append("festivalCodeId", festivalCodeId)
        .append("categoryCodeMctId", categoryCodeMctId)
        .append("spanCodeMctId", spanCodeMctId)
        .append("status", status)
        .append("versionstatus", versionstatus)
        .append("workingId", workingId)
        .append("salespic", salespic)
        .append("eventbuilder", eventbuilder)
        .toString();
  }
}

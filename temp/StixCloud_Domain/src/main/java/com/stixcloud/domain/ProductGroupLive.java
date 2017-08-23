package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chongye on 6/1/2017.
 */
@Entity
@Table(name = "PRODUCT_GROUP_LIVE")
public class ProductGroupLive {
  private Long productgroupid;

  @Id
  @Column(name = "PRODUCTGROUPID", nullable = false, precision = 0)
  public Long getProductgroupid() {
    return productgroupid;
  }

  public void setProductgroupid(Long productgroupid) {
    this.productgroupid = productgroupid;
  }

  private String productgroupname;

  @Column(name = "PRODUCTGROUPNAME", nullable = false, length = 255)
  public String getProductgroupname() {
    return productgroupname;
  }

  public void setProductgroupname(String productgroupname) {
    this.productgroupname = productgroupname;
  }

  private String productgroupcode;

  @Column(name = "PRODUCTGROUPCODE", nullable = false, length = 50)
  public String getProductgroupcode() {
    return productgroupcode;
  }

  public void setProductgroupcode(String productgroupcode) {
    this.productgroupcode = productgroupcode;
  }

  private String glcode;

  @Column(name = "GLCODE", nullable = false, length = 50)
  public String getGlcode() {
    return glcode;
  }

  public void setGlcode(String glcode) {
    this.glcode = glcode;
  }

  private String description;

  @Column(name = "DESCRIPTION", nullable = true, length = 500)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private String remarks;

  @Column(name = "REMARKS", nullable = true, length = 1000)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  private String istaxinclusive;

  @Column(name = "ISTAXINCLUSIVE", nullable = false, length = 1)
  public String getIstaxinclusive() {
    return istaxinclusive;
  }

  public void setIstaxinclusive(String istaxinclusive) {
    this.istaxinclusive = istaxinclusive;
  }

  private Long taxPercentId;

  @Column(name = "TAX_PERCENT_ID", nullable = true, precision = 0)
  public Long getTaxPercentId() {
    return taxPercentId;
  }

  public void setTaxPercentId(Long taxPercentId) {
    this.taxPercentId = taxPercentId;
  }

  private String keywordsearch;

  @Column(name = "KEYWORDSEARCH", nullable = true, length = 255)
  public String getKeywordsearch() {
    return keywordsearch;
  }

  public void setKeywordsearch(String keywordsearch) {
    this.keywordsearch = keywordsearch;
  }

  private String portalurl;

  @Column(name = "PORTALURL", nullable = true, length = 500)
  public String getPortalurl() {
    return portalurl;
  }

  public void setPortalurl(String portalurl) {
    this.portalurl = portalurl;
  }

  private String reportvisibility;

  @Column(name = "REPORTVISIBILITY", nullable = false, length = 1)
  public String getReportvisibility() {
    return reportvisibility;
  }

  public void setReportvisibility(String reportvisibility) {
    this.reportvisibility = reportvisibility;
  }

  private String financialreportvisibility;

  @Column(name = "FINANCIALREPORTVISIBILITY", nullable = false, length = 1)
  public String getFinancialreportvisibility() {
    return financialreportvisibility;
  }

  public void setFinancialreportvisibility(String financialreportvisibility) {
    this.financialreportvisibility = financialreportvisibility;
  }

  private String productowner;

  @Column(name = "PRODUCTOWNER", nullable = true, length = 128)
  public String getProductowner() {
    return productowner;
  }

  public void setProductowner(String productowner) {
    this.productowner = productowner;
  }

  private String internetcontentcode;

  @Column(name = "INTERNETCONTENTCODE", nullable = true, length = 50)
  public String getInternetcontentcode() {
    return internetcontentcode;
  }

  public void setInternetcontentcode(String internetcontentcode) {
    this.internetcontentcode = internetcontentcode;
  }

  private Long createdBy;

  @Column(name = "CREATED_BY", nullable = false, precision = 0)
  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  private OffsetDateTime createddate;

  @Column(name = "CREATEDDATE", nullable = false)
  public OffsetDateTime getCreateddate() {
    return createddate;
  }

  public void setCreateddate(OffsetDateTime createddate) {
    this.createddate = createddate;
  }

  private Long updatedBy;

  @Column(name = "UPDATED_BY", nullable = true, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  private OffsetDateTime updateddate;

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  private BigDecimal weight;

  @Column(name = "WEIGHT", nullable = true, precision = 2)
  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  private Long festivalCodeId;

  @Column(name = "FESTIVAL_CODE_ID", nullable = true, precision = 0)
  public Long getFestivalCodeId() {
    return festivalCodeId;
  }

  public void setFestivalCodeId(Long festivalCodeId) {
    this.festivalCodeId = festivalCodeId;
  }

  private Long categoryCodeMctId;

  @Column(name = "CATEGORY_CODE_MCT_ID", nullable = false, precision = 0)
  public Long getCategoryCodeMctId() {
    return categoryCodeMctId;
  }

  public void setCategoryCodeMctId(Long categoryCodeMctId) {
    this.categoryCodeMctId = categoryCodeMctId;
  }

  private Long spanCodeMctId;

  @Column(name = "SPAN_CODE_MCT_ID", nullable = true, precision = 0)
  public Long getSpanCodeMctId() {
    return spanCodeMctId;
  }

  public void setSpanCodeMctId(Long spanCodeMctId) {
    this.spanCodeMctId = spanCodeMctId;
  }

  private String salespic;

  @Column(name = "SALESPIC", nullable = true, length = 128)
  public String getSalespic() {
    return salespic;
  }

  public void setSalespic(String salespic) {
    this.salespic = salespic;
  }

  private String eventbuilder;

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

    ProductGroupLive that = (ProductGroupLive) o;

    return Objects.equals(this.categoryCodeMctId, that.categoryCodeMctId) &&
        Objects.equals(this.createdBy, that.createdBy) &&
        Objects.equals(this.createddate, that.createddate) &&
        Objects.equals(this.description, that.description) &&
        Objects.equals(this.eventbuilder, that.eventbuilder) &&
        Objects.equals(this.festivalCodeId, that.festivalCodeId) &&
        Objects.equals(this.financialreportvisibility, that.financialreportvisibility) &&
        Objects.equals(this.glcode, that.glcode) &&
        Objects.equals(this.internetcontentcode, that.internetcontentcode) &&
        Objects.equals(this.istaxinclusive, that.istaxinclusive) &&
        Objects.equals(this.keywordsearch, that.keywordsearch) &&
        Objects.equals(this.portalurl, that.portalurl) &&
        Objects.equals(this.productgroupcode, that.productgroupcode) &&
        Objects.equals(this.productgroupid, that.productgroupid) &&
        Objects.equals(this.productgroupname, that.productgroupname) &&
        Objects.equals(this.productowner, that.productowner) &&
        Objects.equals(this.remarks, that.remarks) &&
        Objects.equals(this.reportvisibility, that.reportvisibility) &&
        Objects.equals(this.salespic, that.salespic) &&
        Objects.equals(this.spanCodeMctId, that.spanCodeMctId) &&
        Objects.equals(this.taxPercentId, that.taxPercentId) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate) &&
        Objects.equals(this.weight, that.weight);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(categoryCodeMctId, createdBy, createddate, description, eventbuilder, festivalCodeId,
            financialreportvisibility, glcode, internetcontentcode, istaxinclusive, keywordsearch,
            portalurl, productgroupcode, productgroupid, productgroupname, productowner,
            remarks, reportvisibility, salespic, spanCodeMctId, taxPercentId,
            updatedBy, updateddate, weight);
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
        .append("salespic", salespic)
        .append("eventbuilder", eventbuilder)
        .toString();
  }
}

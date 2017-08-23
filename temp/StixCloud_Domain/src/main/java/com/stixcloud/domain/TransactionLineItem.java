package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 12/14/2016.
 */
@Entity
@Table(name = "TRANSACTION_LINE_ITEM")
public class TransactionLineItem {
  private Long txnlineitemid;
  private Long transactionId;
  private String transactionrefnumber;
  private Integer lineitemtype;
  private String description;
  private BigDecimal basepricevalue;
  private BigDecimal unitprice;
  private Integer quantity;
  private BigDecimal totallineitemvalue;
  private Long productId;
  private Long packageId;
  private String productname;
  private String venuename;
  private Long priceCategoryId;
  private String pricecategoryname;
  private Long priceClassId;
  private String priceclassname;
  private Integer productcategory;
  private String feename;
  private String feecategory;
  private String levyby;
  private Boolean isfeewaived;
  private Long feeId;
  private Integer chargetype;
  private Integer pricecategorynum;
  private String priceclasscode;

  public TransactionLineItem() {
  }

  public TransactionLineItem(Long txnlineitemid, Long transactionId,
                             String transactionrefnumber, Integer lineitemtype,
                             String description, BigDecimal basepricevalue, BigDecimal unitprice,
                             Integer quantity, BigDecimal totallineitemvalue, Long productId,
                             Long packageId, String productname, String venuename,
                             Long priceCategoryId, String pricecategoryname,
                             Long priceClassId, String priceclassname,
                             Integer productcategory, String feename, String feecategory,
                             String levyby, Boolean isfeewaived, Long feeId,
                             Integer chargetype, Integer pricecategorynum,
                             String priceclasscode) {
    this.txnlineitemid = txnlineitemid;
    this.transactionId = transactionId;
    this.transactionrefnumber = transactionrefnumber;
    this.lineitemtype = lineitemtype;
    this.description = description;
    this.basepricevalue = basepricevalue;
    this.unitprice = unitprice;
    this.quantity = quantity;
    this.totallineitemvalue = totallineitemvalue;
    this.productId = productId;
    this.packageId = packageId;
    this.productname = productname;
    this.venuename = venuename;
    this.priceCategoryId = priceCategoryId;
    this.pricecategoryname = pricecategoryname;
    this.priceClassId = priceClassId;
    this.priceclassname = priceclassname;
    this.productcategory = productcategory;
    this.feename = feename;
    this.feecategory = feecategory;
    this.levyby = levyby;
    this.isfeewaived = isfeewaived;
    this.feeId = feeId;
    this.chargetype = chargetype;
    this.pricecategorynum = pricecategorynum;
    this.priceclasscode = priceclasscode;
  }

  @Id
  @Column(name = "TXNLINEITEMID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TxnLineItemIdSeq")
  @GenericGenerator(name = "TxnLineItemIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TXN_LINE_ITEM_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTxnlineitemid() {
    return txnlineitemid;
  }

  public void setTxnlineitemid(Long txnlineitemid) {
    this.txnlineitemid = txnlineitemid;
  }

  @Column(name = "TRANSACTION_ID", nullable = false, precision = 0)
  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  @Column(name = "TRANSACTIONREFNUMBER", nullable = false, length = 40)
  public String getTransactionrefnumber() {
    return transactionrefnumber;
  }

  public void setTransactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
  }

  @Column(name = "LINEITEMTYPE", nullable = false, precision = 0)
  public Integer getLineitemtype() {
    return lineitemtype;
  }

  public void setLineitemtype(Integer lineitemtype) {
    this.lineitemtype = lineitemtype;
  }

  @Column(name = "DESCRIPTION", nullable = true, length = 500)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "BASEPRICEVALUE", nullable = true, precision = 5)
  public BigDecimal getBasepricevalue() {
    return basepricevalue;
  }

  public void setBasepricevalue(BigDecimal basepricevalue) {
    this.basepricevalue = basepricevalue;
  }

  @Column(name = "UNITPRICE", nullable = true, precision = 5)
  public BigDecimal getUnitprice() {
    return unitprice;
  }

  public void setUnitprice(BigDecimal unitprice) {
    this.unitprice = unitprice;
  }

  @Column(name = "QUANTITY", nullable = true, precision = 0)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Column(name = "TOTALLINEITEMVALUE", nullable = false, precision = 5)
  public BigDecimal getTotallineitemvalue() {
    return totallineitemvalue;
  }

  public void setTotallineitemvalue(BigDecimal totallineitemvalue) {
    this.totallineitemvalue = totallineitemvalue;
  }

  @Column(name = "PRODUCT_ID", nullable = true, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Column(name = "PACKAGE_ID", nullable = true, precision = 0)
  public Long getPackageId() {
    return packageId;
  }

  public void setPackageId(Long packageId) {
    this.packageId = packageId;
  }

  @Column(name = "PRODUCTNAME", nullable = true, length = 255)
  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

  @Column(name = "VENUENAME", nullable = true, length = 255)
  public String getVenuename() {
    return venuename;
  }

  public void setVenuename(String venuename) {
    this.venuename = venuename;
  }

  @Column(name = "PRICE_CATEGORY_ID", nullable = true, precision = 0)
  public Long getPriceCategoryId() {
    return priceCategoryId;
  }

  public void setPriceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
  }

  @Column(name = "PRICECATEGORYNAME", nullable = true, length = 50)
  public String getPricecategoryname() {
    return pricecategoryname;
  }

  public void setPricecategoryname(String pricecategoryname) {
    this.pricecategoryname = pricecategoryname;
  }

  @Column(name = "PRICE_CLASS_ID", nullable = true, precision = 0)
  public Long getPriceClassId() {
    return priceClassId;
  }

  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  @Column(name = "PRICECLASSNAME", nullable = true, length = 100)
  public String getPriceclassname() {
    return priceclassname;
  }

  public void setPriceclassname(String priceclassname) {
    this.priceclassname = priceclassname;
  }

  @Column(name = "PRODUCTCATEGORY", nullable = true, precision = 0)
  public Integer getProductcategory() {
    return productcategory;
  }

  public void setProductcategory(Integer productcategory) {
    this.productcategory = productcategory;
  }

  @Column(name = "FEENAME", nullable = true, length = 50)
  public String getFeename() {
    return feename;
  }

  public void setFeename(String feename) {
    this.feename = feename;
  }

  @Column(name = "FEECATEGORY", nullable = true, length = 25)
  public String getFeecategory() {
    return feecategory;
  }

  public void setFeecategory(String feecategory) {
    this.feecategory = feecategory;
  }

  @Column(name = "LEVYBY", nullable = true, length = 25)
  public String getLevyby() {
    return levyby;
  }

  public void setLevyby(String levyby) {
    this.levyby = levyby;
  }

  @Type(type = "true_false")
  @Column(name = "ISFEEWAIVED", nullable = true, length = 1)
  public Boolean getIsfeewaived() {
    return isfeewaived;
  }

  public void setIsfeewaived(Boolean isfeewaived) {
    this.isfeewaived = isfeewaived;
  }

  @Column(name = "FEE_ID", nullable = true, precision = 0)
  public Long getFeeId() {
    return feeId;
  }

  public void setFeeId(Long feeId) {
    this.feeId = feeId;
  }

  @Column(name = "CHARGETYPE", nullable = true, precision = 0)
  public Integer getChargetype() {
    return chargetype;
  }

  public void setChargetype(Integer chargetype) {
    this.chargetype = chargetype;
  }

  @Column(name = "PRICECATEGORYNUM", nullable = true, precision = 0)
  public Integer getPricecategorynum() {
    return pricecategorynum;
  }

  public void setPricecategorynum(Integer pricecategorynum) {
    this.pricecategorynum = pricecategorynum;
  }

  @Column(name = "PRICECLASSCODE", nullable = true, length = 25)
  public String getPriceclasscode() {
    return priceclasscode;
  }

  public void setPriceclasscode(String priceclasscode) {
    this.priceclasscode = priceclasscode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionLineItem that = (TransactionLineItem) o;
    return Objects.equals(txnlineitemid, that.txnlineitemid) &&
        Objects.equals(transactionId, that.transactionId) &&
        Objects.equals(transactionrefnumber, that.transactionrefnumber) &&
        Objects.equals(lineitemtype, that.lineitemtype) &&
        Objects.equals(description, that.description) &&
        Objects.equals(basepricevalue, that.basepricevalue) &&
        Objects.equals(unitprice, that.unitprice) &&
        Objects.equals(quantity, that.quantity) &&
        Objects.equals(totallineitemvalue, that.totallineitemvalue) &&
        Objects.equals(productId, that.productId) &&
        Objects.equals(packageId, that.packageId) &&
        Objects.equals(productname, that.productname) &&
        Objects.equals(venuename, that.venuename) &&
        Objects.equals(priceCategoryId, that.priceCategoryId) &&
        Objects.equals(pricecategoryname, that.pricecategoryname) &&
        Objects.equals(priceClassId, that.priceClassId) &&
        Objects.equals(priceclassname, that.priceclassname) &&
        Objects.equals(productcategory, that.productcategory) &&
        Objects.equals(feename, that.feename) &&
        Objects.equals(feecategory, that.feecategory) &&
        Objects.equals(levyby, that.levyby) &&
        Objects.equals(isfeewaived, that.isfeewaived) &&
        Objects.equals(feeId, that.feeId) &&
        Objects.equals(chargetype, that.chargetype) &&
        Objects.equals(pricecategorynum, that.pricecategorynum) &&
        Objects.equals(priceclasscode, that.priceclasscode);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(txnlineitemid, transactionId, transactionrefnumber, lineitemtype, description,
            basepricevalue, unitprice, quantity, totallineitemvalue, productId, packageId,
            productname,
            venuename, priceCategoryId, pricecategoryname, priceClassId, priceclassname,
            productcategory,
            feename, feecategory, levyby, isfeewaived, feeId, chargetype, pricecategorynum,
            priceclasscode);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnlineitemid", txnlineitemid)
        .append("transactionId", transactionId)
        .append("transactionrefnumber", transactionrefnumber)
        .append("lineitemtype", lineitemtype)
        .append("description", description)
        .append("basepricevalue", basepricevalue)
        .append("unitprice", unitprice)
        .append("quantity", quantity)
        .append("totallineitemvalue", totallineitemvalue)
        .append("productId", productId)
        .append("packageId", packageId)
        .append("productname", productname)
        .append("venuename", venuename)
        .append("priceCategoryId", priceCategoryId)
        .append("pricecategoryname", pricecategoryname)
        .append("priceClassId", priceClassId)
        .append("priceclassname", priceclassname)
        .append("productcategory", productcategory)
        .append("feename", feename)
        .append("feecategory", feecategory)
        .append("levyby", levyby)
        .append("isfeewaived", isfeewaived)
        .append("feeId", feeId)
        .append("chargetype", chargetype)
        .append("pricecategorynum", pricecategorynum)
        .append("priceclasscode", priceclasscode)
        .toString();
  }
}

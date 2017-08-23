package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionLineItem;

import java.math.BigDecimal;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionLineItemBuilder {
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
  private boolean isfeewaived;
  private Long feeId;
  private Integer chargetype;
  private Integer pricecategorynum;
  private String priceclasscode;

  private TransactionLineItemBuilder() {
  }

  public static TransactionLineItemBuilder aTransactionLineItem() {
    return new TransactionLineItemBuilder();
  }

  public TransactionLineItemBuilder txnlineitemid(Long txnlineitemid) {
    this.txnlineitemid = txnlineitemid;
    return this;
  }

  public TransactionLineItemBuilder transactionId(Long transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public TransactionLineItemBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TransactionLineItemBuilder lineitemtype(Integer lineitemtype) {
    this.lineitemtype = lineitemtype;
    return this;
  }

  public TransactionLineItemBuilder description(String description) {
    this.description = description;
    return this;
  }

  public TransactionLineItemBuilder basepricevalue(BigDecimal basepricevalue) {
    this.basepricevalue = basepricevalue;
    return this;
  }

  public TransactionLineItemBuilder unitprice(BigDecimal unitprice) {
    this.unitprice = unitprice;
    return this;
  }

  public TransactionLineItemBuilder quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  public TransactionLineItemBuilder totallineitemvalue(BigDecimal totallineitemvalue) {
    this.totallineitemvalue = totallineitemvalue;
    return this;
  }

  public TransactionLineItemBuilder productId(Long productId) {
    this.productId = productId;
    return this;
  }

  public TransactionLineItemBuilder packageId(Long packageId) {
    this.packageId = packageId;
    return this;
  }

  public TransactionLineItemBuilder productname(String productname) {
    this.productname = productname;
    return this;
  }

  public TransactionLineItemBuilder venuename(String venuename) {
    this.venuename = venuename;
    return this;
  }

  public TransactionLineItemBuilder priceCategoryId(Long priceCategoryId) {
    this.priceCategoryId = priceCategoryId;
    return this;
  }

  public TransactionLineItemBuilder pricecategoryname(String pricecategoryname) {
    this.pricecategoryname = pricecategoryname;
    return this;
  }

  public TransactionLineItemBuilder priceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
    return this;
  }

  public TransactionLineItemBuilder priceclassname(String priceclassname) {
    this.priceclassname = priceclassname;
    return this;
  }

  public TransactionLineItemBuilder productcategory(Integer productcategory) {
    this.productcategory = productcategory;
    return this;
  }

  public TransactionLineItemBuilder feename(String feename) {
    this.feename = feename;
    return this;
  }

  public TransactionLineItemBuilder feecategory(String feecategory) {
    this.feecategory = feecategory;
    return this;
  }

  public TransactionLineItemBuilder levyby(String levyby) {
    this.levyby = levyby;
    return this;
  }

  public TransactionLineItemBuilder isfeewaived(boolean isfeewaived) {
    this.isfeewaived = isfeewaived;
    return this;
  }

  public TransactionLineItemBuilder feeId(Long feeId) {
    this.feeId = feeId;
    return this;
  }

  public TransactionLineItemBuilder chargetype(Integer chargetype) {
    this.chargetype = chargetype;
    return this;
  }

  public TransactionLineItemBuilder pricecategorynum(Integer pricecategorynum) {
    this.pricecategorynum = pricecategorynum;
    return this;
  }

  public TransactionLineItemBuilder priceclasscode(String priceclasscode) {
    this.priceclasscode = priceclasscode;
    return this;
  }

  public TransactionLineItem build() {
    TransactionLineItem transactionLineItem = new TransactionLineItem();
    transactionLineItem.setTxnlineitemid(txnlineitemid);
    transactionLineItem.setTransactionId(transactionId);
    transactionLineItem.setTransactionrefnumber(transactionrefnumber);
    transactionLineItem.setLineitemtype(lineitemtype);
    transactionLineItem.setDescription(description);
    transactionLineItem.setBasepricevalue(basepricevalue);
    transactionLineItem.setUnitprice(unitprice);
    transactionLineItem.setQuantity(quantity);
    transactionLineItem.setTotallineitemvalue(totallineitemvalue);
    transactionLineItem.setProductId(productId);
    transactionLineItem.setPackageId(packageId);
    transactionLineItem.setProductname(productname);
    transactionLineItem.setVenuename(venuename);
    transactionLineItem.setPriceCategoryId(priceCategoryId);
    transactionLineItem.setPricecategoryname(pricecategoryname);
    transactionLineItem.setPriceClassId(priceClassId);
    transactionLineItem.setPriceclassname(priceclassname);
    transactionLineItem.setProductcategory(productcategory);
    transactionLineItem.setFeename(feename);
    transactionLineItem.setFeecategory(feecategory);
    transactionLineItem.setLevyby(levyby);
    transactionLineItem.setIsfeewaived(isfeewaived);
    transactionLineItem.setFeeId(feeId);
    transactionLineItem.setChargetype(chargetype);
    transactionLineItem.setPricecategorynum(pricecategorynum);
    transactionLineItem.setPriceclasscode(priceclasscode);
    return transactionLineItem;
  }
}

package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionProduct;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionProductBuilder {
  private Long txnproductid;
  private Long productId;
  private Integer paymentmode;
  private Long soldBy;
  private Long priceCatId;
  private Long priceClassId;
  private Long printedBy;
  private Long holdCodeId;
  private Long reprintedBy;
  private Long fulfilledBy;
  private Long pickupPosId;
  private Long salesInvId;
  private Integer inventorytype;
  private Long returnedBy;
  private Long returnPosId;
  private Long packageReqId;
  private OffsetDateTime solddate;
  private Boolean isreturned;
  private BigDecimal basepricevalue;
  private BigDecimal pricevalue;
  private Integer taxincluded;
  private Integer printstatus;
  private OffsetDateTime printeddate;
  private Long reprintedcount;
  private OffsetDateTime lastreprinteddate;
  private String deliverymethod;
  private Integer tickettype;
  private OffsetDateTime showdate;
  private Boolean iscomplimentary;
  private Long taxrate;
  private String newdeliverymethod;
  private Long orderId;
  private Integer reprintstatus;
  private Long updatedBy;
  private Long ticketstatus;
  private OffsetDateTime updateddate;
  private OffsetDateTime returneddate;
  private String remarks;
  private Long acsbarcoderegencount;
  private String acsbarcode;
  private Long printedProfileId;

  private TransactionProductBuilder() {
  }

  public static TransactionProductBuilder aTransactionProduct() {
    return new TransactionProductBuilder();
  }

  public TransactionProductBuilder txnproductid(Long txnproductid) {
    this.txnproductid = txnproductid;
    return this;
  }

  public TransactionProductBuilder productId(Long productId) {
    this.productId = productId;
    return this;
  }

  public TransactionProductBuilder paymentmode(Integer paymentmode) {
    this.paymentmode = paymentmode;
    return this;
  }

  public TransactionProductBuilder soldBy(Long soldBy) {
    this.soldBy = soldBy;
    return this;
  }

  public TransactionProductBuilder priceCatId(Long priceCatId) {
    this.priceCatId = priceCatId;
    return this;
  }

  public TransactionProductBuilder priceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
    return this;
  }

  public TransactionProductBuilder printedBy(Long printedBy) {
    this.printedBy = printedBy;
    return this;
  }

  public TransactionProductBuilder holdCodeId(Long holdCodeId) {
    this.holdCodeId = holdCodeId;
    return this;
  }

  public TransactionProductBuilder reprintedBy(Long reprintedBy) {
    this.reprintedBy = reprintedBy;
    return this;
  }

  public TransactionProductBuilder fulfilledBy(Long fulfilledBy) {
    this.fulfilledBy = fulfilledBy;
    return this;
  }

  public TransactionProductBuilder pickupPosId(Long pickupPosId) {
    this.pickupPosId = pickupPosId;
    return this;
  }

  public TransactionProductBuilder salesInvId(Long salesInvId) {
    this.salesInvId = salesInvId;
    return this;
  }

  public TransactionProductBuilder inventorytype(Integer inventorytype) {
    this.inventorytype = inventorytype;
    return this;
  }

  public TransactionProductBuilder returnedBy(Long returnedBy) {
    this.returnedBy = returnedBy;
    return this;
  }

  public TransactionProductBuilder returnPosId(Long returnPosId) {
    this.returnPosId = returnPosId;
    return this;
  }

  public TransactionProductBuilder packageReqId(Long packageReqId) {
    this.packageReqId = packageReqId;
    return this;
  }

  public TransactionProductBuilder solddate(OffsetDateTime solddate) {
    this.solddate = solddate;
    return this;
  }

  public TransactionProductBuilder isreturned(Boolean isreturned) {
    this.isreturned = isreturned;
    return this;
  }

  public TransactionProductBuilder basepricevalue(BigDecimal basepricevalue) {
    this.basepricevalue = basepricevalue;
    return this;
  }

  public TransactionProductBuilder pricevalue(BigDecimal pricevalue) {
    this.pricevalue = pricevalue;
    return this;
  }

  public TransactionProductBuilder taxincluded(Integer taxincluded) {
    this.taxincluded = taxincluded;
    return this;
  }

  public TransactionProductBuilder printstatus(Integer printstatus) {
    this.printstatus = printstatus;
    return this;
  }

  public TransactionProductBuilder printeddate(OffsetDateTime printeddate) {
    this.printeddate = printeddate;
    return this;
  }

  public TransactionProductBuilder reprintedcount(Long reprintedcount) {
    this.reprintedcount = reprintedcount;
    return this;
  }

  public TransactionProductBuilder lastreprinteddate(OffsetDateTime lastreprinteddate) {
    this.lastreprinteddate = lastreprinteddate;
    return this;
  }

  public TransactionProductBuilder deliverymethod(String deliverymethod) {
    this.deliverymethod = deliverymethod;
    return this;
  }

  public TransactionProductBuilder tickettype(Integer tickettype) {
    this.tickettype = tickettype;
    return this;
  }

  public TransactionProductBuilder showdate(OffsetDateTime showdate) {
    this.showdate = showdate;
    return this;
  }

  public TransactionProductBuilder iscomplimentary(Boolean iscomplimentary) {
    this.iscomplimentary = iscomplimentary;
    return this;
  }

  public TransactionProductBuilder taxrate(Long taxrate) {
    this.taxrate = taxrate;
    return this;
  }

  public TransactionProductBuilder newdeliverymethod(String newdeliverymethod) {
    this.newdeliverymethod = newdeliverymethod;
    return this;
  }

  public TransactionProductBuilder orderId(Long orderId) {
    this.orderId = orderId;
    return this;
  }

  public TransactionProductBuilder reprintstatus(Integer reprintstatus) {
    this.reprintstatus = reprintstatus;
    return this;
  }

  public TransactionProductBuilder updatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

  public TransactionProductBuilder ticketstatus(Long ticketstatus) {
    this.ticketstatus = ticketstatus;
    return this;
  }

  public TransactionProductBuilder updateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
    return this;
  }

  public TransactionProductBuilder returneddate(OffsetDateTime returneddate) {
    this.returneddate = returneddate;
    return this;
  }

  public TransactionProductBuilder remarks(String remarks) {
    this.remarks = remarks;
    return this;
  }

  public TransactionProductBuilder acsbarcoderegencount(Long acsbarcoderegencount) {
    this.acsbarcoderegencount = acsbarcoderegencount;
    return this;
  }

  public TransactionProductBuilder acsbarcode(String acsbarcode) {
    this.acsbarcode = acsbarcode;
    return this;
  }

  public TransactionProductBuilder printedProfileId(Long printedProfileId) {
    this.printedProfileId = printedProfileId;
    return this;
  }

  public TransactionProduct build() {
    TransactionProduct transactionProduct = new TransactionProduct();
//    transactionProduct.setTxnproductid(txnproductid);
    transactionProduct.setProductId(productId);
    transactionProduct.setPaymentmode(paymentmode);
    transactionProduct.setSoldBy(soldBy);
    transactionProduct.setPriceCatId(priceCatId);
    transactionProduct.setPriceClassId(priceClassId);
    transactionProduct.setPrintedBy(printedBy);
    transactionProduct.setHoldCodeId(holdCodeId);
    transactionProduct.setReprintedBy(reprintedBy);
    transactionProduct.setFulfilledBy(fulfilledBy);
    transactionProduct.setPickupPosId(pickupPosId);
    transactionProduct.setSalesInvId(salesInvId);
    transactionProduct.setInventorytype(inventorytype);
    transactionProduct.setReturnedBy(returnedBy);
    transactionProduct.setReturnPosId(returnPosId);
    transactionProduct.setPackageReqId(packageReqId);
    transactionProduct.setSolddate(solddate);
    transactionProduct.setIsreturned(isreturned);
    transactionProduct.setBasepricevalue(basepricevalue);
    transactionProduct.setPricevalue(pricevalue);
    transactionProduct.setTaxincluded(taxincluded);
    transactionProduct.setPrintstatus(printstatus);
    transactionProduct.setPrinteddate(printeddate);
    transactionProduct.setReprintedcount(reprintedcount);
    transactionProduct.setLastreprinteddate(lastreprinteddate);
    transactionProduct.setDeliverymethod(deliverymethod);
    transactionProduct.setTickettype(tickettype);
    transactionProduct.setShowdate(showdate);
    transactionProduct.setIscomplimentary(iscomplimentary);
    transactionProduct.setTaxrate(taxrate);
    transactionProduct.setNewdeliverymethod(newdeliverymethod);
    transactionProduct.setOrderId(orderId);
    transactionProduct.setReprintstatus(reprintstatus);
    transactionProduct.setUpdatedBy(updatedBy);
    transactionProduct.setTicketstatus(ticketstatus);
    transactionProduct.setUpdateddate(updateddate);
    transactionProduct.setReturneddate(returneddate);
    transactionProduct.setRemarks(remarks);
    transactionProduct.setAcsbarcoderegencount(acsbarcoderegencount);
    transactionProduct.setAcsbarcode(acsbarcode);
    transactionProduct.setPrintedProfileId(printedProfileId);
    return transactionProduct;
  }
}

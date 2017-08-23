package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
@Table(name = "TRANSACTION_PRODUCT")
public class TransactionProduct {
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

  public TransactionProduct() {
  }

  public TransactionProduct(Long txnproductid, Long productId, Integer paymentmode,
                            Long soldBy, Long priceCatId, Long priceClassId, Long printedBy,
                            Long holdCodeId, Long reprintedBy, Long fulfilledBy,
                            Long pickupPosId, Long salesInvId, Integer inventorytype,
                            Long returnedBy, Long returnPosId, Long packageReqId,
                            OffsetDateTime solddate, Boolean isreturned, BigDecimal basepricevalue,
                            BigDecimal pricevalue, Integer taxincluded, Integer printstatus,
                            OffsetDateTime printeddate, Long reprintedcount,
                            OffsetDateTime lastreprinteddate, String deliverymethod,
                            Integer tickettype, OffsetDateTime showdate,
                            Boolean iscomplimentary, Long taxrate, String newdeliverymethod,
                            Long orderId, Integer reprintstatus, Long updatedBy,
                            Long ticketstatus, OffsetDateTime updateddate,
                            OffsetDateTime returneddate, String remarks,
                            Long acsbarcoderegencount, String acsbarcode,
                            Long printedProfileId) {

    this.txnproductid = txnproductid;
    this.productId = productId;
    this.paymentmode = paymentmode;
    this.soldBy = soldBy;
    this.priceCatId = priceCatId;
    this.priceClassId = priceClassId;
    this.printedBy = printedBy;
    this.holdCodeId = holdCodeId;
    this.reprintedBy = reprintedBy;
    this.fulfilledBy = fulfilledBy;
    this.pickupPosId = pickupPosId;
    this.salesInvId = salesInvId;
    this.inventorytype = inventorytype;
    this.returnedBy = returnedBy;
    this.returnPosId = returnPosId;
    this.packageReqId = packageReqId;
    this.solddate = solddate;
    this.isreturned = isreturned;
    this.basepricevalue = basepricevalue;
    this.pricevalue = pricevalue;
    this.taxincluded = taxincluded;
    this.printstatus = printstatus;
    this.printeddate = printeddate;
    this.reprintedcount = reprintedcount;
    this.lastreprinteddate = lastreprinteddate;
    this.deliverymethod = deliverymethod;
    this.tickettype = tickettype;
    this.showdate = showdate;
    this.iscomplimentary = iscomplimentary;
    this.taxrate = taxrate;
    this.newdeliverymethod = newdeliverymethod;
    this.orderId = orderId;
    this.reprintstatus = reprintstatus;
    this.updatedBy = updatedBy;
    this.ticketstatus = ticketstatus;
    this.updateddate = updateddate;
    this.returneddate = returneddate;
    this.remarks = remarks;
    this.acsbarcoderegencount = acsbarcoderegencount;
    this.acsbarcode = acsbarcode;
    this.printedProfileId = printedProfileId;
  }

  @Id
  @Column(name = "TXNPRODUCTID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TxnProductIdSeq")
  @GenericGenerator(name = "TxnProductIdSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "TXN_PRODUCT_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getTxnproductid() {
    return txnproductid;
  }

  public void setTxnproductid(Long txnproductid) {
    this.txnproductid = txnproductid;
  }

  @Column(name = "PRODUCT_ID", nullable = false, precision = 0)
  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  @Column(name = "PAYMENTMODE", nullable = true, precision = 0)
  public Integer getPaymentmode() {
    return paymentmode;
  }

  public void setPaymentmode(Integer paymentmode) {
    this.paymentmode = paymentmode;
  }

  @Column(name = "SOLD_BY", nullable = false, precision = 0)
  public Long getSoldBy() {
    return soldBy;
  }

  public void setSoldBy(Long soldBy) {
    this.soldBy = soldBy;
  }

  @Column(name = "PRICE_CAT_ID", nullable = false, precision = 0)
  public Long getPriceCatId() {
    return priceCatId;
  }

  public void setPriceCatId(Long priceCatId) {
    this.priceCatId = priceCatId;
  }

  @Column(name = "PRICE_CLASS_ID", nullable = false, precision = 0)
  public Long getPriceClassId() {
    return priceClassId;
  }

  public void setPriceClassId(Long priceClassId) {
    this.priceClassId = priceClassId;
  }

  @Column(name = "PRINTED_BY", nullable = true, precision = 0)
  public Long getPrintedBy() {
    return printedBy;
  }

  public void setPrintedBy(Long printedBy) {
    this.printedBy = printedBy;
  }

  @Column(name = "HOLD_CODE_ID", nullable = true, precision = 0)
  public Long getHoldCodeId() {
    return holdCodeId;
  }

  public void setHoldCodeId(Long holdCodeId) {
    this.holdCodeId = holdCodeId;
  }

  @Column(name = "REPRINTED_BY", nullable = true, precision = 0)
  public Long getReprintedBy() {
    return reprintedBy;
  }

  public void setReprintedBy(Long reprintedBy) {
    this.reprintedBy = reprintedBy;
  }

  @Column(name = "FULFILLED_BY", nullable = true, precision = 0)
  public Long getFulfilledBy() {
    return fulfilledBy;
  }

  public void setFulfilledBy(Long fulfilledBy) {
    this.fulfilledBy = fulfilledBy;
  }

  @Column(name = "PICKUP_POS_ID", nullable = true, precision = 0)
  public Long getPickupPosId() {
    return pickupPosId;
  }

  public void setPickupPosId(Long pickupPosId) {
    this.pickupPosId = pickupPosId;
  }

  @Column(name = "SALES_INV_ID", nullable = true, precision = 0)
  public Long getSalesInvId() {
    return salesInvId;
  }

  public void setSalesInvId(Long salesInvId) {
    this.salesInvId = salesInvId;
  }

  @Column(name = "INVENTORYTYPE", nullable = false, precision = 0)
  public Integer getInventorytype() {
    return inventorytype;
  }

  public void setInventorytype(Integer inventorytype) {
    this.inventorytype = inventorytype;
  }

  @Column(name = "RETURNED_BY", nullable = true, precision = 0)
  public Long getReturnedBy() {
    return returnedBy;
  }

  public void setReturnedBy(Long returnedBy) {
    this.returnedBy = returnedBy;
  }

  @Column(name = "RETURN_POS_ID", nullable = true, precision = 0)
  public Long getReturnPosId() {
    return returnPosId;
  }

  public void setReturnPosId(Long returnPosId) {
    this.returnPosId = returnPosId;
  }

  @Column(name = "PACKAGE_REQ_ID", nullable = true, precision = 0)
  public Long getPackageReqId() {
    return packageReqId;
  }

  public void setPackageReqId(Long packageReqId) {
    this.packageReqId = packageReqId;
  }

  @Column(name = "SOLDDATE", nullable = true)
  public OffsetDateTime getSolddate() {
    return solddate;
  }

  public void setSolddate(OffsetDateTime solddate) {
    this.solddate = solddate;
  }

  @Type(type = "true_false")
  @Column(name = "ISRETURNED", nullable = false, length = 1)
  public Boolean getIsreturned() {
    return isreturned;
  }

  public void setIsreturned(Boolean isreturned) {
    this.isreturned = isreturned;
  }

  @Column(name = "BASEPRICEVALUE", nullable = true, precision = 5)
  public BigDecimal getBasepricevalue() {
    return basepricevalue;
  }

  public void setBasepricevalue(BigDecimal basepricevalue) {
    this.basepricevalue = basepricevalue;
  }

  @Column(name = "PRICEVALUE", nullable = true, precision = 5)
  public BigDecimal getPricevalue() {
    return pricevalue;
  }

  public void setPricevalue(BigDecimal pricevalue) {
    this.pricevalue = pricevalue;
  }

  @Column(name = "TAXINCLUDED", nullable = true, precision = 0)
  public Integer getTaxincluded() {
    return taxincluded;
  }

  public void setTaxincluded(Integer taxincluded) {
    this.taxincluded = taxincluded;
  }

  @Column(name = "PRINTSTATUS", nullable = true, precision = 0)
  public Integer getPrintstatus() {
    return printstatus;
  }

  public void setPrintstatus(Integer printstatus) {
    this.printstatus = printstatus;
  }

  @Column(name = "PRINTEDDATE", nullable = true)
  public OffsetDateTime getPrinteddate() {
    return printeddate;
  }

  public void setPrinteddate(OffsetDateTime printeddate) {
    this.printeddate = printeddate;
  }

  @Column(name = "REPRINTEDCOUNT", nullable = false, precision = 0)
  public Long getReprintedcount() {
    return reprintedcount;
  }

  public void setReprintedcount(Long reprintedcount) {
    this.reprintedcount = reprintedcount;
  }

  @Column(name = "LASTREPRINTEDDATE", nullable = true)
  public OffsetDateTime getLastreprinteddate() {
    return lastreprinteddate;
  }

  public void setLastreprinteddate(OffsetDateTime lastreprinteddate) {
    this.lastreprinteddate = lastreprinteddate;
  }

  @Column(name = "DELIVERYMETHOD", nullable = true, length = 100)
  public String getDeliverymethod() {
    return deliverymethod;
  }

  public void setDeliverymethod(String deliverymethod) {
    this.deliverymethod = deliverymethod;
  }

  @Column(name = "TICKETTYPE", nullable = true, precision = 0)
  public Integer getTickettype() {
    return tickettype;
  }

  public void setTickettype(Integer tickettype) {
    this.tickettype = tickettype;
  }

  @Column(name = "SHOWDATE", nullable = true)
  public OffsetDateTime getShowdate() {
    return showdate;
  }

  public void setShowdate(OffsetDateTime showdate) {
    this.showdate = showdate;
  }

  @Type(type = "true_false")
  @Column(name = "ISCOMPLIMENTARY", nullable = false, length = 1)
  public Boolean getIscomplimentary() {
    return iscomplimentary;
  }

  public void setIscomplimentary(Boolean iscomplimentary) {
    this.iscomplimentary = iscomplimentary;
  }

  @Column(name = "TAXRATE", nullable = true, precision = 2)
  public Long getTaxrate() {
    return taxrate;
  }

  public void setTaxrate(Long taxrate) {
    this.taxrate = taxrate;
  }

  @Column(name = "NEWDELIVERYMETHOD", nullable = true, length = 100)
  public String getNewdeliverymethod() {
    return newdeliverymethod;
  }

  public void setNewdeliverymethod(String newdeliverymethod) {
    this.newdeliverymethod = newdeliverymethod;
  }

  @Column(name = "ORDER_ID", nullable = true, precision = 0)
  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  @Column(name = "REPRINTSTATUS", nullable = true, precision = 0)
  public Integer getReprintstatus() {
    return reprintstatus;
  }

  public void setReprintstatus(Integer reprintstatus) {
    this.reprintstatus = reprintstatus;
  }

  @Column(name = "UPDATED_BY", nullable = false, precision = 0)
  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Column(name = "TICKETSTATUS", nullable = true, precision = 0)
  public Long getTicketstatus() {
    return ticketstatus;
  }

  public void setTicketstatus(Long ticketstatus) {
    this.ticketstatus = ticketstatus;
  }

  @Column(name = "UPDATEDDATE", nullable = true)
  public OffsetDateTime getUpdateddate() {
    return updateddate;
  }

  public void setUpdateddate(OffsetDateTime updateddate) {
    this.updateddate = updateddate;
  }

  @Column(name = "RETURNEDDATE", nullable = true)
  public OffsetDateTime getReturneddate() {
    return returneddate;
  }

  public void setReturneddate(OffsetDateTime returneddate) {
    this.returneddate = returneddate;
  }

  @Column(name = "REMARKS", nullable = true, length = 255)
  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Column(name = "ACSBARCODEREGENCOUNT", nullable = false, precision = 0)
  public Long getAcsbarcoderegencount() {
    return acsbarcoderegencount;
  }

  public void setAcsbarcoderegencount(Long acsbarcoderegencount) {
    this.acsbarcoderegencount = acsbarcoderegencount;
  }

  @Column(name = "ACSBARCODE", nullable = true, length = 50)
  public String getAcsbarcode() {
    return acsbarcode;
  }

  public void setAcsbarcode(String acsbarcode) {
    this.acsbarcode = acsbarcode;
  }

  @Column(name = "PRINTED_PROFILE_ID", nullable = true, precision = 0)
  public Long getPrintedProfileId() {
    return printedProfileId;
  }

  public void setPrintedProfileId(Long printedProfileId) {
    this.printedProfileId = printedProfileId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TransactionProduct that = (TransactionProduct) o;

    return Objects.equals(this.acsbarcode, that.acsbarcode) &&
        Objects.equals(this.acsbarcoderegencount, that.acsbarcoderegencount) &&
        Objects.equals(this.basepricevalue, that.basepricevalue) &&
        Objects.equals(this.deliverymethod, that.deliverymethod) &&
        Objects.equals(this.fulfilledBy, that.fulfilledBy) &&
        Objects.equals(this.holdCodeId, that.holdCodeId) &&
        Objects.equals(this.inventorytype, that.inventorytype) &&
        Objects.equals(this.iscomplimentary, that.iscomplimentary) &&
        Objects.equals(this.isreturned, that.isreturned) &&
        Objects.equals(this.lastreprinteddate, that.lastreprinteddate) &&
        Objects.equals(this.newdeliverymethod, that.newdeliverymethod) &&
        Objects.equals(this.orderId, that.orderId) &&
        Objects.equals(this.packageReqId, that.packageReqId) &&
        Objects.equals(this.paymentmode, that.paymentmode) &&
        Objects.equals(this.pickupPosId, that.pickupPosId) &&
        Objects.equals(this.priceCatId, that.priceCatId) &&
        Objects.equals(this.priceClassId, that.priceClassId) &&
        Objects.equals(this.pricevalue, that.pricevalue) &&
        Objects.equals(this.printedBy, that.printedBy) &&
        Objects.equals(this.printeddate, that.printeddate) &&
        Objects.equals(this.printedProfileId, that.printedProfileId) &&
        Objects.equals(this.printstatus, that.printstatus) &&
        Objects.equals(this.productId, that.productId) &&
        Objects.equals(this.remarks, that.remarks) &&
        Objects.equals(this.reprintedBy, that.reprintedBy) &&
        Objects.equals(this.reprintedcount, that.reprintedcount) &&
        Objects.equals(this.reprintstatus, that.reprintstatus) &&
        Objects.equals(this.returnedBy, that.returnedBy) &&
        Objects.equals(this.returneddate, that.returneddate) &&
        Objects.equals(this.returnPosId, that.returnPosId) &&
        Objects.equals(this.salesInvId, that.salesInvId) &&
        Objects.equals(this.showdate, that.showdate) &&
        Objects.equals(this.soldBy, that.soldBy) &&
        Objects.equals(this.solddate, that.solddate) &&
        Objects.equals(this.taxincluded, that.taxincluded) &&
        Objects.equals(this.taxrate, that.taxrate) &&
        Objects.equals(this.ticketstatus, that.ticketstatus) &&
        Objects.equals(this.tickettype, that.tickettype) &&
        Objects.equals(this.txnproductid, that.txnproductid) &&
        Objects.equals(this.updatedBy, that.updatedBy) &&
        Objects.equals(this.updateddate, that.updateddate);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(acsbarcode, acsbarcoderegencount, basepricevalue, deliverymethod, fulfilledBy,
            holdCodeId,
            inventorytype, iscomplimentary, isreturned, lastreprinteddate, newdeliverymethod,
            orderId, packageReqId, paymentmode, pickupPosId, priceCatId,
            priceClassId, pricevalue, printedBy, printeddate, printedProfileId,
            printstatus, productId, remarks, reprintedBy, reprintedcount,
            reprintstatus, returnedBy, returneddate, returnPosId, salesInvId,
            showdate, soldBy, solddate, taxincluded, taxrate,
            ticketstatus, tickettype, txnproductid, updatedBy, updateddate);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("txnproductid", txnproductid)
        .append("productId", productId)
        .append("paymentmode", paymentmode)
        .append("soldBy", soldBy)
        .append("priceCatId", priceCatId)
        .append("priceClassId", priceClassId)
        .append("printedBy", printedBy)
        .append("holdCodeId", holdCodeId)
        .append("reprintedBy", reprintedBy)
        .append("fulfilledBy", fulfilledBy)
        .append("pickupPosId", pickupPosId)
        .append("salesInvId", salesInvId)
        .append("inventorytype", inventorytype)
        .append("returnedBy", returnedBy)
        .append("returnPosId", returnPosId)
        .append("packageReqId", packageReqId)
        .append("solddate", solddate)
        .append("isreturned", isreturned)
        .append("basepricevalue", basepricevalue)
        .append("pricevalue", pricevalue)
        .append("taxincluded", taxincluded)
        .append("printstatus", printstatus)
        .append("printeddate", printeddate)
        .append("reprintedcount", reprintedcount)
        .append("lastreprinteddate", lastreprinteddate)
        .append("deliverymethod", deliverymethod)
        .append("tickettype", tickettype)
        .append("showdate", showdate)
        .append("iscomplimentary", iscomplimentary)
        .append("taxrate", taxrate)
        .append("newdeliverymethod", newdeliverymethod)
        .append("orderId", orderId)
        .append("reprintstatus", reprintstatus)
        .append("updatedBy", updatedBy)
        .append("ticketstatus", ticketstatus)
        .append("updateddate", updateddate)
        .append("returneddate", returneddate)
        .append("remarks", remarks)
        .append("acsbarcoderegencount", acsbarcoderegencount)
        .append("acsbarcode", acsbarcode)
        .append("printedProfileId", printedProfileId)
        .toString();
  }
}

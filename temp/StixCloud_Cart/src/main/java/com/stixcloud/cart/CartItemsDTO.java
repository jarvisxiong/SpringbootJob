package com.stixcloud.cart;

import java.io.Serializable;
import java.math.BigDecimal;


public class CartItemsDTO implements Serializable {

  private static final long serialVersionUID = -7895307360031564973L;

  private Integer No;

  private String Item;

  private String PrdtDescription;

  private String Description;

  private String ngsDescription;

  private String priceClass;

  private String unitPrice;

  // Added for Tuleap.Task #1226 [bryanleong]: 13032016
  private String unitPriceGST;

  private String unitPriceSubtractGST;

  private Integer qty;

  private String subTotal;

  private String ID;

  private String ticketType;

  private boolean
      inventoryExpired =
      false;
  //flag to indicate if the inventory in this line item has already been released

  private boolean productOffSales = false; //flag to indicate if the product has been off sales

  // Added for Tuleap.Task #1210
  private String singleUnitPriceGST;
  private String sepangPrdtDescription;

  private BigDecimal gstPerLineItem;

  private BigDecimal subTotalValue;

  /**
   * Instantiates a new cart items dto.
   */
  public CartItemsDTO() {
    super();
  }

  /**
   * Instantiates a new cart items dto.
   *
   * @param item the item
   */
   /* public CartItemsDTO(LineItem item) {
        super();
        setItem(item.getLineItemType().getName());
        if (item instanceof FeeLineItem) {
            FeeLineItem feeItem = (FeeLineItem) item;
            setNo(null);
            setDescription(feeItem.getFeeName());
            setQty(null);
            setSubTotal(feeItem.getFeeCharge().getValue().toString());
        }
    }*/

  /**
   * Gets the no.
   * @return the no
   */
  public Integer getNo() {
    return No;
  }

  /**
   * Sets the no.
   * @param no the new no
   */
  public void setNo(Integer no) {
    No = no;
  }

  /**
   * Gets the item.
   * @return the item
   */
  public String getItem() {
    return Item;
  }

  /**
   * Sets the item.
   * @param item the new item
   */
  public void setItem(String item) {
    Item = item;
  }

  /**
   * Gets the description.
   * @return the description
   */
  public String getDescription() {
    return Description;
  }

  /**
   * Sets the description.
   * @param description the new description
   */
  public void setDescription(String description) {
    Description = description;
  }

  /**
   * Gets the description.
   * @return the description
   */
  public String getPrdtDescription() {
    return PrdtDescription;
  }

  /**
   * Sets the description.
   * @param description the new description
   */
  public void setPrdtDescription(String prdtDescription) {
    PrdtDescription = prdtDescription;
  }

  /**
   * Gets the price class.
   * @return the price class
   */
  public String getPriceClass() {
    return priceClass;
  }

  /**
   * Sets the price class.
   * @param priceClass the new price class
   */
  public void setPriceClass(String priceClass) {
    this.priceClass = priceClass;
  }

  /**
   * Gets the unit price.
   * @return the unit price
   */
  public String getUnitPrice() {
    return unitPrice;
  }

  /**
   * Sets the unit price.
   * @param unitPrice the new unit price
   */
  public void setUnitPrice(String unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * Gets the unit price gst.
   * @return the unit price gst
   */
  public String getUnitPriceGST() {
    return unitPriceGST;
  }

  /**
   * Sets the unit price gst.
   * @param unitPriceGST the new unit price gst
   */
  public void setUnitPriceGST(String unitPriceGST) {
    this.unitPriceGST = unitPriceGST;
  }

  /**
   * Gets the unit price subtract gst.
   * @return the unit price subtract gst
   */
  public String getUnitPriceSubtractGST() {
    return unitPriceSubtractGST;
  }

  /**
   * Sets the unit price subtract gst.
   * @param unitPriceGST the new unit price subtract gst
   */
  public void setUnitPriceSubtractGST(String unitPriceSubtractGST) {
    this.unitPriceSubtractGST = unitPriceSubtractGST;
  }

  /**
   * Gets the qty.
   * @return the qty
   */
  public Integer getQty() {
    return qty;
  }

  /**
   * Sets the qty.
   * @param qty the new qty
   */
  public void setQty(Integer qty) {
    this.qty = qty;
  }

  /**
   * Gets the sub total.
   * @return the sub total
   */
  public String getSubTotal() {
    return subTotal;
  }

  /**
   * Sets the sub total.
   * @param subTotal the new sub total
   */
  public void setSubTotal(String subTotal) {
    this.subTotal = subTotal;
  }

  /**
   * Gets the id.
   * @return the id
   */
  public String getID() {
    return ID;
  }

  /**
   * Sets the id.
   * @param iD the new id
   */
  public void setID(String iD) {
    ID = iD;
  }

  /**
   * Gets the ticket type.
   * @return the ticket type
   */
  public String getTicketType() {
    return ticketType;
  }

  /**
   * Sets the ticket type.
   * @param ticketType the new ticket type
   */
  public void setTicketType(String ticketType) {
    this.ticketType = ticketType;
  }

  /**
   * Sets the inventory expired.
   * @param inventoryExpired the new inventory expired
   */
  public void setInventoryExpired(boolean inventoryExpired) {
    this.inventoryExpired = inventoryExpired;
  }

  /**
   * Checks if is inventory expired.
   * @return true, if is inventory expired
   */
  public boolean isInventoryExpired() {
    return inventoryExpired;
  }

  /**
   * Sets the product off sales.
   * @param productOffSales the new product off sales
   */
  public void setProductOffSales(boolean productOffSales) {
    this.productOffSales = productOffSales;
  }

  /**
   * Checks if is product off sales.
   * @return true, if is product off sales
   */
  public boolean isProductOffSales() {
    return productOffSales;
  }

  public String getSingleUnitPriceGST() {
    return singleUnitPriceGST;
  }

  public void setSingleUnitPriceGST(String singleUnitPriceGST) {
    this.singleUnitPriceGST = singleUnitPriceGST;
  }

  public String getSepangPrdtDescription() {
    return sepangPrdtDescription;
  }

  public void setSepangPrdtDescription(String sepangPrdtDescription) {
    this.sepangPrdtDescription = sepangPrdtDescription;
  }

  public BigDecimal getGstPerLineItem() {
    return gstPerLineItem;
  }

  public void setGstPerLineItem(BigDecimal gstPerLineItem) {
    this.gstPerLineItem = gstPerLineItem;
  }

  public BigDecimal getSubTotalValue() {
    return subTotalValue;
  }

  public void setSubTotalValue(BigDecimal subTotalValue) {
    this.subTotalValue = subTotalValue;
  }

  public String getNgsDescription() {
    return ngsDescription;
  }

  public void setNgsDescription(String ngsDescription) {
    this.ngsDescription = ngsDescription;
  }
}

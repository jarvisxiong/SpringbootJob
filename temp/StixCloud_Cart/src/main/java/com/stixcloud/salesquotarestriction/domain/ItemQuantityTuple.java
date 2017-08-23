package com.stixcloud.salesquotarestriction.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by mango on 24/3/17.
 */
public class ItemQuantityTuple implements Serializable {

    private static final long serialVersionUID = 2176583303095905210L;

    protected Long quantity, productGroupID, productID, priceClassID, priceCategoryID;

    public ItemQuantityTuple() {
    }

    public ItemQuantityTuple(Long quantity, Long productGroupID, Long productID, Long priceClassID, Long priceCategoryID) {
        this.quantity = quantity;
        this.productGroupID = productGroupID;
        this.productID = productID;
        this.priceClassID = priceClassID;
        this.priceCategoryID = priceCategoryID;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getProductGroupID() {
        return productGroupID;
    }

    public Long getProductID() {
        return productID;
    }

    public Long getPriceClassID() {
        return priceClassID;
    }

    public Long getPriceCategoryID() {
        return priceCategoryID;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setProductGroupID(Long productGroupID) {
        this.productGroupID = productGroupID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public void setPriceClassID(Long priceClassID) {
        this.priceClassID = priceClassID;
    }

    public void setPriceCategoryID(Long priceCategoryID) {
        this.priceCategoryID = priceCategoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemQuantityTuple that = (ItemQuantityTuple) o;
        return Objects.equals(quantity, that.quantity) &&
                Objects.equals(productGroupID, that.productGroupID) &&
                Objects.equals(productID, that.productID) &&
                Objects.equals(priceClassID, that.priceClassID) &&
                Objects.equals(priceCategoryID, that.priceCategoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, productGroupID, productID, priceClassID, priceCategoryID);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("quantity", quantity)
                .append("productGroupID", productGroupID)
                .append("productID", productID)
                .append("priceClassID", priceClassID)
                .append("priceCategoryID", priceCategoryID)
                .toString();
    }
}

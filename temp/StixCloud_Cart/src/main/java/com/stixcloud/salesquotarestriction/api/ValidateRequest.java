package com.stixcloud.salesquotarestriction.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.salesquotarestriction.domain.ItemQuantityTuple;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by mango on 14/3/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"patron_email", "patron_primary_contact_number", "items"})
public class ValidateRequest implements Serializable {

    private static final long serialVersionUID = -3213643774498138641L;

    @JsonProperty("patron_email")
    private String patronEmail;

    @JsonProperty("patron_primary_contact_number")
    private String patronPrimaryContactNumber;

    @NotNull
    @JsonProperty("items")
    private List<Item> itemsList;

    public String getPatronEmail() {
        return patronEmail;
    }

    public void setPatronEmail(String patronEmail) {
        this.patronEmail = patronEmail;
    }

    public String getPatronPrimaryContactNumber() {
        return patronPrimaryContactNumber;
    }

    public void setPatronPrimaryContactNumber(String patronPrimaryContactNumber) {
        this.patronPrimaryContactNumber = patronPrimaryContactNumber;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidateRequest that = (ValidateRequest) o;
        return Objects.equals(patronEmail, that.patronEmail) &&
                Objects.equals(patronPrimaryContactNumber, that.patronPrimaryContactNumber) &&
                Objects.equals(itemsList, that.itemsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patronEmail, patronPrimaryContactNumber, itemsList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("patronEmail", patronEmail)
                .append("patronPrimaryContactNumber", patronPrimaryContactNumber)
                .append("itemsList", itemsList)
                .toString();
    }

    @JsonPropertyOrder({"quantity", "product_group_id", "product_id", "price_class_id", "price_category_id"})
    public static class Item extends ItemQuantityTuple implements Serializable {

        private static final long serialVersionUID = 3612046014144806714L;

        public Item() {
        }

        public Item(Long quantity, Long productGroupID, Long productID, Long priceClassID, Long priceCategoryID) {
            super(quantity, productGroupID, productID, priceClassID, priceCategoryID);
        }

        @NotNull
        @Min(0)
        @JsonProperty(value = "quantity")
        @Override
        public Long getQuantity() {
            return super.getQuantity();
        }

        @JsonProperty("product_group_id")
        @Override
        public Long getProductGroupID() {
            return super.getProductGroupID();
        }

        @NotNull
        @JsonProperty("product_id")
        @Override
        public Long getProductID() {
            return super.getProductID();
        }

        @NotNull
        @JsonProperty("price_class_id")
        @Override
        public Long getPriceClassID() {
            return super.getPriceClassID();
        }

        @NotNull
        @JsonProperty("price_category_id")
        @Override
        public Long getPriceCategoryID() {
            return super.getPriceCategoryID();
        }

    }
}


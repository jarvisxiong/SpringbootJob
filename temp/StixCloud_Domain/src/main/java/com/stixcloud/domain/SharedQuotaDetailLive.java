package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by mango on 19/3/2017.
 */
@Entity
@Table(name="SHARED_QUOTA_DETAIL_LIVE")
public class SharedQuotaDetailLive implements Serializable {

    private static final long serialVersionUID = -2599574376851411011L;



    /*
     * Fields
     */
    private Long ID, sharedQuotaLiveID;
    private Long productID, priceCategoryID, priceClassID;



    /*
     * Constructors
     */
    public SharedQuotaDetailLive() {}

    public SharedQuotaDetailLive(Long ID, Long sharedQuotaLiveID, Long productID, Long priceCategoryID, Long priceClassID) {
        this.ID = ID;
        this.sharedQuotaLiveID = sharedQuotaLiveID;
        this.productID = productID;
        this.priceCategoryID = priceCategoryID;
        this.priceClassID = priceClassID;
    }



    /*
     * Accessors
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SharedQuotaDetailLiveIDSequenceGenerator")
    //@SequenceGenerator(name="SharedQuotaDetailLiveIDSequenceGenerator", sequenceName = "SHARED_QUOTA_DETAIL_LIVE_ID_SEQ", allocationSize = 1)
    @GenericGenerator(name = "SharedQuotaDetailLiveIDSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "SHARED_QUOTA_DETAIL_LIVE_ID_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "none")
            })
    @Id
    @Column(name="ID", nullable=false, precision=10, scale=0)
    public Long getID() {
        return ID;
    }

    @Column(name="SHARED_QUOTA_ID", nullable=true, precision=10, scale=0)
    public Long getSharedQuotaLiveID() {
        return sharedQuotaLiveID;
    }

    @Column(name="PRODUCT_ID", nullable=true, precision=10, scale=0)
    public Long getProductID() {
        return productID;
    }

    @Column(name="PRICE_CAT_ID", nullable=true, precision=10, scale=0)
    public Long getPriceCategoryID() {
        return priceCategoryID;
    }

    @Column(name="PRICE_CLASS_ID", nullable=true, precision=10, scale=0)
    public Long getPriceClassID() {
        return priceClassID;
    }



    /*
     * Mutators
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setSharedQuotaLiveID(Long sharedQuotaLiveID) {
        this.sharedQuotaLiveID = sharedQuotaLiveID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public void setPriceCategoryID(Long priceCategoryID) {
        this.priceCategoryID = priceCategoryID;
    }

    public void setPriceClassID(Long priceClassID) {
        this.priceClassID = priceClassID;
    }



    /*
     * Additional Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedQuotaDetailLive that = (SharedQuotaDetailLive) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(sharedQuotaLiveID, that.sharedQuotaLiveID) &&
                Objects.equals(productID, that.productID) &&
                Objects.equals(priceCategoryID, that.priceCategoryID) &&
                Objects.equals(priceClassID, that.priceClassID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, sharedQuotaLiveID, productID, priceCategoryID, priceClassID);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("ID", ID)
                .append("sharedQuotaLiveID", sharedQuotaLiveID)
                .append("productID", productID)
                .append("priceCategoryID", priceCategoryID)
                .append("priceClassID", priceClassID)
                .toString();
    }
}

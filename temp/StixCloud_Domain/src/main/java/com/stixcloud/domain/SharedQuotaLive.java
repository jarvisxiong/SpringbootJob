package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Created by mango on 19/3/2017.
 */
@Entity
@Table(name="SHARED_QUOTA_LIVE")
public class SharedQuotaLive implements Serializable {

    private static final long serialVersionUID = -4657606064732010265L;

    /*
     * Fields
     */
    private Long ID;
    private String name;
    private Long quota, quantity, alert, updatedBy;
    private OffsetDateTime updatedOn;
    private Long productGroupID;



    /*
     * Constructors
     */

    public SharedQuotaLive() {}

    public SharedQuotaLive(Long ID, String name, Long quota, Long quantity, Long alert, Long updatedBy, OffsetDateTime updatedOn, Long productGroupID) {
        this.ID = ID;
        this.name = name;
        this.quota = quota;
        this.quantity = quantity;
        this.alert = alert;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.productGroupID = productGroupID;
    }



    /*
     * Accessors
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SharedQuotaLiveIDSequenceGenerator")
    //@SequenceGenerator(name="SharedQuotaLiveIDSequenceGenerator", sequenceName = "SHARED_QUOTA_LIVE_ID_SEQ", allocationSize = 1)
    @GenericGenerator(name = "SharedQuotaLiveIDSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "SHARED_QUOTA_LIVE_ID_SEQ"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "none")
            })
    @Id
    @Column(name="ID", nullable=false, precision=10, scale=0)
    public Long getID() {
        return ID;
    }

    @Column(name="NAME", nullable=true, length=50)
    public String getName() {
        return name;
    }

    @Column(name="QUOTA", nullable=true, precision=10, scale=0)
    public Long getQuota() {
        return quota;
    }

    @Column(name="QUANTITY", nullable=true, precision=10, scale=0)
    public Long getQuantity() {
        return quantity;
    }

    @Column(name="ALERT", nullable=true, precision=10, scale=0)
    public Long getAlert() {
        return alert;
    }

    @Column(name="UPDATED_BY", nullable=true, precision=10, scale=0)
    public Long getUpdatedBy() {
        return updatedBy;
    }

    @Column(name="UPDATEDDATE", nullable=true)
    public OffsetDateTime getUpdatedOn() {
        return updatedOn;
    }

    @Column(name="PRODUCTGROUPID", nullable=false, precision=10, scale=0)
    public Long getProductGroupID() {
        return productGroupID;
    }



    /*
     * Mutators
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setAlert(Long alert) {
        this.alert = alert;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedOn(OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setProductGroupID(Long productGroupID) {
        this.productGroupID = productGroupID;
    }


    /*
     * Additional Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedQuotaLive that = (SharedQuotaLive) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(name, that.name) &&
                Objects.equals(quota, that.quota) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(alert, that.alert) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(updatedOn, that.updatedOn) &&
                Objects.equals(productGroupID, that.productGroupID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, quota, quantity, alert, updatedBy, updatedOn, productGroupID);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("ID", ID)
                .append("name", name)
                .append("quota", quota)
                .append("quantity", quantity)
                .append("alert", alert)
                .append("updatedBy", updatedBy)
                .append("updatedOn", updatedOn)
                .append("productGroupID", productGroupID)
                .toString();
    }
}

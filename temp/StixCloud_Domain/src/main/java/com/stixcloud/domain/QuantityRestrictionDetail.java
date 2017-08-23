package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Created by mango on 15/3/17.
 */
@Entity
@Table(name="QUANTITY_RESTRICTION_DETAIL")
public class QuantityRestrictionDetail implements Serializable {

    private static final long serialVersionUID = 6538624661360915430L;



    /*
     * Custom Converters
     */
    public static class IsActiveBooleanConverter implements AttributeConverter<Boolean,String>{
        @Override
        public String convertToDatabaseColumn(Boolean aBoolean) {
            return aBoolean?"T":"F";
        }

        @Override
        public Boolean convertToEntityAttribute(String s) {
            return "T".equals(s);
        }
    }



    /*
     * Fields
     */
    private Long ID, quantityRestrictionID;
    private Boolean isActive;
    private Long quota, createdBy;
    private OffsetDateTime effectiveStart, effectiveEnd;

    /*
     * Constructors
     */
    public QuantityRestrictionDetail() {}

    public QuantityRestrictionDetail(Long quantityRestrictionID, Boolean isActive, Long quota, Long createdBy, OffsetDateTime effectiveStart, OffsetDateTime effectiveEnd) {
        this.quantityRestrictionID = quantityRestrictionID;
        this.isActive = isActive;
        this.quota = quota;
        this.createdBy = createdBy;
        this.effectiveStart = effectiveStart;
        this.effectiveEnd = effectiveEnd;
    }



    /*
     * Accessors
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QuantityRestrictionDetailIDSequenceGenerator")
    //@SequenceGenerator(name="QuantityRestrictionDetailIDSequenceGenerator", sequenceName = "QUANTITY_RESTRICTION_DETAIL_ID", allocationSize = 1)
    @GenericGenerator(name = "QuantityRestrictionDetailIDSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "QUANTITY_RESTRICTION_DETAIL_ID"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "none")
            })
    @Id
    @Column(name="RESTRICTIONDETAILID", nullable=false, precision=10, scale=0)
    public Long getID() {
        return ID;
    }

    @Column(name="RESTRICTION_ID", nullable=false, precision=10, scale=0)
    public Long getQuantityRestrictionID() {
        return quantityRestrictionID;
    }

    @Column(name="ISACTIVE", nullable=false, length=1)
    @Convert(converter=IsActiveBooleanConverter.class)
    public Boolean getActive() {
        return isActive;
    }

    @Column(name="QUANTITY_RESTRICTED", nullable=false, precision=10, scale=0)
    public Long getQuota() {
        return quota;
    }

    @Column(name="STARTED_BY", nullable=false, precision=10, scale=0)
    public Long getCreatedBy() {
        return createdBy;
    }

    @Column(name="START_DATE", nullable=false)
    public OffsetDateTime getEffectiveStart() {
        return effectiveStart;
    }

    @Column(name="END_DATE", nullable=true)
    public OffsetDateTime getEffectiveEnd() {
        return effectiveEnd;
    }



    /*
     * Mutators
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setQuantityRestrictionID(Long quantityRestrictionID) {
        this.quantityRestrictionID = quantityRestrictionID;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public void setEffectiveStart(OffsetDateTime effectiveStart) {
        this.effectiveStart = effectiveStart;
    }

    public void setEffectiveEnd(OffsetDateTime effectiveEnd) {
        this.effectiveEnd = effectiveEnd;
    }



    /*
     * Additional Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityRestrictionDetail that = (QuantityRestrictionDetail) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(quantityRestrictionID, that.quantityRestrictionID) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(quota, that.quota) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(effectiveStart, that.effectiveStart) &&
                Objects.equals(effectiveEnd, that.effectiveEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, quantityRestrictionID, isActive, quota, createdBy, effectiveStart, effectiveEnd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("ID", ID)
                .append("quantityRestrictionID", quantityRestrictionID)
                .append("isActive", isActive)
                .append("quota", quota)
                .append("createdBy", createdBy)
                .append("effectiveStart", effectiveStart)
                .append("effectiveEnd", effectiveEnd)
                .append("active", getActive())
                .toString();
    }
}

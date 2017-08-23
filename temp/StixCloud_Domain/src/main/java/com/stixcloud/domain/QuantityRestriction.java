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
@Table(name="QUANTITY_RESTRICTION")
public class QuantityRestriction implements Serializable {

    private static final long serialVersionUID = -5249619136850091225L;



    /*
     * Enums and Inner Classes
     */
    public enum ScopeType { GLOBAL, TRANSACTION, PATRON_EMAIL, PATRON_PRIMARYCONTACTNUMBER }



    /*
     * Fields
     */
    private Long ID;
    private ScopeType scopeType;
    private Long createdBy, updatedBy;
    private OffsetDateTime createdOn, updatedOn;



    /*
     * Constructors
     */
    public QuantityRestriction() {}

    public QuantityRestriction(Long ID, ScopeType scopeType, Long createdBy, Long updatedBy, OffsetDateTime createdOn, OffsetDateTime updatedOn) {
        this.ID = ID;
        this.scopeType = scopeType;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }



    /*
     * Accessors
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QuantityRestrictionIDSequenceGenerator")
    //@SequenceGenerator(name="QuantityRestrictionIDSequenceGenerator", sequenceName = "QUANTITY_RESTRICTION_ID", allocationSize = 1)
    @GenericGenerator(name = "QuantityRestrictionIDSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "QUANTITY_RESTRICTION_ID"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "none")
            })
    @Id
    @Column(name="RESTRICTIONID", nullable=false, precision=10, scale=0)
    public Long getID() {
        return ID;
    }

    @Column(name="RESTRICTION_SCOPE", nullable=false, precision=1, scale=0)
    @Enumerated(EnumType.ORDINAL)
    public ScopeType getScopeType() {
        return scopeType;
    }

    @Column(name="CREATED_BY", nullable=false, precision=10, scale=0)
    public Long getCreatedBy() {
        return createdBy;
    }

    @Column(name="UPDATED_BY", nullable=true, precision=10, scale=0)
    public Long getUpdatedBy() {
        return updatedBy;
    }

    @Column(name="CREATEDDATE", nullable=false)
    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    @Column(name="UPDATEDDATE", nullable=true)
    public OffsetDateTime getUpdatedOn() {
        return updatedOn;
    }



    /*
     * Mutators
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setScopeType(ScopeType scopeType) {
        this.scopeType = scopeType;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setUpdatedOn(OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    

    /*
     * Additional Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityRestriction that = (QuantityRestriction) o;
        return Objects.equals(ID, that.ID) &&
                scopeType == that.scopeType &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(updatedOn, that.updatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, scopeType, createdBy, updatedBy, createdOn, updatedOn);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("ID", ID)
                .append("scope", scopeType)
                .append("createdBy", createdBy)
                .append("updatedBy", updatedBy)
                .append("createdOn", createdOn)
                .append("updatedOn", updatedOn)
                .toString();
    }

}

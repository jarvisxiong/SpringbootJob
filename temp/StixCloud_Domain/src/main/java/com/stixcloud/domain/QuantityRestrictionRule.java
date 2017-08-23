package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by mango on 15/3/17.
 */
@Entity
@Table(name="QUANTITY_RESTRICTION_RULE")
public class QuantityRestrictionRule implements Serializable {

    private static final long serialVersionUID = -4922090145398247897L;



    /*
     * Enums and Inner Classes
     */
    public enum RuleType {
        PRODUCT_GROUP(1),
        PRODUCT(2),
        PRICE_CLASS_OF_PRODUCT_GROUP(3),
        PRICE_CATEGORY_OF_PRODUCT_GROUP(4),
        PRICE_CLASS_OF_PRODUCT(5),
        PRICE_CATEGORY_OF_PRODUCT(6);

        private int value;
        RuleType(int value) { this.value = value; }
        public int intValue() { return value; }
    }



    /*
     * Custom Converters
     */
    public static class RuleTypeConverter implements AttributeConverter<RuleType,Integer>{
        @Override
        public Integer convertToDatabaseColumn(RuleType ruleType) {
            return ruleType==null?null:ruleType.intValue();
        }

        @Override
        public RuleType convertToEntityAttribute(Integer integer) {
            return Arrays.stream(RuleType.values()).filter(r -> r.intValue()==Optional.of(integer).orElse(Integer.MIN_VALUE)).findFirst().orElse(null);
        }
    }



    /*
     * Fields
     */
    private Long ID, quantityRestrictionID;
    private RuleType type;
    private Long createdBy;
    private OffsetDateTime effectiveStart, effectiveEnd;



    /*
     * Constructors
     */
    public QuantityRestrictionRule() {}

    public QuantityRestrictionRule(Long quantityRestrictionID, RuleType type, Long createdBy, OffsetDateTime effectiveStart, OffsetDateTime effectiveEnd) {
        this.quantityRestrictionID = quantityRestrictionID;
        this.type = type;
        this.createdBy = createdBy;
        this.effectiveStart = effectiveStart;
        this.effectiveEnd = effectiveEnd;
    }



    /*
     * Accessors
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QuantityRestrictionRuleIDSequenceGenerator")
    //@SequenceGenerator(name="QuantityRestrictionRuleIDSequenceGenerator", sequenceName = "QUANTITY_RESTRICTION_RULE_ID", allocationSize = 1)
    @GenericGenerator(name = "QuantityRestrictionRuleIDSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "QUANTITY_RESTRICTION_RULE_ID"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "none")
            })
    @Id
    @Column(name="RESTRICTIONRULEID", nullable=false, precision=10, scale=0)
    public Long getID() {
        return ID;
    }

    @Column(name="RESTRICTION_ID", nullable=false, precision=10, scale=0)
    public Long getQuantityRestrictionID() {
        return quantityRestrictionID;
    }

    @Column(name="RULE_TYPE", nullable=false, precision=1, scale=0)
    @Convert(converter=RuleTypeConverter.class)
    public RuleType getType() {
        return type;
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
     * Accessors
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setQuantityRestrictionID(Long quantityRestrictionID) {
        this.quantityRestrictionID = quantityRestrictionID;
    }

    public void setType(RuleType type) {
        this.type = type;
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
        QuantityRestrictionRule that = (QuantityRestrictionRule) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(quantityRestrictionID, that.quantityRestrictionID) &&
                type == that.type &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(effectiveStart, that.effectiveStart) &&
                Objects.equals(effectiveEnd, that.effectiveEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, quantityRestrictionID, type, createdBy, effectiveStart, effectiveEnd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("ID", ID)
                .append("quantityRestrictionID", quantityRestrictionID)
                .append("type", type)
                .append("createdBy", createdBy)
                .append("effectiveStart", effectiveStart)
                .append("effectiveEnd", effectiveEnd)
                .toString();
    }

}

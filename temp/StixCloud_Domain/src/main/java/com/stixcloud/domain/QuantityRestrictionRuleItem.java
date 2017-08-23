package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by mango on 15/3/17.
 */
@Entity
@Table(name="QUANTITY_RESTRICTION_ITEM")
public class QuantityRestrictionRuleItem implements Serializable {

    private static final long serialVersionUID = 6784343538179630192L;



    /*
     * Enums and Inner Classes
     */
    public enum ItemType {
        PRODUCT_GROUP_ID(1),
        PRODUCT_ID(2),
        PRICE_CLASS_ID(3),
        PRICE_CATEGORY_ID(4);

        private int value;
        ItemType(int value) { this.value = value; }
        public int intValue() { return value; }
    }



    /*
     * Custom Converters
     */
    public static class ItemTypeConverter implements AttributeConverter<ItemType,Integer>{
        @Override
        public Integer convertToDatabaseColumn(ItemType itemType) {
            return itemType==null?null:itemType.intValue();
        }

        @Override
        public ItemType convertToEntityAttribute(Integer integer) {
            return Arrays.stream(ItemType.values()).filter(r -> r.intValue()== Optional.of(integer).orElse(Integer.MIN_VALUE)).findFirst().orElse(null);
        }
    }



    /*
     * Fields
     */
    private Long ID, quantityRestrictionRuleID;
    private ItemType type;
    private Long value;



    /*
     * Accessors
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QuantityRestrictionRuleItemIDSequenceGenerator")
    //@SequenceGenerator(name="QuantityRestrictionRuleItemIDSequenceGenerator", sequenceName = "QUANTITY_RESTRICTION_ITEM_ID", allocationSize = 1)
    @GenericGenerator(name = "QuantityRestrictionRuleItemIDSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "QUANTITY_RESTRICTION_ITEM_ID"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "none")
            })
    @Id
    @Column(name="RESTRICTIONITEMID", nullable=false, precision=10, scale=0)
    public Long getID() {
        return ID;
    }

    @Column(name="RESTRICTION_RULE_ID", nullable=false, precision=10, scale=0)
    public Long getQuantityRestrictionRuleID() {
        return quantityRestrictionRuleID;
    }

    @Column(name="ITEM_TYPE", nullable=false, precision=10, scale=0)
    @Convert(converter=ItemTypeConverter.class)
    public ItemType getType() {
        return type;
    }

    @Column(name="PARAM", nullable=false, precision=10, scale=0)
    public Long getValue() {
        return value;
    }



    /*
     * Mutators
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setQuantityRestrictionRuleID(Long quantityRestrictionRuleID) {
        this.quantityRestrictionRuleID = quantityRestrictionRuleID;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setValue(Long value) {
        this.value = value;
    }



    /*
     * Additional Methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityRestrictionRuleItem that = (QuantityRestrictionRuleItem) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(quantityRestrictionRuleID, that.quantityRestrictionRuleID) &&
                type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, quantityRestrictionRuleID, type, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("ID", ID)
                .append("quantityRestrictionRuleID", quantityRestrictionRuleID)
                .append("type", type)
                .append("value", value)
                .toString();
    }
}

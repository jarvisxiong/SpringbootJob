package com.stixcloud.salesquotarestriction.domain.converters;

import com.stixcloud.domain.QuantityRestrictionRuleItem;
import com.stixcloud.salesquotarestriction.domain.Environment;
import com.stixcloud.salesquotarestriction.domain.ItemQuantityTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mango on 21/3/17.
 */
@Component
public class ItemQuantityTupleToEnvironmentConverter implements TypeConverter<ItemQuantityTuple, Environment> {


    private final ItemToVariableConverter itemToVariableConverter;

    @Autowired
    public ItemQuantityTupleToEnvironmentConverter(ItemToVariableConverter itemToVariableConverter) {
        this.itemToVariableConverter = itemToVariableConverter;
    }


    @Override
    public Environment convert(ItemQuantityTuple item) {

        Environment environment = new Environment();
        environment.put(itemToVariableConverter.convert(QuantityRestrictionRuleItem.ItemType.PRODUCT_GROUP_ID), item.getProductGroupID());
        environment.put(itemToVariableConverter.convert(QuantityRestrictionRuleItem.ItemType.PRODUCT_ID), item.getProductID());
        environment.put(itemToVariableConverter.convert(QuantityRestrictionRuleItem.ItemType.PRICE_CLASS_ID), item.getPriceClassID());
        environment.put(itemToVariableConverter.convert(QuantityRestrictionRuleItem.ItemType.PRICE_CATEGORY_ID), item.getPriceCategoryID());

        return environment;

    }

}

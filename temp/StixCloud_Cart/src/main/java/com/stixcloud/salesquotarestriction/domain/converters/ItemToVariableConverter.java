package com.stixcloud.salesquotarestriction.domain.converters;

import com.stixcloud.domain.QuantityRestrictionRuleItem;
import com.stixcloud.salesquotarestriction.domain.expression.Variable;
import org.springframework.stereotype.Component;

/**
 * Created by mango on 21/3/17.
 */
@Component
public class ItemToVariableConverter implements TypeConverter<QuantityRestrictionRuleItem.ItemType, Variable> {

    @Override
    public Variable convert(QuantityRestrictionRuleItem.ItemType itemType) {
        return Variable.valueOf(itemType.name());
    }

}

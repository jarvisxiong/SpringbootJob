package com.stixcloud.salesquotarestriction.domain.converters;

import com.stixcloud.domain.QuantityRestriction;
import com.stixcloud.domain.QuantityRestrictionDetail;
import com.stixcloud.domain.QuantityRestrictionRule;
import com.stixcloud.domain.QuantityRestrictionRuleItem;
import com.stixcloud.salesquotarestriction.domain.Quota;
import com.stixcloud.salesquotarestriction.domain.expression.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mango on 21/3/17.
 */
@Component
public class QuantityRestrictionToQuotaListConverter implements TypeConverter<Map<QuantityRestriction, Map<QuantityRestrictionDetail, Map<QuantityRestrictionRule, EnumMap<QuantityRestrictionRuleItem.ItemType, List<QuantityRestrictionRuleItem>>>>>, List<Quota>> {

    private final ItemToVariableConverter                               itemToVariableConverter;
    private final QuantityRestrictionScopeTypeToQuotaScopeTypeConverter quantityRestrictionScopeTypeToQuotaScopeTypeConverter;

    @Autowired
    public QuantityRestrictionToQuotaListConverter(ItemToVariableConverter itemToVariableConverter, QuantityRestrictionScopeTypeToQuotaScopeTypeConverter quantityRestrictionScopeTypeToQuotaScopeTypeConverter) {
        this.itemToVariableConverter = itemToVariableConverter;
        this.quantityRestrictionScopeTypeToQuotaScopeTypeConverter = quantityRestrictionScopeTypeToQuotaScopeTypeConverter;
    }

    @Override
    public List<Quota> convert(Map<QuantityRestriction, Map<QuantityRestrictionDetail, Map<QuantityRestrictionRule, EnumMap<QuantityRestrictionRuleItem.ItemType, List<QuantityRestrictionRuleItem>>>>> b) {

        // construct internal quota representation from domain structures
        List<Quota> quotaList = new ArrayList<>();

        // construct expression for each rule of type1 kianleong quota
        b.forEach((qr, vqr) -> {

            Quota        q              = new Quota();
            OrExpression rootExpression = new OrExpression();

            q.setScope(new Quota.Scope(quantityRestrictionScopeTypeToQuotaScopeTypeConverter.convert(qr.getScopeType()), null));
            q.setCriteria(rootExpression);

            quotaList.add(q);

            // construct expression for each rule detail
            vqr.forEach((qrd, vqrd) -> {

                q.setQuota(qrd.getQuota());

                // construct expression for each rule
                vqrd.forEach((qrr, vqrri_it) -> {

                    // construct expression for each rule item type
                    AndExpression ruleItemExpression = new AndExpression();

                    vqrri_it.forEach((qrri_it, vqrri) -> {

                        // construct expression for each rule item
                        // combine multiple item types within one rule using ||
                        OrExpression ruleItemConditionExpression = new OrExpression();
                        vqrri.forEach(qrri -> ruleItemConditionExpression.or(new RelationalExpression<>(new VariableExpression<>(itemToVariableConverter.convert(qrri.getType())), RelationalExpression.Operator.EQUALS, new ConstantExpression<>(qrri.getValue()))));
                        ruleItemExpression.and(ruleItemConditionExpression);

                    });

                    // concat existing expressions with newly constructed rule item expression
                    rootExpression.or(ruleItemExpression);

                });
            });

        });

        return quotaList;
    }
}

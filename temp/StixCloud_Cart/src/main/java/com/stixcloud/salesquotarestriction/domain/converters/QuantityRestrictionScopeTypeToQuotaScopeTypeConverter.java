package com.stixcloud.salesquotarestriction.domain.converters;

import com.stixcloud.domain.QuantityRestriction;
import com.stixcloud.salesquotarestriction.domain.Quota;
import org.springframework.stereotype.Component;

/**
 * Created by mango on 27/3/17.
 */
@Component
public class QuantityRestrictionScopeTypeToQuotaScopeTypeConverter implements TypeConverter<QuantityRestriction.ScopeType, Quota.Scope.Type> {

    @Override
    public Quota.Scope.Type convert(QuantityRestriction.ScopeType scopeType) {
        return Quota.Scope.Type.valueOf(scopeType.name());
    }
}

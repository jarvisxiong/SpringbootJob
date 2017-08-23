package com.stixcloud.salesquotarestriction.domain.converters;

import com.stixcloud.domain.SharedQuotaDetailLive;
import com.stixcloud.domain.SharedQuotaLive;
import com.stixcloud.salesquotarestriction.domain.Quota;
import com.stixcloud.salesquotarestriction.domain.expression.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by mango on 21/3/17.
 */
@Component
public class SharedQuotaLiveToQuotaListConverter implements TypeConverter<Map<SharedQuotaLive, List<SharedQuotaDetailLive>>, List<Quota>> {

    @Override
    public List<Quota> convert(Map<SharedQuotaLive, List<SharedQuotaDetailLive>> a) {

        // construct internal quota representation from domain structures
        List<Quota> quotaList = new ArrayList<>();

        // process each rule of type2 hari quota
        a.forEach((sql, vsqdl) -> {

            Quota         q                           = new Quota();
            OrExpression  rootExpression              = new OrExpression();
            AndExpression sharedQuotaExpression       = new AndExpression();
            OrExpression  sharedQuotaDetailExpression = new OrExpression();

            q.setScope(new Quota.Scope(Quota.Scope.Type.GLOBAL, null));
            q.setQuota(sql.getQuota());
            Optional.ofNullable(sql.getAlert()).ifPresent(alert -> q.setWarningThreshold((long) ((100 - alert) * sql.getQuota() / 100d)));

            // construct expression for product group id
            // concat existing expressions with newly constructed shared quota expression
            q.setCriteria(
                    rootExpression.or(
                            sharedQuotaExpression.and(
                                    new RelationalExpression<>(new VariableExpression<>(Variable.PRODUCT_GROUP_ID), RelationalExpression.Operator.EQUALS, new ConstantExpression<>(sql.getProductGroupID())))
                                    .and(sharedQuotaDetailExpression)
                    )
            );

            quotaList.add(q);

            // construct expression for each detail
            vsqdl.forEach(sqdl -> {

                AndExpression sharedQuotaDetailItemExpression = new AndExpression();

                // construct product id expression if product id is configured
                if (sqdl.getProductID() != null && sqdl.getProductID() != 0)
                    sharedQuotaDetailItemExpression.and(new RelationalExpression<>(new VariableExpression<>(Variable.PRODUCT_ID), RelationalExpression.Operator.EQUALS, new ConstantExpression<>(sqdl.getProductID())));

                // construct price class expression if price class id is configured
                if (sqdl.getPriceClassID() != null && sqdl.getPriceClassID() != 0)
                    sharedQuotaDetailItemExpression.and(new RelationalExpression<>(new VariableExpression<>(Variable.PRICE_CLASS_ID), RelationalExpression.Operator.EQUALS, new ConstantExpression<>(sqdl.getPriceClassID())));

                // construct price category expression if price category id is configured
                if (sqdl.getPriceCategoryID() != null && sqdl.getPriceCategoryID() != 0)
                    sharedQuotaDetailItemExpression.and(new RelationalExpression<>(new VariableExpression<>(Variable.PRICE_CATEGORY_ID), RelationalExpression.Operator.EQUALS, new ConstantExpression<>(sqdl.getPriceCategoryID())));

                sharedQuotaDetailExpression.or(sharedQuotaDetailItemExpression);

            });

        });

        return quotaList;
    }
}

package com.stixcloud.salesquotarestriction.service;

import com.stixcloud.salesquotarestriction.api.ValidateRequest;
import com.stixcloud.salesquotarestriction.api.ValidateResult;

/**
 * Created by mango on 15/3/17.
 */
public interface SalesQuotaRestrictionService {
    ValidateResult checkQuantityRestriction(ValidateRequest request);
    ValidateResult checkSharedQuota(ValidateRequest request);
}

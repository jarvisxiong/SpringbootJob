package com.stixcloud.salesquotarestriction.service;

import com.stixcloud.salesquotarestriction.api.ValidateRequest;
import com.stixcloud.salesquotarestriction.api.ValidateResult;
import com.stixcloud.salesquotarestriction.domain.ItemQuantityTuple;
import com.stixcloud.salesquotarestriction.domain.Quota;
import com.stixcloud.salesquotarestriction.domain.converters.ItemQuantityTupleToEnvironmentConverter;
import com.stixcloud.salesquotarestriction.domain.converters.QuantityRestrictionToQuotaListConverter;
import com.stixcloud.salesquotarestriction.domain.converters.SharedQuotaLiveToQuotaListConverter;
import com.stixcloud.salesquotarestriction.repo.QuantityRestrictionRepository;
import com.stixcloud.salesquotarestriction.repo.QuotaRepositoryCustom;
import com.stixcloud.salesquotarestriction.repo.SharedQuotaLiveRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by mango on 15/3/17.
 */
@RefreshScope
@Service
public class SalesQuotaRestrictionServiceImpl implements SalesQuotaRestrictionService {

    private static final Logger LOGGER = LogManager.getLogger(SalesQuotaRestrictionServiceImpl.class);
    private final QuantityRestrictionRepository           quantityRestrictionRepository;
    private final SharedQuotaLiveRepository               sharedQuotaLiveRepository;
    private final QuotaRepositoryCustom                   quotaRepository;
    private final QuantityRestrictionToQuotaListConverter quantityRestrictionToQuotaListConverter;
    private final SharedQuotaLiveToQuotaListConverter     sharedQuotaLiveToQuotaListConverter;
    private final ItemQuantityTupleToEnvironmentConverter itemQuantityTupleToEnvironmentConverter;
    private final MessageSource                           messageSource;

    @Autowired
    public SalesQuotaRestrictionServiceImpl(MessageSource messageSource, QuantityRestrictionRepository quantityRestrictionRepository, SharedQuotaLiveRepository sharedQuotaLiveRepository, QuantityRestrictionToQuotaListConverter quantityRestrictionToQuotaListConverter, SharedQuotaLiveToQuotaListConverter sharedQuotaLiveToQuotaListConverter, ItemQuantityTupleToEnvironmentConverter itemQuantityTupleToEnvironmentConverter, QuotaRepositoryCustom quotaRepository) {
        this.messageSource = messageSource;
        this.quantityRestrictionRepository = quantityRestrictionRepository;
        this.sharedQuotaLiveRepository = sharedQuotaLiveRepository;
        this.quantityRestrictionToQuotaListConverter = quantityRestrictionToQuotaListConverter;
        this.sharedQuotaLiveToQuotaListConverter = sharedQuotaLiveToQuotaListConverter;
        this.itemQuantityTupleToEnvironmentConverter = itemQuantityTupleToEnvironmentConverter;
        this.quotaRepository = quotaRepository;
    }


    @Override
    public ValidateResult checkQuantityRestriction(ValidateRequest request) {
        return checkQuotaByQuotaListAndRequest(fetchQuantityRestriction(request), request);
    }


    @Override
    public ValidateResult checkSharedQuota(ValidateRequest request) {
        return checkQuotaByQuotaListAndRequest(fetchSharedQuota(request), request);
    }


    private List<Quota> fetchQuantityRestriction(ValidateRequest request) {

        List<Quota> a = quantityRestrictionToQuotaListConverter.convert(quantityRestrictionRepository.findApplicableRestriction(null));

        // patron scopes only exist for kianleong quantity restrictionhari|ki
        // patron email and patron primary contact number have 1 param each, the param is the actual email/primary contact number
        a.forEach(q -> {
            switch (q.getScope().getType()) {
                case PATRON_EMAIL:
                    q.getScope().setParameter(request.getPatronEmail());
                    break;
                case PATRON_PRIMARYCONTACTNUMBER:
                    q.getScope().setParameter(request.getPatronPrimaryContactNumber());
                    break;
            }
        });

        if (LOGGER.isDebugEnabled())
            LOGGER.debug(String.format("kl: %s", a));

        return a;

    }


    private List<Quota> fetchSharedQuota(ValidateRequest request) {

        // construct internal quota representation from domain
        List<Quota> b = sharedQuotaLiveToQuotaListConverter.convert(sharedQuotaLiveRepository.findApplicableQuota(request.getItemsList().stream().map(ValidateRequest.Item::getProductGroupID).distinct().collect(Collectors.toList()), request.getItemsList().stream().map(ValidateRequest.Item::getProductID).distinct().collect(Collectors.toList())));

        if (LOGGER.isDebugEnabled())
            LOGGER.debug(String.format("h: %s", b));

        return b;

    }


    private ValidateResult checkQuotaByQuotaListAndRequest(final List<Quota> quotaList, final ValidateRequest request) {
        final ValidateResult   validateResult   = new ValidateResult(ValidateResult.Result.PASS);
        final MiniQuotaChecker miniQuotaChecker = new MiniQuotaChecker();

        final Map<Quota.Scope.Type, List<Quota>> quotaByScopeTypeMap = quotaList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.groupingBy(
                                q -> q.getScope().getType()
                                , Collectors.toList()
                        )
                        , (Map<Quota.Scope.Type, List<Quota>> m) -> {
                            Stream.of(Quota.Scope.Type.values()).forEach(t -> m.putIfAbsent(t, Collections.emptyList()));
                            return m;
                        }
                )
        );

        List<ItemQuantityTuple> requestItemList = request.getItemsList().stream().map(i -> (ItemQuantityTuple) i).collect(Collectors.toList());

        // check by TRANSACTION using only request data
        miniQuotaChecker.processQuota(quotaByScopeTypeMap.get(Quota.Scope.Type.TRANSACTION), q -> requestItemList.stream(), validateResult);

        // fetch additional data and check by PATRON_PRIMARYCONTACTNUMBER
        miniQuotaChecker.processQuota(quotaByScopeTypeMap.get(Quota.Scope.Type.PATRON_PRIMARYCONTACTNUMBER), miniQuotaChecker.accumulator(quotaRepository::findQuantityByPatronPrimaryContactNumber, requestItemList.stream()), validateResult);

        // fetch additional data and check by PATRON_EMAIL
        miniQuotaChecker.processQuota(quotaByScopeTypeMap.get(Quota.Scope.Type.PATRON_EMAIL), miniQuotaChecker.accumulator(quotaRepository::findQuantityByPatronEmailAddress, requestItemList.stream()), validateResult);

        // fetch additional data and check by GLOBAL
        miniQuotaChecker.processQuota(quotaByScopeTypeMap.get(Quota.Scope.Type.GLOBAL), miniQuotaChecker.accumulator(quotaRepository::findQuantity, requestItemList.stream()), validateResult);

        // resolve result message
        validateResult.setMessage(messageSource.getMessage(validateResult.getResult().getMessageKey(), null, LocaleContextHolder.getLocale()));

        return validateResult;

    }


    class MiniQuotaChecker {

        /**
         * Checks quotas given the quotas and datasource and returns failing result
         *
         * @param quotaList      quota configuration
         * @param dataFunction   datasource
         * @param validateResult result
         * @return true if quota exceeded or alert threshold exceeded
         */
        private void processQuota(final List<Quota> quotaList, final Function<Quota[], Stream<ItemQuantityTuple>> dataFunction, final ValidateResult validateResult) {
            if (validateResult.getResult() != ValidateResult.Result.PASS && validateResult.getResult() != ValidateResult.Result.ALERT)
                return;

            Map<Quota, Long> quotaConsumption = quotaList.stream().collect(Collectors.toMap(Function.identity(), q -> 0L, (e,n) -> n));

            // invoke the function
            dataFunction.apply(quotaList.toArray(new Quota[0]))

                    // process the results. i.e check quota
                    .anyMatch(i -> quotaList.stream().anyMatch(q -> {

                                boolean matchesCriteria = q.getCriteria().test(itemQuantityTupleToEnvironmentConverter.convert(i));
                                if (matchesCriteria)
                                    quotaConsumption.compute(q, (qq, l) -> l += i.getQuantity());

                                boolean withinWarningThreshold = !q.getWarningThreshold().isPresent() || quotaConsumption.get(q) <= q.getWarningThreshold().getAsLong();
                                boolean withinQuotaThreshold   = quotaConsumption.get(q) <= q.getQuota();

                                if (LOGGER.isDebugEnabled() && (!withinWarningThreshold || !withinQuotaThreshold))
                                    LOGGER.debug(String.format("exceeded warning threshold: %s (%s/%s), exceeded quota threshold: %s (%s/%s), quantity: %s, quota: %s", !withinWarningThreshold, quotaConsumption.get(q), q.getWarningThreshold().isPresent() ? q.getWarningThreshold().getAsLong() : "N/A", !withinQuotaThreshold, quotaConsumption.get(q), q.getQuota(), quotaConsumption.get(q), q));

                                if (!withinQuotaThreshold)
                                    switch (q.getScope().getType()) {
                                        case GLOBAL:
                                            validateResult.setResult(ValidateResult.Result.QUOTA_EXCEEDED_GLOBAL);
                                            break;
                                        case TRANSACTION:
                                            validateResult.setResult(ValidateResult.Result.QUOTA_EXCEEDED_PER_TRANSACTION);
                                            break;
                                        case PATRON_EMAIL:
                                            validateResult.setResult(ValidateResult.Result.QUOTA_EXCEEDED_PER_PATRON_EMAIL);
                                            break;
                                        case PATRON_PRIMARYCONTACTNUMBER:
                                            validateResult.setResult(ValidateResult.Result.QUOTA_EXCEEDED_PER_PATRON_PRIMARY_CONTACT_NUMBER);
                                            break;
                                    }
                                else if (!withinWarningThreshold && validateResult.getResult() == ValidateResult.Result.PASS)
                                    validateResult.setResult(ValidateResult.Result.ALERT);

                                return !withinQuotaThreshold;

                            })

                    );

        }

        private Function<Quota[], Stream<ItemQuantityTuple>> accumulator(final Function<Quota[], Stream<ItemQuantityTuple>> dataFunction, final Stream<ItemQuantityTuple> additionalItemList) {
            return dataFunction.andThen(i -> Stream.concat(additionalItemList, i));
        }

    }
}

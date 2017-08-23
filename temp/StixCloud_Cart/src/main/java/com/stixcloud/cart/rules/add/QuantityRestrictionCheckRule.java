package com.stixcloud.cart.rules.add;

import com.stixcloud.cart.AddToCartException;
import com.stixcloud.cart.repo.PatronPhoneRepository;
import com.stixcloud.cart.repo.PatronProfileRepository;
import com.stixcloud.cart.repo.ProductLiveRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.PatronPhone;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.ProductLive;
import com.stixcloud.salesquotarestriction.api.ValidateRequest;
import com.stixcloud.salesquotarestriction.api.ValidateResult;
import com.stixcloud.salesquotarestriction.service.SalesQuotaRestrictionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by mango on 30/3/2017.
 */
@SpringRule
public class QuantityRestrictionCheckRule extends BaseCartRule {

    private static final Logger LOGGER = LogManager.getLogger(QuantityRestrictionCheckRule.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SalesQuotaRestrictionService salesQuotaRestrictionService;

    @Autowired
    private PatronProfileRepository patronProfileRepository;

    @Autowired
    private PatronPhoneRepository patronPhoneRepository;

    @Autowired
    private ProductLiveRepository productLiveRepository;

    private Long patronProfileId;

    public void setPatronProfileId(Long patronProfileId) {
        this.patronProfileId = patronProfileId;
    }

    @Priority
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Condition
    public boolean when() {
        return isCartNotEmpty();
    }

    @Action
    public void then() throws AddToCartException {
        assert false;

        ValidateRequest validateRequest = new ValidateRequest();

        Optional<PatronProfile> patronProfile = Optional.ofNullable(patronProfileRepository.findOne(patronProfileId));
        Optional<PatronPhone> patronPhone = patronPhoneRepository.getNumbersByPatronProfileId(patronProfileId)
                .stream()
                .filter(PatronPhone::getIsprimary)
                .findAny();

        Map<Long, Long> product_productGroupMap = new HashMap<>();
        for (ProductLive pl:productLiveRepository.findAll(
                getShoppingCart().getCartItems().stream()
                        .map(CartItem::getProductId)
                        .collect(Collectors.toList()))
                )
            product_productGroupMap.put(pl.getProductid(), pl.getProductGroupId());

        validateRequest.setItemsList(new ArrayList<>());
        validateRequest.setPatronEmail(patronProfile.map(PatronProfile::getEmailaddress).orElse(null));
        validateRequest.setPatronPrimaryContactNumber(patronPhone.map(PatronPhone::getPhonenumber).orElse(null));
        for (CartItem cartItem : getShoppingCart().getCartItems())
            validateRequest.getItemsList().add(new ValidateRequest.Item(cartItem.getQuantity().longValue(), product_productGroupMap.get(cartItem.getProductId()), cartItem.getProductId(), cartItem.getPriceClassId(), cartItem.getPriceCatId()));


        ValidateResult validateResult = salesQuotaRestrictionService.checkQuantityRestriction(validateRequest);


        switch (validateResult.getResult()) {
            case PASS:
                break;
            case ALERT:
                // Booking Engine does not need to cater to alert. Treat as PASS
                break;
            case QUOTA_EXCEEDED_GLOBAL:
                throw new AddToCartException(messageSource.getMessage("salesquotarestriction.message.exceeded.global", null, LocaleContextHolder.getLocale()));
            case QUOTA_EXCEEDED_PER_TRANSACTION:
                throw new AddToCartException(messageSource.getMessage("salesquotarestriction.message.exceeded.transaction", null, LocaleContextHolder.getLocale()));
            case QUOTA_EXCEEDED_PER_PATRON_EMAIL:
                throw new AddToCartException(messageSource.getMessage("salesquotarestriction.message.exceeded.patronemail", null, LocaleContextHolder.getLocale()));
            case QUOTA_EXCEEDED_PER_PATRON_PRIMARY_CONTACT_NUMBER:
                throw new AddToCartException(messageSource.getMessage("salesquotarestriction.message.exceeded.patronprimarycontactnumber", null, LocaleContextHolder.getLocale()));
        }


    }
}

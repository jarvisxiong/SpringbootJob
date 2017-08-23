package com.stixcloud.evoucher.types;

import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.constant.EVoucherConstants;
import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import com.stixcloud.evoucher.rules.BaseEVoucherRule;
import com.stixcloud.evoucher.rules.BasicRule;
import com.stixcloud.evoucher.rules.CreditCardRule;
import com.stixcloud.evoucher.rules.MaxQuantityRule;
import com.stixcloud.evoucher.rules.MultiUsageRule;
import com.stixcloud.evoucher.rules.OrganizationRule;
import com.stixcloud.evoucher.rules.RedeemRestrictionsRule;
import com.stixcloud.evoucher.rules.VenueRule;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DbsEvoucher extends AbstractEvoucher {

  private CreditCardRule creditCardRule;
  private RedeemRestrictionsRule redeemRestrictionsRule;
  private MaxQuantityRule maxQuantityRule;
  private MultiUsageRule multiUsageRule;
  private OrganizationRule organizationRule;
  private VenueRule venueRule;

  public DbsEvoucher(MessageSource messageSource, List<EVoucherCreditCardRegex> ccRegex,
                     String creditCardNo, List<ProductPaymentMethod> productPaymentMethod,
                     List<UserPaymentMethod> userPaymentMethod, Map<String, Long> evoucherQtyMap,
                     List<EVoucherView> eVoucherViewList,
                     List<ProductPromoterVenue> productPromoterVenueList) {
    basicRule = new BasicRule(messageSource);
    creditCardRule = new CreditCardRule(ccRegex, creditCardNo, messageSource);
    redeemRestrictionsRule =
        new RedeemRestrictionsRule(messageSource, productPaymentMethod, userPaymentMethod);
    maxQuantityRule = new MaxQuantityRule(messageSource, evoucherQtyMap);
    multiUsageRule = new MultiUsageRule(messageSource, eVoucherViewList);
    organizationRule = new OrganizationRule(messageSource, productPromoterVenueList);
    venueRule = new VenueRule(messageSource, productPromoterVenueList);
  }

  public CreditCardRule getCreditCardRule() {
    return creditCardRule;
  }

  public RedeemRestrictionsRule getRedeemRestrictionsRule() {
    return redeemRestrictionsRule;
  }

  public MaxQuantityRule getMaxQuantityRule() {
    return maxQuantityRule;
  }

  public MultiUsageRule getMultiUsageRule() {
    return multiUsageRule;
  }

  public OrganizationRule getOrganizationRule() {
    return organizationRule;
  }

  public VenueRule getVenueRule() {
    return venueRule;
  }

  @Override
  public EVoucherValidation validateAtApplyEvoucher() {
    List<BaseEVoucherRule> rules = new ArrayList<BaseEVoucherRule>();
    rules.add(maxQuantityRule);
    rules.add(multiUsageRule);
    rules.add(organizationRule);
    rules.add(redeemRestrictionsRule);
    rules.add(venueRule);
    return runRules(rules);
  }

  @Override
  public EVoucherValidation validateAtPreCommitOrder() {
    return runRules(Arrays.asList(creditCardRule));
  }

  @Override
  public void setEvoucher(List<EVoucherView> evoucherViewList) {
    this.evoucherView = evoucherViewList.get(0);
    basicRule.setEVoucherView(evoucherView);
    for (EVoucherView evoucherTmp : evoucherViewList) {
      switch (evoucherTmp.getAttributeName()) {
        case EVoucherConstants.CREDIT_CARD:
          creditCardRule.setEVoucherView(evoucherTmp);
          break;
        case EVoucherConstants.REDEEM_RESTRICTIONS:
          redeemRestrictionsRule.setEVoucherView(evoucherTmp);
          break;
        case EVoucherConstants.MAXIMUM_QTY:
          maxQuantityRule.setEVoucherView(evoucherTmp);
          break;
        case EVoucherConstants.USAGE_WITH_OTHER_VOUCHERS:
          multiUsageRule.setEVoucherView(evoucherTmp);
          break;
        case EVoucherConstants.ORGANIZATION:
          organizationRule.setEVoucherView(evoucherTmp);
          break;
        case EVoucherConstants.VENUE:
          venueRule.setEVoucherView(evoucherTmp);
          break;
      }
    }
  }

}

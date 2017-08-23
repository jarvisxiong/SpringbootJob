package com.stixcloud.evoucher.types;

import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.constant.EVoucherConstants;
import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import com.stixcloud.evoucher.rules.BaseEVoucherRule;
import com.stixcloud.evoucher.rules.BasicRule;
import com.stixcloud.evoucher.rules.MaxQuantityRule;
import com.stixcloud.evoucher.rules.MembershipPatronRule;
import com.stixcloud.evoucher.rules.MultiUsageRule;
import com.stixcloud.evoucher.rules.OrganizationRule;
import com.stixcloud.evoucher.rules.RedeemRestrictionsRule;
import com.stixcloud.evoucher.rules.VenueRule;
import org.javamoney.moneta.Money;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

public class EsplanadeEvoucher extends AbstractEvoucher {

  private MembershipPatronRule membershipPatronRule;
  private RedeemRestrictionsRule redeemRestrictionsRule;
  private MaxQuantityRule maxQuantityRule;
  private MultiUsageRule multiUsageRule;
  private OrganizationRule organizationRule;
  private VenueRule venueRule;

  public EsplanadeEvoucher(MessageSource messageSource,
                           List<ProductPaymentMethod> productPaymentMethod,
                           List<UserPaymentMethod> userPaymentMethod,
                           Map<String, Long> evoucherQtyMap, List<EVoucherView> eVoucherViewList,
                           List<ProductPromoterVenue> productPromoterVenueList,
                           Long patronAccountId) {
    basicRule = new BasicRule(messageSource);
    membershipPatronRule = new MembershipPatronRule(messageSource, patronAccountId);
    redeemRestrictionsRule =
        new RedeemRestrictionsRule(messageSource, productPaymentMethod, userPaymentMethod);
    maxQuantityRule = new MaxQuantityRule(messageSource, evoucherQtyMap);
    multiUsageRule = new MultiUsageRule(messageSource, eVoucherViewList);
    organizationRule = new OrganizationRule(messageSource, productPromoterVenueList);
    venueRule = new VenueRule(messageSource, productPromoterVenueList);
  }


  public MembershipPatronRule getMembershipPatronRule() {
    return membershipPatronRule;
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
    rules.add(membershipPatronRule);
    return runRules(rules);
  }

  @Override
  public EVoucherValidation validateAtPreCommitOrder() {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    return new EVoucherValidation(evoucherView.geteVoucherId(),
        Money.of(evoucherView.getRedeemValue(), currencyUnit)
        , evoucherView.geteVoucherType(), Boolean.TRUE,
        EVoucherEnum.VALIDATION_RULE_PASS.name());
  }

  @Override
  public void setEvoucher(List<EVoucherView> evoucherViewList) {
    this.evoucherView = evoucherViewList.get(0);
    basicRule.setEVoucherView(evoucherView);
    for (EVoucherView evoucherTmp : evoucherViewList) {
      switch (evoucherTmp.getAttributeName()) {
        case EVoucherConstants.MEMBERSHIP_PATRON_ACCT_NO:
          membershipPatronRule.setEVoucherView(evoucherTmp);
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

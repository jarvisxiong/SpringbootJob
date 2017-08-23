package com.stixcloud.evoucher.types;

import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.constant.EVoucherConstants;
import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import com.stixcloud.evoucher.rules.BasicRule;
import com.stixcloud.evoucher.rules.RedeemRestrictionsRule;
import org.javamoney.moneta.Money;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Arrays;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

public class SisticEvoucher extends AbstractEvoucher {

  private RedeemRestrictionsRule redeemRestrictionsRule;

  public SisticEvoucher(MessageSource messageSource,
                        List<ProductPaymentMethod> productPaymentMethod,
                        List<UserPaymentMethod> userPaymentMethod) {
    basicRule = new BasicRule(messageSource);
    redeemRestrictionsRule =
        new RedeemRestrictionsRule(messageSource, productPaymentMethod, userPaymentMethod);
  }

  public RedeemRestrictionsRule getRedeemRestrictionsRule() {
    return redeemRestrictionsRule;
  }

  @Override
  public EVoucherValidation validateAtApplyEvoucher() {
    return runRules(Arrays.asList(redeemRestrictionsRule));
  }

  @Override
  public EVoucherValidation validateAtPreCommitOrder() {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    return new EVoucherValidation(evoucherView.geteVoucherId(),
        Money.of(evoucherView.getRedeemValue(), currencyUnit), evoucherView.geteVoucherType(),
        Boolean.TRUE, EVoucherEnum.VALIDATION_RULE_PASS.name());
  }

  @Override
  public void setEvoucher(List<EVoucherView> evoucherViewList) {
    this.evoucherView = evoucherViewList.get(0);
    basicRule.setEVoucherView(evoucherView);
    for (EVoucherView evoucherTmp : evoucherViewList) {
      switch (evoucherTmp.getAttributeName()) {
        case EVoucherConstants.REDEEM_RESTRICTIONS:
          redeemRestrictionsRule.setEVoucherView(evoucherTmp);
          break;
      }
    }
  }

}

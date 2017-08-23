package com.stixcloud.evoucher.types;

import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.rules.BaseEVoucherRule;
import com.stixcloud.evoucher.rules.BasicRule;
import com.stixcloud.evoucher.rules.EVoucherRulesListener;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Arrays;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

public abstract class AbstractEvoucher implements IEvoucher {

  protected RulesEngine ruleEngine;
  protected EVoucherRulesListener eVoucherRulesListener;
  protected MessageSource messageSource;
  protected BasicRule basicRule;
  protected EVoucherView evoucherView;
  @Value("${easyrule.silent.mode:false}")
  protected boolean isSilentMode;

  @Override
  public EVoucherValidation validateBasicRule() {
    return runRules(Arrays.asList(basicRule));
  }

  protected EVoucherValidation runRules(List<BaseEVoucherRule> rules) {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    EVoucherValidation eVoucherValidation = new EVoucherValidation(evoucherView, currencyUnit);
    eVoucherRulesListener = new EVoucherRulesListener();
    ruleEngine =
        RulesEngineBuilder.aNewRulesEngine().withSilentMode(isSilentMode)
            .withRuleListener(eVoucherRulesListener)
            .withSkipOnFirstFailedRule(true).build();

    rules.forEach(ruleEngine::registerRule);
    ruleEngine.fireRules();

    if (eVoucherRulesListener.hasError()) {
      eVoucherValidation.setValidationConstant(
          eVoucherRulesListener.geteVoucherException().getInvalidValidationConstant());
      eVoucherValidation.setValidationResult(Boolean.FALSE);
    } else {
      eVoucherValidation.setValidationResult(Boolean.TRUE);
      eVoucherValidation.setValidationConstant(EVoucherEnum.VALIDATION_RULE_PASS.name());
    }

    return eVoucherValidation;
  }
}

package com.stixcloud.evoucher.rules;

import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;

@SpringRule
public class AllowedPaymentMethodRule extends BaseEVoucherRule {
  private static final Logger logger = LogManager.getLogger(AllowedPaymentMethodRule.class);

  private String paymentMethod;

  public AllowedPaymentMethodRule() {
    super();
  }

  public AllowedPaymentMethodRule(String paymentMethod, MessageSource messageSource) {
    this.paymentMethod = paymentMethod;
    this.messageSource = messageSource;

  }

  @Priority
  public int getPriority() {
    return getEVoucherView().getPriority();
  }

  @Condition
  public boolean when() {
    return StringUtils.isNotBlank(getEVoucherView().getAttributeValue());
  }

  @Action
  public void then() throws EVoucherValidationException {
    setExecuted(true);
    EVoucherView eVoucher = getEVoucherView();
    logger.info("Validate allowed payment method for evoucher id: " + eVoucher.geteVoucherId());
    logger.info("The selected payment method: " + paymentMethod);
    logger.info("The allowed payment method: " + eVoucher.getAttributeValue());
    String[] allowedPaymentMethodArray = eVoucher.getAttributeValue().split(";");

    if (paymentMethod == null || !Arrays.asList(allowedPaymentMethodArray).stream()
        .filter(e -> e.toUpperCase().contains(paymentMethod.toUpperCase())).findAny().isPresent()) {
      throw new EVoucherValidationException(messageSource
          .getMessage("evoucher.rule.allowed.payment.method.error", null, Locale.getDefault()),
          EVoucherEnum.VALIDATION_RULE_ALLOWED_PM_FAIL.name());
    }
  }
}

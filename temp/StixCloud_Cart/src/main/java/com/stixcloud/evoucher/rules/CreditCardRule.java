package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import org.apache.commons.lang3.StringUtils;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

@SpringRule
public class CreditCardRule extends BaseEVoucherRule {

  public CreditCardRule() {
    super();
  }

  public CreditCardRule(List<EVoucherCreditCardRegex> ccRegex, String creditCardNo,
                        MessageSource messageSource) {
    this.ccRegex = ccRegex;
    this.creditCardNumber = creditCardNo;
    this.messageSource = messageSource;
  }

  private String creditCardNumber;
  private List<EVoucherCreditCardRegex> ccRegex;

  @Priority
  public int getPriority() {
    return getEVoucherView().getPriority();
  }

  @Condition
  public boolean when() {
    return !ccRegex.isEmpty() && !StringUtils.isEmpty(creditCardNumber);
  }

  @Action
  public void then() throws EVoucherValidationException {
    this.setExecuted(Boolean.TRUE);

    boolean result = false;
    for (EVoucherCreditCardRegex regex : ccRegex) {
      String regexIterator = regex.getCcRegex();
      result |= creditCardNumber.matches(regexIterator);
      if (result) {
        break;
      }
    }

    if (!result) {
      throw new EVoucherValidationException(messageSource.getMessage(
          "evoucher.rule.creditcard.error", null, Locale.getDefault()),
          EVoucherEnum.VALIDATION_RULE_CC_FAIL.name());
    }
  }
}

package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SpringRule
public class BasicRule extends BaseEVoucherRule {

  public BasicRule() {
    super();
  }

  public BasicRule(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    return true;
  }

  @Action
  public void then() throws EVoucherValidationException {
    setExecuted(Boolean.TRUE);
    EVoucherView eVoucher = getEVoucherView();
    String errorMessageKey = null;
    String errorConstant = null;
    switch (eVoucher.geteVoucherStatus()) {
      case "0":
        errorMessageKey = "evoucher.rule.basic.inactive.error";
        errorConstant = EVoucherEnum.VALIDATION_BASIC_INACTIVE.name();
        break;
      case "1":
        Date currentDate = Calendar.getInstance().getTime();
        if (currentDate.before(eVoucher.getStartDate()) || currentDate
            .after(eVoucher.getEndDate())) {
          errorMessageKey = "evoucher.rule.basic.expired.error";
          errorConstant = EVoucherEnum.VALIDATION_BASIC_EXPIRED.name();
        }
        break;
      case "2":
        errorMessageKey = "evoucher.rule.basic.redeemed.error";
        errorConstant = EVoucherEnum.VALIDATION_BASIC_REDEEMED.name();
    }
    if (null != errorMessageKey) {
      throw new EVoucherValidationException(messageSource.getMessage(errorMessageKey, null,
          Locale.getDefault()), errorConstant);
    }
  }
}

package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by dbthan on 13/10/2016.
 */

@SpringRule
public class MembershipPatronRule extends BaseEVoucherRule {

  private Long patronAccountId;

  public MembershipPatronRule() {
    super();
  }

  public MembershipPatronRule(MessageSource messageSource, Long patronAccountId) {
    this.messageSource = messageSource;
    this.patronAccountId = patronAccountId;
  }

  @Priority
  public int getPriority() {
    return getEVoucherView().getPriority();
  }

  @Condition
  public boolean when() {
    return patronAccountId != null;
  }

  @Action
  public void then() throws EVoucherValidationException {
    setExecuted(Boolean.TRUE);
    if (!patronAccountId.equals(getEVoucherView().getPatronId())) {
      throw new EVoucherValidationException(messageSource.getMessage("evoucher.rule.patron.error",
          null, Locale.getDefault()), EVoucherEnum.VALIDATION_VIOLATE_OWNERSHIP.name());
    }
  }

}

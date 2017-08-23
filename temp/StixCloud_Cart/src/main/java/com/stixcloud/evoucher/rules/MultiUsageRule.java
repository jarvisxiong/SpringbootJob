package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

/**
 * Created by dbthan on 13/10/2016.
 */

@SpringRule
public class MultiUsageRule extends BaseEVoucherRule {

  private static final String FALSE_STR = "FALSE";

  private List<EVoucherView> eVoucherViewList;

  public MultiUsageRule() {
    super();
  }

  public MultiUsageRule(MessageSource messageSource, List<EVoucherView> eVoucherViewList) {
    this.messageSource = messageSource;
    this.eVoucherViewList = eVoucherViewList;
  }

  public void setEVoucherViewList(List<EVoucherView> eVoucherViewList) {
    this.eVoucherViewList = eVoucherViewList;
  }

  @Priority
  public int getPriority() {
    return getEVoucherView().getPriority();
  }

  @Condition
  public boolean when() {
    return eVoucherViewList != null && !eVoucherViewList.isEmpty();
  }

  @Action
  public void then() throws EVoucherValidationException {
    setExecuted(Boolean.TRUE);
    EVoucherView eVoucher = getEVoucherView();
    if (FALSE_STR.equals(eVoucher.getAttributeValue())) {
      for (EVoucherView eVoucherCheck : eVoucherViewList) {
        if (!eVoucherCheck.geteVoucherType().equalsIgnoreCase(eVoucher.geteVoucherType())
            && !eVoucherCheck.geteVoucherId().equalsIgnoreCase(eVoucher.geteVoucherId())) {
          throw new EVoucherValidationException(messageSource.getMessage(
              "evoucher.rule.multi.usage.error", null, Locale.getDefault()),
              EVoucherEnum.VALIDATION_RULE_USAGE_WITH_OTHER_VOUCHERS_FAIL.name());
        }
      }
    }
  }
}

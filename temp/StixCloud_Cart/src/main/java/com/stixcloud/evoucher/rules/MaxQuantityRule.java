package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.constant.EVoucherEnum;
import com.stixcloud.evoucher.domain.EVoucherView;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Map;

/**
 * Created by dbthan on 13/10/2016.
 */
@SpringRule
public class MaxQuantityRule extends BaseEVoucherRule {

  private Map<String, Long> evoucherQtyMap;

  public MaxQuantityRule() {
    super();
  }

  public MaxQuantityRule(MessageSource messageSource, Map<String, Long> evoucherQtyMap) {
    this.messageSource = messageSource;
    this.evoucherQtyMap = evoucherQtyMap;
  }

  @Priority
  public int getPriority() {
    return getEVoucherView().getPriority();
  }

  @Condition
  public boolean when() {
    return evoucherQtyMap != null && !evoucherQtyMap.isEmpty();
  }

  @Action
  public void then() throws EVoucherValidationException {

    this.setExecuted(Boolean.TRUE);
    EVoucherView eVoucher = getEVoucherView();
    Long quantity = evoucherQtyMap.get(eVoucher.geteVoucherType());
    if (quantity > Integer.parseInt(eVoucher.getAttributeValue())) {
      throw new EVoucherValidationException(messageSource.getMessage(
          "evoucher.rule.maxquantity.error", null, Locale.getDefault()),
          EVoucherEnum.VALIDATION_RULE_MAX_QTY_FAIL.name());
    }

  }
}

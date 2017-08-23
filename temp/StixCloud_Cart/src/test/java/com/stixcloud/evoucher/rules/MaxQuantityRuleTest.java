package com.stixcloud.evoucher.rules;

import static org.junit.Assert.assertFalse;

import com.stixcloud.evoucher.domain.EVoucherView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MaxQuantityRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;
  private MaxQuantityRule rule;
  private EVoucherView eVoucherView;
  private Map<String, Long> evoucherQtyMap;

  @Before
  public void setup() {
    evoucherQtyMap = new HashMap<String, Long>();
    evoucherQtyMap.put("SISTIC_E_VOUCHER", 1L);
  }

  @Test
  public void onSuccess() {
    eVoucherView = new EVoucherView("83HNF2", "SISTIC_E_VOUCHER", 10L, "1", null, null, null, null,
        null, null, "1", 1);
    rule = new MaxQuantityRule(messageSource, evoucherQtyMap);
    rule.setEVoucherView(eVoucherView);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailed() {
    evoucherQtyMap.put("ESPLANADE_E_VOUCHER", 2L);
    eVoucherView = new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null,
        null, null, null, "1", 1);
    rule = new MaxQuantityRule(messageSource, evoucherQtyMap);
    rule.setEVoucherView(eVoucherView);
    fireRulesAndAssertError(rule,
        messageSource.getMessage("evoucher.rule.maxquantity.error", null, Locale.getDefault()));
  }
}

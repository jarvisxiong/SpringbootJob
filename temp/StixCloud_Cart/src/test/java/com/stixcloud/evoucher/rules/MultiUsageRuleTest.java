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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiUsageRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;

  private MultiUsageRule rule;

  private List<EVoucherView> eVoucherViewList;
  private EVoucherView eVoucher;

  @Before
  public void setup() {

    eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView e1 =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 1);
    eVoucherViewList.add(e1);
    rule = new MultiUsageRule(messageSource, eVoucherViewList);
    eVoucher =
        new EVoucherView("83HNF1", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 1);
  }

  @Test
  public void onSuccess() {
    EVoucherView e2 =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 1);
    eVoucherViewList.add(e2);
    rule.setEVoucherViewList(eVoucherViewList);
    rule.setEVoucherView(eVoucher);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailed() {
    EVoucherView e2 =
        new EVoucherView("83HNF3", "DBS_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 1);
    eVoucherViewList.add(e2);
    rule.setEVoucherViewList(eVoucherViewList);
    rule.setEVoucherView(eVoucher);
    fireRulesAndAssertError(rule,
        messageSource.getMessage("evoucher.rule.multi.usage.error", null, Locale.getDefault()));
  }
}

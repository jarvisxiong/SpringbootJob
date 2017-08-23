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

import java.text.SimpleDateFormat;
import java.util.Locale;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;

  private BasicRule basicRule;

  private String startDate = "19-10-2016";
  private String endDate = "19-10-2018";
  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

  @Before
  public void setup() {
    basicRule = new BasicRule(messageSource);
  }

  @Test
  public void onSuccessCaseActiveNormal() throws Exception {
    EVoucherView eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), null, null, null, null, null, null);
    basicRule.setEVoucherView(eVoucherView);
    fireRuleAndAssertExecuted(basicRule);
    assertFalse(getEVoucherRulesListener().hasError());

  }

  @Test
  public void onFailedCaseExpired() throws Exception {
    startDate = "19-10-2017";
    EVoucherView eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), null, null, null, null, null, null);
    basicRule.setEVoucherView(eVoucherView);
    fireRulesAndAssertError(basicRule,
        messageSource.getMessage("evoucher.rule.basic.expired.error", null, Locale.getDefault()));
  }

  @Test
  public void onFailedCaseInactive() throws Exception {
    EVoucherView eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "0", formatter.parse(startDate),
            formatter.parse(endDate), null, null, null, null, null, null);
    basicRule.setEVoucherView(eVoucherView);
    fireRulesAndAssertError(basicRule,
        messageSource.getMessage("evoucher.rule.basic.inactive.error", null, Locale.getDefault()));
  }

  @Test
  public void onFailedCaseRedeemed() throws Exception {
    EVoucherView eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "2", formatter.parse(startDate),
            formatter.parse(endDate), null, null, null, null, null, null);
    basicRule.setEVoucherView(eVoucherView);
    fireRulesAndAssertError(basicRule,
        messageSource.getMessage("evoucher.rule.basic.redeemed.error", null, Locale.getDefault()));
  }
}

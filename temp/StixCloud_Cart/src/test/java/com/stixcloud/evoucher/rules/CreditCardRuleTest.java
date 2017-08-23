package com.stixcloud.evoucher.rules;

import static org.junit.Assert.assertFalse;

import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
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
public class CreditCardRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;
  private CreditCardRule rule;

  private List<EVoucherCreditCardRegex> ccRegex;
  private String creditCardNumber = "";
  private EVoucherView eVoucherView;

  @Before
  public void setup() {
    ccRegex = new ArrayList<EVoucherCreditCardRegex>();
    EVoucherCreditCardRegex regex = new EVoucherCreditCardRegex("E839HJ2", "^412345\\d{10}$");
    ccRegex.add(regex);
  }

  @Test
  public void onSuccess() {
    creditCardNumber = "4123455789012349";
    rule = new CreditCardRule(ccRegex, creditCardNumber, messageSource);
    eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            null, null, 1);
    rule.setEVoucherView(eVoucherView);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailed() {
    creditCardNumber = "1223456789012349";
    rule = new CreditCardRule(ccRegex, creditCardNumber, messageSource);
    eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            null, null, 1);
    rule.setEVoucherView(eVoucherView);
    fireRulesAndAssertError(rule,
        messageSource.getMessage("evoucher.rule.creditcard.error", null, Locale.getDefault()));
  }
}

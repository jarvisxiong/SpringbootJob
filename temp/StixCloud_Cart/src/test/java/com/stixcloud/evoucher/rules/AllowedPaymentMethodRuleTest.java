package com.stixcloud.evoucher.rules;

import static org.junit.Assert.assertFalse;

import com.stixcloud.evoucher.domain.EVoucherView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllowedPaymentMethodRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;

  private AllowedPaymentMethodRule rule;
  private String paymentMethod;
  private EVoucherView eVoucherView;

  @Test
  public void onSuccess() {
    paymentMethod = "VISA";
    eVoucherView = new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null,
        null, null, null, "VISA;MASTER", 1);
    rule = new AllowedPaymentMethodRule(paymentMethod, messageSource);
    rule.setEVoucherView(eVoucherView);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailed() {
    paymentMethod = "ESPLANADE_E_VOUCHER";
    eVoucherView = new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null,
        null, null, null, "VISA;MASTER", 1);
    rule = new AllowedPaymentMethodRule(paymentMethod, messageSource);
    rule.setEVoucherView(eVoucherView);
    fireRulesAndAssertError(rule,
        messageSource
            .getMessage("evoucher.rule.allowed.payment.method.error", null, Locale.getDefault()));
  }

}

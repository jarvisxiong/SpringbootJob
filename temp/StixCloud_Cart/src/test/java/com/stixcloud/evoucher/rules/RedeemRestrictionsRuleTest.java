package com.stixcloud.evoucher.rules;

import static org.junit.Assert.assertFalse;

import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
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
public class RedeemRestrictionsRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;

  private RedeemRestrictionsRule rule;
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;

  @Before
  public void setup() {

    productPaymentMethod = new ArrayList<ProductPaymentMethod>();
    userPaymentMethod = new ArrayList<UserPaymentMethod>();
    ProductPaymentMethod p1 = new ProductPaymentMethod(44L, 55L, "MASTER");
    productPaymentMethod.add(p1);
    UserPaymentMethod u1 = new UserPaymentMethod(55L, "MASTER", 44L, 55L);
    UserPaymentMethod u2 = new UserPaymentMethod(66L, "EXPRESS", 41L, 55L);
    userPaymentMethod.add(u1);
    userPaymentMethod.add(u2);
    rule = new RedeemRestrictionsRule(messageSource, productPaymentMethod, userPaymentMethod);
  }

  @Test
  public void onSuccess() {
    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "MASTER", 10L, "1", null, null, null, null, null,
            "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 1);
    rule.setEVoucherView(eVoucher);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailedCaseWithin() {
    ProductPaymentMethod p2 = new ProductPaymentMethod(41L, 51L, "VISA");
    productPaymentMethod.add(p2);
    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "REDEEM_RESTRICTIONS", "RESTRICT_WITHIN", 1);
    rule.setEVoucherView(eVoucher);
    fireRulesAndAssertError(
        rule,
        messageSource.getMessage("evoucher.rule.redeem.restriction.within.error", null,
            Locale.getDefault()));
  }

  @Test
  public void onFailedCaseOutside() {
    ProductPaymentMethod p2 = new ProductPaymentMethod(41L, 51L, "VISA");
    productPaymentMethod.add(p2);
    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "REDEEM_RESTRICTIONS", "RESTRICT_OUTSIDE", 1);
    rule.setEVoucherView(eVoucher);
    fireRulesAndAssertError(
        rule,
        messageSource.getMessage("evoucher.rule.redeem.restriction.outside.error", null,
            Locale.getDefault()));
  }

  @Test
  public void onFailedCaseAll() {
    ProductPaymentMethod p2 = new ProductPaymentMethod(41L, 51L, "VISA");
    productPaymentMethod.add(p2);
    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "REDEEM_RESTRICTIONS", "RESTRICT_ALL", 1);
    rule.setEVoucherView(eVoucher);
    fireRulesAndAssertError(
        rule,
        messageSource.getMessage("evoucher.rule.redeem.restriction.all.error", null,
            Locale.getDefault()));
  }
}

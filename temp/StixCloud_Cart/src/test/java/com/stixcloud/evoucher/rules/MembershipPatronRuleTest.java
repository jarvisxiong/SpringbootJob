package com.stixcloud.evoucher.rules;

import static org.junit.Assert.assertFalse;

import com.stixcloud.cart.TenantPropertiesTest;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.evoucher.domain.EVoucherView;
import org.junit.Before;
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
public class MembershipPatronRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;

  private MembershipPatronRule rule;

  private Long patronProfileId;
  private EVoucherView eVoucherView;

  @Before
  public void setup() throws Exception {
    TenantPropertiesTest.setUp();
    patronProfileId = TenantContextHolder.getTenant().getProfileInfoId();
    rule = new MembershipPatronRule(messageSource, patronProfileId);

  }

  @Test
  public void onSuccess() {

    eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null,
            TenantContextHolder
                .getTenant().getProfileInfoId(), null,
            null, null, 1);
    rule.setEVoucherView(eVoucherView);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailed() {
    eVoucherView =
        new EVoucherView("83HNF2", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            null, null, 1);
    rule.setEVoucherView(eVoucherView);
    fireRulesAndAssertError(rule,
        messageSource.getMessage("evoucher.rule.patron.error", null, Locale.getDefault()));
  }
}

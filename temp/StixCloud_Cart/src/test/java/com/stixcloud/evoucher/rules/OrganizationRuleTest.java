package com.stixcloud.evoucher.rules;

import static org.junit.Assert.assertFalse;

import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
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
public class OrganizationRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;

  private OrganizationRule rule;
  private List<ProductPromoterVenue> productPromoterVenueList;

  @Before
  public void setup() {
    productPromoterVenueList = new ArrayList<ProductPromoterVenue>();
    ProductPromoterVenue promoter1 = new ProductPromoterVenue(12L, 13L, 14L);
    productPromoterVenueList.add(promoter1);
    rule = new OrganizationRule(messageSource, productPromoterVenueList);
  }

  @Test
  public void onSuccess() {
    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "ORGANIZATION", "13", 1);
    rule.setEVoucherView(eVoucher);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailed() {
    ProductPromoterVenue promoter2 = new ProductPromoterVenue(111L, 1311L, 1411L);
    productPromoterVenueList.add(promoter2);

    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "ORGANIZATION", "154", 1);
    rule.setEVoucherView(eVoucher);
    fireRulesAndAssertError(rule,
        messageSource.getMessage("evoucher.rule.organization.error", null, Locale.getDefault()));
  }
}

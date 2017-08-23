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
public class VenueRuleTest extends BaseEVoucherRuleTest {

  @Autowired
  private MessageSource messageSource;

  private VenueRule rule;

  private List<ProductPromoterVenue> productPromoterVenue;

  @Before
  public void setup() {
    productPromoterVenue = new ArrayList<ProductPromoterVenue>();
    ProductPromoterVenue promoter1 = new ProductPromoterVenue(12L, 13L, 14L);
    productPromoterVenue.add(promoter1);
    rule = new VenueRule(messageSource, productPromoterVenue);
  }

  @Test
  public void onSuccess() {
    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "VENUE", "14", 1);
    rule.setEVoucherView(eVoucher);
    fireRuleAndAssertExecuted(rule);
    assertFalse(getEVoucherRulesListener().hasError());
  }

  @Test
  public void onFailed() {
    ProductPromoterVenue promoter2 = new ProductPromoterVenue(111L, 1311L, 1411L);
    productPromoterVenue.add(promoter2);
    rule = new VenueRule(messageSource, productPromoterVenue);
    EVoucherView eVoucher =
        new EVoucherView("83HNF3", "ESPLANADE_E_VOUCHER", 10L, "1", null, null, null, null, null,
            "VENUE", "154", 1);
    rule.setEVoucherView(eVoucher);
    fireRulesAndAssertError(rule,
        messageSource.getMessage("evoucher.rule.venue.error", null, Locale.getDefault()));
  }
}

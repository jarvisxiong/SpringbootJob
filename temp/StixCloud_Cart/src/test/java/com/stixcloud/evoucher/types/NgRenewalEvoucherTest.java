package com.stixcloud.evoucher.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.evoucher.api.EVoucherValidation;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class NgRenewalEvoucherTest {

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;

  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
  private String startDate = "19-Oct-2016";
  private String endDate = "19-Oct-2018";
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;
  private NgRenewalEvoucher ngRenewalEvoucher;

  @Before
  public void setup() throws Exception {

    Locale.setDefault(new Locale("en", "SG"));
    productPaymentMethod = new ArrayList<ProductPaymentMethod>();
    userPaymentMethod = new ArrayList<UserPaymentMethod>();
  }

  private void basicSetup() throws Exception {
    ProductPaymentMethod product = new ProductPaymentMethod(251254L, 635254L, "MASTERCARD");
    productPaymentMethod.add(product);
    UserPaymentMethod user = new UserPaymentMethod(25425L, "VISA", 26L, 66L);
    userPaymentMethod.add(user);
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "NG_RENEWAL_EVOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    ngRenewalEvoucher =
        new NgRenewalEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
    ngRenewalEvoucher.setEvoucher(Arrays.asList(viewRestriction));

  }

  @Test
  public void validateAtApplyEvoucher() throws Exception {
    basicSetup();
    EVoucherValidation result = ngRenewalEvoucher.validateAtApplyEvoucher();
    assertTrue(ngRenewalEvoucher.getRedeemRestrictionsRule().isExecuted());
    EVoucherValidation expected =
        objectMapper.readValue(
            this.getClass().getResource(
                "/evoucher/json/evoucherValidation_NgRenewalEvoucher_Pass.json"),
            EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void validateAtPreCommitOrder() throws Exception {
    basicSetup();
    EVoucherValidation result = ngRenewalEvoucher.validateAtPreCommitOrder();
    EVoucherValidation expected =
        objectMapper.readValue(
            this.getClass().getResource(
                "/evoucher/json/evoucherValidation_NgRenewalEvoucher_Pass.json"),
            EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void setEvoucherCaseRedeemRestriction() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "NG_RENEWAL_EVOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    eVoucherViewList.add(viewRestriction);
    ngRenewalEvoucher =
        new NgRenewalEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
    ngRenewalEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = ngRenewalEvoucher.getRedeemRestrictionsRule().getEVoucherView();
    assertEquals(viewRestriction, result);
  }
}

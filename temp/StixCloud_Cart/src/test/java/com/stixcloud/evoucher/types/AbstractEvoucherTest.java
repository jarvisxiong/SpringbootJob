package com.stixcloud.evoucher.types;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import com.stixcloud.evoucher.rules.BaseEVoucherRule;
import com.stixcloud.evoucher.rules.BasicRule;
import com.stixcloud.evoucher.rules.OrganizationRule;
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
import java.util.List;
import java.util.Locale;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractEvoucherTest {

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;

  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
  private String startDate = "19-Oct-2016";
  private String endDate = "19-Oct-2018";
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;
  private SisticEvoucher sisticEvoucher;
  private BasicRule basicRule;
  private OrganizationRule organizationRule;
  private List<ProductPromoterVenue> productPromoterVenueList;
  private EVoucherView view;

  @Before
  public void setup() throws Exception {

    Locale.setDefault(new Locale("en", "SG"));
    productPaymentMethod = new ArrayList<ProductPaymentMethod>();
    userPaymentMethod = new ArrayList<UserPaymentMethod>();
    productPromoterVenueList = new ArrayList<ProductPromoterVenue>();
    ProductPaymentMethod product = new ProductPaymentMethod(251254L, 635254L, "MASTERCARD");
    productPaymentMethod.add(product);
    UserPaymentMethod user = new UserPaymentMethod(25425L, "VISA", 26L, 66L);
    userPaymentMethod.add(user);
    ProductPromoterVenue venue = new ProductPromoterVenue(25444L, 100L, 100L);
    productPromoterVenueList.add(venue);
    view =
        new EVoucherView("MCjT1X5GTU", "SISTIC_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 5);
    basicRule = new BasicRule(messageSource);
    basicRule.setEVoucherView(view);
    sisticEvoucher = new SisticEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
    sisticEvoucher.evoucherView = view;
  }

  @Test
  public void runRulesCasePass() throws Exception {

    organizationRule = new OrganizationRule(messageSource, productPromoterVenueList);
    organizationRule.setEVoucherView(view);
    List<BaseEVoucherRule> rules = new ArrayList<BaseEVoucherRule>();
    rules.add(organizationRule);
    rules.add(basicRule);
    EVoucherValidation result = sisticEvoucher.runRules(rules);
    EVoucherValidation expected =
        objectMapper.readValue(
            this.getClass().getResource(
                "/evoucher/json/evoucherValidation_SisticEvoucher_Pass.json"),
            EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void runRulesCaseFailed() throws Exception {
    organizationRule = new OrganizationRule(messageSource, productPromoterVenueList);
    view =
        new EVoucherView("MCjT1X5GTU", "SISTIC_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "11", 5);
    organizationRule.setEVoucherView(view);
    List<BaseEVoucherRule> rules = new ArrayList<BaseEVoucherRule>();
    rules.add(organizationRule);
    rules.add(basicRule);
    EVoucherValidation result = sisticEvoucher.runRules(rules);
    EVoucherValidation expected =
        objectMapper.readValue(
            this.getClass().getResource(
                "/evoucher/json/evoucherValidation_SisticEvoucher_Failed.json"),
            EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void validateBasicRule() throws Exception {
    sisticEvoucher.basicRule = basicRule;
    EVoucherValidation result = sisticEvoucher.validateBasicRule();
    EVoucherValidation expected =
        objectMapper.readValue(
            this.getClass().getResource(
                "/evoucher/json/evoucherValidation_SisticEvoucher_Pass.json"),
            EVoucherValidation.class);
    assertEquals(expected, result);
  }
}

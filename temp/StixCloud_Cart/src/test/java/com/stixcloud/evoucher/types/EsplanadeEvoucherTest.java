package com.stixcloud.evoucher.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsplanadeEvoucherTest {

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;

  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
  private String startDate = "19-Oct-2016";
  private String endDate = "19-Oct-2018";
  private EsplanadeEvoucher esplanadeEvoucher;
  private Long patronAccountId;
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;
  private Map<String, Long> evoucherQtyMap;
  private List<EVoucherView> eVoucherViewList;
  private List<ProductPromoterVenue> productPromoterVenueList;

  @Before
  public void setup() throws Exception {

    Locale.setDefault(new Locale("en", "SG"));
    productPaymentMethod = new ArrayList<ProductPaymentMethod>();
    userPaymentMethod = new ArrayList<UserPaymentMethod>();
    evoucherQtyMap = new HashMap<String, Long>();
    eVoucherViewList = new ArrayList<EVoucherView>();
    productPromoterVenueList = new ArrayList<ProductPromoterVenue>();

  }

  private void basicSetup() throws Exception {
    patronAccountId = 44L;
    ProductPaymentMethod product = new ProductPaymentMethod(251254L, 635254L, "MASTERCARD");
    productPaymentMethod.add(product);
    UserPaymentMethod user = new UserPaymentMethod(25425L, "VISA", 26L, 66L);
    userPaymentMethod.add(user);
    evoucherQtyMap.put("ESPLANADE_E_VOUCHER", 1L);
    evoucherQtyMap.put("OCBC_E_VOUCHER", 2L);
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    EVoucherView viewMultiUsage =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "1", 4);
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 6);
    EVoucherView viewMembership =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MEMBERSHIP_PATRON_ACCT_NO",
            "MEMBERSHIP_PATRON_ACCT_NO", 5);
    eVoucherViewList.add(viewOrganization);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewMultiUsage);
    eVoucherViewList.add(viewMaxQuantity);
    eVoucherViewList.add(viewMembership);
    eVoucherViewList.add(viewRestriction);
    ProductPromoterVenue venue = new ProductPromoterVenue(25444L, 100L, 100L);
    productPromoterVenueList.add(venue);
    esplanadeEvoucher =
        new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
            evoucherQtyMap, Arrays.asList(viewOrganization), productPromoterVenueList,
            patronAccountId);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
  }

  @Test
  public void validateAtApplyEvoucher() throws Exception {
    basicSetup();
    EVoucherValidation result = esplanadeEvoucher.validateAtApplyEvoucher();
    assertTrue(esplanadeEvoucher.getMaxQuantityRule().isExecuted());
    assertTrue(esplanadeEvoucher.getMultiUsageRule().isExecuted());
    assertTrue(esplanadeEvoucher.getOrganizationRule().isExecuted());
    assertTrue(esplanadeEvoucher.getRedeemRestrictionsRule().isExecuted());
    assertTrue(esplanadeEvoucher.getVenueRule().isExecuted());
    assertTrue(esplanadeEvoucher.getMembershipPatronRule().isExecuted());
    EVoucherValidation expected =
        objectMapper.readValue(
            this.getClass().getResource(
                "/evoucher/json/evoucherValidation_EsplanadeEvoucher_Pass.json"),
            EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void validateAtPreCommitOrder() throws Exception {
    basicSetup();
    EVoucherValidation result = esplanadeEvoucher.validateAtPreCommitOrder();
    EVoucherValidation expected =
        objectMapper.readValue(
            this.getClass().getResource(
                "/evoucher/json/evoucherValidation_EsplanadeEvoucher_Pass.json"),
            EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void setEvoucherCaseRedeemRestriction() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    eVoucherViewList.add(viewRestriction);
    esplanadeEvoucher =
        new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
            evoucherQtyMap, eVoucherViewList, productPromoterVenueList, patronAccountId);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = esplanadeEvoucher.getRedeemRestrictionsRule().getEVoucherView();
    assertEquals(viewRestriction, result);
  }

  @Test
  public void setEvoucherCaseMaxQuantity() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewMaxQuantity =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "1", 4);
    eVoucherViewList.add(viewMaxQuantity);
    esplanadeEvoucher =
        new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
            evoucherQtyMap, eVoucherViewList, productPromoterVenueList, patronAccountId);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = esplanadeEvoucher.getMaxQuantityRule().getEVoucherView();
    assertEquals(viewMaxQuantity, result);
  }

  @Test
  public void setEvoucherCaseMultiUsage() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewMultiUsage =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    eVoucherViewList.add(viewMultiUsage);
    esplanadeEvoucher =
        new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
            evoucherQtyMap, eVoucherViewList, productPromoterVenueList, patronAccountId);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = esplanadeEvoucher.getMultiUsageRule().getEVoucherView();
    assertEquals(viewMultiUsage, result);
  }

  @Test
  public void setEvoucherCaseOrganization() throws Exception {
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    eVoucherViewList.add(viewOrganization);
    esplanadeEvoucher =
        new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
            evoucherQtyMap, eVoucherViewList, productPromoterVenueList, patronAccountId);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = esplanadeEvoucher.getOrganizationRule().getEVoucherView();
    assertEquals(viewOrganization, result);
  }

  @Test
  public void setEvoucherCaseVenue() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewVenue =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    eVoucherViewList.add(viewVenue);
    esplanadeEvoucher =
        new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
            evoucherQtyMap, eVoucherViewList, productPromoterVenueList, patronAccountId);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = esplanadeEvoucher.getVenueRule().getEVoucherView();
    assertEquals(viewVenue, result);
  }

  @Test
  public void setEvoucherCaseMembershipPatron() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewMembership =
        new EVoucherView("MCjT1X5GTU", "ESPLANADE_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MEMBERSHIP_PATRON_ACCT_NO",
            "MEMBERSHIP_PATRON_ACCT_NO", 5);
    eVoucherViewList.add(viewMembership);
    esplanadeEvoucher =
        new EsplanadeEvoucher(messageSource, productPaymentMethod, userPaymentMethod,
            evoucherQtyMap, eVoucherViewList, productPromoterVenueList, patronAccountId);
    esplanadeEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = esplanadeEvoucher.getMembershipPatronRule().getEVoucherView();
    assertEquals(viewMembership, result);
  }
}

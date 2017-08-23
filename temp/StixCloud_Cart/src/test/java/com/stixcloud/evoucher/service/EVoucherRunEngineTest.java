package com.stixcloud.evoucher.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.TenantPropertiesTest;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.api.EVoucherValidationRequest;
import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class EVoucherRunEngineTest {

  @Mock
  private MessageSource messageSource;
  @Mock
  private IEVoucherService eVoucherService;
  @InjectMocks
  private EVoucherRunEngine eVoucherRunEngine;

  @Mock
  private IShoppingCartService shoppingCartService;

  private CurrencyUnit currencyUnit;
  private EVoucherValidationRequest eVoucherValidationRequest;
  private List<EVoucherView> eVoucherViewList;
  private List<ProductPaymentMethod> productPaymentMethodList;
  private List<UserPaymentMethod> userPaymentMethodList;
  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
  private String startDate = "19-10-2016";
  private String endDate = "19-10-2018";
  private List<String> evoucherIdList;
  private List<ProductPromoterVenue> productPromoterVenueList;
  private List<EVoucherCreditCardRegex> creditCardRegexList;

  @Before
  public void setup() throws Exception {
    TenantPropertiesTest.setUp();

    currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    MockitoAnnotations.initMocks(this);
    evoucherIdList = new ArrayList<String>();
    evoucherIdList.add("EEKGP32fP");
    productPromoterVenueList = new ArrayList<ProductPromoterVenue>();
    creditCardRegexList = new ArrayList<EVoucherCreditCardRegex>();
    List<Long> productIdList = new ArrayList<Long>();
    productIdList.add(11L);
    productIdList.add(33L);
    eVoucherViewList = new ArrayList<EVoucherView>();

    productPaymentMethodList = new ArrayList<ProductPaymentMethod>();
    ProductPaymentMethod productPaymentMethod = new ProductPaymentMethod(2L, 3L, "MASTER");
    productPaymentMethodList.add(productPaymentMethod);
    userPaymentMethodList = new ArrayList<UserPaymentMethod>();
    UserPaymentMethod
        userPaymentMethod =
        new UserPaymentMethod(13L, "MASTER", TenantContextHolder.getTenant().getUserInfoId(),
            TenantContextHolder
                .getTenant().getProfileInfoId());
    userPaymentMethodList.add(userPaymentMethod);
    EVoucherCreditCardRegex regex = new EVoucherCreditCardRegex("EEKGP32fP", "^545301\\d{10}$");
    creditCardRegexList.add(regex);
    ProductPromoterVenue promoterVenue = new ProductPromoterVenue(11L, 100L, 100L);
    productPromoterVenueList.add(promoterVenue);
    EVoucherView viewMultiUsage =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "2", 4);
    EVoucherView viewCC =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    eVoucherViewList.add(viewMultiUsage);
    eVoucherViewList.add(viewRestriction);
    eVoucherViewList.add(viewCC);
    eVoucherViewList.add(viewMaxQuantity);
    when(shoppingCartService.getProductIdsByCartGuid(anyString())).thenReturn(null);
    when(eVoucherService.getProductPaymentMethod(anyListOf(Long.class))).thenReturn(
        productPaymentMethodList);
    when(shoppingCartService.getProductIdsByCartGuid(anyString())).thenReturn(productIdList);
    when(eVoucherService.getCreditCardRegex(anyListOf(String.class))).thenReturn(
        creditCardRegexList);
    when(eVoucherService.getUserPaymentMethod(anyLong())).thenReturn(userPaymentMethodList);
    when(eVoucherService.getPromoterAndVenueByProductIdList(anyListOf(Long.class))).thenReturn(
        productPromoterVenueList);
  }


  private void setupCaseBasicFailed() throws Exception {

    eVoucherViewList.removeAll(eVoucherViewList);
    endDate = "19-11-2016";
    EVoucherView viewMultiUsage1 =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity1 =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "2", 4);
    EVoucherView viewCC1 =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewRestriction1 =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    EVoucherView viewOrganization1 =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue1 =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    eVoucherViewList.add(viewVenue1);
    eVoucherViewList.add(viewOrganization1);
    eVoucherViewList.add(viewRestriction1);
    eVoucherViewList.add(viewCC1);
    eVoucherViewList.add(viewMaxQuantity1);
    eVoucherViewList.add(viewMultiUsage1);
    eVoucherValidationRequest = new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU"), "", "");

    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
  }

  private void setupCaseOneBasicFailed() throws Exception {
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    endDate = "19-11-2016";
    EVoucherView viewMultiUsage1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "2", 4);
    EVoucherView viewCC1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewRestriction1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    EVoucherView viewOrganization1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewOrganization);
    eVoucherViewList.add(viewVenue1);
    eVoucherViewList.add(viewOrganization1);
    eVoucherViewList.add(viewRestriction1);
    eVoucherViewList.add(viewCC1);
    eVoucherViewList.add(viewMaxQuantity1);
    eVoucherViewList.add(viewMultiUsage1);

    eVoucherValidationRequest =
        new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU", "MCjT1Y5GTU"), "", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);

  }

  private void setupCaseRunSuccess() throws Exception {
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewOrganization);
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
  }

  private void setupCaseRunPairOrganizationAndVenuePass() throws Exception {
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    EVoucherView viewMultiUsage1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "2", 4);
    EVoucherView viewCC1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewRestriction1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    EVoucherView viewOrganization1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewOrganization);
    eVoucherViewList.add(viewVenue1);
    eVoucherViewList.add(viewOrganization1);
    eVoucherViewList.add(viewRestriction1);
    eVoucherViewList.add(viewCC1);
    eVoucherViewList.add(viewMaxQuantity1);
    eVoucherViewList.add(viewMultiUsage1);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewOrganization);
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
  }

  private void setupCaseRunPairOrganizationAndVenueFailed() throws Exception {
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "10", 1);
    EVoucherView viewVenue =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    EVoucherView viewMultiUsage1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "2", 4);
    EVoucherView viewCC1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewRestriction1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    EVoucherView viewOrganization1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue1 =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewOrganization);
    eVoucherViewList.add(viewVenue1);
    eVoucherViewList.add(viewOrganization1);
    eVoucherViewList.add(viewRestriction1);
    eVoucherViewList.add(viewCC1);
    eVoucherViewList.add(viewMaxQuantity1);
    eVoucherViewList.add(viewMultiUsage1);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewOrganization);
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
  }

  private void setupCaseEvoucherTypeIsNotImplemented() throws Exception {
    eVoucherViewList.removeAll(eVoucherViewList);
    EVoucherView viewMultiUsage =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHERS", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHERS", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "2", 4);
    EVoucherView viewCC =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHERS", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHERS", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHERS", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue =
        new EVoucherView("MCjT1Y5GTU", "DBS_E_VOUCHERS", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    eVoucherViewList.add(viewVenue);
    eVoucherViewList.add(viewOrganization);
    eVoucherViewList.add(viewRestriction);
    eVoucherViewList.add(viewCC);
    eVoucherViewList.add(viewMaxQuantity);
    eVoucherViewList.add(viewMultiUsage);

    eVoucherValidationRequest =
        new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU", "MCjT1Y5GTU"), "", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);

  }

  @Test
  public void runCaseEvoucherIdListIsEmpty() {

    EVoucherValidationRequest request =
        new EVoucherValidationRequest(new ArrayList<String>(), "", "");
    List<EVoucherValidation> result = eVoucherRunEngine.run(request, "", 0l, currencyUnit);
    assertThat(result).isEmpty();
  }

  @Test
  public void runCaseNotFoundEvoucherId() {
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(Arrays.asList("MCjT1A5GTU", "MCjT1B5GTU"), "", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1A5GTU", null, null, false, "VALIDATION_BASIC_FAIL");
    EVoucherValidation expect2 =
        new EVoucherValidation("MCjT1B5GTU", null, null, false, "VALIDATION_BASIC_FAIL");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    expected.add(expect2);
    List<EVoucherValidation> result = eVoucherRunEngine.run(request, "", 0l, currencyUnit);
    assertEquals(2, result.size());
    assertEquals(expected, result);
  }

  @Test
  public void runCaseNotFoundEvoucherType() throws Exception {
    setupCaseEvoucherTypeIsNotImplemented();
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1X5GTU", null, null, false, "VALIDATION_BASIC_FAIL");
    EVoucherValidation expect2 =
        new EVoucherValidation("MCjT1Y5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHERS", false,
            "EVOUCHER_TYPE_NOT_IMPLEMENTED");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    expected.add(expect2);
    List<EVoucherValidation>
        result =
        eVoucherRunEngine.run(eVoucherValidationRequest, "", 0l, currencyUnit);
    assertEquals(2, result.size());
    assertEquals(expected, result);
  }

  @Test
  public void runCaseBasicRuleFailed() throws Exception {
    setupCaseBasicFailed();
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1X5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", false,
            "VALIDATION_BASIC_EXPIRED");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    List<EVoucherValidation>
        result =
        eVoucherRunEngine.run(eVoucherValidationRequest, "", 0l, currencyUnit);
    assertEquals(1, result.size());
    assertEquals(expected, result);
  }

  @Test
  public void runCaseOneBasicRuleFailed() throws Exception {
    setupCaseOneBasicFailed();
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU", "MCjT1Y5GTU"), "", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1X5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", true,
            "VALIDATION_RULE_PASS");
    EVoucherValidation expect2 =
        new EVoucherValidation("MCjT1Y5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", false,
            "VALIDATION_BASIC_EXPIRED");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    expected.add(expect2);
    List<EVoucherValidation> result = eVoucherRunEngine.run(request, "", 0l, currencyUnit);
    assertEquals(2, result.size());
    assertEquals(expected, result);
  }

  @Test
  public void runCaseApplyStep() throws Exception {
    setupCaseRunSuccess();
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU", "MCjT1Y5GTU"), "", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1X5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", true,
            "VALIDATION_RULE_PASS");
    EVoucherValidation expect2 =
        new EVoucherValidation("MCjT1Y5GTU", null, null, false, "VALIDATION_BASIC_FAIL");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    expected.add(expect2);
    List<EVoucherValidation> result = eVoucherRunEngine.run(request, "", 0l, currencyUnit);
    assertEquals(2, result.size());
    assertEquals(expected, result);
  }

  @Test
  public void runCasePreCommitOrder() throws Exception {
    setupCaseRunSuccess();
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU", "MCjT1Y5GTU"),
            "4523632541257785", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1X5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", true,
            "VALIDATION_RULE_PASS");
    EVoucherValidation expect2 =
        new EVoucherValidation("MCjT1Y5GTU", null, null, false, "VALIDATION_BASIC_FAIL");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    expected.add(expect2);
    List<EVoucherValidation> result = eVoucherRunEngine.run(request, "", 0l, currencyUnit);
    assertEquals(2, result.size());
    assertEquals(expected, result);
  }

  @Test
  public void runCaseOrganizationAndVenueIsPairCasePass() throws Exception {

    setupCaseRunPairOrganizationAndVenuePass();
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU", "MCjT1Y5GTU"), "", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1X5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", true,
            "VALIDATION_RULE_PASS");
    EVoucherValidation expect2 =
        new EVoucherValidation("MCjT1Y5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", true,
            "VALIDATION_RULE_PASS");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    expected.add(expect2);
    List<EVoucherValidation> result = eVoucherRunEngine.run(request, "", 0l, currencyUnit);
    assertEquals(2, result.size());
    assertEquals(expected, result);
  }

  @Test
  public void runCaseOrganizationAndVenueIsPairCaseFailed() throws Exception {

    setupCaseRunPairOrganizationAndVenueFailed();
    EVoucherValidationRequest request =
        new EVoucherValidationRequest(Arrays.asList("MCjT1X5GTU", "MCjT1Y5GTU"), "", "");
    when(eVoucherService.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
    EVoucherValidation expect1 =
        new EVoucherValidation("MCjT1X5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", false,
            "VALIDATION_RULE_ORG_FAIL");
    EVoucherValidation expect2 =
        new EVoucherValidation("MCjT1Y5GTU", Money.of(10, currencyUnit), "DBS_E_VOUCHER", true,
            "VALIDATION_RULE_PASS");
    List<EVoucherValidation> expected = new ArrayList<EVoucherValidation>();
    expected.add(expect1);
    expected.add(expect2);
    List<EVoucherValidation> result = eVoucherRunEngine.run(request, "", 0l, currencyUnit);
    assertEquals(2, result.size());
    assertEquals(expected, result);
  }
}

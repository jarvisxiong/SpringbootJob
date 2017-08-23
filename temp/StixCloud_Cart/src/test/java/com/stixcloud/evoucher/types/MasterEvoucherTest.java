package com.stixcloud.evoucher.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterEvoucherTest {

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;

  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
  private String startDate = "19-Oct-2016";
  private String endDate = "19-Oct-2018";

  private MasterEvoucher masterEvoucher;
  private List<EVoucherCreditCardRegex> ccRegex;
  private String creditCardNo;
  private String paymentMethod;
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;
  private Map<String, Long> evoucherQtyMap;
  private List<EVoucherView> eVoucherViewList;

  @Before
  public void setup() throws Exception {

    Locale.setDefault(new Locale("en", "SG"));
    ccRegex = new ArrayList<EVoucherCreditCardRegex>();
    productPaymentMethod = new ArrayList<ProductPaymentMethod>();
    userPaymentMethod = new ArrayList<UserPaymentMethod>();
    evoucherQtyMap = new HashMap<String, Long>();
    eVoucherViewList = new ArrayList<EVoucherView>();

  }

  private void basicSetup() throws Exception {
    EVoucherCreditCardRegex regex = new EVoucherCreditCardRegex("MCjT1X5GTU", "^545301\\d{10}$");
    ccRegex.add(regex);
    creditCardNo = "5453016552384752";
    paymentMethod = "MASTERCARD";
    ProductPaymentMethod product = new ProductPaymentMethod(251254L, 635254L, "MASTERCARD");
    productPaymentMethod.add(product);
    UserPaymentMethod user = new UserPaymentMethod(25425L, "VISA", 26L, 66L);
    userPaymentMethod.add(user);
    evoucherQtyMap.put("DBS_E_VOUCHER", 1L);
    evoucherQtyMap.put("MASTER_E_VOUCHER", 1L);
    EVoucherView viewMultiUsage =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "1", 4);
    EVoucherView viewCC =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewAllowedPayment =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ALLOWED_PAYMENT_METHOD", "MASTERCARD;", 6);
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    eVoucherViewList.add(viewMultiUsage);
    eVoucherViewList.add(viewMaxQuantity);
    eVoucherViewList.add(viewCC);
    eVoucherViewList.add(viewRestriction);
    eVoucherViewList.add(viewAllowedPayment);
    masterEvoucher = new MasterEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
        productPaymentMethod, userPaymentMethod, evoucherQtyMap, Arrays.asList(viewMultiUsage));
    masterEvoucher.setEvoucher(eVoucherViewList);

  }

  @Test
  public void validateAtApplyEvoucher() throws Exception {
    basicSetup();
    EVoucherValidation result = masterEvoucher.validateAtApplyEvoucher();
    assertTrue(masterEvoucher.getMaxQuantityRule().isExecuted());
    assertTrue(masterEvoucher.getMultiUsageRule().isExecuted());
    assertTrue(masterEvoucher.getRedeemRestrictionsRule().isExecuted());
    EVoucherValidation expected = objectMapper.readValue(
        this.getClass().getResource("/evoucher/json/evoucherValidation_MasterEvoucher_Pass.json"),
        EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void validateAtPreCommitOrder() throws Exception {
    basicSetup();
    EVoucherValidation result = masterEvoucher.validateAtPreCommitOrder();
    assertTrue(masterEvoucher.getCreditCardRule().isExecuted());
    assertTrue(masterEvoucher.getAllowedPaymentMethodRule().isExecuted());
    EVoucherValidation expected = objectMapper.readValue(
        this.getClass().getResource("/evoucher/json/evoucherValidation_MasterEvoucher_Pass.json"),
        EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void setEvoucherCaseCreditCard() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewCC =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    eVoucherViewList.add(viewCC);
    masterEvoucher = new MasterEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
        productPaymentMethod, userPaymentMethod, evoucherQtyMap, null);
    masterEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = masterEvoucher.getCreditCardRule().getEVoucherView();
    assertEquals(viewCC, result);
  }

  @Test
  public void setEvoucherCaseRedeemRestriction() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    eVoucherViewList.add(viewRestriction);
    masterEvoucher = new MasterEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
        productPaymentMethod, userPaymentMethod, evoucherQtyMap, null);
    masterEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = masterEvoucher.getRedeemRestrictionsRule().getEVoucherView();
    assertEquals(viewRestriction, result);
  }

  @Test
  public void setEvoucherCaseMaxQuantity() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewMaxQuantity =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "1", 4);
    eVoucherViewList.add(viewMaxQuantity);
    masterEvoucher = new MasterEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
        productPaymentMethod, userPaymentMethod, evoucherQtyMap, null);
    masterEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = masterEvoucher.getMaxQuantityRule().getEVoucherView();
    assertEquals(viewMaxQuantity, result);
  }

  @Test
  public void setEvoucherCaseMultiUsage() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewMultiUsage =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    eVoucherViewList.add(viewMultiUsage);
    masterEvoucher = new MasterEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
        productPaymentMethod, userPaymentMethod, evoucherQtyMap, null);
    masterEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = masterEvoucher.getMultiUsageRule().getEVoucherView();
    assertEquals(viewMultiUsage, result);
  }

  @Test
  public void setEvoucherCaseAllowedPaymentMethod() throws Exception {
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ALLOWED_PAYMENT_METHOD", "MASTERCARD;", 1);
    eVoucherViewList.add(viewOrganization);
    masterEvoucher = new MasterEvoucher(messageSource, paymentMethod, ccRegex, creditCardNo,
        productPaymentMethod, userPaymentMethod, evoucherQtyMap, null);
    masterEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = masterEvoucher.getAllowedPaymentMethodRule().getEVoucherView();
    assertEquals(viewOrganization, result);
  }

}

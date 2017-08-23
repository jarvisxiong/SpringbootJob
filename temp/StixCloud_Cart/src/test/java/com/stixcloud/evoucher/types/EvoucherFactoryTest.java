package com.stixcloud.evoucher.types;

import static org.assertj.core.api.Assertions.assertThat;

import com.stixcloud.evoucher.constant.EVoucherConstants;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EvoucherFactoryTest {

  @Autowired
  private MessageSource messageSource;

  private List<EVoucherView> validationRuleList;
  private List<EVoucherCreditCardRegex> ccRegex;
  private String creditCardNo;
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;
  private Map<String, Long> evoucherQtyMap;
  private List<EVoucherView> eVoucherViewList;
  private Long patronAccountId;
  private String paymentMethod;
  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
  private String startDate = "19-Oct-2016";
  private String endDate = "19-Oct-2018";

  private EvoucherFactory evoucherFactory;

  @Before
  public void setup() throws Exception {

    ccRegex = new ArrayList<EVoucherCreditCardRegex>();
    productPaymentMethod = new ArrayList<ProductPaymentMethod>();
    userPaymentMethod = new ArrayList<UserPaymentMethod>();
    evoucherQtyMap = new HashMap<String, Long>();
    eVoucherViewList = new ArrayList<EVoucherView>();
    validationRuleList = new ArrayList<EVoucherView>();
    EVoucherCreditCardRegex regex = new EVoucherCreditCardRegex("MCjT1X5GTU", "^545301\\d{10}$");
    ccRegex.add(regex);
    creditCardNo = "5453016552384752";
    ProductPaymentMethod product = new ProductPaymentMethod(251254L, 635254L, "MASTERCARD");
    productPaymentMethod.add(product);
    UserPaymentMethod user = new UserPaymentMethod(25425L, "VISA", 26L, 66L);
    userPaymentMethod.add(user);
    evoucherQtyMap.put("DBS_E_VOUCHER", 1L);
    evoucherQtyMap.put("OCBC_E_VOUCHER", 2L);
    EVoucherView viewOrganization =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ORGANIZATION", "100", 1);
    EVoucherView viewVenue =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "VENUE", "100", 2);
    EVoucherView viewMultiUsage =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "USAGE_WITH_OTHER_VOUCHERS", "FALSE", 3);
    EVoucherView viewMaxQuantity =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MAXIMUM_QTY", "1", 4);
    EVoucherView viewCC =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "CREDIT_CARD", "36", 6);
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    EVoucherView viewMembership =
        new EVoucherView("MCjT1X5GTU", "DBS_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "MEMBERSHIP_PATRON_ACCT_NO",
            "MEMBERSHIP_PATRON_ACCT_NO", 5);
    EVoucherView viewAllowedPayment =
        new EVoucherView("MCjT1X5GTU", "MASTER_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "ALLOWED_PAYMENT_METHOD", "MASTERCARD;", 6);

    validationRuleList.add(viewOrganization);
    validationRuleList.add(viewVenue);
    validationRuleList.add(viewMultiUsage);
    validationRuleList.add(viewMaxQuantity);
    validationRuleList.add(viewCC);
    validationRuleList.add(viewRestriction);
    validationRuleList.add(viewMembership);
    validationRuleList.add(viewAllowedPayment);

    eVoucherViewList.add(viewMultiUsage);

    evoucherFactory =
        new EvoucherFactory(messageSource, validationRuleList, ccRegex, creditCardNo,
            userPaymentMethod, evoucherQtyMap, eVoucherViewList, patronAccountId, paymentMethod);

  }

  @Test
  public void getEvoucherTypeCaseDbsEvoucher() throws Exception {
    IEvoucher evoucher = evoucherFactory.getEvoucherType(EVoucherConstants.DBS_E_VOUCHER);
    assertThat(evoucher).isInstanceOf(DbsEvoucher.class);
  }

  @Test
  public void getEvoucherTypeCaseOcbcEvoucher() throws Exception {
    IEvoucher evoucher = evoucherFactory.getEvoucherType(EVoucherConstants.OCBC_E_VOUCHER);
    assertThat(evoucher).isInstanceOf(OcbcEvoucher.class);
  }

  @Test
  public void getEvoucherTypeCaseEsplanadeEvoucher() throws Exception {
    IEvoucher evoucher = evoucherFactory.getEvoucherType(EVoucherConstants.ESPLANADE_E_VOUCHER);
    assertThat(evoucher).isInstanceOf(EsplanadeEvoucher.class);
  }

  @Test
  public void getEvoucherTypeCaseMasterEvoucher() throws Exception {
    IEvoucher evoucher = evoucherFactory.getEvoucherType(EVoucherConstants.MASTER_E_VOUCHER);
    assertThat(evoucher).isInstanceOf(MasterEvoucher.class);
  }

  @Test
  public void getEvoucherTypeCaseSisticEvoucher() throws Exception {
    IEvoucher evoucher = evoucherFactory.getEvoucherType(EVoucherConstants.SISTIC_E_VOUCHER);
    assertThat(evoucher).isInstanceOf(SisticEvoucher.class);
  }

  @Test
  public void getEvoucherTypeCaseNgsEvoucher() throws Exception {
    IEvoucher evoucher = evoucherFactory.getEvoucherType(EVoucherConstants.NGS_E_VOUCHER);
    assertThat(evoucher).isInstanceOf(NgsEvoucher.class);
  }

  @Test
  public void getEvoucherTypeCaseNgRenewalEvoucher() throws Exception {
    IEvoucher evoucher = evoucherFactory.getEvoucherType(EVoucherConstants.NG_RENEWAL_EVOUCHER);
    assertThat(evoucher).isInstanceOf(NgRenewalEvoucher.class);
  }

  @Test(expected = Exception.class)
  public void getEvoucherTypeCaseError() throws Exception {
    evoucherFactory.getEvoucherType("other");
  }
}

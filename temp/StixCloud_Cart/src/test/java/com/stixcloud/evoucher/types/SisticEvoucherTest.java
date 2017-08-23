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
public class SisticEvoucherTest {

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private ObjectMapper objectMapper;

  private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
  private String startDate = "19-10-2016";
  private String endDate = "19-10-2018";
  private List<ProductPaymentMethod> productPaymentMethod;
  private List<UserPaymentMethod> userPaymentMethod;
  private SisticEvoucher sisticEvoucher;

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
        new EVoucherView("MCjT1X5GTU", "SISTIC_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    sisticEvoucher = new SisticEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
    sisticEvoucher.setEvoucher(Arrays.asList(viewRestriction));

  }

  @Test
  public void validateAtApplyEvoucher() throws Exception {
    basicSetup();
    EVoucherValidation result = sisticEvoucher.validateAtApplyEvoucher();
    assertTrue(sisticEvoucher.getRedeemRestrictionsRule().isExecuted());
    EVoucherValidation expected = objectMapper.readValue(
        this.getClass().getResource("/evoucher/json/evoucherValidation_SisticEvoucher_Pass.json"),
        EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void validateAtPreCommitOrder() throws Exception {
    basicSetup();
    EVoucherValidation result = sisticEvoucher.validateAtPreCommitOrder();
    EVoucherValidation expected = objectMapper.readValue(
        this.getClass().getResource("/evoucher/json/evoucherValidation_SisticEvoucher_Pass.json"),
        EVoucherValidation.class);
    assertEquals(expected, result);
  }

  @Test
  public void setEvoucherCaseRedeemRestriction() throws Exception {
    List<EVoucherView> eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView viewRestriction =
        new EVoucherView("MCjT1X5GTU", "SISTIC_E_VOUCHER", 10L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 55L, 44L, true, "REDEEM_RESTRICTIONS", "RESTRICT_NONE", 5);
    eVoucherViewList.add(viewRestriction);
    sisticEvoucher = new SisticEvoucher(messageSource, productPaymentMethod, userPaymentMethod);
    sisticEvoucher.setEvoucher(eVoucherViewList);
    EVoucherView result = sisticEvoucher.getRedeemRestrictionsRule().getEVoucherView();
    assertEquals(viewRestriction, result);
  }
}

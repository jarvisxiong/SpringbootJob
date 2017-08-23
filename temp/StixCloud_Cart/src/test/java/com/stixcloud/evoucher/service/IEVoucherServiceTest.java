package com.stixcloud.evoucher.service;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import com.stixcloud.cart.TenantPropertiesTest;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import com.stixcloud.evoucher.repo.IEVoucherViewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IEVoucherServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IEVoucherServiceTest.class);
  @Mock
  IEVoucherViewRepository eVoucherRepository;
  @InjectMocks
  private EVoucherService eVoucherService;
  private List<EVoucherView> eVoucherViewList;
  private List<ProductPaymentMethod> productPaymentMethodList;
  private List<UserPaymentMethod> userPaymentMethodList;
  private List<EVoucherCreditCardRegex> ccRegexList;
  private List<ProductPromoterVenue> productPromoterVenueList;

  @Before
  public void setup() throws Exception {
    setupGetEVoucherView();
    setupGetProductPaymentMethod();
    setupGetUserPaymentMethod();
    setupGetCreditCardRegex();
    setupGetPromoterAndVenueByProductIdList();
    TenantPropertiesTest.setUp();
  }

  private void setupGetEVoucherView() throws Exception {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    String startDate = "19-10-2016";
    String endDate = "19-10-2018";
    eVoucherViewList = new ArrayList<EVoucherView>();
    EVoucherView eVoucherView =
        new EVoucherView("EEKGP2fP", "ESPLANADE_E_VOUCHER", 20L, "1", formatter.parse(startDate),
            formatter.parse(endDate), 14256L, 3652L, true, null, null, null);
    eVoucherViewList.add(eVoucherView);
    when(eVoucherRepository.getEVoucherView(anyListOf(String.class))).thenReturn(eVoucherViewList);
  }

  private void setupGetProductPaymentMethod() {
    productPaymentMethodList = new ArrayList<ProductPaymentMethod>();
    ProductPaymentMethod
        productPaymentMethod = new ProductPaymentMethod(156325L, 56985L, "MASTER CARD");
    productPaymentMethodList.add(productPaymentMethod);
  }

  private void setupGetUserPaymentMethod() {
    userPaymentMethodList = new ArrayList<UserPaymentMethod>();
    UserPaymentMethod
        userPaymentMethod =
        new UserPaymentMethod(56985L, "MASTER CARD",
            TenantContextHolder.getTenant().getUserInfoId(),
            TenantContextHolder.getTenant().getProfileInfoId());
    userPaymentMethodList.add(userPaymentMethod);
  }

  private void setupGetCreditCardRegex() {
    ccRegexList = new ArrayList<EVoucherCreditCardRegex>();
    EVoucherCreditCardRegex cc = new EVoucherCreditCardRegex("EEKGP2fP", "^1\\{10}$");
    ccRegexList.add(cc);
  }

  private void setupGetPromoterAndVenueByProductIdList() {
    productPromoterVenueList = new ArrayList<ProductPromoterVenue>();
    ProductPromoterVenue venue1 = new ProductPromoterVenue(1L, 2L, 3L);
    ProductPromoterVenue venue2 = new ProductPromoterVenue(4L, 5L, 6L);
    productPromoterVenueList.add(venue1);
    productPromoterVenueList.add(venue2);
  }

  @Test
  public void getEVoucherView() {

    long startTime = System.nanoTime();
    List<EVoucherView> result = eVoucherService.getEVoucherView(new ArrayList<String>());
    LOGGER.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    LOGGER.info(Arrays.toString(result.toArray()));
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    assertEquals(eVoucherViewList.get(0), result.get(0));

  }

  @Test
  public void getProductPaymentMethod() {

    long startTime = System.nanoTime();
    when(eVoucherRepository.getProductPaymentMethod(anyListOf(Long.class)))
        .thenReturn(productPaymentMethodList);
    List<ProductPaymentMethod>
        list =
        eVoucherService.getProductPaymentMethod(new ArrayList<Long>());
    LOGGER.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    LOGGER.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(productPaymentMethodList.get(0), list.get(0));
  }

  @Test
  public void getUserPaymentMethod() {

    long startTime = System.nanoTime();
    when(eVoucherRepository.getUserPaymentMethod(anyLong())).thenReturn(userPaymentMethodList);
    List<UserPaymentMethod> list = eVoucherService.getUserPaymentMethod(
        TenantContextHolder.getTenant().getProfileInfoId());
    LOGGER.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    LOGGER.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(userPaymentMethodList.get(0), list.get(0));
  }

  @Test
  public void getCreditCardRegex() {

    long startTime = System.nanoTime();
    when(eVoucherRepository.getCreditCardRegex(anyListOf(String.class))).thenReturn(ccRegexList);
    List<EVoucherCreditCardRegex>
        result =
        eVoucherService.getCreditCardRegex(new ArrayList<String>());
    LOGGER.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    LOGGER.info(Arrays.toString(result.toArray()));
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    assertEquals(ccRegexList.get(0), result.get(0));
  }

  @Test
  public void getPromoterAndVenueByProductIdList() {

    long startTime = System.nanoTime();
    when(eVoucherRepository.getPromoterAndVenueByProductIdList(anyListOf(Long.class)))
        .thenReturn(productPromoterVenueList);
    List<ProductPromoterVenue> result =
        eVoucherRepository.getPromoterAndVenueByProductIdList(new ArrayList<Long>());
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    LOGGER.info(Arrays.toString(result.toArray()));
    assertFalse(result.isEmpty());
    assertEquals(2, result.size());
    assertEquals(productPromoterVenueList.get(0), result.get(0));
  }
}

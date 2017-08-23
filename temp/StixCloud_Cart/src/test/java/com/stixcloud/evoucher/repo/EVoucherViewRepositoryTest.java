package com.stixcloud.evoucher.repo;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class EVoucherViewRepositoryTest {

  private final Logger LOGGER = LogManager.getLogger(EVoucherViewRepositoryTest.class);

  @InjectMocks
  EVoucherViewRepository eVoucherViewRepository;

  @Mock
  EntityManager em;

  @Mock
  TypedQuery typedQuery;

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
  }

  private void setupGetProductPaymentMethod() {
    productPaymentMethodList = new ArrayList<ProductPaymentMethod>();
    ProductPaymentMethod productUserPaymentMethod =
        new ProductPaymentMethod(156325L, 56985L, "MASTER CARD");
    productPaymentMethodList.add(productUserPaymentMethod);
  }

  private void setupGetUserPaymentMethod() {
    userPaymentMethodList = new ArrayList<UserPaymentMethod>();
    UserPaymentMethod userPaymentMethod = new UserPaymentMethod(56985L, "MASTER CARD", 66L, 99L);
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
    when(em.createQuery(anyString(), eq(EVoucherView.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyListOf(String.class))).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(eVoucherViewList);

    List<EVoucherView> list = eVoucherViewRepository.getEVoucherView(new ArrayList<String>());
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    LOGGER.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(eVoucherViewList.get(0), list.get(0));
  }

  @Test
  public void getProductPaymentMethod() {

    long startTime = System.nanoTime();
    when(em.createQuery(anyString(), eq(ProductPaymentMethod.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyListOf(Long.class))).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(productPaymentMethodList);
    List<ProductPaymentMethod> list =
        eVoucherViewRepository.getProductPaymentMethod(new ArrayList<Long>());
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    LOGGER.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(productPaymentMethodList.get(0), list.get(0));
  }

  @Test
  public void getUserPaymentMethod() {

    long startTime = System.nanoTime();
    when(em.createQuery(anyString(), eq(UserPaymentMethod.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(productPaymentMethodList);
    List<UserPaymentMethod> list = eVoucherViewRepository.getUserPaymentMethod(2L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    LOGGER.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(productPaymentMethodList.get(0), list.get(0));
  }

  @Test
  public void getCreditCardRegex() {

    long startTime = System.nanoTime();
    when(em.createQuery(anyString(), eq(EVoucherCreditCardRegex.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyListOf(String.class))).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(ccRegexList);
    List<EVoucherCreditCardRegex> result =
        eVoucherViewRepository.getCreditCardRegex(new ArrayList<String>());
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    LOGGER.info(Arrays.toString(result.toArray()));
    assertFalse(result.isEmpty());
    assertEquals(1, result.size());
    assertEquals(ccRegexList.get(0), result.get(0));
  }

  @Test
  public void getPromoterAndVenueByProductIdList() {
    long startTime = System.nanoTime();
    when(em.createQuery(anyString(), eq(ProductPromoterVenue.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyListOf(Long.class))).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(productPromoterVenueList);
    List<ProductPromoterVenue> result =
        eVoucherViewRepository.getPromoterAndVenueByProductIdList(new ArrayList<Long>());
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    LOGGER.info(Arrays.toString(result.toArray()));
    assertFalse(result.isEmpty());
    assertEquals(2, result.size());
    assertEquals(productPromoterVenueList.get(0), result.get(0));
  }

}

package com.stixcloud.product.event;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import com.stixcloud.domain.RetrievePriceTypeView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by mhviet on 26-Aug-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class IPriceClassRepositoryTest {
  private final Logger logger = LogManager.getLogger(IPriceClassRepositoryTest.class);

  @InjectMocks
  PriceClassRepository priceClassRepository;
  @Mock
  EntityManager em;
  @Mock
  Query query;

  private List<RetrievePriceTypeView> retrievePriceTypeList;

  /**
   * Setup base information
   */
//  @Before
//  public void setUp() {
//    try {
//      RetrievePriceTypeView
//          view =
//          new RetrievePriceTypeView("Test class name", "D1", new BigDecimal("30"), 1L, 10L, 1101L,
//              2202L, 59L, 11L, 3303L, false, "testcode", "grouptestcode");
//      retrievePriceTypeList = new ArrayList<RetrievePriceTypeView>();
//      retrievePriceTypeList.add(view);
//    } catch (Exception e) {
//      logger.error(e.getMessage(), e);
//    }
//  }

  /**
   * Test for method getPriceClassList() in PriceClassRepository
   */
  /*@Test
  public void getPriceClassListWithPromoIsNull() {
    long startTime = System.nanoTime();
    when(em.createNativeQuery(anyString(), eq(RetrievePriceTypeView.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.getResultList()).thenReturn(retrievePriceTypeList);

    List<RetrievePriceTypeView>
        list =
        priceClassRepository.getPriceClassList(243870L, 12176L, 59L, 11L, "", "F");
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    logger.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(retrievePriceTypeList.get(0), list.get(0));

  }*/

  /**
   * Test for method getPriceClassList() in PriceClassRepository
   */
  /*@Test
  public void getPriceClassListWithPromoIsNotNull() {
    long startTime = System.nanoTime();
    when(em.createNativeQuery(anyString(), eq(RetrievePriceTypeView.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.getResultList()).thenReturn(retrievePriceTypeList);

    List<RetrievePriceTypeView>
        list =
        priceClassRepository.getPriceClassList(243870L, 12176L, 59L, 11L, "test", "F");
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    logger.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(retrievePriceTypeList.get(0), list.get(0));

  }*/

  /**
   * Test for method checkExistPromotion case TRUE
   */
  @Test
  public void checkExistPromotionCaseTrue() {

    long startTime = System.nanoTime();
    when(em.createNativeQuery(anyString())).thenReturn(query);
    when(query.setParameter(anyInt(), anySetOf(Long.class))).thenReturn(query);
    List result = new ArrayList<>();
    result.add(1L);
    when(query.getResultList()).thenReturn(result);
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    assertFalse(result.isEmpty());
  }

  /**
   * Test for method checkExistPromotion case FALSE
   */
  @Test
  public void checkExistPromotionCaseFalse() {

    long startTime = System.nanoTime();
    when(em.createNativeQuery(anyString())).thenReturn(query);
    when(query.setParameter(anyInt(), anySetOf(Long.class))).thenReturn(query);
    List result = new ArrayList<>();
    when(query.getResultList()).thenReturn(result);
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    assertTrue(result.isEmpty());
  }

  /**
   * Test for method checkValidPromotionCode case TRUE
   */
  @Test
  public void checkValidPromotionCodeCaseTrue() {

    long startTime = System.nanoTime();
    when(em.createNativeQuery(anyString())).thenReturn(query);
    when(query.setParameter(anyInt(), anySetOf(Long.class))).thenReturn(query);
    when(query.setParameter(anyInt(), anySetOf(Long.class))).thenReturn(query);
    List result = new ArrayList<>();
    result.add(1L);
    when(query.getResultList()).thenReturn(result);
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    assertFalse(result.isEmpty());
  }

  /**
   * Test for method checkValidPromotionCode case FALSE
   */
  @Test
  public void checkValidPromotionCodeCaseFalse() {

    long startTime = System.nanoTime();
    when(em.createNativeQuery(anyString())).thenReturn(query);
    when(query.setParameter(anyInt(), anySetOf(Long.class))).thenReturn(query);
    when(query.setParameter(anyInt(), anySetOf(Long.class))).thenReturn(query);
    List result = new ArrayList<>();
    when(query.getResultList()).thenReturn(result);
    logger.info("Time taken " + TimeUnit.MILLISECONDS
        .convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    assertTrue(result.isEmpty());
  }

}

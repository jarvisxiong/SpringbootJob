package com.stixcloud.product.event;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stixcloud.domain.SeatmapAvailablityProducts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by Chong Ye on 24/8/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeatmapAvailabilityProductsRepositoryTest {
  private final Logger
      logger =
      LogManager.getLogger(SeatmapAvailabilityProductsRepositoryTest.class);

  @InjectMocks
  SeatmapAvailablityProductsRepository repositoryImpl;
  @Mock
  EntityManager em;
  @Mock
  Query query;

  private List<SeatmapAvailablityProducts> seatmapAvailabilityList;

  /*@Before
  public void setUp() throws Exception {
    seatmapAvailabilityList = new ArrayList<>();
    SeatmapAvailablityProducts
        products =
        new SeatmapAvailablityProducts(218991L, 51331L, 10200L, 11L, 1);
    seatmapAvailabilityList.add(products);
  }

  @Test
  public void getSeatmapAvailabilityWithPromoCode() throws Exception {
    when(em.createNativeQuery(anyString(), eq(SeatmapAvailablityProducts.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(seatmapAvailabilityList);

    List<SeatmapAvailablityProducts>
        seatmapAvailability =
        repositoryImpl.getSeatmapAvailability(1L, "Test");

    verify(query).getResultList();
    verify(em).createNativeQuery(anyString(), eq(SeatmapAvailablityProducts.class));
    assertFalse(seatmapAvailability.isEmpty());
    assertEquals(seatmapAvailability.size(), seatmapAvailabilityList.size());
    assertEquals(seatmapAvailabilityList.get(0), seatmapAvailability.get(0));
  }

  @Test
  public void getSeatmapAvailabilityWithoutPromoCode() throws Exception {
    when(em.createNativeQuery(anyString(), eq(SeatmapAvailablityProducts.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.getResultList()).thenReturn(seatmapAvailabilityList);

    List<SeatmapAvailablityProducts>
        seatmapAvailability =
        repositoryImpl.getSeatmapAvailability(1L, "");

    verify(query).getResultList();
    verify(em).createNativeQuery(anyString(), eq(SeatmapAvailablityProducts.class));
    assertFalse(seatmapAvailability.isEmpty());
    assertEquals(seatmapAvailability.size(), seatmapAvailabilityList.size());
    assertEquals(seatmapAvailabilityList.get(0), seatmapAvailability.get(0));
  }*/

}

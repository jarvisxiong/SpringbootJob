package com.stixcloud.product.event;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import com.stixcloud.domain.SeatmapOverviewProducts;
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

import java.math.BigDecimal;
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
public class SeatmapOverviewProductsRepositoryTest {
  private final Logger logger = LogManager.getLogger(SeatmapOverviewProductsRepositoryTest.class);

  @InjectMocks
  SeatmapOverviewProductsRepository repositoryImpl;
  @Mock
  EntityManager em;
  @Mock
  Query query;

  private List<SeatmapOverviewProducts> seatmapOverviewList;

  /*@Before
  public void setUp() throws Exception {
    seatmapOverviewList = new ArrayList<>();
    SeatmapOverviewProducts
        seatmapOverviewProduct =
        new SeatmapOverviewProducts(1L, "Stall", "Center", 51331L, "RS", "Stall", 10200L, "Cat 1",
            1,
            new BigDecimal("48.00"), "A", "ASD", 3, "T", 11L, 0, 1, "SP",
            "450,633,456,616,711,571,769,651,514,694,506,712",
            "esrt1608061100_The_Three_Little_Pigs_weekends.jpg", "F", 539L, 0L);
    seatmapOverviewList.add(seatmapOverviewProduct);
  }

  @Test
  public void getSeatmapOverviewWithPromoCode() throws Exception {
    when(em.createNativeQuery(anyString(), eq(SeatmapOverviewProducts.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(seatmapOverviewList);

    List<SeatmapOverviewProducts>
        seatmapOverviewProducts =
        repositoryImpl.getSeatmapOverview(1L, "Test");

    assertFalse(seatmapOverviewProducts.isEmpty());
    assertEquals(seatmapOverviewProducts.size(), 1);
    assertEquals(seatmapOverviewList.get(0), seatmapOverviewProducts.get(0));
  }

  @Test
  public void getSeatmapOverviewWithoutPromoCode() throws Exception {
    when(em.createNativeQuery(anyString(), eq(SeatmapOverviewProducts.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.getResultList()).thenReturn(seatmapOverviewList);

    List<SeatmapOverviewProducts>
        seatmapOverviewProducts =
        repositoryImpl.getSeatmapOverview(1L, "");

    assertFalse(seatmapOverviewProducts.isEmpty());
    assertEquals(seatmapOverviewProducts.size(), 1);
    assertEquals(seatmapOverviewList.get(0), seatmapOverviewProducts.get(0));
  }*/
}

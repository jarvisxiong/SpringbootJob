package com.stixcloud.product.event;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import com.stixcloud.common.config.multitenant.MultiTenantProperties;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.DetailSeatmapProducts;
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
import javax.persistence.TypedQuery;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetailSeatmapRepositoryTest {

  private final Logger logger = LogManager.getLogger(DetailSeatmapRepositoryTest.class);

  @InjectMocks
  DetailSeatmapRepository detailSeatmapRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<DetailSeatmapProducts> typedQuery;

  private List<DetailSeatmapProducts> detailSeatmapProductsList;

  /*@Before
  public void setUp() throws Exception {
    setupTenantContextHolder();
    detailSeatmapProductsList = new ArrayList<>();

    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 287, 0, 118494547L, "AA", "4", 1, "84,55,101,72", "84,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 289, 0, 118494548L, "AA", "3", 1, "67,55,84,74", "67,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 292, 0, 118494542L, "AA", "15", 1, "276,55,293,72", "276,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 298, 0, 118494546L, "AA", "2", 1, "49,55,67,72", "49,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 299, 0, 118494540L, "AA", "16", 1, "293,55,310,72", "293,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 303, 0, 118494545L, "AA", "1", 1, "32,55,49,72", "32,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 305, 0, 118494538L, "AA", "17", 1, "310,55,328,72", "310,55", "0",
        "Center", 11));
    detailSeatmapProductsList.add(new DetailSeatmapProducts(218994L, 10202L, 51340L, 1, 0, 596L,
        "DBS_Circle_Center_Seats_cat3.jpg",
        "Circle Center", true, 314, 0, 118494552L, "AA", "18", 1, "328,55,345,72", "328,55", "0",
        "Center", 11));
  }*/

  /**
   * Setup TenantContextHolder as it is unable to grab automatically
   * as this is a JUnit
   */
  private void setupTenantContextHolder() {
    MultiTenantProperties.Tenant tenant = new MultiTenantProperties.Tenant();
    tenant.setName("SISTIC");
    tenant.setUrl("jdbc:oracle:thin:@//192.168.11.202:1521/devcloud");
    tenant.setUserInfoId(59L);
    tenant.setProfileInfoId(11L);
    tenant.setUsername("devcloud");
    tenant.setPassword("devcloud");
    TenantContextHolder.setTenant(tenant);
  }

  @Test
  public void getSeatmapOverview() throws Exception {/*
    when(em.createQuery(anyString(), eq(DetailSeatmapProducts.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyInt(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(detailSeatmapProductsList);

    List<DetailSeatmapProducts> result = detailSeatmapRepository.getDetailSeatmap(1L, 1L);

    assertFalse(result.isEmpty());
    assertEquals(result.size(), detailSeatmapProductsList.size());

    for (int i = 0; i < result.size(); i++) {
      assertEquals(detailSeatmapProductsList.get(i), result.get(i));
    }

  */}

}

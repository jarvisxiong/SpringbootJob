package com.stixcloud.product.event;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import com.stixcloud.domain.PriceTableInfo;
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
 * Created by chongye on 30-Aug-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PriceTableInfoRepositoryTest {
  @InjectMocks
  PriceTableInfoRepository repository;
  @Mock
  EntityManager em;
  @Mock
  Query query;

  private List<PriceTableInfo> priceTableInfoList;

  @Before
  public void setUp() throws Exception {
    priceTableInfoList = new ArrayList<>();
    PriceTableInfo priceTableInfo = new PriceTableInfo(218977L, 11L, "A", "Standard", "Cat 1",
        1, new BigDecimal("28.0000"), 18);
    priceTableInfoList.add(priceTableInfo);
  }

  /*@Test
  public void getPriceInfoTableWithoutPromo() throws Exception {
    when(em.createNativeQuery(anyString(), eq(PriceTableInfo.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.getResultList()).thenReturn(priceTableInfoList);

    List<PriceTableInfo> infoList = repository.getPriceInfoTable(1L, "");

    assertFalse(infoList.isEmpty());
    assertEquals(infoList.size(), priceTableInfoList.size());
    assertEquals(priceTableInfoList.get(0), infoList.get(0));
  }

  @Test
  public void getPriceInfoTableWithPromo() throws Exception {
    when(em.createNativeQuery(anyString(), eq(PriceTableInfo.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyLong())).thenReturn(query);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(priceTableInfoList);

    List<PriceTableInfo> infoList = repository.getPriceInfoTable(1L, "Test");

    assertFalse(infoList.isEmpty());
    assertEquals(infoList.size(), priceTableInfoList.size());
    assertEquals(priceTableInfoList.get(0), infoList.get(0));
  }*/

}

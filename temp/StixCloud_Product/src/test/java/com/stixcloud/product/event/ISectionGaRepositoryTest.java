package com.stixcloud.product.event;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import com.stixcloud.domain.RetrieveSectionGaView;
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
import javax.persistence.TypedQuery;

/**
 * Created by dbthan on 07-Sep-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ISectionGaRepositoryTest {

  private final Logger logger = LogManager.getLogger(ISectionGaRepositoryTest.class);

  @InjectMocks
  SectionGaRepository sectionGaRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<RetrieveSectionGaView> typedQuery;

  private List<RetrieveSectionGaView> retrieveSectionGaList;


  /**
   * Setup base information.
   */
  /*@Before
  public void setUp() {
    try {
      retrieveSectionGaList = new ArrayList<RetrieveSectionGaView>();
      RetrieveSectionGaView retrieveSectionGaView = new RetrieveSectionGaView(10L, 9L,
          "Section Alias", "GA", 8, 7L, 6, new BigDecimal(50), 1L);
      retrieveSectionGaList.add(retrieveSectionGaView);
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }
  }*/

  /**
   * Test for method getSectionGaList.
   */
  @Test
  public void getSectionGaList() {

    when(em.createQuery(anyString(), eq(RetrieveSectionGaView.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyInt(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyInt(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(retrieveSectionGaList);

    long startTime = System.nanoTime();
    List<RetrieveSectionGaView> list = sectionGaRepository.getSectionGaList(2430L, 1L);
    logger.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    logger.info(Arrays.toString(list.toArray()));
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(retrieveSectionGaList.get(0), list.get(0));
  }
}

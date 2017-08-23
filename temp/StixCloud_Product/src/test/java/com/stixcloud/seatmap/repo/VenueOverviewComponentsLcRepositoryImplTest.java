package com.stixcloud.seatmap.repo;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.domain.VenueOverviewComponentsLc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by chongye on 15-Sep-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VenueOverviewComponentsLcRepositoryImplTest {
  private final Logger logger =
      LogManager
          .getLogger(VenueOverviewComponentsLcRepositoryImplTest.class);

  @InjectMocks
  VenueOverviewComponentsLcRepositoryImpl overviewComponentsLcRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<VenueOverviewComponentsLc> typedQuery;
  @Autowired
  ObjectMapper mapper;

  private List<VenueOverviewComponentsLc> createVenueOverviewComponentsLcList() {
    List<VenueOverviewComponentsLc> results = new ArrayList<>();
    try {
      results = mapper.readValue(
          this.getClass().getResource("/sample/seatmap/repo/VenueOverviewComponentsLc.json"),
          new TypeReference<List<VenueOverviewComponentsLc>>() {
          });
    } catch (IOException ioe) {
      logger.error(ioe.getMessage(), ioe);
      fail();
    }
    return results;
  }

  @Test
  public void findAll() throws Exception {
    List<VenueOverviewComponentsLc> componentsLcList = createVenueOverviewComponentsLcList();
    when(em.createQuery(anyString(), eq(VenueOverviewComponentsLc.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(componentsLcList);

    List<VenueOverviewComponentsLc> results = overviewComponentsLcRepository.findAll(245617L);

    verify(typedQuery).getResultList();
    verify(em).createQuery(anyString(), eq(VenueOverviewComponentsLc.class));
    assertFalse(results.isEmpty());
    assertEquals(results.size(), componentsLcList.size());
    assertTrue(results.size() == 6);
  }

  @Test
  public void findSelectedDistinctBlocks() throws Exception {
    Long mockResult = 171272L;

    TypedQuery<Long> longTypedQuery = mock(TypedQuery.class);
    when(em.createQuery(anyString(), eq(Long.class))).thenReturn(longTypedQuery);
    when(longTypedQuery.setParameter(anyString(), anyLong())).thenReturn(longTypedQuery);
    when(longTypedQuery.setParameter(anyString(), anyListOf(Long.class)))
        .thenReturn(longTypedQuery);
    when(longTypedQuery.getSingleResult()).thenReturn(mockResult);

    List<Long> longList = new ArrayList<>();
    longList.add(118491895L);
    Long result = overviewComponentsLcRepository.findSelectedDistinctBlocks(218987L, longList);

    verify(em).createQuery(anyString(), eq(Long.class));
    verify(longTypedQuery).getSingleResult();
    assertEquals(result, mockResult);
  }

}

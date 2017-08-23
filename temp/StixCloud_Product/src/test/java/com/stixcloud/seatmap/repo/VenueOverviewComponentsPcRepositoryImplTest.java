package com.stixcloud.seatmap.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.domain.VenueOverviewComponentsPc;
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
public class VenueOverviewComponentsPcRepositoryImplTest {
  private final Logger logger =
      LogManager.getLogger(VenueOverviewComponentsPcRepositoryImplTest.class);
  @InjectMocks
  VenueOverviewComponentsPcRepositoryImpl venueOverviewComponentsPcRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<VenueOverviewComponentsPc> typedQuery;
  @Autowired
  ObjectMapper mapper;

  private List<VenueOverviewComponentsPc> createVenueOverviewComponentsPcList() {
    List<VenueOverviewComponentsPc> results = new ArrayList<>();
    try {
      results = mapper.readValue(
          this.getClass().getResource("/sample/seatmap/repo/VenueOverviewComponentsPc.json"),
          new TypeReference<List<VenueOverviewComponentsPc>>() {
          });
    } catch (IOException ioe) {
      logger.error(ioe.getMessage(), ioe);
      fail();
    }
    return results;
  }

  @Test
  public void findAll() throws Exception {
    List<VenueOverviewComponentsPc> overviewComponentsPcs = createVenueOverviewComponentsPcList();

    when(em.createQuery(anyString(), eq(VenueOverviewComponentsPc.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(overviewComponentsPcs);

    List<VenueOverviewComponentsPc> results = venueOverviewComponentsPcRepository.findAll(1L);

    verify(typedQuery).getResultList();
    verify(em).createQuery(anyString(), eq(VenueOverviewComponentsPc.class));
    assertFalse(results.isEmpty());
    assertEquals(results.size(), overviewComponentsPcs.size());
    assertEquals(results, overviewComponentsPcs);
  }
}

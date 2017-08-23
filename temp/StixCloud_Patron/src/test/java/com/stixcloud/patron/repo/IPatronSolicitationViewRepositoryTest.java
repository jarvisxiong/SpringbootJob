package com.stixcloud.patron.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.patron.domain.PatronSolicitationView;

@Ignore
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class IPatronSolicitationViewRepositoryTest {

  private final Logger LOGGER = LogManager.getLogger(IPatronSolicitationViewRepositoryTest.class);

  @InjectMocks
  PatronSolicitationViewRepository repo;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<PatronSolicitationView> typedQuery;

  private List<PatronSolicitationView> patronSolicitationList;

  @Before
  public void setup() {
    patronSolicitationList = new ArrayList<PatronSolicitationView>();
    // PatronSolicitationView patron =
    // new PatronSolicitationView("Tenant", 15L, "SISTIC", "http://abcd.com", true, 15236L);
    // patronSolicitationList.add(patron);
  }

  @Test
  public void getPatronProfileCaseNormal() {
    when(em.createQuery(anyString(), eq(PatronSolicitationView.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(patronSolicitationList);
    long startTime = System.nanoTime();
    List<PatronSolicitationView> actual = repo.getPatronSolicitationView(254L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(patronSolicitationList, actual);
  }

}

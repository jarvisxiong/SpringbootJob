package com.stixcloud.patron.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

import com.stixcloud.patron.domain.PatronProfileView;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class IPatronRepositoryTest {

  private final Logger LOGGER = LogManager.getLogger(IPatronRepositoryTest.class);

  @InjectMocks
  PatronRepository repo;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery typedQuery;

  private List<PatronProfileView> patronProfileList;

  @Before
  public void setup() {
    patronProfileList = new ArrayList<PatronProfileView>();
    PatronProfileView patron = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "HOME", "84923654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Billing Address",
        "111", "1", "CMC Tower", "15236", "VN", "T", 20000L, 15265L);
    patronProfileList.add(patron);
  }

  @Test
  public void getPatronProfileCaseNormal() {
    when(em.createQuery(anyString(), eq(PatronProfileView.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(patronProfileList);
    long startTime = System.nanoTime();
    List<PatronProfileView> actual = repo.getPatronProfile(254L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(patronProfileList, actual);
  }

  @Test
  public void checkExistEmailAddressCaseExist() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(BigDecimal.valueOf(1L));
    long startTime = System.nanoTime();
    Integer actual = repo.checkExistEmailAddress("cmc@gmail.com");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(Integer.valueOf(1), actual);
  }

  @Test
  public void checkExistEmailAddressCaseNotExist() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenThrow(new NoResultException());
    long startTime = System.nanoTime();
    Integer actual = repo.checkExistEmailAddress("cmc@gmail.com");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(Integer.valueOf(0), actual);
  }

  @Test
  public void isPatronLock() {
    Integer result = 1;
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(result);
    long startTime = System.nanoTime();
    Object actual = repo.isPatronLock("dbthan@gmail.com");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(result, Integer.valueOf(actual.toString()));
  }

  @Ignore
  @Test
  public void getPatronSolicitation() {
    /*
     * List<Object[]> objects = new ArrayList<>(); Object[] object = new Object[5]; object[2] =
     * "National Gallery Singapore"; object[3] = "http://www.nationalgallery.sg/"; object[0] =
     * "Promoter"; object[1] = 1913; object[4] = false; objects.add(object);
     * when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
     * when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
     * when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
     * when(typedQuery.getResultList()).thenReturn(objects); long startTime = System.nanoTime();
     * List<Object[]> actual = repo.getPatronSolicitation(88L, 59L); LOGGER.info("Time taken " +
     * TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) +
     * " ms"); assertEquals(objects, actual);
     */
  }
}

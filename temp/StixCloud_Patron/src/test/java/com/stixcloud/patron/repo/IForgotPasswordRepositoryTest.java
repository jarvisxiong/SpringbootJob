package com.stixcloud.patron.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class IForgotPasswordRepositoryTest {

  private final Logger LOGGER = LogManager.getLogger(IForgotPasswordRepositoryTest.class);

  @InjectMocks
  ForgotPasswordRepository repo;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery typedQuery;

  @Test
  public void invalidateAllActiveTokens() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), any(Date.class))).thenReturn(typedQuery);
    when(typedQuery.executeUpdate()).thenReturn(1);
    long startTime = System.nanoTime();
    repo.invalidateAllActiveTokens("dbthan@cmc.com.vn");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
  }

  @Test
  public void validateTokenCaseTrue() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), any(Date.class))).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(new BigDecimal(1));
    long startTime = System.nanoTime();
    boolean actual = repo.validateToken("testToken", "dbthan@cmc.com.vn");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }

  @Test
  public void validateTokenCaseFalse() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), any(Date.class))).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenThrow(new NoResultException());
    long startTime = System.nanoTime();
    boolean actual = repo.validateToken("testToken", "dbthan@cmc.com.vn");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }
  
  @Test
  public void updatePasswordCaseTrue() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.executeUpdate()).thenReturn(1);
    long startTime = System.nanoTime();
    boolean actual = repo.updatePassword("dbthan@cmc.com.vn", "password");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }
  
  @Test
  public void updatePasswordCaseFalse() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.executeUpdate()).thenReturn(0);
    long startTime = System.nanoTime();
    boolean actual = repo.updatePassword("dbthan@cmc.com.vn", "password");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }
  
  @Test
  public void getTokenExpiryTime() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyString())).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn("abcd1234");
    String actual = repo.getTokenExpiryTime();
    assertEquals("abcd1234", actual);
  }
}

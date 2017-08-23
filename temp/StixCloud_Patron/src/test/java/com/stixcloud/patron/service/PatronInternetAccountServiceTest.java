package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronInternetAccount;
import com.stixcloud.patron.repo.IPatronInternetAccountRepository;
import com.stixcloud.patron.util.PatronProfileUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PatronInternetAccountServiceTest {

  private final Logger LOGGER = LogManager.getLogger(PatronInternetAccountServiceTest.class);
  @InjectMocks
  private PatronInternetAccountService service;
  @Mock
  private IPatronInternetAccountRepository repo;
  @Mock
  private MessageSource messageSource;

  private PatronInternetAccount patronAccount;
  private PatronUpdateRequest request;
  private PatronProfileJson original;

  @Before
  public void setup() {
    patronAccount =
        new PatronInternetAccount(155L, "cmc@gmail.com", "a66da05257ed8df1675c906d93b22f06",
            OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC), 1525L, 1);
    patronAccount.setPassword(PatronProfileUtil.encrypt("password12"));
    original = new PatronProfileJson();
    request = new PatronUpdateRequest();

  }

  @Test
  public void findByEmailAddress() {
    when(repo.findByEmailAddress(anyString())).thenReturn(patronAccount);
    long startTime = System.nanoTime();
    PatronInternetAccount actual = service.findByEmailAddress("cmc@gmail.com");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(patronAccount, actual);
  }

  @Test
  public void isChangedCaseTrue() {
    request.setNewPassword("152225");
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }

  @Test
  public void isChangedCaseFalse() {
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }

  @Test
  public void updatePatronCaseCallSaveMethod() throws Exception {
	List<PatronInternetAccount> patronAccountList = new ArrayList<PatronInternetAccount>();
	patronAccountList.add(patronAccount);
    when(repo.findByPatronIdEmailAddress(any(Long.class),anyString())).thenReturn(patronAccountList);
    when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Error");
    request.setPassword("password12");
    request.setNewPassword("password");
    long startTime = System.nanoTime();
    service.updatePatron(request, 1525L, 1L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patronAccount);
  }

  @Test(expected = SisticApiException.class)
  public void updatePatronCaseNotCallSaveMethod() throws Exception {
    List<PatronInternetAccount> patronAccountList = new ArrayList<PatronInternetAccount>();
	patronAccountList.add(patronAccount);
    when(repo.findByPatronIdEmailAddress(any(Long.class),anyString())).thenReturn(patronAccountList);
    when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Error");
    request.setPassword("passwossrd");
    request.setNewPassword("password123");
    long startTime = System.nanoTime();
    service.updatePatron(request, 1525L, 1L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(0)).save(patronAccount);
  }

} 

package com.stixcloud.patron.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.domain.PatronAdvanceProfile;
import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.repo.IPatronAdvanceProfileRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PatronAdvanceProfileServiceTest {

  private final Logger LOGGER = LogManager.getLogger(PatronAdvanceProfileServiceTest.class);

  private PatronUpdateRequest request;
  private PatronProfileJson original;
  private PatronAdvanceProfile patronAdv;

  @InjectMocks
  private PatronAdvanceProfileService service;
  @Mock
  private IPatronAdvanceProfileRepository repo;

  @Before
  public void setup() {
    original = new PatronProfileJson();
    original.setYearOfBirth(1995);
    request = new PatronUpdateRequest();
    request.setYearOfBirth(1996);
    patronAdv = new PatronAdvanceProfile();
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronAdv);
  }

  @Test
  public void isChangedCaseTrue() {
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }

  @Test
  public void isChangedCaseFalse() {
    request.setYearOfBirth(1995);
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }

  @Test
  public void updatePatronCaseCallSaveMethod() throws Exception {
    request.setYearOfBirth(1995);
    long startTime = System.nanoTime();
    service.updatePatron(request, 154L, 99L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patronAdv);
  }

  @Test
  public void insertPatronAdvanceProfile() {
    PatronAdvanceProfile patronAdv = new PatronAdvanceProfile();
    long startTime = System.nanoTime();
    service.insertPatronAdvanceProfile(patronAdv);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patronAdv);
  }

}

package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
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

import com.stixcloud.domain.PatronSolicitation;
import com.stixcloud.patron.repo.IPatronSolicitationRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class IPatronSolicitationServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IPatronSolicitationServiceTest.class);

  @InjectMocks
  private PatronSolicitationService service;
  @Mock
  private IPatronSolicitationRepository repo;

  private List<PatronSolicitation> patronSolicitationList;

  @Before
  public void setup() {
    patronSolicitationList = new ArrayList<>();
    PatronSolicitation p1 = new PatronSolicitation(15L, 555L, 45L, 55L, 44L, true,
        OffsetDateTime.of(2017, 10, 10, 10, 10, 10, 10, ZoneOffset.UTC), 1L);
    patronSolicitationList.add(p1);
  }

  @Test
  public void findByPatronProfileId() {
    when(repo.findByPatronProfileId(anyLong(), anyLong())).thenReturn(patronSolicitationList);
    long startTime = System.nanoTime();
    List<PatronSolicitation> actual = service.findByPatronProfileId(22L, 59l);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(patronSolicitationList, actual);
  }

  @Test
  public void save() {
    PatronSolicitation patron = new PatronSolicitation();
    service.save(patron);
    verify(repo, times(1)).save(patron);
  }

  @Test
  public void update() {
    List<PatronSolicitation> patron = new ArrayList<PatronSolicitation>();
    service.update(patron);
    verify(repo, times(1)).save(patron);
  }
  
  @Test
  public void delete() {
    List<PatronSolicitation> patron = new ArrayList<PatronSolicitation>();
    service.delete(patron);
    verify(repo, times(1)).delete(patron);
  }
}

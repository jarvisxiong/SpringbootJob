package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.patron.domain.PatronSolicitationView;
import com.stixcloud.patron.repo.IPatronSolicitationViewRepository;

@Ignore
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class IPatronSolicitationViewServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IPatronSolicitationViewServiceTest.class);

  @InjectMocks
  private PatronSolicitationViewService service;
  @Mock
  private IPatronSolicitationViewRepository repo;

  private List<PatronSolicitationView> patronSolicitationViews;

  @Before
  public void setup() {
    /*patronSolicitationViews = new ArrayList<>();
    PatronSolicitationView solicitation =
        new PatronSolicitationView("Tenant", 152552L, "SISTIC", "http://abcd.com", true, 252545L);
    PatronSolicitationView solicitation2 = new PatronSolicitationView("Promoter", 152552L,
        "Esplanade", "http://ghijk.com", true, 252545L);
    patronSolicitationViews.add(solicitation);
    patronSolicitationViews.add(solicitation2);
    when(repo.getPatronSolicitationView(anyLong())).thenReturn(patronSolicitationViews);*/
  }

  @Test
  public void getPatronSolicitationView() {
    long startTime = System.nanoTime();
    List<PatronSolicitationView> actual = service.getPatronSolicitationView(225L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS));
    assertEquals(patronSolicitationViews, actual);
  }
}

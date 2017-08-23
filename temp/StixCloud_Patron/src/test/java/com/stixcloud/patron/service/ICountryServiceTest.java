package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
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

import com.stixcloud.domain.Country;
import com.stixcloud.patron.repo.ICountryRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ICountryServiceTest {

  private final Logger LOGGER = LogManager.getLogger(ICountryServiceTest.class);
  
  @InjectMocks
  private CountryService service;
  @Mock
  private ICountryRepository repo;
  
  private Country country;
  
  @Before
  public void setup() {
    country = new Country(1115L, "SG", "SGD", "65");
  }
  
  @Test
  public void findByCountryCode() {
    when(repo.findByCountryCode(anyString())).thenReturn(country);
    long startTime = System.nanoTime();
    Country actual = service.findByCountryCode("SG");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(country, actual);
  }
}

package com.stixcloud.patron.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.domain.Country;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ICountryRepositoryIntegrationTest {

  @Autowired
  private ICountryRepository iCountryRepository;

  private Country country;

  @Before
  public void setup() {
    country = new Country();
    country.setCountryID(1L);
    country.setCountryCode("SG");
    country.setCountryCallingCode("65");
    country.setCurrencySymbol("$");
    country.setCurrencyCode("SGD");
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueData.sql"})
  public void findByCountryCodeCaseNormal() throws Exception {
    String countryCode = "SG";
    Country actual = iCountryRepository.findByCountryCode(countryCode);
    assertEquals(country, actual);
  }
}

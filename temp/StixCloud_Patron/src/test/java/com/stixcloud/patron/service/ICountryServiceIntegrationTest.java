package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.patron.api.CountryListResponse;
import com.stixcloud.domain.Country;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ICountryServiceIntegrationTest {


  @Autowired
  private CountryService service;

  @Autowired
  private ObjectMapper objectMapper;

  private Country country;
  String languageCode = "en";

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
    Country actual = service.findByCountryCode(countryCode);
    assertEquals(country, actual);
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueData.sql"})
  public void findByCountryCodeCaseNoResult() throws Exception {
    String countryCode = "HK";
    Country actual = service.findByCountryCode(countryCode);
    CountryListResponse expected = null;
    assertEquals(expected, actual);
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueData.sql"})
  public void fillAllCountryCaseNormal() throws Exception {
    List<Country> lstActual = service.fillAllCountry();
    assertEquals(5, lstActual.size());
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueData.sql"})
  public void getCountryListResponseCaseNormal() throws Exception {
    CountryListResponse actualCountryListResponse = service.getCountryListResponse(languageCode);
    CountryListResponse expectedCountryListResponse =
        objectMapper.readValue(this.getClass().getResource("/country/json/contryListResponse.json"),
            CountryListResponse.class);
    assertEquals(expectedCountryListResponse, actualCountryListResponse);
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueNoData.sql"})
  public void getCountryListResponseCaseFillAllCountryNotResult() throws Exception {
    CountryListResponse actual = service.getCountryListResponse(languageCode);
    CountryListResponse expected = null;
    assertEquals(expected, actual);
  }

}

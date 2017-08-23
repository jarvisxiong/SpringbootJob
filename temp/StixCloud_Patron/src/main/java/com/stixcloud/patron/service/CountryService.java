package com.stixcloud.patron.service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.stixcloud.patron.api.CountryListResponse;
import com.stixcloud.patron.api.json.CountryJson;
import com.stixcloud.domain.Country;
import com.stixcloud.patron.repo.ICountryRepository;

@Service
public class CountryService implements ICountryService {
  private static final Logger logger = LogManager.getLogger(CountryService.class);

  @Autowired
  private ICountryRepository repo;

  @Override
  @Cacheable(value = "findByCountryCode", key = "{#root.methodName, #countryCode}")
  public Country findByCountryCode(String countryCode) {
    return repo.findByCountryCode(countryCode);
  }

  @Override
  @Cacheable(value = "fillAllCountry", key = "{#root.methodName}")
  public List<Country> fillAllCountry() {
    return Lists.newArrayList(repo.findAll());
  }

  @Override
  @Cacheable(value = "getCountryListResponse", key = "{#root.methodName, #languageCode}")
  public CountryListResponse getCountryListResponse(String languageCode) {
    logger.info("Start method getCountryListResponse");
    CountryListResponse response = new CountryListResponse();
    List<Country> countryList = fillAllCountry();

    if (countryList.isEmpty()) {
      logger.info("Data not found");
      return null;
    }

    Locale locale = new Locale(languageCode);

    List<CountryJson> countryJsonList = countryList.stream()
        .map(c -> new CountryJson((new Locale("", c.getCountryCode()).getDisplayCountry(locale)),
            c.getCountryCode(), c.getCountryCallingCode()))
        .collect(Collectors.toList());

    countryJsonList.sort(Comparator.comparing(c -> c.getCountryCode()));
    response.setCountryList(countryJsonList);
    
    logger.info("End method getCountryListResponse");
    return response;
  }

}

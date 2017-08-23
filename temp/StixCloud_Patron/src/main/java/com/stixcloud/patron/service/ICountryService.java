package com.stixcloud.patron.service;

import java.util.List;

import com.stixcloud.patron.api.CountryListResponse;
import com.stixcloud.domain.Country;

public interface ICountryService {

  Country findByCountryCode(String countryCode);

  List<Country> fillAllCountry();

  CountryListResponse getCountryListResponse(String languageCode);
}

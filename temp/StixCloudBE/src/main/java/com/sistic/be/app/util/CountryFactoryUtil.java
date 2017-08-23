package com.sistic.be.app.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.sistic.be.countries.Country;

@Service
public class CountryFactoryUtil {

	public List<Country> getListOfCountries() {

		String[] locales = Locale.getISOCountries();

		List<Country> countryList = new ArrayList<Country>();

		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			Country country = new Country(obj.getCountry(), obj.getDisplayCountry(), obj.getISO3Country());
			countryList.add(country);
		}
		Collections.sort(countryList, Comparator.comparing(Country::getCountryName));

		return countryList;
	}

	public List<Country> getListOfCountries(Locale locale) {

		String[] locales = Locale.getISOCountries();

		List<Country> countryList = new ArrayList<Country>();

		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			Country country = new Country(obj.getCountry(), obj.getDisplayCountry(locale), obj.getISO3Country());
			countryList.add(country);
		}
		Collections.sort(countryList, Comparator.comparing(Country::getCountryName));

		return countryList;
	}

}

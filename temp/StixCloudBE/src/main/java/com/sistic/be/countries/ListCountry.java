package com.sistic.be.countries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ListCountry implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6148156585116585699L;
	
	@JsonProperty("countryList")
	private List<Country> countries = new ArrayList<Country>();
	
	
	public List<Country> getCountries() {
		return countries;
	}

	@JsonSetter("countryList")
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	public String getDisplayCountry(String countryCode) {
		
		Locale countryObj = new Locale("", countryCode);
		String displayCountry = countryObj.getDisplayCountry(countryObj);
		
		return displayCountry;
	}
	
	@JsonIgnore
	public List<Country> getCountriesSortedByCountryCode() {
		ArrayList<Country> countryArrayList = new ArrayList<Country>(countries);
		countryArrayList.sort(Comparator.comparing(Country::getCountryCode));
		return countryArrayList;
	}
	
	@JsonIgnore
	public List<Country> getCountriesSortedByCountryName() {
		ArrayList<Country> countryArrayList = new ArrayList<Country>(countries);
		countryArrayList.sort(Comparator.comparing(Country::getCountryName));
		return countryArrayList;
	}
	
	@JsonIgnore
	public List<Country> getCountriesSortedByCountryCallingCode() {
		ArrayList<Country> countryArrayList = new ArrayList<Country>(countries);
		/**
		 * @todo
		 * someone help me to change this comparing to cast to Integer when comparing
		 */
//		countryArrayList.sort(Comparator.comparing(Country::getCountryCallingCode));
		Collections.sort(countryArrayList, (o1, o2) -> Short.valueOf(o1.getCountryCallingCode()) - Short.valueOf(o2.getCountryCallingCode()));
		return countryArrayList;
	}

}

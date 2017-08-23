package com.stixcloud.patron.api;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.patron.api.json.CountryJson;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"countryList"})
public class CountryListResponse implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = -1432978383757886187L;
  @JsonProperty("countryList")
  private List<CountryJson> countryList;

  public CountryListResponse() {
  }

  public CountryListResponse(List<CountryJson> countryList) {
    super();
    this.countryList = countryList;
  }

  /**
   * @return the countryList
   */
  public List<CountryJson> getCountryList() {
    return countryList;
  }

  /**
   * @param countryList the countryList to set
   */
  public void setCountryList(List<CountryJson> countryList) {
    this.countryList = countryList;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("countryList", countryList)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }

    CountryListResponse countryList = (CountryListResponse) o;
    return countryList.getCountryList().equals(this.countryList);
  }

  @Override
  public int hashCode() {
    return this.countryList.hashCode();
  }
}

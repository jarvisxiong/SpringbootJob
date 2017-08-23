package com.stixcloud.patron.api;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.IdentificationJson;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"newPassword"})
public class PatronUpdateRequest extends PatronRequest implements Serializable {

  private static final long serialVersionUID = 3163963007046964136L;

  @JsonProperty("newPassword")
  private String newPassword;

  public PatronUpdateRequest() {

  }

  public PatronUpdateRequest(String title, String firstName, String lastName,
      List<ContactJson> contactNos, Integer yearOfBirth, String nationality,
      String countryOfResidence, IdentificationJson identification,
      String newPassword, boolean isBillingAddressSameAsMailing, List<AddressJson> addresses,
      Integer accno, Integer status, Boolean isReceiveTicketingAgent, Boolean isReceivePromoter, Boolean isReceiveVenue) {
    super(title, firstName, lastName, contactNos, yearOfBirth, nationality, countryOfResidence,
        identification, null, isBillingAddressSameAsMailing, addresses, accno, status, isReceiveTicketingAgent, isReceivePromoter, isReceiveVenue);
    this.newPassword = newPassword;
  }

  /**
   * @return the newPassword
   */
  @JsonProperty("newPassword")
  public String getNewPassword() {
    return newPassword;
  }

  /**
   * @param newPassword the newPassword to set
   */
  @JsonProperty("newPassword")
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronUpdateRequest that = (PatronUpdateRequest) o;
    return new EqualsBuilder().append(newPassword, that.getNewPassword())
        .append(title, that.getTitle())
        .append(firstName, that.getFirstName()).append(lastName, that.getLastName())
        .append(contacts, that.getContacts()).append(yearOfBirth, that.getYearOfBirth())
        .append(nationality, that.getNationality())
        .append(countryOfResidence, that.getCountryOfResidence())
        .append(identification, that.getIdentification()).append(addresses, that.getAddresses())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(newPassword).append(title)
        .append(firstName).append(lastName).append(contacts).append(yearOfBirth)
        .append(nationality).append(countryOfResidence).append(identification).append(addresses)
        .toHashCode();
  }

}

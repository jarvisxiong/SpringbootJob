package com.stixcloud.patron.api;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.IdentificationJson;
import com.stixcloud.patron.api.json.PatronProfileJson;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"accNo", "status", "organizationId","disableWebAccount", "buzz"})
public class PatronRequest extends PatronProfileJson implements Serializable {

  private static final long serialVersionUID = 3163963007046964136L;
  
  @JsonProperty("accNo")
  private Integer accNo;
  @JsonProperty("status")
  private Integer status;
  @JsonProperty("organizationId")
  private Long organizationId;
  @JsonProperty("disableWebAccount")
  private Boolean disableWebAccount;

  public PatronRequest() {
    super();
  }

  public PatronRequest(String title, String firstName, String lastName,
      List<ContactJson> contactNos, Integer yearOfBirth, String nationality,
      String countryOfResidence, IdentificationJson identification, String password,
      boolean isBillingAddressSameAsMailing, List<AddressJson> addresses, Integer accNo,
      Integer status, Boolean isReceiveTicketingAgent, Boolean isReceivePromoter,
      Boolean isReceiveVenue) {
    super(title, firstName, lastName, contactNos, yearOfBirth, nationality, countryOfResidence,
        identification, isBillingAddressSameAsMailing, addresses, isReceiveTicketingAgent, isReceivePromoter, isReceiveVenue);
    this.status = status;
  }

  public Integer getAccNo() {
	return accNo;
  }

  public void setAccNo(Integer accNo) {
	  this.accNo = accNo;
  }

/**
   * @return the status
   */
  @JsonProperty("status")
  public Integer getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  @JsonProperty("status")
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * @return the organizationId
   */
  public Long getOrganizationId() {
    return organizationId;
  }

  /**
   * @param organizationId the organizationId to set
   */
  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }



public Boolean getDisableWebAccount() {
	return disableWebAccount;
}

public void setDisableWebAccount(Boolean disableWebAccount) {
	this.disableWebAccount = disableWebAccount;
}

/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((disableWebAccount == null) ? 0 : disableWebAccount.hashCode());
	result = prime * result + ((organizationId == null) ? 0 : organizationId.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	return result;
}

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (!super.equals(obj))
		return false;
	if (getClass() != obj.getClass())
		return false;
	PatronRequest other = (PatronRequest) obj;
	if (disableWebAccount == null) {
		if (other.disableWebAccount != null)
			return false;
	} else if (!disableWebAccount.equals(other.disableWebAccount))
		return false;
	if (organizationId == null) {
		if (other.organizationId != null)
			return false;
	} else if (!organizationId.equals(other.organizationId))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	return true;
}

}

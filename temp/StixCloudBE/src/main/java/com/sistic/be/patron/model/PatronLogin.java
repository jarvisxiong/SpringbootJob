package com.sistic.be.patron.model;

import java.io.Serializable;

public class PatronLogin implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2937793608701643338L;
	
	private Long patronId;
	private Long accountNo;
	private String email;
	private String firstName;
	private String lastName;
	private String patronName;
	private String refreshToken;
	private boolean hasMembership;

	public PatronLogin() {
		
	}
	
	public PatronLogin(Long patronId, Long accountNo, String email, String firstName, String lastName, String patronName, String refreshToken, 
			boolean hasMembership) {
		this.patronId = patronId;
		this.accountNo = accountNo;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronName = patronName;
		this.refreshToken = refreshToken;
		this.hasMembership = hasMembership;
	}

	public Long getPatronId() {
		return patronId;
	}

	public void setPatronId(Long patronId) {
		this.patronId = patronId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronName() {
		return patronName;
	}

	public void setPatronName(String patronName) {
		this.patronName = patronName;
	}

	public boolean isHasMembership() {
      return hasMembership;
    }
  
    public void setHasMembership(boolean hasMembership) {
        this.hasMembership = hasMembership;
    }
    
    public String getRefreshToken() {
      return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
    }

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "PatronLogin [patronId=" + patronId + ", accountNo=" + accountNo + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", patronName=" + patronName + ", refreshToken=" + refreshToken
				+ ", hasMembership=" + hasMembership + "]";
	}

}

package com.stixcloud.oauth2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Last Modified by : ZhenHui<br />
 * Last Modified : 1 Feb 2017<br />
 * Purpose : Mainly cater for BE LOGIN section
 * 
 */
@Entity(name="PATRON_INTERNET_ACCOUNT")
public class PatronLogin implements Serializable {

    private static final long serialVersionUID = 2937793608701643338L;
    @Id
    private Long patronId;
    private String email;
    private String firstName;
    private String lastName;
    private Long accountNo;

    @Column(name="PATRON_PROFILE_ID")
    public Long getPatronId() {
        return patronId;
    }

    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }

    @Column(name="USERID")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="FIRSTNAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="LASTNAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="ACCNO")
    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "PatronLogin [patronId=" + patronId + ", email=" + email + ", firstName=" + firstName + ", lastName="
                + lastName + ", accountNo=" + accountNo + "]";
    }

}

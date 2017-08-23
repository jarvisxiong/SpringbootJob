package com.sistic.be.patron.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Deprecated
public class PatronInternetAccount implements Serializable {
	
	private String acctNum;
	private String userId;
	private String password;
	private int loginFailedCount;
	private OffsetDateTime lastLoginDate;
	
	// Getter Setter
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginFailedCount() {
		return loginFailedCount;
	}
	public void setLoginFailedCount(int loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}
	public OffsetDateTime getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(OffsetDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	

}

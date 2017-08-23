package com.stixcloud.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_GROUP_TYPE_INFO")
public class UserGroupTypeInfo implements Serializable {
	private static final long serialVersionUID = -7354014234007638431L;

	@Id
	Long id;
	Long profileInfoId,
		groupInfoId,
		status,
		userstatus;
	
	String grouptypename,
		userid;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROFILE_INFO_ID", nullable = false)
	public Long getProfileInfoId() {
		return profileInfoId;
	}

	public void setProfileInfoId(Long profileinfoid) {
		this.profileInfoId = profileinfoid;
	}

	@Column(name = "GROUP_INFO_ID", nullable = false)
	public Long getGroupInfoId() {
		return groupInfoId;
	}

	public void setGroupInfoId(Long groupinfoid) {
		this.groupInfoId = groupinfoid;
	}

	@Column(name = "STATUS", nullable = false)
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "USERSTATUS", nullable = false)
	public Long getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(Long userstatus) {
		this.userstatus = userstatus;
	}

	@Column(name = "GROUPTYPENAME", nullable = false)
	public String getGrouptypename() {
		return grouptypename;
	}

	public void setGrouptypename(String grouptypename) {
		this.grouptypename = grouptypename;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
		
}

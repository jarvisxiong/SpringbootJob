package com.sistic.be.membership.api.model;

import java.io.Serializable;

public class MembershipSubMemberInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8386354291747342709L;
	
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MembershipSubMemberInfo [key=" + key + ", value=" + value
				 + "]";
	}

}

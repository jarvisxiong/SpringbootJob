package com.sistic.be.membership.api.model;

import java.io.Serializable;
import java.util.List;

public class MembershipConfigProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8386354291747342709L;
	
	private String alias;
	private List<MembershipConfigOption> options;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public List<MembershipConfigOption> getOptions() {
		return options;
	}

	public void setOptions(List<MembershipConfigOption> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "MembershipProfile [alias=" + alias + ", membershipConfigOption=" + options
				 + "]";
	}

}

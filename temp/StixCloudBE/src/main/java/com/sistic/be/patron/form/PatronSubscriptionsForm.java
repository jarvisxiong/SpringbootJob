package com.sistic.be.patron.form;

import java.io.Serializable;
import java.util.List;

public class PatronSubscriptionsForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1739835947500766842L;
	
	private List<String> solicitationList;

	public List<String> getSolicitationList() {
		return solicitationList;
	}

	public void setSolicitationList(List<String> solicitationList) {
		this.solicitationList = solicitationList;
	}

	@Override
	public String toString() {
		return "PatronSubscriptionsForm [solicitationList=" + solicitationList + "]";
	}
	
}

package com.sistic.be.patron.api.model;

import java.io.Serializable;
import java.util.List;

public class SolicationListType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5849153880434908194L;
	
	private String solicitationType;
	private List<Solicitation> solicitationList;
	
	
	public String getSolicitationType() {
		return solicitationType;
	}
	public void setSolicitationType(String solicitationType) {
		this.solicitationType = solicitationType;
	}
	public List<Solicitation> getSolicitationList() {
		return solicitationList;
	}
	public void setSolicitationList(List<Solicitation> solicitationList) {
		this.solicitationList = solicitationList;
	}
	public void addSolicitationList(Solicitation solicitation) {
		this.solicitationList.add(solicitation);
	}
	
	@Override
	public String toString() {
		return "SolicationListType [solicitationType=" + solicitationType + ", solicitationList=" + solicitationList
				+ "]";
	}

}

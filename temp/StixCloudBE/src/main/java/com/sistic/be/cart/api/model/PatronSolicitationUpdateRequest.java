package com.sistic.be.cart.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatronSolicitationUpdateRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9191793206001695031L;
	
	
	private SolicitationUpdateFlags flags;
	private List<SolicitationUpdate> solicitationList;
	
	public PatronSolicitationUpdateRequest() {
		this.solicitationList = new ArrayList<SolicitationUpdate>();
	}
	
	
	public SolicitationUpdateFlags getFlags() {
		return flags;
	}
	public void setFlags(SolicitationUpdateFlags flags) {
		this.flags = flags;
	}
	public List<SolicitationUpdate> getSolicitationList() {
		return solicitationList;
	}
	public void setSolicitationList(List<SolicitationUpdate> solicitationList) {
		this.solicitationList = solicitationList;
	}
	public void addSolicitation(SolicitationUpdate solicitationUpdate) {
		this.solicitationList.add(solicitationUpdate);
	}
	public void addAllSolicitation(List<SolicitationUpdate> solicitationUpdates) {
		this.solicitationList.addAll(solicitationUpdates);
	}


	@Override
	public String toString() {
		return "PatronSolicitationUpdateRequest [flags=" + flags + ", solicitationList=" + solicitationList + "]";
	}

}

package com.sistic.be.patron.api.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

public class PatronSolicitation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7719601929363364374L;
	
	private List<Solicitation> solicitationList;

	public List<Solicitation> getSolicitationList() {
		return solicitationList;
	}

	public void setSolicitationList(List<Solicitation> solicitationList) {
		this.solicitationList = solicitationList.stream().sorted(Comparator.comparing(Solicitation::getOrder).thenComparing(Solicitation::getOrganizationName)).collect(Collectors.toList());
	}
	
	@JsonIgnore
	public Map<String, Collection<Solicitation>> getSolicitationListGroupByType() {
				
		Multimap<String, Solicitation> solicitationListMultiMap = LinkedHashMultimap.create();
		if (solicitationList != null && !solicitationList.isEmpty()) {
			for (Solicitation solicitation : solicitationList) {
				solicitationListMultiMap.put(solicitation.getSolicitationType(), solicitation);
			}
		}
		
		return solicitationListMultiMap.asMap();
	}

	@Override
	public String toString() {
		return "PatronSolicitation [solicitationList=" + solicitationList + "]";
	}
	

}

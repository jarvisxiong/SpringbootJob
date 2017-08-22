package com.cmcglobal.senseinfosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.IncidentCase;

/**
 * 
 * @author NVAn
 * File name：IncidentCaseRepository.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                  (Comment)
 *   Rev 1.00   2017/07/27    IncidentCaseRepository    Create New
 */

@Repository("incidentCaseRepository")
public interface IncidentCaseRepository extends JpaRepository<IncidentCase, Integer>{
	
	IncidentCase getById(int Id);
}
package com.cmcglobal.senseinfosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.IncidentLookup;
import com.cmcglobal.senseinfosys.model.IncidentLookupID;

/**
 * 
 * @author NVAn
 * File name：IncidentLookupRepository.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                  (Comment)
 *   Rev 1.00   2015/07/27    IncidentLookupRepo        Create New
 */
@Repository("incidentLookupRepository")
public interface IncidentLookupRepository extends JpaRepository<IncidentLookup, IncidentLookupID> {
//	IncidentLookup findByPk(IncidentLookupID pk);
}
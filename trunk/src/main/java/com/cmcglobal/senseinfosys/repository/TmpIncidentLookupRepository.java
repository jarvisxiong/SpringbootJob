package com.cmcglobal.senseinfosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.IncidentLookupID;
import com.cmcglobal.senseinfosys.model.TmpIncidentLookup;

/**
 * 
 * @author NVAn
 * File name：TmpIncidentLookupRepository.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                  (Comment)
 *   Rev 1.00   2015/07/27    TmpIncidentLookupRepo      Create New
 */
@Repository("tmpIncidentLookupRepository")
public interface TmpIncidentLookupRepository extends JpaRepository<TmpIncidentLookup, IncidentLookupID> {
}
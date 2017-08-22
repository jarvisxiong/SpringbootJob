package com.cmcglobal.senseinfosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.Ship;

/**
 * 
 * @author NVAn
 * File name：ShipRepository.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)       (Comment)
 *   Rev 1.00   2015/07/27    ShipRepo       Create New
 */
@Repository("shipRepository")
public interface ShipRepository extends JpaRepository<Ship, Integer> {
	 Ship findById(int id);
}

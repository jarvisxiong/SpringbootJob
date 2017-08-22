package com.cmcglobal.senseinfosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.TmpShip;

/**
 * 
 * @author NVAn
 * File name：TmpShipRepository.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)         (Comment)
 *   Rev 1.00   2015/07/27    TmpShipRepo       Create New
 */
@Repository("tmpShipRepository")
public interface TmpShipRepository extends JpaRepository<TmpShip, Integer> {
	TmpShip findById(int id);
}
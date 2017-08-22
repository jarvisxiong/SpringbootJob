package com.cmcglobal.senseinfosys.service;

import java.util.List;

import org.quartz.JobExecutionException;

import com.cmcglobal.senseinfosys.model.TmpShip;

/**
 * 
 * @author NVAn
 * File name：ShipService.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                    (Comment)
 *   Rev 1.00   2017/07/27    ShipServiceInterface         Create New
 */
public interface ShipService {
	public void getShipData() throws JobExecutionException;
	
	public void importData(List<TmpShip> ships) throws JobExecutionException;
}

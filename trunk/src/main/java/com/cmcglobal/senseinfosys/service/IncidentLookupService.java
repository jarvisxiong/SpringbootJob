/**
 * 
 */
package com.cmcglobal.senseinfosys.service;

import java.util.List;

import org.quartz.JobExecutionException;

import com.cmcglobal.senseinfosys.model.TmpIncidentLookup;

/**
 * 
 * @author NVAn
 * File name：IncidentLookupService.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                            (Comment)
 *   Rev 1.00   2015/07/27    IncidentLookupServiceInterface      Create New
 */
public interface IncidentLookupService {
	public void getLookupData() throws JobExecutionException;

	public void importData(List<TmpIncidentLookup> tmpLookups) throws JobExecutionException;
}

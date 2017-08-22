package com.cmcglobal.senseinfosys.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.service.IncidentLookupService;
import com.cmcglobal.senseinfosys.service.JobLogService;

/**
 * 
 * @author NVAn
 * File name：GetIncidentLookupApiJob.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)             (Comment)
 *   Rev 1.00   2017/07/27    IncidentLookupApiJob    Create New
 */
@Service
@Transactional
public class IncidentLookupApiJob extends BaseJob {
	private static final Logger log = Logger.getLogger(IncidentLookupApiJob.class);
	
	@Autowired
	IncidentLookupService incidentLookupService;
	
	@Autowired
	JobLogService jobLogService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
		try {
			executeJob();
		} catch (JobExecutionException e) {
			log.error("IncidentLookupApiJob", e);
			throw e;
		}
	}
	
	@Override
	public void executeJob() throws JobExecutionException {
		try {
			// Get incident lookup data
			incidentLookupService.getLookupData();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new JobExecutionException("Execute incidentlookup api job failed");
		}
	}
}
package com.cmcglobal.senseinfosys.quartz.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.TmpIncidentLookup;
import com.cmcglobal.senseinfosys.service.IncidentLookupService;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：GetIncidentLookupDbJob.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)             (Comment)
 *   Rev 1.00   2017/07/27    IncidentLookupApiJob    Create New
 */
@Service
@Transactional
public class IncidentLookupDbJob extends BaseJob {
	private static final Logger log = LoggerFactory.getLogger(IncidentLookupDbJob.class);
	
	@Autowired
	IncidentLookupService incidentLookupService;
	
	@Autowired
	JobLogService jobLogService;
	
	private List<TmpIncidentLookup> tmpLookups;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
		try {
			executeJob();
		} catch (JobExecutionException e) {
			log.error("IncidentLookupDbJob", e);
			throw e;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void executeJob() throws JobExecutionException {
		try {
			tmpLookups = (List<TmpIncidentLookup>)getExtra(Utils.LOOKUP_EXTRA_DATA);
			
			// If tmpLookups is null or empty => get data from API
			if (tmpLookups != null && !tmpLookups.isEmpty()) {
				// Import incident lookup data to database
				incidentLookupService.importData(tmpLookups);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new JobExecutionException("Execute incidentlookup database job failed");
		}
	}
}
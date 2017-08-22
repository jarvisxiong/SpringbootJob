package com.cmcglobal.senseinfosys.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.service.ShipService;

/**
 * 
 * @author NVAn
 * File name：ShipApiJob.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)       (Comment)
 *   Rev 1.00   2017/07/27    ShipApiJob    Create New
 */
@Service
@Transactional
public class ShipApiJob extends BaseJob {
	private static final Logger log = Logger.getLogger(ShipApiJob.class);
	
	@Autowired
	ShipService shipService;
	
	@Autowired
	JobLogService jobLogService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
		try {
			executeJob();
		} catch (JobExecutionException e) {
			throw e;
		}
	}
	
	@Override
	public void executeJob() throws JobExecutionException {
		try {
			// Get data from API
			shipService.getShipData();
		} catch(Exception ex) {
			log.error("ShipApiJob", ex);
			throw new JobExecutionException("Execute ship api job failed");
		}
	}
}
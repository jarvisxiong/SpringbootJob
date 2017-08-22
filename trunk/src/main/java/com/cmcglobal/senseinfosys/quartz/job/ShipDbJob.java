package com.cmcglobal.senseinfosys.quartz.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.TmpShip;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.service.ShipService;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：ShipDbJob.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)       (Comment)
 *   Rev 1.00   2017/07/27    ShipDbJob    Create New
 */
@Service
@Transactional
public class ShipDbJob extends BaseJob {
	private static final Logger log = Logger.getLogger(ShipDbJob.class);
	
	@Autowired
	ShipService shipService;
	
	@Autowired
	JobLogService jobLogService;

	private List<TmpShip> tmpShips;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
		try {
			executeJob();
		} catch (JobExecutionException e) {
			log.error("ShipDbJob", e);
			throw e;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void executeJob() throws JobExecutionException {
		try {
			tmpShips = (List<TmpShip>) getExtra(Utils.SHIP_EXTRA_DATA);
			// import ship data to database
			if (tmpShips != null && !tmpShips.isEmpty()) {
				shipService.importData(tmpShips);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new JobExecutionException("Execute ship database job failed");
		}
	}
}
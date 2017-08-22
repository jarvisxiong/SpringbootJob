package com.cmcglobal.senseinfosys.quartz.job;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.JobLog;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.utils.JobStatus;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn
 * File name：AutomaticJob.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)          (Comment)
 *   Rev 1.00   2017/07/27    AutomaticJob      Create New
 */

@Service
@Transactional
public class AutomaticJob implements Job {
	
	private static final Logger log = Logger.getLogger(AutomaticJob.class);
	
	JobsManagement managerJobs;
	
	@Value("${ifn-dateFrom}")
	public String dateFromConfig;
	
	@Autowired
	private JobLogService jobLogService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
		log.debug("------------------AUTO JOB--------------");
		try {
			managerJobs = (JobsManagement) jobExecutionContext.getScheduler().getContext().get("jobManager");
			boolean isRunFirstJob = managerJobs.isInitFlag() || (managerJobs.getInitDate().before(new Date()));
			if (!isRunningJob() && isRunFirstJob) {
				// Get incident case api job has last time.
				String data = createDataAutomaticJob();
				
				managerJobs.setInitFlag(false);
				managerJobs.setJobMode(JobStatus.AUTO);
				managerJobs.initJobs(data);
			}
		} catch (SchedulerException e) {
			log.error("AutomaticJob", e);
		}
		return;
    }
    
    /**
     * 
     * @return String data include date from and date to.
     */
    private String createDataAutomaticJob() {
    	List<JobLog> jobLogs = jobLogService.getIncidentJobApi();
    	if(jobLogs .size() > 0) {
			JobLog jobLog = Utils.getLastJob(jobLogs);
			String lastDate = Utils.formatDateIncident(jobLog.getStartTime());
			return Utils.createJobParameter(lastDate, Utils.formatDate(new Date(), Utils.DATE_FORMAT_PARTTERN_3));
		} else {
			return Utils.createJobParameter(dateFromConfig, Utils.formatDate(new Date(), Utils.DATE_FORMAT_PARTTERN_3));
		}
    }
    
    private boolean isRunningJob() {
    	List<JobLog> jobLogs = jobLogService.getJobLogsWithoutAutoJob();
    	if (jobLogs == null || jobLogs.size() == 0) {
    		return false;
    	}
    	JobLog jobLog = Utils.getLastJob(jobLogs);
    	List<JobLog> logs = jobLogService.getJobLogByGroupId(jobLog.getGroupId());
		long numberOfJobs =  getRunningJobs (logs);
		return numberOfJobs > 0 ;
	}
    
    private long getRunningJobs (List<JobLog> jobLogs) {
    	return jobLogs.stream().filter(p -> p.getStatus() == JobStatus.RUNNING.value()).count();
    }
}

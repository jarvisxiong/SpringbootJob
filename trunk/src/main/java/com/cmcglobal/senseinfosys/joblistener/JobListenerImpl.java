package com.cmcglobal.senseinfosys.joblistener;


import java.util.Date;

import javax.annotation.Nonnull;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.model.JobLog;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.utils.JobStatus;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NHLong File JobListenerImpl.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/27 JobListenerImpl Create New
 */

@Service("jobListener")
@Transactional
public class JobListenerImpl implements JobListener {

	private static final Logger log = Logger.getLogger(JobListenerImpl.class);
	
	public static final String LISTENER_NAME = "IFNJobListener";

	@Nonnull
	private JobLogService jobLogService;

	@Override
	public String getName() {
		return LISTENER_NAME;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		// Get joblogservice from context
		try {
			jobLogService = (JobLogService) context.getScheduler().getContext().get(LISTENER_NAME);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		String jobKey = context.getJobDetail().getKey().getName();
		String jobGroup = context.getJobDetail().getKey().getGroup();
		String jobName = context.getJobDetail().getJobClass().getSimpleName();
		int mode = context.getJobDetail().getJobDataMap().getInt(Utils.MODE_KEY);
		JobLog jobLog = new JobLog();
		jobLog.setStartTime(new Date());
		jobLog.setJobName(jobName);
		jobLog.setId(jobKey);
		jobLog.setStatus(JobStatus.RUNNING.value());
		jobLog.setGroupId(jobGroup);
		jobLog.setMode(mode);

		// Save to log database
		if (jobLogService != null) {
			jobLog = jobLogService.saveJobLog(jobLog);
		}
		log.debug(jobName + "was executed");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {

	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		if (jobLogService == null) {
			try {
				jobLogService = (JobLogService) context.getScheduler().getContext().get(LISTENER_NAME);
			} catch (SchedulerException e) {
				log.error(e);
			}
		}
		String jobKey = context.getJobDetail().getKey().getName();
		String jobName = context.getJobDetail().getJobClass().getSimpleName();
		
		
		JobLog jobLog = jobLogService.getJobLogByJobKey(jobKey);
		if(jobLog == null){
			jobLog = new JobLog();
			jobLog.setId(jobKey);
		}
		jobLog.setEndTime(new Date());
		log.info("Job : " + jobName + " is finished...");

		if (jobException != null && !jobException.getMessage().equals("")) {
			jobLog.setStatus(JobStatus.FAILURE.value());
			jobLog.setDescription(jobException.getMessage());
			log.error("Exception thrown by: " + jobName + " Exception: " + jobException.getMessage());
		} else {
			jobLog.setStatus(JobStatus.SUCCESS.value());
			log.info(jobName + " was executed succesfully.");
		}

		jobLog = jobLogService.saveJobLog(jobLog);
		log.info(jobName + " was saved succesfully.");
	}

}

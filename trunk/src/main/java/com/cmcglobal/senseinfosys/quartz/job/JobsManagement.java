package com.cmcglobal.senseinfosys.quartz.job;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmcglobal.senseinfosys.joblistener.JobListenerImpl;
import com.cmcglobal.senseinfosys.service.ApiCallback;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.utils.JobStatus;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NHLong File JobsManagement.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/28 JobsManagement Create New
 */

@Service("manageJobs")
@Transactional
public class JobsManagement implements ApiCallback {
	
	private static final Logger log = Logger.getLogger(JobsManagement.class);

	private Scheduler scheduler;
	
	@Value("${ifn-automatic-schedule}")
	private String cronSchedule;

	private JobLogService jobLogService;

	private String groupId ;

	private JobStatus jobStatus;
	
	private boolean initFlag;
	
	private Date initDate;
	
	public boolean isInitFlag() {
		return initFlag;
	}

	public void setInitFlag(boolean initFlag) {
		this.initFlag = initFlag;
	}

	public JobsManagement() {
		
	}

	public JobsManagement(Scheduler scheduler, JobLogService jobLogService, JobStatus jobStatus) {
		super();
		this.scheduler = scheduler;
		this.jobLogService = jobLogService;
		this.jobStatus = jobStatus;
	}

	public void setJobStatus(Scheduler scheduler, JobLogService jobLogService, JobStatus jobStatus){
		this.jobStatus = jobStatus;
		this.scheduler = scheduler;
		this.jobLogService = jobLogService;
	}
	
	public void setJobMode(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	/**
	 * Init three jobs run when start job
	 * @param dataIncidentCaseApiJob
	 */
	public void initJobs(String dataIncidentCaseApiJob) {
		groupId =  UUID.randomUUID().toString();
        // Perform ship api job.
		createJob(ShipApiJob.class, Utils.SHIP_EXTRA_DATA, null);

		// Perform incident look up api job.
		createJob(IncidentLookupApiJob.class, Utils.LOOKUP_EXTRA_DATA, null);

		// Perform Incident Job.
		createJob(IncidentCaseApiJob.class, Utils.INCIDENT_CASE_EXTRA_DATA, dataIncidentCaseApiJob);
	}

	/**
	 * Create dynamic any job with data put to JobDataMap
	 * @param jobClass
	 * @param keyData
	 * @param value
	 */
	public void createJob(Class<? extends Job> jobClass, String keyData, Object value) {
		String strJobKey = UUID.randomUUID().toString();

		JobKey jobKey = new JobKey(strJobKey, groupId);
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).build();
		jobDetail.getJobDataMap().put(Utils.MODE_KEY, jobStatus.value());
		if (!Utils.isEmpty(keyData)) {
			jobDetail.getJobDataMap().put(keyData, value);
		}
		Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).startNow().build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.getContext().put(JobListenerImpl.LISTENER_NAME, jobLogService);
		} catch (SchedulerException e) {
			log.error("createJob manual jobsManagement", e);
		}
	}
	
	/**
	 * @param jobClass
	 */
	public void createAutomaticJob(Class<? extends Job> jobClass) {
		String strJobKey = UUID.randomUUID().toString();
		groupId =  UUID.randomUUID().toString();
		JobKey jobKey = new JobKey(strJobKey, groupId);
		
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).build();
		jobDetail.getJobDataMap().put(Utils.MODE_KEY, jobStatus.value());
		Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(cronSchedule)).build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.getContext().put(JobListenerImpl.LISTENER_NAME, jobLogService);
			scheduler.getContext().put("jobManager", this);
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			log.error("CreateAutomaticJob jobsManagement", e);
		}
	}

	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public void onSuccess(Class<? extends Job> jobClass, String keyData, Object data) {
		createJob(jobClass, keyData, data);

	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub

	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
}

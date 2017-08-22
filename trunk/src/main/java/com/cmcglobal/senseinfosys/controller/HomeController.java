package com.cmcglobal.senseinfosys.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmcglobal.senseinfosys.model.JobLog;
import com.cmcglobal.senseinfosys.quartz.job.JobsManagement;
import com.cmcglobal.senseinfosys.service.JobLogService;
import com.cmcglobal.senseinfosys.utils.JobStatus;
import com.cmcglobal.senseinfosys.utils.Utils;

@Controller
public class HomeController {
	
	private static final String MESSAGE_ERROR_INVALID_DATE_FROM  = "Invalid from date"; 
	
	@Autowired
	private JobLogService jobLogService;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	JobsManagement manageJobs;
	
	@RequestMapping(value = "/syncData", method = RequestMethod.GET)
	public ResponseEntity<String> home(@RequestParam String dateFrom, @RequestParam String dateTo) {
		// check status other jobs
		if (isRunningJob() || isRunningAutomaticJob()) {
			return new ResponseEntity<String>("Other job is running...", HttpStatus.BAD_REQUEST);
		}
		
		// Setting manual job
		manageJobs.setJobStatus(scheduler, jobLogService, JobStatus.MANUAL);

		// Check empty.
		if (dateFrom.trim().isEmpty()) {
			return new ResponseEntity<String>("The from date in yyyyMMdd format", HttpStatus.BAD_REQUEST);
		}

		if (dateTo.trim().isEmpty()) {
			return new ResponseEntity<String>("The to date in yyyyMMdd format", HttpStatus.BAD_REQUEST);
		}
		
		// If date from = date to
		if (Utils.parseDate(dateFrom, Utils.DATE_FORMAT_PARTTERN_4)
				.compareTo(Utils.parseDate(dateTo, Utils.DATE_FORMAT_PARTTERN_4)) == 0) {
			return new ResponseEntity<String>(MESSAGE_ERROR_INVALID_DATE_FROM, HttpStatus.BAD_REQUEST);
		}
		
		// Check date from with date to.
		if (Utils.parseDate(dateFrom, Utils.DATE_FORMAT_PARTTERN_4).after(
				Utils.parseDate(dateTo, Utils.DATE_FORMAT_PARTTERN_4))) {
			return new ResponseEntity<String>(MESSAGE_ERROR_INVALID_DATE_FROM, HttpStatus.BAD_REQUEST);
		}
		
		// Reformat date time
		String frDate = formatDateddMMYYYY(dateFrom);
		String toDate = formatDateddMMYYYY(dateTo);

		// Check date format error.
		if (!isValidDate(frDate)) {
			return new ResponseEntity<String>(MESSAGE_ERROR_INVALID_DATE_FROM, HttpStatus.BAD_REQUEST);
		}

		if (!isValidDate(toDate)) {
			return new ResponseEntity<String>("Invalid to date", HttpStatus.BAD_REQUEST);
		}
		
		// Create date data
		String data = Utils.createJobParameter(frDate, toDate);
		
		// Call jobs.
		manageJobs.initJobs(data);

		return new ResponseEntity<String>("Created job to sync data", HttpStatus.OK);
	}

	/**
	 * API to get jobs status
	 * @return
	 */
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ResponseEntity<JobLog> getJobStatus() {
		List<JobLog> jobLogs = jobLogService.getJobLogsByGroup();
		
		// Get job has max start time.
		JobLog lastJob = Utils.getLastJob(jobLogs);
		
		// Get job is same group id.
		JobLog result = filterResult(jobLogs);
		
		if(lastJob != null && result != null
			&& result.getStatus() == JobStatus.SUCCESS.value()){
			result.setStartTime(lastJob.getStartTime());
		}
		
		// Get automatic job has max start time.
		JobLog lastAutomaticJob = getLastAutomaticJob();
		
		if (lastAutomaticJob != null && result != null &&
				(lastAutomaticJob.getStatus() == JobStatus.RUNNING.value() && result.getStatus() != JobStatus.FAILURE.value())) {
			result.setStatus(JobStatus.RUNNING.value());
			result.setStartTime(lastAutomaticJob.getStartTime());
			result.setJobName(lastAutomaticJob.getJobName());
		}
		
		return new ResponseEntity<JobLog>(result, HttpStatus.OK);
	}

	private JobLog getLastAutomaticJob() {
		List<JobLog> listAutomaticJobs = jobLogService.getAutomaticJob();
		JobLog lastAutomaticJob = Utils.getLastJob(listAutomaticJobs);
		return lastAutomaticJob;
	}

	/**
	 * @param dateStr
	 * @return true if the same format or else
	 */
	private static boolean isValidDate(String dateStr) {
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(Utils.DATE_FORMAT_PARTTERN_3);
			formatter.parseDateTime(dateStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param dateStr
	 * @return string after formated
	 */
	private static String formatDateddMMYYYY(String dateStr) {
		DateFormat fromFormat = new SimpleDateFormat(Utils.DATE_FORMAT_PARTTERN_4);
		fromFormat.setLenient(false);
		DateFormat toFormat = new SimpleDateFormat(Utils.DATE_FORMAT_PARTTERN_3);
		toFormat.setLenient(false);
		Date date;
		try {
			date = fromFormat.parse(dateStr);
			return toFormat.format(date);
		} catch (ParseException e) {
			return Utils.EMPTY;
		}
	}

	/**
	 * 
	 * @param jobLogs
	 * @return
	 */
	private JobLog filterResult(List<JobLog> jobLogs) {
		JobLog result = new JobLog();
		
		if (jobLogs.size() == 0) {
			return null;
		}
		
		for (JobLog jobLog : jobLogs) {
			int index = jobLogs.indexOf(jobLog);
			if (index == 0) {
				switch(jobLog.getMode()) {
				case Utils.AUTO_MODE:
					result.setMode(JobStatus.AUTO.value());
					break;
				case Utils.MANUAL_MODE:
					result.setMode(JobStatus.MANUAL.value());
					break;
				default:
					break;
				}
			}
			
			if (jobLog.getStatus() == JobStatus.FAILURE.value()) {
				result.setStatus(JobStatus.FAILURE.value());
				result.setDescription(jobLog.getDescription());
				result.setId(jobLog.getId());
				result.setJobName(jobLog.getJobName());
				result.setStartTime(jobLog.getStartTime());
				break;
			} else if (jobLog.getStatus() == JobStatus.RUNNING.value()) {
				result.setStatus(JobStatus.RUNNING.value());
				result.setDescription(jobLog.getDescription());
				result.setId(jobLog.getId());
				result.setJobName(jobLog.getJobName());
				result.setStartTime(jobLog.getStartTime());
			} else if (jobLog.getStatus() == JobStatus.SUCCESS.value()) {
				if (result.getStatus() != JobStatus.RUNNING.value()) {
					result.setStatus(JobStatus.SUCCESS.value());
				}
				result.setDescription(jobLog.getDescription());
				result.setId(jobLog.getId());
				result.setJobName(jobLog.getJobName());
			}
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * @return true if job is running or else.
	 */
	private boolean isRunningJob() {
		List<JobLog> jobLogs = jobLogService.getJobLogsByGroup();
		long numberOfJobs =  getRunningJobs (jobLogs);
		return numberOfJobs > 0 ;
	}
    
    /**
     * @return true if automatic job is running or else.
     */
    private boolean isRunningAutomaticJob() {
    	List<JobLog> jobLogs = jobLogService.getAutomaticJob();
    	long job = getRunningJobs (jobLogs);
    	return job > 0;
    }
    
    /**
     * @param <p>list of jobLogs</p>
     * @return number of jobs
     */
    private long getRunningJobs (List<JobLog> jobLogs) {
    	return jobLogs.stream().filter(p -> p.getStatus() == JobStatus.RUNNING.value() ).count() ;
    }
}

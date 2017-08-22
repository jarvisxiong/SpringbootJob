package com.cmcglobal.senseinfosys.service;

import java.util.List;

import com.cmcglobal.senseinfosys.model.JobLog;

/**
 * 
 * @author NVAn
 * File name：JobLogService.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                   (Comment)
 *   Rev 1.00   2017/07/27    JobLogServiceInterface      Create New
 */
public interface JobLogService {
	public JobLog getJobLogByJobKey(String jobKey);
	
	public List<JobLog> getJobLogsByGroup();
	
	public JobLog saveJobLog(JobLog jobLog);
	
	public List<JobLog> getAutomaticJob();
	
	public List<JobLog> getIncidentJobApi();
	
	public List<JobLog> getJobLogsWithoutAutoJob();
	
	public List<JobLog> getJobLogByGroupId(String groupId);
}

package com.cmcglobal.senseinfosys.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cmcglobal.senseinfosys.utils.JobStatus;
import com.cmcglobal.senseinfosys.utils.Utils;

/**
 * 
 * @author NVAn File name：BaseJob.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/27 BaseJob Create New
 */
public abstract class BaseJob implements Job {

	private JobExecutionContext mContext;

	private String groupId = null;
	
	private String jobKey = null;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		mContext = context;
		groupId = context.getJobDetail().getKey().getGroup();
		jobKey = context.getJobDetail().getKey().getName();
	}

	/**
	 * get data from other job with custom key
	 * 
	 * @param extraKey
	 * @return object
	 */
	public Object getExtra(String extraKey) {
		if (mContext != null) {
			return mContext.getJobDetail().getJobDataMap().get(extraKey);
		}
		return null;
	}

	/**
	 * Get mode is running current time (AUTO or MANUAL)
	 * see more {@link JobStatus}
	 * @return
	 */
	public int getMode(){
		return mContext.getJobDetail().getJobDataMap().getInt(Utils.MODE_KEY);
	}
	
	/**
	 * Getting current group is running
	 * @return current group
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 
	 * @return current key of job's
	 */
	public String getJobKey() {
		return jobKey;
	}

	public abstract void executeJob() throws JobExecutionException;
}

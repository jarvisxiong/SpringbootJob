package com.cmcglobal.senseinfosys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author NVAn
 * File name：JobLog.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)      (Comment)
 *   Rev 1.00   2017/07/27    JobLog        Create New
 */
@Entity
@Table(name = "\"JOBS_LOG\"")
public class JobLog {
	@Id
	@NotNull
	@Column(name = "\"ID\"")
	private String id;
	
	@NotNull
	@Column(name = "\"GROUP_ID\"")
	private String groupId;
	

	@Column(name = "\"JOB_NAME\"")
	@NotNull
	@Length(max = 100)
	private String jobName;
	
	@Column(name = "\"DESCRIPTION\"")
	private String description;

	@Column(name = "\"STATUS\"")
	@NotNull
	private int status;
	
	@Column(name = "\"START_TIME\"")
	@NotNull
	private Date startTime;
	
	@Column(name = "\"END_TIME\"")
	private Date endTime;
	
	@Column(name = "\"MODE\"")
	private int mode;

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the jobKey
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param jobKey the jobKey to set
	 */
	public void setId(String jobKey) {
		this.id = jobKey;
	}

	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the logDescription to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the logDate
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param logDate the logDate to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
}

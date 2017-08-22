package com.cmcglobal.senseinfosys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author NHLong File TmpAttachment.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/27 TmpAttachment Create New
 */

@Entity
@Table(name = "\"TMP_ATTACHMENT\"")
public class TmpAttachment {
	
	@Id
	@Column(name = "\"ID\"")
	private int id;
	
	@Column(name = "\"INCIDENT_CASE_ID\"")
	private int incidentCaseId;
	
	/**
	 * -1: status  FAILS
	 * 0: status NONE
	 * 1: status SUCCESS
	 */
	@Column(name = "\"STATUS\"")
	private int status; 
	
	@Column(name = "\"UPDATED_DATE\"")
	private Date updatedDate;

	public int getId() {
		return id;
	}

	public void setId(int attachmentId) {
		this.id = attachmentId;
	}

	public int getIncidentCaseId() {
		return incidentCaseId;
	}

	public void setIncidentCaseId(int incidentCaseId) {
		this.incidentCaseId = incidentCaseId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	} 
}

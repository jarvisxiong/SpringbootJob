package com.cmcglobal.senseinfosys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author NHLong File FileAttachment.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/26 FileAttachment Create New
 */

@Entity(name = "fileAttachment")
@Table(name = "\"INCIDENT_CASE_ATTACHMENT\"")
public class FileAttachment  implements Serializable{

	private static final long serialVersionUID = 8047028890419559155L;

	@Id
	@Column(name="\"ID\"")
	private int id;
	
	@Column(name="\"INCIDENT_CASE_ID\"")
	private int incidentCaseId;
	
	@Column(name="\"ATTACHMENT_TYPE\"")
	private String attachmentType;
	
	@Column(name="\"FILE_NAME\"")
	private String fileName;
	
	@Column(name="\"EXTERNAL_ID\"")
	private String externalId;
	
	@Column(name="\"ATTACHMENT_INDEX\"")
	private int attachmentIndex;
	
	@Column(name="\"FILE_CONTENT\"")
	private String fileContent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIncidentCaseId() {
		return incidentCaseId;
	}

	public void setIncidentCaseId(int incidentCaseId) {
		this.incidentCaseId = incidentCaseId;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public int getAttachmentIndex() {
		return attachmentIndex;
	}

	public void setAttachmentIndex(int attachmentIndex) {
		this.attachmentIndex = attachmentIndex;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
}

package com.cmcglobal.senseinfosys.utils;

/**
 * 
 * @author NHLong File JobStatus.java
 * 
 *         <MODIFICATION HISTORY> (Rev.) (Date) (ID/NAME) (Comment) Rev 1.00
 *         2017/07/26 JobStatus Create New
 */

public enum JobStatus {

	/**
	 * Job is running
	 */
	RUNNING(2, "Running"),

	/**
	 * Job executed success
	 */
	SUCCESS(1, "Success"),

	/**
	 * Job executed with error
	 */
	FAILURE(-1, "Failure"),

	/**
	 * Job is running with AUTO mode
	 */
	AUTO(10, "Auto"),

	/**
	 * Job is running with MANUAL mode
	 */
	MANUAL(11, "Manual");

	private final int value;

	private final String description;

	JobStatus(int value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the Description of this status code.
	 */
	public String getDescription() {
		return this.description;
	}
}

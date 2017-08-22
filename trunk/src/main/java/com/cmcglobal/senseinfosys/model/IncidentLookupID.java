package com.cmcglobal.senseinfosys.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author NVAn
 * File name：IncidentLookupID.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                      (Comment)
 *   Rev 1.00   2015/07/27    IncidentLookupID Model        Create New
 */
@Embeddable
public class IncidentLookupID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String type;
	String value;
	
	public IncidentLookupID() {
		
	}
	
	public IncidentLookupID(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
}

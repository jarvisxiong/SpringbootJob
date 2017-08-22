package com.cmcglobal.senseinfosys.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * 
 * @author NVAn
 * File name：IncidentLookup.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)                   (Comment)
 *   Rev 1.00   2015/07/27    IncidentLookupModel        Create New
 */
@Entity
@Table(name = "\"INCIDENT_LOOKUP\"")
@IdClass(IncidentLookupID.class)
public class IncidentLookup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "\"TYPE\"", nullable = false)
	@Length(max = 50)
	@JsonInclude(Include.NON_NULL)
	String type;
	
	@Id
	@NotNull
	@Column(name = "\"VALUE\"", nullable = false)
	@Length(max = 200)
	@JsonInclude(Include.NON_NULL)
    String value;
    
	@Column(name = "\"CATLEVEL\"")
    double catLevel;
    
	@Column(name = "\"DESCRIPTION\"")
	@Length(max = 500)
    String description;

	/**
	 * @return the type
	 */
	@JsonGetter("type")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	@JsonSetter("type")
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	@JsonGetter("value")
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	@JsonSetter("value")
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the catlevel
	 */
	@JsonGetter("cat_level")
	public double getCatLevel() {
		return catLevel;
	}

	/**
	 * @param catLevel the catlevel to set
	 */
	@JsonSetter("cat_level")
	public void setCatlevel(double catLevel) {
		this.catLevel = catLevel;
	}

	/**
	 * @return the description
	 */
	@JsonGetter("description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	@JsonSetter("description")
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof IncidentLookup)) {
			return false;
		}

		IncidentLookup incidentLookup = (IncidentLookup) obj;
		return incidentLookup.getType().equals(type) && incidentLookup.getValue().equals(value)
			&& Double.compare(incidentLookup.getCatLevel(), catLevel) == 0
			&& incidentLookup.getDescription().equals(description);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

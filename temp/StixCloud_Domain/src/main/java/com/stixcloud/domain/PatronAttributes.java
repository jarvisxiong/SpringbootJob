package com.stixcloud.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "PATRON_ATTRIBUTES")
public class PatronAttributes {

	private Long patronProfileId;
	private String attributes;
	private OffsetDateTime createddate;
	private Long createdBy;
	private OffsetDateTime updateddate;
	private Long updatedBy;
	
	public PatronAttributes(){
	}
	
	public PatronAttributes(Long patronProfileId, String attributes, OffsetDateTime createddate, Long createdBy, OffsetDateTime updateddate, Long updatedBy){
		this.patronProfileId = patronProfileId;
		this.attributes = attributes;
		this.createddate = createddate;
		this.createdBy = createdBy;
		this.updateddate = updateddate;
		this.updatedBy = updatedBy;
	}
	
	@Id
	@Column(name = "PATRON_PROFILE_ID", nullable = false, precision = 0)
	public Long getPatronProfileId() {
		return patronProfileId;
	}
	public void setPatronProfileId(Long patronProfileId) {
		this.patronProfileId = patronProfileId;
	}
	
	@Column(name = "ATTRIBUTES", nullable = true, length = 1000)
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	@Column(name = "CREATEDDATE", nullable = false)
	public OffsetDateTime getCreateddate() {
		return createddate;
	}
	public void setCreateddate(OffsetDateTime createddate) {
		this.createddate = createddate;
	}
	
	@Column(name = "CREATED_BY", nullable = false, precision = 0)
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "UPDATEDDATE", nullable = true)
	public OffsetDateTime getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(OffsetDateTime updateddate) {
		this.updateddate = updateddate;
	}
	
	@Column(name = "UPDATED_BY", nullable = true, precision = 0)
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(patronProfileId,attributes,createddate,createdBy,updateddate,updatedBy);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
		   return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
		   return false;
		}
		PatronAttributes that = (PatronAttributes) obj;
		return Objects.equals(this.patronProfileId, that.patronProfileId)
				&& Objects.equals(this.attributes, that.attributes);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("patronProfileId", this.patronProfileId)
				.append("attributes", this.attributes)
				.append("createdBy", this.createdBy)
				.append("createddate",this.createddate)
				.append("updatedBy",this.updatedBy)
				.append("updateddate",this.updateddate).toString();
	}
	
	
}

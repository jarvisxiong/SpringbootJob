package com.stixcloud.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Created by sengkai on 12/1/2016.
 */
@Entity
@Table(name = "PATRON_ADVANCE_PROFILE")
public class PatronAdvanceProfile {
  private Long patronadvancedprofileid;
  private OffsetDateTime anniversary;
  private OffsetDateTime dob;
  private Long noofchildren;
  private String designation;
  private Long raceMctId;
  private Long religionMctId;
  private Long educationalLevelMctId;
  private Long incomeLevelMctId;
  private Long maritalStatusMctId;
  private Long industryMctId;
  private Long patronProfileId;

  public PatronAdvanceProfile() {

  }
  
  /**
   * 
   * @param patronProfileId
   * @param dob
   * @param designation
   */
  public PatronAdvanceProfile(Long patronProfileId, OffsetDateTime dob, String designation) {
	  this.patronProfileId = patronProfileId;
	  this.dob = dob;
	  this.designation = designation;
  }
  
  /**
   * create PatronAdvanceProfile
   * @param patronadvancedprofileid
   * @param anniversary
   * @param dob
   * @param noofchildren
   * @param designation
   * @param raceMctId
   * @param religionMctId
   * @param educationalLevelMctId
   * @param incomeLevelMctId
   * @param maritalStatusMctId
   * @param industryMctId
   * @param patronProfileId
   */
  public PatronAdvanceProfile(Long patronadvancedprofileid, OffsetDateTime anniversary,
                              OffsetDateTime dob, Long noofchildren, String designation,
                              Long raceMctId, Long religionMctId,
                              Long educationalLevelMctId, Long incomeLevelMctId,
                              Long maritalStatusMctId, Long industryMctId,
                              Long patronProfileId) {
    this.patronadvancedprofileid = patronadvancedprofileid;
    this.anniversary = anniversary;
    this.dob = dob;
    this.noofchildren = noofchildren;
    this.designation = designation;
    this.raceMctId = raceMctId;
    this.religionMctId = religionMctId;
    this.educationalLevelMctId = educationalLevelMctId;
    this.incomeLevelMctId = incomeLevelMctId;
    this.maritalStatusMctId = maritalStatusMctId;
    this.industryMctId = industryMctId;
    this.patronProfileId = patronProfileId;
  }

  public PatronAdvanceProfile(OffsetDateTime anniversary, OffsetDateTime dob,
      Long noofchildren, String designation, Long raceMctId, Long religionMctId,
      Long educationalLevelMctId, Long incomeLevelMctId, Long maritalStatusMctId,
      Long industryMctId, Long patronProfileId) {
    this.anniversary = anniversary;
    this.dob = dob;
    this.noofchildren = noofchildren;
    this.designation = designation;
    this.raceMctId = raceMctId;
    this.religionMctId = religionMctId;
    this.educationalLevelMctId = educationalLevelMctId;
    this.incomeLevelMctId = incomeLevelMctId;
    this.maritalStatusMctId = maritalStatusMctId;
    this.industryMctId = industryMctId;
    this.patronProfileId = patronProfileId;
  }
  
  @Id
  @Column(name = "PATRONADVANCEDPROFILEID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "patronAdvanceProfileSeq")
  @GenericGenerator(name = "patronAdvanceProfileSeq",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "PATRON_ADVANCE_PROFILE_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")})
  public Long getPatronadvancedprofileid() {
    return patronadvancedprofileid;
  }

  public void setPatronadvancedprofileid(Long patronadvancedprofileid) {
    this.patronadvancedprofileid = patronadvancedprofileid;
  }

  @Column(name = "ANNIVERSARY", nullable = true)
  public OffsetDateTime getAnniversary() {
    return anniversary;
  }

  public void setAnniversary(OffsetDateTime anniversary) {
    this.anniversary = anniversary;
  }

  @Column(name = "DOB", nullable = true)
  public OffsetDateTime getDob() {
    return dob;
  }

  public void setDob(OffsetDateTime dob) {
    this.dob = dob;
  }

  @Column(name = "NOOFCHILDREN", nullable = true, precision = 0)
  public Long getNoofchildren() {
    return noofchildren;
  }

  public void setNoofchildren(Long noofchildren) {
    this.noofchildren = noofchildren;
  }

  @Column(name = "DESIGNATION", nullable = true, length = 255)
  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  @Column(name = "RACE_MCT_ID", nullable = true, precision = 0)
  public Long getRaceMctId() {
    return raceMctId;
  }

  public void setRaceMctId(Long raceMctId) {
    this.raceMctId = raceMctId;
  }

  @Column(name = "RELIGION_MCT_ID", nullable = true, precision = 0)
  public Long getReligionMctId() {
    return religionMctId;
  }

  public void setReligionMctId(Long religionMctId) {
    this.religionMctId = religionMctId;
  }

  @Column(name = "EDUCATIONAL_LEVEL_MCT_ID", nullable = true, precision = 0)
  public Long getEducationalLevelMctId() {
    return educationalLevelMctId;
  }

  public void setEducationalLevelMctId(Long educationalLevelMctId) {
    this.educationalLevelMctId = educationalLevelMctId;
  }

  @Column(name = "INCOME_LEVEL_MCT_ID", nullable = true, precision = 0)
  public Long getIncomeLevelMctId() {
    return incomeLevelMctId;
  }

  public void setIncomeLevelMctId(Long incomeLevelMctId) {
    this.incomeLevelMctId = incomeLevelMctId;
  }

  @Column(name = "MARITAL_STATUS_MCT_ID", nullable = true, precision = 0)
  public Long getMaritalStatusMctId() {
    return maritalStatusMctId;
  }

  public void setMaritalStatusMctId(Long maritalStatusMctId) {
    this.maritalStatusMctId = maritalStatusMctId;
  }

  @Column(name = "INDUSTRY_MCT_ID", nullable = true, precision = 0)
  public Long getIndustryMctId() {
    return industryMctId;
  }

  public void setIndustryMctId(Long industryMctId) {
    this.industryMctId = industryMctId;
  }

  @Column(name = "PATRON_PROFILE_ID", nullable = false, precision = 0)
  public Long getPatronProfileId() {
    return patronProfileId;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatronAdvanceProfile that = (PatronAdvanceProfile) o;
    return Objects.equals(patronadvancedprofileid, that.patronadvancedprofileid) &&
        Objects.equals(anniversary, that.anniversary) &&
        Objects.equals(dob, that.dob) &&
        Objects.equals(noofchildren, that.noofchildren) &&
        Objects.equals(designation, that.designation) &&
        Objects.equals(raceMctId, that.raceMctId) &&
        Objects.equals(religionMctId, that.religionMctId) &&
        Objects.equals(educationalLevelMctId, that.educationalLevelMctId) &&
        Objects.equals(incomeLevelMctId, that.incomeLevelMctId) &&
        Objects.equals(maritalStatusMctId, that.maritalStatusMctId) &&
        Objects.equals(industryMctId, that.industryMctId) &&
        Objects.equals(patronProfileId, that.patronProfileId);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(patronadvancedprofileid, anniversary, dob, noofchildren, designation, raceMctId,
            religionMctId, educationalLevelMctId, incomeLevelMctId, maritalStatusMctId,
            industryMctId,
            patronProfileId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("patronadvancedprofileid", patronadvancedprofileid)
        .append("anniversary", anniversary)
        .append("dob", dob)
        .append("noofchildren", noofchildren)
        .append("designation", designation)
        .append("raceMctId", raceMctId)
        .append("religionMctId", religionMctId)
        .append("educationalLevelMctId", educationalLevelMctId)
        .append("incomeLevelMctId", incomeLevelMctId)
        .append("maritalStatusMctId", maritalStatusMctId)
        .append("industryMctId", industryMctId)
        .append("patronProfileId", patronProfileId)
        .toString();
  }
}

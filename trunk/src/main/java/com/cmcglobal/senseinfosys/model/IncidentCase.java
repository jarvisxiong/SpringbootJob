package com.cmcglobal.senseinfosys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author NVAn
 * File name：IncidentCase.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)      (Comment)
 *   Rev 1.00   2017/07/27    IncidentCase   Create New
 */
@Entity
@Table(name = "\"INCIDENT_CASE\"")
public class IncidentCase {
	
	@Id
	@Column(name="\"ID\"")
	private int id;

	@Column(name="\"PRECEDENCE\"")
	private String precedence;

	@Column(name="\"INCIDENT_TYPE\"")
	private String incident_type;

	@Column(name="\"TIMEZONE\"")
	private String timezone;

	@Column(name="\"TIME_OF_INCIDENT\"")
	private Date time_of_incident;

	@Column(name="\"SHIP_NAME\"")
	private String ship_name;

	@Column(name="\"SHIP_CALLSIGN\"")
	private String ship_callsign;

	@Column(name="\"SHIP_FLAG\"")
	private String ship_flag;

	@Column(name="\"SHIP_TYPE\"")
	private String ship_type;

	@Column(name="\"SHIP_GROSS_TONNAGE\"")
	private String ship_gross_tonnage;

	@Column(name="\"SHIP_IMO_NUMBER\"")
	private String ship_imo_number;

	@Column(name="\"SHIP_MMSI_NUMBER\"")
	private String ship_mmsi_number;

	@Column(name="\"SHIP_BUILT\"")
	private String ship_built;

	@Column(name="\"SHIP_NAME_OF_COMPANY\"")
	private String ship_name_of_company;

	@Column(name="\"POSITION_SEA_NAME\"")
	private String position_sea_name;

	@Column(name="\"POSITION_LATITUDE\"")
	private double position_latitude;

	@Column(name="\"POSITION_LONGITUDE\"")
	private double position_longitude;

	@Column(name="\"POSITION_AREA_DESCRIPTION\"")
	private String position_area_description;

	@Column(name="\"POSITION_SHIP_ACTIVITY\"")
	private String position_ship_activity;

	@Column(name="\"POSITION_ISPS_LEVEL\"")
	private String position_isps_level;

	@Column(name="\"DETAILS_ATTACK_METHOD\"")
	private String details_attack_method;

	@Column(name="\"DETAILS_DESCRIPTION_SUSPECT_CRAFT\"")
	private String Stringdetails_description_suspect_craft;

	@Column(name="\"DETAILS_NUMBER_PIRATES\"")
	private String details_number_pirates;

	@Column(name="\"DETAILS_TYPE_OF_WEAPONS\"")
	private String details_type_of_weapons;

	@Column(name="\"DETAILS_TREATMENT_INJURES\"")
	private String details_treatment_injures;

	@Column(name="\"DETAILS_DAMAGES\"")
	private String details_damages;

	@Column(name="\"DETAILS_OTHER_INFORMATION\"")
	private String details_other_information;

	@Column(name="\"DETAILS_STOLEN_CARGO\"")
	private String details_stolen_cargo;

	@Column(name="\"DETAILS_REPORT_TO_AUTHORITIES\"")
	private String details_report_to_authorities;

	@Column(name="\"DETAILS_ACTION_TAKEN_BY_MASTER\"")
	private String details_action_taken_by_master;

	@Column(name="\"DETAILS_ACTION_TAKEN_BY_COASTAL_STATE\"")
	private String details_action_taken_by_coastal_state;

	@Column(name="\"DETAILS_SOURCE_INFORMATION\"")
	private String details_source_information;

	@Column(name="\"DETAILS_REMARKS\"")
	private String details_remarks;

	@Column(name="\"SIGNIFICANCE_LEVEL\"")
	private String significance_level;

	@Column(name="\"SMS_TO_ALL_FOCAL_POINTS\"")
	private String sms_to_all_focal_points;

	@Column(name="\"STATUS\"")
	private String status;

	@Column(name="\"INCIDENT_NO\"")
	private String incident_no;

	@Column(name="\"RELATED_ID\"")
	private int related_id;

	@Column(name="\"CREATED_DATE\"")
	private Date created_date;

	@Column(name="\"CREATED_BY\"")
	private String created_by;

	@Column(name="\"UPDATED_DATE\"")
	private Date updated_date;

	@Column(name="\"UPDATED_BY\"")
	private String updated_by;

	@Column(name="\"LOCAL_TIME\"")
	private String local_time;

	@Column(name="\"POSITION_LONGITUDE_DEGREE\"")
	private double position_longitude_degree;

	@Column(name="\"POSITION_LONGITUDE_MINUTE\"")
	private double position_longitude_minute;

	@Column(name="\"POSITION_LONGITUDE_DIRECTION\"")
	private String position_longitude_direction;

	@Column(name="\"POSITION_LATITUDE_DEGREE\"")
	private double position_latitude_degree;

	@Column(name="\"POSITION_LATITUDE_MINUTE\"")
	private double position_latitude_minute;

	@Column(name="\"POSITION_LATITUDE_DIRECTION\"")
	private String position_latitude_direction;

	@Column(name="\"SHIP_INMARSAT_ID\"")
	private String ship_inmarsat_id;

	@Column(name="\"TREATMENT_INJURIES\"")
	private String treatment_injuries;

	@Column(name="\"NUMBER_OF_PIRATES\"")
	private String number_of_pirates;

	@Column(name="\"STOLEN_CARGO\"")
	private String stolen_cargo;

	@Column(name="\"WEAPONS_USED\"")
	private String weapons_used;

	@Column(name="\"ISC_COMMENTS\"")
	private String isc_comments;

	@Column(name="\"COASTAL_STATE\"")
	private String coastal_state;

	@Column(name="\"FLAG_STATE\"")
	private String flag_state;

	@Column(name="\"LAST_PORT_OF_CALL\"")
	private String last_port_of_call;
	
	@Column(name="\"NEXT_PORT_OF_CALL\"")
	private String next_port_of_call;
	
	@Column(name="\"UPDATED_FLAG\"")
	private String updated_flag;
	
	@Column(name="\"LAST_PORT_OF_CALL_DESC\"")
	private String last_port_of_call_desc;
	
	@Column(name="\"NEXT_PORT_OF_CALL_DESC\"")
	private String next_port_of_call_desc;

	@Column(name="\"VOYAGE_TYPE\"")
	private String voyage_type;

	@Column(name="\"INCIDENT_CASE_NAME\"")
	private String incident_case_name;

	@Column(name="\"INCIDENT_CASE_DESCRIPTION\"")
	private String incident_case_description;

	@Column(name="\"NOT_COUNTED\"")
	private String not_counted;

	@Column(name="\"NUMBER_OF_SUSPECT_CRAFT\"")
	private String number_of_suspect_craft;

	@Column(name="\"LINKED_IR\"")
	private String linked_ir;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrecedence() {
		return precedence;
	}

	public void setPrecedence(String precedence) {
		this.precedence = precedence;
	}

	public String getIncident_type() {
		return incident_type;
	}

	public void setIncident_type(String incident_type) {
		this.incident_type = incident_type;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Date getTime_of_incident() {
		return time_of_incident;
	}

	public void setTime_of_incident(Date time_of_incident) {
		this.time_of_incident = time_of_incident;
	}

	public String getShip_name() {
		return ship_name;
	}

	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}

	public String getShip_callsign() {
		return ship_callsign;
	}

	public void setShip_callsign(String ship_callsign) {
		this.ship_callsign = ship_callsign;
	}

	public String getShip_flag() {
		return ship_flag;
	}

	public void setShip_flag(String ship_flag) {
		this.ship_flag = ship_flag;
	}

	public String getShip_type() {
		return ship_type;
	}

	public void setShip_type(String ship_type) {
		this.ship_type = ship_type;
	}

	public String getShip_gross_tonnage() {
		return ship_gross_tonnage;
	}

	public void setShip_gross_tonnage(String ship_gross_tonnage) {
		this.ship_gross_tonnage = ship_gross_tonnage;
	}

	public String getShip_imo_number() {
		return ship_imo_number;
	}

	public void setShip_imo_number(String ship_imo_number) {
		this.ship_imo_number = ship_imo_number;
	}

	public String getShip_mmsi_number() {
		return ship_mmsi_number;
	}

	public void setShip_mmsi_number(String ship_mmsi_number) {
		this.ship_mmsi_number = ship_mmsi_number;
	}

	public String getShip_built() {
		return ship_built;
	}

	public void setShip_built(String ship_built) {
		this.ship_built = ship_built;
	}

	public String getShip_name_of_company() {
		return ship_name_of_company;
	}

	public void setShip_name_of_company(String ship_name_of_company) {
		this.ship_name_of_company = ship_name_of_company;
	}

	public String getPosition_sea_name() {
		return position_sea_name;
	}

	public void setPosition_sea_name(String position_sea_name) {
		this.position_sea_name = position_sea_name;
	}

	public double getPosition_latitude() {
		return position_latitude;
	}

	public void setPosition_latitude(double position_latitude) {
		this.position_latitude = position_latitude;
	}

	public double getPosition_longitude() {
		return position_longitude;
	}

	public void setPosition_longitude(double position_longitude) {
		this.position_longitude = position_longitude;
	}

	public String getPosition_area_description() {
		return position_area_description;
	}

	public void setPosition_area_description(String position_area_description) {
		this.position_area_description = position_area_description;
	}

	public String getPosition_ship_activity() {
		return position_ship_activity;
	}

	public void setPosition_ship_activity(String position_ship_activity) {
		this.position_ship_activity = position_ship_activity;
	}

	public String getPosition_isps_level() {
		return position_isps_level;
	}

	public void setPosition_isps_level(String position_isps_level) {
		this.position_isps_level = position_isps_level;
	}

	public String getDetails_attack_method() {
		return details_attack_method;
	}

	public void setDetails_attack_method(String details_attack_method) {
		this.details_attack_method = details_attack_method;
	}

	public String getStringdetails_description_suspect_craft() {
		return Stringdetails_description_suspect_craft;
	}

	public void setStringdetails_description_suspect_craft(String stringdetails_description_suspect_craft) {
		Stringdetails_description_suspect_craft = stringdetails_description_suspect_craft;
	}

	public String getDetails_number_pirates() {
		return details_number_pirates;
	}

	public void setDetails_number_pirates(String details_number_pirates) {
		this.details_number_pirates = details_number_pirates;
	}

	public String getDetails_type_of_weapons() {
		return details_type_of_weapons;
	}

	public void setDetails_type_of_weapons(String details_type_of_weapons) {
		this.details_type_of_weapons = details_type_of_weapons;
	}

	public String getDetails_treatment_injures() {
		return details_treatment_injures;
	}

	public void setDetails_treatment_injures(String details_treatment_injures) {
		this.details_treatment_injures = details_treatment_injures;
	}

	public String getDetails_damages() {
		return details_damages;
	}

	public void setDetails_damages(String details_damages) {
		this.details_damages = details_damages;
	}

	public String getDetails_other_information() {
		return details_other_information;
	}

	public void setDetails_other_information(String details_other_information) {
		this.details_other_information = details_other_information;
	}

	public String getDetails_stolen_cargo() {
		return details_stolen_cargo;
	}

	public void setDetails_stolen_cargo(String details_stolen_cargo) {
		this.details_stolen_cargo = details_stolen_cargo;
	}

	public String getDetails_report_to_authorities() {
		return details_report_to_authorities;
	}

	public void setDetails_report_to_authorities(String details_report_to_authorities) {
		this.details_report_to_authorities = details_report_to_authorities;
	}

	public String getDetails_action_taken_by_master() {
		return details_action_taken_by_master;
	}

	public void setDetails_action_taken_by_master(String details_action_taken_by_master) {
		this.details_action_taken_by_master = details_action_taken_by_master;
	}

	public String getDetails_action_taken_by_coastal_state() {
		return details_action_taken_by_coastal_state;
	}

	public void setDetails_action_taken_by_coastal_state(String details_action_taken_by_coastal_state) {
		this.details_action_taken_by_coastal_state = details_action_taken_by_coastal_state;
	}

	public String getDetails_source_information() {
		return details_source_information;
	}

	public void setDetails_source_information(String details_source_information) {
		this.details_source_information = details_source_information;
	}

	public String getDetails_remarks() {
		return details_remarks;
	}

	public void setDetails_remarks(String details_remarks) {
		this.details_remarks = details_remarks;
	}

	public String getSignificance_level() {
		return significance_level;
	}

	public void setSignificance_level(String significance_level) {
		this.significance_level = significance_level;
	}

	public String getSms_to_all_focal_points() {
		return sms_to_all_focal_points;
	}

	public void setSms_to_all_focal_points(String sms_to_all_focal_points) {
		this.sms_to_all_focal_points = sms_to_all_focal_points;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIncident_no() {
		return incident_no;
	}

	public void setIncident_no(String incident_no) {
		this.incident_no = incident_no;
	}

	public int getRelated_id() {
		return related_id;
	}

	public void setRelated_id(int related_id) {
		this.related_id = related_id;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getLocal_time() {
		return local_time;
	}

	public void setLocal_time(String local_time) {
		this.local_time = local_time;
	}

	public double getPosition_longitude_degree() {
		return position_longitude_degree;
	}

	public void setPosition_longitude_degree(double position_longitude_degree) {
		this.position_longitude_degree = position_longitude_degree;
	}

	public double getPosition_longitude_minute() {
		return position_longitude_minute;
	}

	public void setPosition_longitude_minute(double position_longitude_minute) {
		this.position_longitude_minute = position_longitude_minute;
	}

	public String getPosition_longitude_direction() {
		return position_longitude_direction;
	}

	public void setPosition_longitude_direction(String position_longitude_direction) {
		this.position_longitude_direction = position_longitude_direction;
	}

	public double getPosition_latitude_degree() {
		return position_latitude_degree;
	}

	public void setPosition_latitude_degree(double position_latitude_degree) {
		this.position_latitude_degree = position_latitude_degree;
	}

	public double getPosition_latitude_minute() {
		return position_latitude_minute;
	}

	public void setPosition_latitude_minute(double position_latitude_minute) {
		this.position_latitude_minute = position_latitude_minute;
	}

	public String getPosition_latitude_direction() {
		return position_latitude_direction;
	}

	public void setPosition_latitude_direction(String position_latitude_direction) {
		this.position_latitude_direction = position_latitude_direction;
	}

	public String getShip_inmarsat_id() {
		return ship_inmarsat_id;
	}

	public void setShip_inmarsat_id(String ship_inmarsat_id) {
		this.ship_inmarsat_id = ship_inmarsat_id;
	}

	public String getTreatment_injuries() {
		return treatment_injuries;
	}

	public void setTreatment_injuries(String treatment_injuries) {
		this.treatment_injuries = treatment_injuries;
	}

	public String getNumber_of_pirates() {
		return number_of_pirates;
	}

	public void setNumber_of_pirates(String number_of_pirates) {
		this.number_of_pirates = number_of_pirates;
	}

	public String getStolen_cargo() {
		return stolen_cargo;
	}

	public void setStolen_cargo(String stolen_cargo) {
		this.stolen_cargo = stolen_cargo;
	}

	public String getWeapons_used() {
		return weapons_used;
	}

	public void setWeapons_used(String weapons_used) {
		this.weapons_used = weapons_used;
	}

	public String getIsc_comments() {
		return isc_comments;
	}

	public void setIsc_comments(String isc_comments) {
		this.isc_comments = isc_comments;
	}

	public String getFlag_state() {
		return flag_state;
	}

	public void setFlag_state(String flag_state) {
		this.flag_state = flag_state;
	}

	public String getLast_port_of_call() {
		return last_port_of_call;
	}

	public void setLast_port_of_call(String last_port_of_call) {
		this.last_port_of_call = last_port_of_call;
	}

	public String getNext_port_of_call() {
		return next_port_of_call;
	}

	public void setNext_port_of_call(String next_port_of_call) {
		this.next_port_of_call = next_port_of_call;
	}

	public String getUpdated_flag() {
		return updated_flag;
	}

	public void setUpdated_flag(String updated_flag) {
		this.updated_flag = updated_flag;
	}

	public String getLast_port_of_call_desc() {
		return last_port_of_call_desc;
	}

	public void setLast_port_of_call_desc(String last_port_of_call_desc) {
		this.last_port_of_call_desc = last_port_of_call_desc;
	}

	public String getNext_port_of_call_desc() {
		return next_port_of_call_desc;
	}

	public void setNext_port_of_call_desc(String next_port_of_call_desc) {
		this.next_port_of_call_desc = next_port_of_call_desc;
	}

	public String getVoyage_type() {
		return voyage_type;
	}

	public void setVoyage_type(String voyage_type) {
		this.voyage_type = voyage_type;
	}

	public String getIncident_case_name() {
		return incident_case_name;
	}

	public void setIncident_case_name(String incident_case_name) {
		this.incident_case_name = incident_case_name;
	}

	public String getIncident_case_description() {
		return incident_case_description;
	}

	public void setIncident_case_description(String incident_case_description) {
		this.incident_case_description = incident_case_description;
	}

	public String getNot_counted() {
		return not_counted;
	}

	public void setNot_counted(String not_counted) {
		this.not_counted = not_counted;
	}

	public String getNumber_of_suspect_craft() {
		return number_of_suspect_craft;
	}

	public void setNumber_of_suspect_craft(String number_of_suspect_craft) {
		this.number_of_suspect_craft = number_of_suspect_craft;
	}

	public String getLinked_ir() {
		return linked_ir;
	}

	public void setLinked_ir(String linked_ir) {
		this.linked_ir = linked_ir;
	}

	public String getCoastal_state() {
		return coastal_state;
	}

	public void setCoastal_state(String coastal_state) {
		this.coastal_state = coastal_state;
	}

}

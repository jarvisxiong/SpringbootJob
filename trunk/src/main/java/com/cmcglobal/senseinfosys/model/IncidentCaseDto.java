package com.cmcglobal.senseinfosys.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

import org.postgresql.core.Utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author NVAn
 * File name：IncidentCaseDto.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)          (Comment)
 *   Rev 1.00   2017/07/27    IncidentCaseDto   Create New
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class IncidentCaseDto implements Serializable{
	
	private static final long serialVersionUID = -1815809687944606897L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int id;

	private String precedence;

	private String incident_type;

	private String timezone;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd-hhmmss")
	private Date time_of_incident;

	private String ship_name;

	private String ship_callsign;

	private String ship_flag;

	private String ship_type;

	private String ship_gross_tonnage;

	private String ship_imo_number;

	private String ship_mmsi_number;

	private String ship_built;

	private String ship_name_of_company;

	private String position_sea_name;

	private double position_latitude;

	private double position_longitude;

	private String position_area_description;

	private String position_ship_activity;

	private String position_isps_level;

	private String details_attack_method;

	private String details_description_suspect_craft;

	private String details_number_pirates;

	private String details_type_of_weapons;

	private String details_treatment_injures;

	private String details_damages;

	private String details_other_information;

	private String details_stolen_cargo;

	private String details_report_to_authorities;

	private String details_action_taken_by_master;

	private String details_action_taken_by_coastal_state;

	private String details_source_information;

	private String details_remarks;

	private String significance_level;

	private String sms_to_all_focal_points;

	private String status;

	private String incident_no;

	private int related_id;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd-hhmmss")
	private Date created_date;

	private String created_by;

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd-hhmmss")
	private Date updated_date;

	private String updated_by;

	private String local_time;
	
	private double position_longitude_degree;

	private double position_longitude_minute;

	private String position_longitude_direction;

	private double position_latitude_degree;

	private double position_latitude_minute;

	private String position_latitude_direction;

	private String ship_inmarsat_id;

	private String treatment_injuries;

	private String number_of_pirates;

	private String stolen_cargo;

	private String weapons_used;

	private String isc_comments;

	private String coastal_state;

	private String flag_state;

	private String last_port_of_call;
	
	private String next_port_of_call;
	
	private String updated_flag;
	
	private String last_port_of_call_desc;
	
	private String next_port_of_call_desc;

	private String voyage_type;

	private String incident_case_name;

	private String incident_case_description;

	private String not_counted;

	private String number_of_suspect_craft;
	
	private int[] attachments;
	
	private String linked_ir;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrecedence() {
		return precedence == null ? "" : precedence;
	}

	public void setPrecedence(String precedence) {
		this.precedence = precedence == null ? "" : precedence;
	}

	public String getIncident_type() {
		return incident_type == null ? "" : incident_type;
	}

	public void setIncident_type(String incident_type) {
		this.incident_type = incident_type == null ? "" : incident_type;
	}

	public String getTimezone() {
		return timezone == null ? "" : timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone == null ? "" : timezone;
	}

	public Date getTime_of_incident() {
		return time_of_incident;
	}

	public void setTime_of_incident(Date time_of_incident) {
		this.time_of_incident = time_of_incident;
	}

	public String getShip_name() {
		return ship_name == null ? "" : ship_name;
	}

	public void setShip_name(String ship_name) {
		this.ship_name = ship_name == null ? "" : ship_name;
	}

	public String getShip_callsign() {
		return ship_callsign == null ? "" : ship_callsign;
	}

	public void setShip_callsign(String ship_callsign) {
		this.ship_callsign = ship_callsign == null ? "" : ship_callsign;
	}

	public String getShip_flag() {
		return ship_flag == null ? "" : ship_flag;
	}

	public void setShip_flag(String ship_flag) {
		this.ship_flag = ship_flag == null ? "" : ship_flag;
	}

	public String getShip_type() {
		return ship_type == null ? "" : ship_type;
	}

	public void setShip_type(String ship_type) {
		this.ship_type = ship_type == null ? "" : ship_type;
	}

	public String getShip_gross_tonnage() {
		return ship_gross_tonnage == null ? "" : ship_gross_tonnage;
	}

	public void setShip_gross_tonnage(String ship_gross_tonnage) {
		this.ship_gross_tonnage = ship_gross_tonnage == null ? "" : ship_gross_tonnage;
	}

	public String getShip_imo_number() {
		return ship_imo_number == null ? "" : ship_imo_number;
	}

	public void setShip_imo_number(String ship_imo_number) {
		this.ship_imo_number = ship_imo_number == null ? "" : ship_imo_number;
	}

	public String getShip_mmsi_number() {
		return ship_mmsi_number  == null ? "" : ship_mmsi_number;
	}

	public void setShip_mmsi_number(String ship_mmsi_number) {
		this.ship_mmsi_number = ship_mmsi_number == null ? "" : ship_mmsi_number;
	}

	public String getShip_built() {
		return ship_built == null ? "" : ship_built;
	}

	public void setShip_built(String ship_built) {
		this.ship_built = ship_built == null ? "" : ship_built;
	}

	public String getShip_name_of_company() {
		return ship_name_of_company == null ? "" : ship_name_of_company;
	}

	public void setShip_name_of_company(String ship_name_of_company) {
		this.ship_name_of_company = ship_name_of_company == null ? "" : ship_name_of_company;
	}

	public String getPosition_sea_name() {
		return position_sea_name == null ? "" : position_sea_name;
	}

	public void setPosition_sea_name(String position_sea_name) {
		this.position_sea_name = position_sea_name == null ? "" : position_sea_name;
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
		return position_area_description == null ? "" : position_area_description ;
	}

	public void setPosition_area_description(String position_area_description) {
		this.position_area_description = position_area_description == null ? "" : position_area_description ;
	}

	public String getPosition_ship_activity() {
		return position_ship_activity == null ? "" : position_ship_activity ;
	}

	public void setPosition_ship_activity(String position_ship_activity) {
		this.position_ship_activity = position_ship_activity == null ? "" : position_ship_activity ;
	}

	public String getPosition_isps_level() {
		return position_isps_level == null ? "" : position_isps_level ;
	}

	public void setPosition_isps_level(String position_isps_level) {
		this.position_isps_level = position_isps_level == null ? "" : position_isps_level ;
	}

	public String getDetails_attack_method() {
		return details_attack_method == null ? "" : details_attack_method ;
	}

	public void setDetails_attack_method(String details_attack_method) {
		this.details_attack_method = details_attack_method == null ? "" : details_attack_method ;
	}

	public String getDetails_description_suspect_craft() {
		return details_description_suspect_craft == null ? "" : details_description_suspect_craft ;
	}

	public void setDetails_description_suspect_craft(String details_description_suspect_craft) {
		this.details_description_suspect_craft = details_description_suspect_craft == null ? "" : details_description_suspect_craft ;
	}

	public String getDetails_number_pirates() {
		return details_number_pirates == null ? "" : details_number_pirates;
	}

	public void setDetails_number_pirates(String details_number_pirates) {
		this.details_number_pirates = details_number_pirates == null ? "" : details_number_pirates;
	}

	public String getDetails_type_of_weapons() {
		return details_type_of_weapons == null ? "" : details_type_of_weapons;
	}

	public void setDetails_type_of_weapons(String details_type_of_weapons) {
		this.details_type_of_weapons = details_type_of_weapons == null ? "" : details_type_of_weapons;
	}

	public String getDetails_treatment_injures() {
		return details_treatment_injures == null ? "" : details_treatment_injures;
	}

	public void setDetails_treatment_injures(String details_treatment_injures) {
		this.details_treatment_injures = details_treatment_injures == null ? "" : details_treatment_injures;
	}

	public String getDetails_damages() {
		return details_damages == null ? "" : details_damages;
	}

	public void setDetails_damages(String details_damages) {
		this.details_damages = details_damages == null ? "" : details_damages;
	}

	public String getDetails_other_information() {
		return details_other_information == null ? "" : details_other_information;
	}

	public void setDetails_other_information(String details_other_information) {
		this.details_other_information = details_other_information == null ? "" : details_other_information;
	}

	public String getDetails_stolen_cargo() {
		return details_stolen_cargo == null ? "" : details_stolen_cargo;
	}

	public void setDetails_stolen_cargo(String details_stolen_cargo) {
		this.details_stolen_cargo = details_stolen_cargo == null ? "" : details_stolen_cargo;
	}

	public String getDetails_report_to_authorities() {
		return details_report_to_authorities == null ? "" : details_report_to_authorities;
	}

	public void setDetails_report_to_authorities(String details_report_to_authorities) {
		this.details_report_to_authorities = details_report_to_authorities == null ? "" : details_report_to_authorities;
	}

	public String getDetails_action_taken_by_master() {
		return details_action_taken_by_master == null ? "" : details_action_taken_by_master;
	}

	public void setDetails_action_taken_by_master(String details_action_taken_by_master) {
		this.details_action_taken_by_master = details_action_taken_by_master == null ? "" : details_action_taken_by_master;
	}

	public String getDetails_action_taken_by_coastal_state() {
		return details_action_taken_by_coastal_state == null ? "" : details_action_taken_by_coastal_state;
	}

	public void setDetails_action_taken_by_coastal_state(String details_action_taken_by_coastal_state) {
		this.details_action_taken_by_coastal_state = details_action_taken_by_coastal_state == null ? "" : details_action_taken_by_coastal_state;
	}

	public String getDetails_source_information() {
		return details_source_information == null ? "" : details_source_information;
	}

	public void setDetails_source_information(String details_source_information) {
		this.details_source_information = details_source_information == null ? "" : details_source_information;
	}

	public String getDetails_remarks() {
		return details_remarks == null ? "" : details_remarks;
	}

	public void setDetails_remarks(String details_remarks) {
		this.details_remarks = details_remarks == null ? "" : details_remarks;
	}

	public String getSignificance_level() {
		return significance_level == null ? "" : significance_level;
	}

	public void setSignificance_level(String significance_level) {
		this.significance_level = significance_level == null ? "" : significance_level;
	}

	public String getSms_to_all_focal_points() {
		return sms_to_all_focal_points == null ? "" : sms_to_all_focal_points;
	}

	public void setSms_to_all_focal_points(String sms_to_all_focal_points) {
		this.sms_to_all_focal_points = sms_to_all_focal_points == null ? "" : sms_to_all_focal_points;
	}

	public String getStatus() {
		return status == null ? "" : status;
	}

	public void setStatus(String status) {
		this.status = status == null ? "" : status;
	}

	public String getIncident_no() {
		return incident_no == null ? "" : incident_no;
	}

	public void setIncident_no(String incident_no) {
		this.incident_no = incident_no == null ? "" : incident_no;
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
		return created_by == null ?  com.cmcglobal.senseinfosys.utils.Utils.EMPTY : created_by ; 
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
		return updated_by == null ?  com.cmcglobal.senseinfosys.utils.Utils.EMPTY : updated_by ; 
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getLocal_time() {
		return local_time == null ? "" : local_time ;
	}

	public void setLocal_time(String local_time) {
		this.local_time = local_time == null ? "" : local_time ;
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
		return position_longitude_direction == null ? "" : position_longitude_direction;
	}

	public void setPosition_longitude_direction(String position_longitude_direction) {
		this.position_longitude_direction = position_longitude_direction == null ? "" : position_longitude_direction;
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
		return position_latitude_direction == null ? "" : position_latitude_direction;
	}

	public void setPosition_latitude_direction(String position_latitude_direction) {
		this.position_latitude_direction = position_latitude_direction == null ? "" : position_latitude_direction;
	}

	public String getShip_inmarsat_id() {
		return ship_inmarsat_id == null ? "" : ship_inmarsat_id;
	}

	public void setShip_inmarsat_id(String ship_inmarsat_id) {
		this.ship_inmarsat_id = ship_inmarsat_id == null ? "" : ship_inmarsat_id;
	}

	public String getTreatment_injuries() {
		return treatment_injuries == null ? "" : treatment_injuries;
	}

	public void setTreatment_injuries(String treatment_injuries) {
		this.treatment_injuries = treatment_injuries == null ? "" : treatment_injuries;
	}

	public String getNumber_of_pirates() {
		return number_of_pirates == null ? "" : number_of_pirates;
	}

	public void setNumber_of_pirates(String number_of_pirates) {
		this.number_of_pirates = number_of_pirates == null ? "" : number_of_pirates;
	}

	public String getStolen_cargo() {
		return stolen_cargo == null ? "" : stolen_cargo;
	}

	public void setStolen_cargo(String stolen_cargo) {
		this.stolen_cargo = stolen_cargo == null ? "" : stolen_cargo;
	}

	public String getWeapons_used() {
		return weapons_used == null ? "" : weapons_used;
	}

	public void setWeapons_used(String weapons_used) {
		this.weapons_used = weapons_used == null ? "" : weapons_used;
	}

	public String getIsc_comments() {
		return isc_comments == null ? "" : isc_comments;
	}

	public void setIsc_comments(String isc_comments) {
		this.isc_comments = isc_comments == null ? "" : isc_comments;
	}

	public String getFlag_state() {
		return flag_state == null ? "" : flag_state;
	}

	public void setFlag_state(String flag_state) {
		this.flag_state = flag_state == null ? "" : flag_state;
	}

	public String getLast_port_of_call() {
		return last_port_of_call == null ? "" : last_port_of_call;
	}

	public void setLast_port_of_call(String last_port_of_call) {
		this.last_port_of_call = last_port_of_call == null ? "" : last_port_of_call;
	}

	public String getNext_port_of_call() {
		return next_port_of_call  == null ? "" : next_port_of_call;
	}

	public void setNext_port_of_call(String next_port_of_call) {
		this.next_port_of_call = next_port_of_call == null ? "" : next_port_of_call;
	}

	public String getUpdated_flag() {
		return updated_flag  == null ? "" : updated_flag;
	}

	public void setUpdated_flag(String updated_flag) {
		this.updated_flag = updated_flag == null ? "" : updated_flag;
	}

	public String getLast_port_of_call_desc() {
		return last_port_of_call_desc  == null ? "" : last_port_of_call_desc;
	}

	public void setLast_port_of_call_desc(String last_port_of_call_desc) {
		this.last_port_of_call_desc = last_port_of_call_desc == null ? "" : last_port_of_call_desc;
	}

	public String getNext_port_of_call_desc() {
		return next_port_of_call_desc == null ? "" : next_port_of_call_desc;
	}

	public void setNext_port_of_call_desc(String next_port_of_call_desc) {
		this.next_port_of_call_desc = next_port_of_call_desc == null ? "" : next_port_of_call_desc;
	}

	public String getVoyage_type() {
		return voyage_type  == null ? "" : voyage_type;
	}

	public void setVoyage_type(String voyage_type) {
		this.voyage_type = voyage_type == null ? "" : voyage_type;
	}

	public String getIncident_case_name() {
		return incident_case_name == null ? "" : incident_case_name;
	}

	public void setIncident_case_name(String incident_case_name) {
		this.incident_case_name = incident_case_name == null ? "" : incident_case_name;
	}

	public String getIncident_case_description() {
		return incident_case_description == null ? "" : incident_case_description;
	}

	public void setIncident_case_description(String incident_case_description) {
		this.incident_case_description = incident_case_description == null ? "" : incident_case_description;
	}

	public String getNot_counted() {
		return not_counted == null ? "" : not_counted;
	}

	public void setNot_counted(String not_counted) {
		this.not_counted = not_counted == null ? "" : not_counted;
	}

	public String getNumber_of_suspect_craft() {
		return number_of_suspect_craft == null ? "" : number_of_suspect_craft;
	}

	public void setNumber_of_suspect_craft(String number_of_suspect_craft) {
		this.number_of_suspect_craft = number_of_suspect_craft == null ? "" : number_of_suspect_craft;
	}

	public int[] getAttachments() {
		return attachments;
	}

	public void setAttachments(int[] attachments) {
		this.attachments = attachments;
	}

	public String getLinked_ir() {
		return linked_ir  == null ? "" : linked_ir;
	}

	public void setLinked_ir(String linked_ir) {
		this.linked_ir = linked_ir == null ? "" : linked_ir;
	}

	public String getCoastal_state() {
		return coastal_state == null ? "" : coastal_state;
	}

	public void setCoastal_state(String coastal_state) {
		this.coastal_state = coastal_state == null ? "" : coastal_state;
	}

	@Override
	public String toString() {
		try {
			return String.format(new StringBuilder()
					.append("INSERT INTO \"INCIDENT_CASE\" AS IN_CA (")
					.append(" \"ID\"")
					.append(",\"PRECEDENCE\"")
					.append(",\"INCIDENT_TYPE\"")
					.append(",\"TIMEZONE\"")
					.append(",\"TIME_OF_INCIDENT\"")
					.append(",\"SHIP_NAME\"")
					.append(",\"SHIP_CALLSIGN\"")
					.append(",\"SHIP_FLAG\"")
					.append(",\"SHIP_TYPE\"")
					.append(",\"SHIP_GROSS_TONNAGE\"")
					.append(",\"SHIP_IMO_NUMBER\"")
					.append(",\"SHIP_MMSI_NUMBER\"")
					.append(",\"SHIP_BUILT\"")
					.append(",\"SHIP_NAME_OF_COMPANY\"")
					.append(",\"POSITION_SEA_NAME\"")
					.append(",\"POSITION_LATITUDE\"")
					.append(",\"POSITION_LONGITUDE\"")
					.append(",\"POSITION_AREA_DESCRIPTION\"")
					.append(",\"POSITION_SHIP_ACTIVITY\"")
					.append(",\"POSITION_ISPS_LEVEL\"")
					.append(",\"DETAILS_ATTACK_METHOD\"")
					.append(",\"DETAILS_DESCRIPTION_SUSPECT_CRAFT\"")
					.append(",\"DETAILS_NUMBER_PIRATES\"")
					.append(",\"DETAILS_TYPE_OF_WEAPONS\"")
					.append(",\"DETAILS_TREATMENT_INJURES\"")
					.append(",\"DETAILS_DAMAGES\"")
					.append(",\"DETAILS_OTHER_INFORMATION\"")
					.append(",\"DETAILS_STOLEN_CARGO\"")
					.append(",\"DETAILS_REPORT_TO_AUTHORITIES\"")
					.append(",\"DETAILS_ACTION_TAKEN_BY_MASTER\"")
					.append(",\"DETAILS_ACTION_TAKEN_BY_COASTAL_STATE\"")
					.append(",\"DETAILS_SOURCE_INFORMATION\"")
					.append(",\"DETAILS_REMARKS\"")
					.append(",\"SIGNIFICANCE_LEVEL\"")
					.append(",\"SMS_TO_ALL_FOCAL_POINTS\"")
					.append(",\"STATUS\"")
					.append(",\"INCIDENT_NO\"")
					.append(",\"RELATED_ID\"")
					.append(",\"CREATED_DATE\"")
					.append(",\"CREATED_BY\"")
					.append(",\"UPDATED_DATE\"")
					.append(",\"UPDATED_BY\"")
					.append(",\"LOCAL_TIME\"")
					.append(",\"POSITION_LONGITUDE_DEGREE\"")
					.append(",\"POSITION_LONGITUDE_MINUTE\"")
					.append(",\"POSITION_LONGITUDE_DIRECTION\"")
					.append(",\"POSITION_LATITUDE_DEGREE\"")
					.append(",\"POSITION_LATITUDE_MINUTE\"")
					.append(",\"POSITION_LATITUDE_DIRECTION\"")
					.append(",\"SHIP_INMARSAT_ID\"")
					.append(",\"TREATMENT_INJURIES\"")
					.append(",\"NUMBER_OF_PIRATES\"")
					.append(",\"STOLEN_CARGO\"")
					.append(",\"WEAPONS_USED\"")
					.append(",\"ISC_COMMENTS\"")
					.append(",\"COASTAL_STATE\"")
					.append(",\"FLAG_STATE\"")
					.append(",\"LAST_PORT_OF_CALL\"")
					.append(",\"NEXT_PORT_OF_CALL\"")
					.append(",\"UPDATED_FLAG\"")
					.append(",\"LAST_PORT_OF_CALL_DESC\"")
					.append(",\"NEXT_PORT_OF_CALL_DESC\"")
					.append(",\"VOYAGE_TYPE\"")
					.append(",\"INCIDENT_CASE_NAME\"")
					.append(",\"INCIDENT_CASE_DESCRIPTION\"")
					.append(",\"NOT_COUNTED\"")
					.append(",\"NUMBER_OF_SUSPECT_CRAFT\"")
					.append(",\"LINKED_IR\") ")
					.append("VALUES (")
					.append("'%d',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s'")
					.append(",'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%f',"
							+ "'%f',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s'")
					.append(",'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s'")
					.append(",'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%d',"
							+ "'%s',"
							+ "'%s'")
					.append(",'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%f',"
							+ "'%f',"
							+ "'%s',"
							+ "'%f',"
							+ "'%f',"
							+ "'%s',"
							+ "'%s'")
					.append(",'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s',"
							+ "'%s'")
					.append(",'%s',"
							+ "'%s'"
							+ ",'%s'"
							+ ",'%s'"
							+ ",'%s'"
							+ ",'%s'"
							+ ",'%s'"
							+ ",'%s'")
					.append(") ")
					.append("ON CONFLICT (\"ID\") DO UPDATE ")
					.append("SET ")
					.append("\"PRECEDENCE\"= EXCLUDED.\"PRECEDENCE\"")
					.append(",\"INCIDENT_TYPE\"= EXCLUDED.\"INCIDENT_TYPE\"")
					.append(",\"TIMEZONE\"= EXCLUDED.\"TIMEZONE\"")
					.append(",\"TIME_OF_INCIDENT\"= EXCLUDED.\"TIME_OF_INCIDENT\"")
					.append(",\"SHIP_NAME\"= EXCLUDED.\"SHIP_NAME\"")
					.append(",\"SHIP_CALLSIGN\"= EXCLUDED.\"SHIP_CALLSIGN\"")
					.append(",\"SHIP_FLAG\"= EXCLUDED.\"SHIP_FLAG\"")
					.append(",\"SHIP_TYPE\"= EXCLUDED.\"SHIP_TYPE\"")
					.append(",\"SHIP_GROSS_TONNAGE\"= EXCLUDED.\"SHIP_GROSS_TONNAGE\"")
					.append(",\"SHIP_IMO_NUMBER\"= EXCLUDED.\"SHIP_IMO_NUMBER\"")
					.append(",\"SHIP_MMSI_NUMBER\"= EXCLUDED.\"SHIP_MMSI_NUMBER\"")
					.append(",\"SHIP_BUILT\"= EXCLUDED.\"SHIP_BUILT\"")
					.append(",\"SHIP_NAME_OF_COMPANY\"= EXCLUDED.\"SHIP_NAME_OF_COMPANY\"")
					.append(",\"POSITION_SEA_NAME\"= EXCLUDED.\"POSITION_SEA_NAME\"")
					.append(",\"POSITION_LATITUDE\"= EXCLUDED.\"POSITION_LATITUDE\"")
					.append(",\"POSITION_LONGITUDE\"= EXCLUDED.\"POSITION_LONGITUDE\"")
					.append(",\"POSITION_AREA_DESCRIPTION\"= EXCLUDED.\"POSITION_AREA_DESCRIPTION\"")
					.append(",\"POSITION_SHIP_ACTIVITY\"= EXCLUDED.\"POSITION_SHIP_ACTIVITY\"")
					.append(",\"POSITION_ISPS_LEVEL\"= EXCLUDED.\"POSITION_ISPS_LEVEL\"")
					.append(",\"DETAILS_ATTACK_METHOD\"= EXCLUDED.\"DETAILS_ATTACK_METHOD\"")
					.append(",\"DETAILS_DESCRIPTION_SUSPECT_CRAFT\"= EXCLUDED.\"DETAILS_DESCRIPTION_SUSPECT_CRAFT\"")
					.append(",\"DETAILS_NUMBER_PIRATES\"= EXCLUDED.\"DETAILS_NUMBER_PIRATES\"")
					.append(",\"DETAILS_TYPE_OF_WEAPONS\"= EXCLUDED.\"DETAILS_TYPE_OF_WEAPONS\"")
					.append(",\"DETAILS_TREATMENT_INJURES\"= EXCLUDED.\"DETAILS_TREATMENT_INJURES\"")
					.append(",\"DETAILS_DAMAGES\"= EXCLUDED.\"DETAILS_DAMAGES\"")
					.append(",\"DETAILS_OTHER_INFORMATION\"= EXCLUDED.\"DETAILS_OTHER_INFORMATION\"")
					.append(",\"DETAILS_STOLEN_CARGO\"= EXCLUDED.\"DETAILS_STOLEN_CARGO\"")
					.append(",\"DETAILS_REPORT_TO_AUTHORITIES\"= EXCLUDED.\"DETAILS_REPORT_TO_AUTHORITIES\"")
					.append(",\"DETAILS_ACTION_TAKEN_BY_MASTER\"= EXCLUDED.\"DETAILS_ACTION_TAKEN_BY_MASTER\"")
					.append(",\"DETAILS_ACTION_TAKEN_BY_COASTAL_STATE\"= EXCLUDED.\"DETAILS_ACTION_TAKEN_BY_COASTAL_STATE\"")
					.append(",\"DETAILS_SOURCE_INFORMATION\"= EXCLUDED.\"DETAILS_SOURCE_INFORMATION\"")
					.append(",\"DETAILS_REMARKS\"= EXCLUDED.\"DETAILS_REMARKS\"")
					.append(",\"SIGNIFICANCE_LEVEL\"= EXCLUDED.\"SIGNIFICANCE_LEVEL\"")
					.append(",\"SMS_TO_ALL_FOCAL_POINTS\"= EXCLUDED.\"SMS_TO_ALL_FOCAL_POINTS\"")
					.append(",\"STATUS\"= EXCLUDED.\"STATUS\"")
					.append(",\"INCIDENT_NO\"= EXCLUDED.\"INCIDENT_NO\"")
					.append(",\"RELATED_ID\"= EXCLUDED.\"RELATED_ID\"")
					.append(",\"CREATED_DATE\"= EXCLUDED.\"CREATED_DATE\"")
					.append(",\"CREATED_BY\"= EXCLUDED.\"CREATED_BY\"")
					.append(",\"UPDATED_DATE\"= EXCLUDED.\"UPDATED_DATE\"")
					.append(",\"UPDATED_BY\"= EXCLUDED.\"UPDATED_BY\"")
					.append(",\"LOCAL_TIME\"= EXCLUDED.\"LOCAL_TIME\"")
					.append(",\"POSITION_LONGITUDE_DEGREE\"= EXCLUDED.\"POSITION_LONGITUDE_DEGREE\"")
					.append(",\"POSITION_LONGITUDE_MINUTE\"= EXCLUDED.\"POSITION_LONGITUDE_MINUTE\"")
					.append(",\"POSITION_LONGITUDE_DIRECTION\"= EXCLUDED.\"POSITION_LONGITUDE_DIRECTION\"")
					.append(",\"POSITION_LATITUDE_DEGREE\"= EXCLUDED.\"POSITION_LATITUDE_DEGREE\"")
					.append(",\"POSITION_LATITUDE_MINUTE\"= EXCLUDED.\"POSITION_LATITUDE_MINUTE\"")
					.append(",\"POSITION_LATITUDE_DIRECTION\" = EXCLUDED.\"POSITION_LATITUDE_DIRECTION\"")
					.append(",\"SHIP_INMARSAT_ID\"= EXCLUDED.\"SHIP_INMARSAT_ID\"")
					.append(",\"TREATMENT_INJURIES\"= EXCLUDED.\"TREATMENT_INJURIES\"")
					.append(",\"NUMBER_OF_PIRATES\"= EXCLUDED.\"NUMBER_OF_PIRATES\"")
					.append(",\"STOLEN_CARGO\"= EXCLUDED.\"STOLEN_CARGO\"")
					.append(",\"WEAPONS_USED\"= EXCLUDED.\"WEAPONS_USED\"")
					.append(",\"ISC_COMMENTS\"= EXCLUDED.\"ISC_COMMENTS\"")
					.append(",\"COASTAL_STATE\"= EXCLUDED.\"COASTAL_STATE\"")
					.append(",\"FLAG_STATE\"= EXCLUDED.\"FLAG_STATE\"")
					.append(",\"LAST_PORT_OF_CALL\"= EXCLUDED.\"LAST_PORT_OF_CALL\"")
					.append(",\"NEXT_PORT_OF_CALL\"= EXCLUDED.\"NEXT_PORT_OF_CALL\"")
					.append(",\"UPDATED_FLAG\"= EXCLUDED.\"UPDATED_FLAG\"")
					.append(",\"LAST_PORT_OF_CALL_DESC\"= EXCLUDED.\"LAST_PORT_OF_CALL_DESC\"")
					.append(",\"NEXT_PORT_OF_CALL_DESC\"= EXCLUDED.\"NEXT_PORT_OF_CALL_DESC\"")
					.append(",\"VOYAGE_TYPE\"= EXCLUDED.\"VOYAGE_TYPE\"")
					.append(",\"INCIDENT_CASE_NAME\"= EXCLUDED.\"INCIDENT_CASE_NAME\"")
					.append(",\"INCIDENT_CASE_DESCRIPTION\"= EXCLUDED.\"INCIDENT_CASE_DESCRIPTION\"")
					.append(",\"NOT_COUNTED\"= EXCLUDED.\"NOT_COUNTED\"")
					.append(",\"NUMBER_OF_SUSPECT_CRAFT\"= EXCLUDED.\"NUMBER_OF_SUSPECT_CRAFT\"")
					.append(",\"LINKED_IR\"= EXCLUDED.\"LINKED_IR\"")
					.append("WHERE EXCLUDED.\"UPDATED_DATE\" > IN_CA.\"UPDATED_DATE\";").toString(),
					this.id,
					Utils.escapeLiteral(null, getPrecedence(), true), 
					Utils.escapeLiteral(null, getIncident_type(), true),
					Utils.escapeLiteral(null, getTimezone(), true),
					this.time_of_incident,
					Utils.escapeLiteral(null, getShip_name(), true),
					Utils.escapeLiteral(null, getShip_callsign(), true),
					Utils.escapeLiteral(null, getShip_flag(), true),
					Utils.escapeLiteral(null, getShip_type(), true),
					Utils.escapeLiteral(null, getShip_gross_tonnage(), true),
					Utils.escapeLiteral(null, getShip_imo_number(), true),
					Utils.escapeLiteral(null, getShip_mmsi_number(), true),
					Utils.escapeLiteral(null, getShip_built(), true),
					Utils.escapeLiteral(null, getShip_name_of_company(), true),
					Utils.escapeLiteral(null, getPosition_sea_name(), true),
					this.position_latitude,
					this.position_longitude,
					Utils.escapeLiteral(null, getPosition_area_description(), true),
					Utils.escapeLiteral(null, getPosition_ship_activity(), true),
					Utils.escapeLiteral(null, getPosition_isps_level(), true),
					Utils.escapeLiteral(null, getDetails_attack_method(), true),
					Utils.escapeLiteral(null, getDetails_description_suspect_craft(), true),
					Utils.escapeLiteral(null, getDetails_number_pirates(), true),
					Utils.escapeLiteral(null, getDetails_type_of_weapons(), true),
					Utils.escapeLiteral(null, getDetails_treatment_injures(), true),
					Utils.escapeLiteral(null, getDetails_damages(), true),
					Utils.escapeLiteral(null, getDetails_other_information(), true),
					Utils.escapeLiteral(null, getDetails_stolen_cargo(), true),
					Utils.escapeLiteral(null, getDetails_report_to_authorities(), true),
					Utils.escapeLiteral(null, getDetails_action_taken_by_master(), true),
					Utils.escapeLiteral(null, getDetails_action_taken_by_coastal_state(), true),
					Utils.escapeLiteral(null, getDetails_source_information(), true),
					Utils.escapeLiteral(null, getDetails_remarks(), true),
					Utils.escapeLiteral(null, getSignificance_level(), true),
					Utils.escapeLiteral(null, getSms_to_all_focal_points(), true),
					Utils.escapeLiteral(null, getStatus(), true),
					Utils.escapeLiteral(null, getIncident_no(), true),
					this.related_id,
					this.created_date,
					Utils.escapeLiteral(null, getCreated_by(), true),
					this.updated_date,
					Utils.escapeLiteral(null, getUpdated_by(), true),
					Utils.escapeLiteral(null, getLocal_time(), true),
					this.position_longitude_degree,
					this.position_longitude_minute,
					Utils.escapeLiteral(null, getPosition_longitude_direction(), true),
					this.position_latitude_degree,
					this.position_latitude_minute,
					Utils.escapeLiteral(null, getPosition_latitude_direction(), true),
					Utils.escapeLiteral(null, getShip_inmarsat_id(), true),
					Utils.escapeLiteral(null, getTreatment_injuries(), true),
					Utils.escapeLiteral(null, getNumber_of_pirates(), true),
					Utils.escapeLiteral(null, getStolen_cargo(), true),
					Utils.escapeLiteral(null, getWeapons_used(), true),
					Utils.escapeLiteral(null, getIsc_comments(), true),
					Utils.escapeLiteral(null, getCoastal_state(), true),
					Utils.escapeLiteral(null, getFlag_state(), true),
					Utils.escapeLiteral(null, getLast_port_of_call(), true),
					Utils.escapeLiteral(null, getNext_port_of_call(), true),
					Utils.escapeLiteral(null, getUpdated_flag(), true),
					Utils.escapeLiteral(null, getLast_port_of_call_desc(), true),
					Utils.escapeLiteral(null, getNext_port_of_call_desc(), true),
					Utils.escapeLiteral(null, getVoyage_type(), true),
					Utils.escapeLiteral(null, getIncident_case_name(), true),
					Utils.escapeLiteral(null, getIncident_case_description(), true),
					Utils.escapeLiteral(null, getNot_counted(), true),
					Utils.escapeLiteral(null, getNumber_of_suspect_craft(), true),
					Utils.escapeLiteral(null, getLinked_ir(), true));
		} catch (SQLException e) {
			return com.cmcglobal.senseinfosys.utils.Utils.EMPTY;
		}
	 }
}

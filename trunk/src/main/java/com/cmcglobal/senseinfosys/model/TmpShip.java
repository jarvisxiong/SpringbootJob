package com.cmcglobal.senseinfosys.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * 
 * @author NVAn
 * File name：TmpShip.java
 * 
 * <MODIFICATION HISTORY>
 *   (Rev.)     (Date)       (ID/NAME)            (Comment)
 *   Rev 1.00   2015/07/27    TmpShipModel        Create New
 */
@Entity
@Table(name = "\"TMP_SHIP\"")
public class TmpShip implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "\"ID\"", nullable = false)
//	@JsonInclude(Include.NON_NULL)
	private int id;

	@Column(name = "\"SHIP_NAME\"")
	@NotNull
	@Length(max = 100)
	private String shipName;

	@Column(name = "\"SHIP_CALLSIGN\"")
	@NotNull
	@Length(max = 100)
	private String shipCallSign;

	@Column(name = "\"SHIP_FLAG\"")
	@NotNull
	@Length(max = 100)
	private String shipFlag;

	@Column(name = "\"SHIP_TYPE\"")
	@NotNull
	@Length(max = 100)
	private String shipType;

	@Column(name = "\"SHIP_GROSS_TONNAGE\"")
	@NotNull
	@Length(max = 100)
	private String shipGrossTonnage;

	@Column(name = "\"SHIP_IMO_NUMBER\"")
	@NotNull
	@Length(max = 100)
	private String shipIMONumber;

	@Column(name = "\"SHIP_MSSI_NUMBER\"")
	@NotNull
	@Length(max = 100)
	private String shipMSSINumber;

	@Column(name = "\"SHIP_BUILT\"")
	@NotNull
	@Length(max = 100)
	private String shipBuilt;

	@Column(name = "\"SHIP_NAME_OF_COMPANY\"")
	@NotNull
	@Length(max = 100)
	private String shipNameOfCompany;

	@Column(name = "\"SHIP_INMARSAT_ID\"")
	@NotNull
	@Length(max = 100)
	private String shipInmarsatId;

	@Column(name = "\"CREATED_DATE\"")
	@DateTimeFormat(pattern = "YYMMDD")
	private Date createdDate;

	@Column(name = "\"CREATED_BY\"")
	@Length(max = 50)
	private String createdBy;

	@Column(name = "\"UPDATED_DATE\"")
	@DateTimeFormat(pattern = "YYMMDD")
	private Date updatedDate;

	@Column(name = "\"UPDATED_BY\"")
	@Length(max = 50)
	private String updatedBy;

	@JsonGetter("id")
	public int getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(int id) {
		this.id = id;
	}

	@JsonGetter("ship_name")
	public String getShipName() {
		return shipName;
	}

	@JsonSetter("ship_name")
	public void setShipName(String shipName) {
		this.shipName = (shipName == null) ? "" : shipName;
	}

	@JsonGetter("ship_callsign")
	public String getShipCallSign() {
		return shipCallSign;
	}

	@JsonSetter("ship_callsign")
	public void setShipCallSign(String shipCallSign) {
		this.shipCallSign = (shipCallSign == null) ? "" : shipCallSign;
	}

	@JsonGetter("ship_flag")
	public String getShipFlag() {
		return shipFlag;
	}

	@JsonSetter("ship_flag")
	public void setShipFlag(String shipFlag) {
		this.shipFlag = (shipFlag == null) ? "" : shipFlag;
	}

	@JsonGetter("ship_type")
	public String getShipType() {
		return shipType;
	}

	@JsonSetter("ship_type")
	public void setShipType(String shipType) {
		this.shipType = (shipType == null) ? "" : shipType;
	}

	@JsonGetter("ship_gross_tonnage")
	public String getShipGrossTonnage() {
		return shipGrossTonnage;
	}

	@JsonSetter("ship_gross_tonnage")
	public void setShipGrossTonnage(String shipGrossTonnage) {
		this.shipGrossTonnage = (shipGrossTonnage == null) ? "" : shipGrossTonnage;
	}

	@JsonGetter("ship_imo_number")
	public String getShipIMONumber() {
		return shipIMONumber;
	}

	@JsonSetter("ship_imo_number")
	public void setShipIMONumber(String shipIMONumber) {
		this.shipIMONumber = (shipIMONumber == null) ? "" : shipIMONumber;
	}

	@JsonGetter("ship_mmsi_number")
	public String getShipMSSINumber() {
		return shipMSSINumber;
	}

	@JsonSetter("ship_mmsi_number")
	public void setShipMSSINumber(String shipMSSINumber) {
		this.shipMSSINumber = (shipMSSINumber == null) ? "" : shipMSSINumber;
	}

	@JsonGetter("ship_built")
	public String getShipBuilt() {
		return shipBuilt;
	}

	@JsonSetter("ship_built")
	public void setShipBuilt(String shipBuilt) {
		this.shipBuilt = (shipBuilt == null) ? "" : shipBuilt;
	}

	@JsonGetter("ship_name_of_company")
	public String getShipNameOfCompany() {
		return shipNameOfCompany;
	}

	@JsonSetter("ship_name_of_company")
	public void setShipNameOfCompany(String shipNameOfCompany) {
		this.shipNameOfCompany = (shipNameOfCompany == null) ? "" : shipNameOfCompany;
	}

	@JsonGetter("ship_inmarsat_id")
	public String getShipInmarsatId() {
		return shipInmarsatId;
	}

	@JsonSetter("ship_inmarsat_id")
	public void setShipInmarsatId(String shipInmarsatId) {
		this.shipInmarsatId = (shipInmarsatId == null) ? "" : shipInmarsatId;
	}

	@JsonGetter("created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	@JsonSetter("created_date")
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@JsonGetter("created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	@JsonSetter("created_by")
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@JsonGetter("updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	@JsonSetter("updated_date")
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@JsonGetter("updated_by")
	public String getUpdatedBy() {
		return updatedBy;
	}

	@JsonSetter("updated_by")
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof TmpShip)) {
			return false;
		}

		TmpShip ship = (TmpShip) obj;
		return ship.id == id && ship.shipBuilt.equals(shipBuilt)
			&& ship.shipCallSign.equals(shipCallSign)
			&& ship.shipFlag.equals(shipFlag)
			&& ship.shipGrossTonnage.equals(shipGrossTonnage)
			&& ship.shipIMONumber.equals(shipIMONumber)
			&& ship.shipInmarsatId.equals(shipInmarsatId)
			&& ship.shipMSSINumber.equals(shipMSSINumber)
			&& ship.shipName.equals(shipName)
			&& ship.shipNameOfCompany.equals(shipNameOfCompany)
			&& ship.shipType.equals(shipType);
	}
	
	public void updateShip(Ship newShip) {
		if (!this.shipBuilt.equals(newShip.getShipBuilt())) {
			this.shipBuilt = newShip.getShipBuilt();
		}
		if (!this.shipCallSign.equals(newShip.getShipCallSign())) {
			this.shipCallSign = newShip.getShipCallSign();
		}
		if (!this.shipFlag.equals(newShip.getShipFlag())) {
			this.shipFlag = newShip.getShipFlag();
		}
		if (!this.shipGrossTonnage.equals(newShip.getShipGrossTonnage())) {
			this.shipGrossTonnage = newShip.getShipGrossTonnage();
		}
		if (!this.shipIMONumber.equals(newShip.getShipIMONumber())) {
			this.shipIMONumber = newShip.getShipIMONumber();
		}
		if (!this.shipInmarsatId.equals(newShip.getShipInmarsatId())) {
			this.shipInmarsatId = newShip.getShipInmarsatId();
		}
		if (!this.shipMSSINumber.equals(newShip.getShipMSSINumber())) {
			this.shipMSSINumber = newShip.getShipMSSINumber();
		}
		if (!this.shipName.equals(newShip.getShipName())) {
			this.shipName = newShip.getShipName();
		}
		if (!this.shipNameOfCompany.equals(newShip.getShipNameOfCompany())) {
			this.shipNameOfCompany = newShip.getShipNameOfCompany();
		}
		if (!this.shipType.equals(newShip.getShipType())) {
			this.shipType = newShip.getShipType();
		}
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

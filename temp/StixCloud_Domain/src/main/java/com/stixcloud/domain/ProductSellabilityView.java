package com.stixcloud.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_SELLABILITY_VIEW")
public class ProductSellabilityView implements Serializable{
	private static final long serialVersionUID = -8827654916181279939L;
	@Id
	Long sellabilitypcdetailssid;
	Long productid,
		paugId;
	OffsetDateTime sellabilityStartdate, 
		sellabilityEnddate;

	@Column(name = "SellabilityPcDetailsSid", nullable = false)
	public Long getSellabilitypcdetailssid() {
		return sellabilitypcdetailssid;
	}
	public void setSellabilitypcdetailssid(Long sellabilitypcdetailssid) {
		this.sellabilitypcdetailssid = sellabilitypcdetailssid;
	}
	
	@Column(name = "Paug_Id", nullable = false)
	public Long getPaugId() {
		return paugId;
	}
	public void setPaugId(Long paugid) {
		this.paugId = paugid;
	}

	@Column(name = "Sellability_Startdate", nullable = false)
	public OffsetDateTime getSellabilityStartdate() {
		return sellabilityStartdate;
	}
	public void setSellabilityStartdate(OffsetDateTime sellabilitystartdate) {
		this.sellabilityStartdate = sellabilitystartdate;
	}

	@Column(name = "Sellability_Enddate", nullable = false)
	public OffsetDateTime getSellabilityEnddate() {
		return sellabilityEnddate;
	}
	public void setSellabilityEnddate(OffsetDateTime sellabilityenddate) {
		this.sellabilityEnddate = sellabilityenddate;
	}
	
}

package com.stixcloud.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_VISIBILITY_VIEW")
public class ProductVisibilityView implements Serializable {
	private static final long serialVersionUID = 7465651445823802651L;
	@Id
	Long pvvid;
	Long productid,
		paugId;
	
	OffsetDateTime visibilityStartdate, 
		visibilityEnddate;
	
	public Long getPvvid() {
		return pvvid;
	}
	public void setPvvid(Long pvvid) {
		this.pvvid = pvvid;
	}
	@Column(name = "ProductId", nullable = false)
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	@Column(name = "Paug_Id", nullable = false)
	public Long getPaugId() {
		return paugId;
	}
	public void setPaugId(Long paugid) {
		this.paugId = paugid;
	}
	@Column(name = "Visibility_Startdate", nullable = false)
	public OffsetDateTime getVisibilityStartdate() {
		return visibilityStartdate;
	}
	public void setVisibilityStartdate(OffsetDateTime visibilitystartdate) {
		this.visibilityStartdate = visibilitystartdate;
	}
	@Column(name = "Visibility_Enddate", nullable = false)
	public OffsetDateTime getVisibilityEnddate() {
		return visibilityEnddate;
	}
	public void setVisibilityEnddate(OffsetDateTime visibilityenddate) {
		this.visibilityEnddate = visibilityenddate;
	}

}

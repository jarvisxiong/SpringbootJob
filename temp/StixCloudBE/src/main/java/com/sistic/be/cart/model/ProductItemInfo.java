/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;

/**
 * @author jianhong
 *
 */

/**
 * 
 * @author jianhong
 * {@code used to populate shopping cart}
 *
 */
public class ProductItemInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1192056596871352368L;
	
	private Long productId;
	private String productName;

	private OffsetDateTime productDate;
	private String level;
	private String section;
	private String row;
	private List<Integer> seatNo;
	private String productType;	// RS or GA
	private String venue;
	
	// Getter Setter
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public OffsetDateTime getProductDate() {
		return productDate;
	}
	public void setProductDate(OffsetDateTime productDate) {
		this.productDate = productDate;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public List<Integer> getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(List<Integer> seatNo) {
		this.seatNo = seatNo;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	@JsonIgnore
	public String getSeatsFormatted() {
		return Joiner.on(",").join(seatNo);
	}
	
	@Override
	public String toString() {
		return "ProductItemInfo [productId=" + productId + ", productName=" + productName + ", productDate="
				+ productDate + ", level=" + level + ", section=" + section + ", row=" + row + ", seatNo=" + seatNo
				+ ", productType=" + productType + ", venue=" + venue + "]";
	}
	
}

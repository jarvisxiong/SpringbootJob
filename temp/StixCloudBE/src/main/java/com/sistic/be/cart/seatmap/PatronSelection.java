/**
 * 
 */
package com.sistic.be.cart.seatmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.cart.model.MasterpassPaymentMethod;

/**
 * @author jianhong
 *
 */
public class PatronSelection implements Serializable {
	
	private static final long serialVersionUID = 8155279913173352279L;
	
	private Long productId;
	private String internetContentCode;
	private String mode;
	
	private Integer maxQuantity;
	
	private Long priceCatId;
	private Long seatSectionId;

	private SeatSelection seatSelection;
//	private Map<String, Integer> priceClassMap;
	private String priceClassMap;
	private MasterpassPaymentMethod paymentInfo;
	private String promoCode;
	
	public PatronSelection() {
		this.seatSelection = new SeatSelection();
//		this.priceClassMap = new HashMap<String, Integer>();
	}
	
	// Helper
	@JsonIgnore
	public void initProductSelection(String internetContentCode, Long productId) {
		this.internetContentCode = internetContentCode;
		this.productId = productId;
		
		// Delete the rest
		this.maxQuantity = null;
		this.mode = null;
		this.priceCatId = null;
		this.seatSectionId = null;
		this.seatSelection = new SeatSelection();
		this.priceClassMap = null;
		this.promoCode = null;
	}
	
	@JsonIgnore
	public boolean validateProductSelection() {

		if (internetContentCode != null && productId != null) {
			return true;
		}
		return false;
	}
	
	@JsonIgnore
	public void initCatSectionSelection(Long priceCatId, Long seatSectionId, String mode) {
		this.priceCatId = priceCatId;
		this.seatSectionId = seatSectionId;
		this.mode = mode;
		
		// Delete the rest
		this.maxQuantity = null;
		this.seatSelection = new SeatSelection();
		this.priceClassMap = null;
		this.promoCode = null;
	}
	
	@JsonIgnore
	public boolean validateCatSectionSelection() {

		if (this.validateProductSelection()) {
			if (priceCatId != null && seatSectionId != null && mode != null) {
				return true;
			}
		}
		return false;
	}
	
	@JsonIgnore
	public void initSeatSelections(List<Long> inventoryIds) {
		
		List<Seat> seats = new ArrayList<Seat>();
		for (Long inventoryId : inventoryIds) {
			seats.add(new Seat(inventoryId));
		}
		this.seatSelection.setSeats(seats);
		
		// Delete the rest
		this.priceClassMap = null;
		this.maxQuantity = null;
	}
	
	@JsonIgnore
	public void initIccMaxQty(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	
//	@JsonIgnore
//	public boolean validateSeatSelections() {
//
//		if (this.validateCatSectionSelection()) {
//			if (seatSelection != null) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	@JsonIgnore
	public void initPriceClassMap(String priceClassMap) {
		this.priceClassMap = priceClassMap;
	}
	
	@JsonIgnore
    public void initPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

	// Getter Setter
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getInternetContentCode() {
		return internetContentCode;
	}

	public void setInternetContentCode(String internetContentCode) {
		this.internetContentCode = internetContentCode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getPriceCatId() {
		return priceCatId;
	}

	public void setPriceCatId(Long priceCatId) {
		this.priceCatId = priceCatId;
	}

	public Long getSeatSectionId() {
		return seatSectionId;
	}

	public void setSeatSectionId(Long seatSectionId) {
		this.seatSectionId = seatSectionId;
	}

	public SeatSelection getSeatSelection() {
		return seatSelection;
	}

	public void setSeatSelection(SeatSelection seatSelection) {
		this.seatSelection = seatSelection;
	}

	public String getPriceClassMap() {
		return priceClassMap;
	}

	public void setPriceClassMap(String priceClassMap) {
		this.priceClassMap = priceClassMap;
	}

	public MasterpassPaymentMethod getPaymentInfo() {
      return paymentInfo;
    }
  
    public void setPaymentInfo(MasterpassPaymentMethod paymentInfo) {
      this.paymentInfo = paymentInfo;
    }

	public Integer getMaxQuantity() {
		return maxQuantity;
	}
	
	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

    public String getPromoCode() {
      return promoCode;
    }

    public void setPromoCode(String promoCode) {
      this.promoCode = promoCode;
    }

  @Override
	public String toString() {
		return "PatronSelection [productId=" + productId + ", internetContentCode=" + internetContentCode + ", mode="
				+ mode + ", maxQuantity=" + maxQuantity + ", priceCatId=" + priceCatId + ", seatSectionId="
				+ seatSectionId + ", seatSelection=" + seatSelection + ", priceClassMap=" + priceClassMap
				+ ", paymentInfo=" + paymentInfo + ",promoCode=" + promoCode + "]";
	}
			
}

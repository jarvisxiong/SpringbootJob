package com.stixcloud.cart;

import java.io.Serializable;
import java.util.List;

public class FeeDetails implements Serializable {

  private static final long serialVersionUID = 5171925546825197839L;

  private List<CartItemsDTO> feeItems;

  /**
   * Gets the fee items.
   * @return the fee items
   */
  public List<CartItemsDTO> getFeeItems() {
    return feeItems;
  }

  /**
   * Sets the fee items.
   * @param feeItems the new fee items
   */
  public void setFeeItems(List<CartItemsDTO> feeItems) {
    this.feeItems = feeItems;
  }


}

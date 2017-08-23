package com.stixcloud.cart.rules;

import com.stixcloud.domain.ShoppingCart;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by chongye on 5/10/2016.
 */
public class BaseCartRule {
  private ShoppingCart shoppingCart;
  private boolean isExecuted;

  public BaseCartRule() {
  }

  public BaseCartRule(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  public boolean isExecuted() {
    return isExecuted;
  }

  public void setExecuted(boolean executed) {
    isExecuted = executed;
  }

  /**
   * Checks that shopping cart is not null and empty
   */
  public boolean isCartNotEmpty() {
    return this.shoppingCart != null
        && this.shoppingCart.getCartItems() != null
        && !this.shoppingCart.getCartItems().isEmpty();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof BaseCartRule)) {
      return false;
    }

    BaseCartRule that = (BaseCartRule) o;

    return new EqualsBuilder()
        .append(isExecuted(), that.isExecuted())
        .append(getShoppingCart(), that.getShoppingCart())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getShoppingCart())
        .append(isExecuted())
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("shoppingCart", shoppingCart)
        .append("isExecuted", isExecuted)
        .toString();
  }
}

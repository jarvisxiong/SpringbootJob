package com.stixcloud.cart.rules.add;

import com.stixcloud.cart.AddToCartException;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Set;

/**
 * Created by chongye on 29/9/2016.
 */
@SpringRule
public class CartTicketsLimitRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(CartTicketsLimitRule.class);
  @Autowired
  private MessageSource messageSource;
  private Integer maxQuantity;
  private String icc;

  public void setMaxQuantity(Integer maxQuantity) {
	    this.maxQuantity = maxQuantity;
  }
  
  public void setIcc(String icc) {
	    this.icc = icc;
 }

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

 /* @Action
  public void then() throws AddToCartException {
    this.setExecuted(true);
    ShoppingCart cart = this.getShoppingCart();
    if(cartMaxTicketsPerShow!=null && cartMaxTicketsPerShow!=0 && cartItems!=null && !cartItems.isEmpty()){
    	 int totalTicketsInCart =
    		        cartItems.stream().map(CartItem::getInventoryIdList).mapToInt(Set::size).sum();

    		    if (totalTicketsInCart > cartMaxTicketsPerShow) {
    		      logger.error("Total tickets in cart : " + totalTicketsInCart + " | cartMaxTicketsPerShow : "
    		          + cartMaxTicketsPerShow);
    		      throw new AddToCartException(
    		          messageSource.getMessage("cart.error.limit", null, LocaleContextHolder.getLocale()));
    		    }
    }
    else {

    int totalTicketsInCart =
        cart.getCartItems().stream().map(CartItem::getInventoryIdList).mapToInt(Set::size).sum();
    Long cartMaxTickets=0l;
    if(TenantContextHolder.getTenant()!=null){
    	cartMaxTickets=TenantContextHolder.getTenant().getCartMaxTickets();
    }
    if (totalTicketsInCart > cartMaxTickets) {
      logger.error("Total tickets in cart : " + totalTicketsInCart + " | Cart max tickets : "
          + cartMaxTickets);
      throw new AddToCartException(
          messageSource.getMessage("cart.error.limit", null, LocaleContextHolder.getLocale()));
    }
    }
  }*/
  
  @Action
  public void then() throws AddToCartException {
    this.setExecuted(true);
    ShoppingCart cart = this.getShoppingCart();
    Integer cartMaxTickets=0;
    int totalTicketsInCart =0;
    if(maxQuantity!=null && maxQuantity >0 && icc!=null){
    	cartMaxTickets=maxQuantity;
    	 totalTicketsInCart =
    		        cart.getCartItems().stream().filter(CartItem->icc.equals(CartItem.getIcc())).map(CartItem::getInventoryIdList).mapToInt(Set::size).sum();
    	 logger.info("Total tickets in cart : " + totalTicketsInCart + " | Cart max tickets : "
    	          + cartMaxTickets +"icc "+icc);
    }
    else {
    	if(TenantContextHolder.getTenant()!=null && TenantContextHolder.getTenant().getCartMaxTickets() != null){
        	cartMaxTickets=TenantContextHolder.getTenant().getCartMaxTickets();
        }
     totalTicketsInCart =
        cart.getCartItems().stream().map(CartItem::getInventoryIdList).mapToInt(Set::size).sum();
    }

    if (totalTicketsInCart > cartMaxTickets) {
      logger.error("Total tickets in cart : " + totalTicketsInCart + " | Cart max tickets : "
          + cartMaxTickets);
      throw new AddToCartException("booking.add.cart.max-limit-exceeded");
    }
  }
	  
}

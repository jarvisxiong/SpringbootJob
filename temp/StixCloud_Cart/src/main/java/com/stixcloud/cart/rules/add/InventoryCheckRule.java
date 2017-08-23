package com.stixcloud.cart.rules.add;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.stixcloud.cart.AddToCartException;
import com.stixcloud.cart.InventoryConstants;
import com.stixcloud.cart.repo.PromoCodeRepository;
import com.stixcloud.cart.repo.SalesSeatInventoryRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.dto.PromoCodeDTO;

/**
 * Checks if there are any duplicated inventory id across the whole cart
 */
@SpringRule
public class InventoryCheckRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(InventoryCheckRule.class);

  @Autowired
  private MessageSource messageSource;

  @Autowired
  SalesSeatInventoryRepository salesSeatInventoryRepository;
  
  @Autowired
  private PromoCodeRepository promoCodeRepo;

  private Set<Long> inventories;
  
  @Priority
  public int getPriority() {
    return 0;
  }

  @Condition
  public boolean when() {
    if (isCartNotEmpty()) {
      inventories =
          this.getShoppingCart().getCartItems().stream()
              .map(CartItem::getInventoryIdList)
              .flatMap(Collection::stream)
              .collect(Collectors.toSet());
      return true;
    }
    return false;
  }

  @Action
  public void checkEmpty() throws AddToCartException {
    this.setExecuted(true);
    if (inventories.isEmpty()) {
      throw new AddToCartException(
          messageSource.getMessage("inventory.error.empty", null, LocaleContextHolder.getLocale()));
    }
  }

  /**
   * Checks if there are any duplicate inv id
   * price class map
   */
  @Action
  public void checkInventoriesForDuplicateInventory() throws AddToCartException {
    this.setExecuted(true);

    List<Long> invIdList =
        this.getShoppingCart().getCartItems().stream()
            .map(CartItem::getInventoryIdList)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    Set<Long> invIdSet = new HashSet<>(invIdList);

    if (invIdSet.size() < invIdList.size()) {
      logger.error("Inventory Ids List : " + Arrays.toString(invIdList.toArray()));
      throw new AddToCartException(
          messageSource.getMessage("inventory.error.duplicates", null,
              LocaleContextHolder.getLocale()));
    }
  }

  /**
   * Checks if the ticket quantities in inventory list are the same as the quantities listed in
   * price class map
   */
  @Action(order = 1)
  public void checkInventoriesAndPriceClassMapTally() throws AddToCartException {
    this.setExecuted(true);

    int invListQty = inventories.size();
    int priceClassMapQty =
        this.getShoppingCart().getCartItems().stream().mapToInt(CartItem::getQuantity).sum();

    if (priceClassMapQty != invListQty) {
      logger
          .error("Total qty for price class : " + priceClassMapQty + " | Total qty for inventory : "
              + invListQty);
      throw new AddToCartException(messageSource.getMessage("inventory.error.quantity.error", null,
          LocaleContextHolder.getLocale()));
    }
  }

  /**
   * Check if the inventory ids exists in database and are still reserved
   */
  @Action(order = 2)
  public void checkAgainstDatabase() throws AddToCartException {
    this.setExecuted(true);
    List<String> errors = new ArrayList<>();
    boolean allMatch = getShoppingCart().getCartItems().stream().allMatch(cartItem -> {
      Set<Long> seatInventories = cartItem.getInventoryIdList();

      List<SalesSeatInventory> reservedSeats =
          salesSeatInventoryRepository
              .findReservedSeats(seatInventories, cartItem.getProductId());

      List<Long> invIdList =
          reservedSeats.stream().map(SalesSeatInventory::getSalesseatinventoryid)
              .collect(Collectors.toList());

      boolean match = !invIdList.isEmpty() &&
          (seatInventories.size() == invIdList.size()) && seatInventories.containsAll(invIdList);
      boolean GA = cartItem.getMode().equalsIgnoreCase(InventoryConstants.SECTION_TYPE_GA.getName());
      
      //update missing seat section id in tuple for RS
      if (match && !GA) {
        if (cartItem.getSeatSectionId() == null) {
          //cartItem.setSeatSectionId(reservedSeats.get(0).getVenueSectionLcId());
          int index = getShoppingCart().getCartItems().indexOf(cartItem);
          cartItem =
              new CartItem.Builder(cartItem)
                  .seatSectionId(reservedSeats.get(0).getVenueSectionLcId()).build();

          getShoppingCart().getCartItems().set(index, cartItem);
        }
      }
      
      // check hold seats
      if(match){
        Predicate<SalesSeatInventory> condition = null;
        if(StringUtils.isBlank(cartItem.getPromoCode())){
          // no promo
          condition = seat -> (
              seat.getSystemSaleCodeId() != null // there is hold code configured for seat but no promo code
              || !InventoryConstants.SALES_STATUS_RESERVED.getValue().equals(seat.getSeatsalesstatus()) // RS no hold code configured -> status  = 1
              );
        }else{
          List<PromoCodeDTO> configuredPromos = promoCodeRepo.getConfiguredPromos(cartItem.getProductId(), cartItem.getPriceClassId(), cartItem.getPromoCode());
          Set<Long> holdCodeIds = configuredPromos.stream().map(PromoCodeDTO::getHoldCodeId).filter(p -> p != null).collect(Collectors.toSet());
          boolean isHold = holdCodeIds != null && holdCodeIds.size() > 0;
          if(GA){
            condition = seat ->(
                isHold || (!InventoryConstants.SALES_STATUS_RESERVED.getValue().equals(seat.getSeatsalesstatus()) // GA but status is not 1 
                    || seat.getSystemSaleCodeId() != null) // GA but seat has hold code configured 
                );
          }else{
            condition = seat -> !((isHold && seat.getSystemSaleCodeId() != null
                && holdCodeIds.contains(seat.getSystemSaleCodeId())
                && InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue().equals(seat.getSeatsalesstatus())) // hold
                || (!isHold && InventoryConstants.SALES_STATUS_RESERVED.getValue().equals(seat.getSeatsalesstatus()))) // no hold code
                ;
          }
        }
        if(reservedSeats.stream().anyMatch(condition)){
          logger.error("error.promo.code.hold.code.not.configured: " + cartItem.toString());
          errors.add("error.promo.code.hold.code.not.configured");
        }
      }
      
      return match;
    });

    if (!allMatch) {
      logger.error("Cart : " + getShoppingCart().toString());
      throw new AddToCartException(
          messageSource.getMessage("inventory.error.check", null, LocaleContextHolder.getLocale()));
    }
    if(errors.size() > 0){
      throw new AddToCartException(messageSource.getMessage(errors.get(0), null,
          LocaleContextHolder.getLocale()));
    }
  }
  
}

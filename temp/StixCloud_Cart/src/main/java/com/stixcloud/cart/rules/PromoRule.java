package com.stixcloud.cart.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.PromoConstants;
import com.stixcloud.cart.repo.IPromoPasswordRepository;
import com.stixcloud.cart.repo.PromoCodeRepository;
import com.stixcloud.cart.util.Utils;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.PromoPassword;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.dto.PromoCodeDTO;

/**
 * Created by dbthan on 3/5/2017.
 */

@SpringRule
public class PromoRule extends BaseCartRule {

  private static final Logger logger = LogManager.getLogger(PromoRule.class);
  @Autowired
  protected MessageSource messageSource;
  
  @Autowired
  private IPromoPasswordRepository promoPwdRepo;
  
  @Autowired
  private PromoCodeRepository promoCodeRepo;
  
  private List<CartItem> cartItems;
  
  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    ShoppingCart cart = this.getShoppingCart();
    if (cart == null) {
      return false;
    } else {
      return isCartNotEmpty();
    }
  }

  @Action
  public void validatePromoPassword() throws CartException {
    this.setExecuted(true);

    List<CartItem> cartItems = this.getShoppingCart().getCartItems();
    List<Long> promoPriceClassIds = new ArrayList<>();
    Set<Long> productIds = new HashSet<>();
    Set<String> priceClassCodes = new HashSet<>();
    
    for (CartItem cartItem : cartItems) {
      promoPriceClassIds.add(cartItem.getPriceClassId());
      productIds.add(cartItem.getProductId());
      priceClassCodes.add(cartItem.getPriceClass());
    }
    
    List<PromoPassword> promos = new ArrayList<>();
    promos = promoPwdRepo.getPromos(promoPriceClassIds);
    List<Long> usedPromoPasswords = new ArrayList<Long>();
    // for errrors
    String errorMessage = null, errorPriceClassCode = null, errorPriceClassName = null;
    Set<PromoPasswordValidationError> errors = new HashSet<>();
    for (CartItem item : cartItems) {
      List<PromoPassword> priceClassIdPromos = promos.stream().filter(p -> item.getPriceClassId().equals(p.getPriceClassId())).collect(Collectors.toList());
      errorPriceClassCode = item.getPriceClass();
      if (priceClassIdPromos == null || priceClassIdPromos.isEmpty()) {
        if (!StringUtils.isBlank(item.getPromoPassword())) {
          // there is no promo pwd for cart item's price class but there is promo pwd in cart item
          logger.error("error.promo.password.not.tie.price.class: cart_item=" + item.toString());
          errorMessage = messageSource.getMessage("error.promo.password.not.tie.price.class", new Object[] {item.getPromoPassword()}, LocaleContextHolder.getLocale());
          errors.add(new PromoPasswordValidationError(errorPriceClassCode, errorPriceClassCode, errorMessage));
        } else {
          // no promo pwd for current item
          continue;
        }
      } else {
        errorPriceClassName = priceClassIdPromos.get(0).getPriceClassName();
        if (StringUtils.isBlank(item.getPromoPassword())) {
          // there is promo pwd configured but no pwd is provided for cart item
          logger.error(errorPriceClassName + " error.promo.password.null: cart_item=" + item.toString());
          errorMessage = messageSource.getMessage("error.promo.password.null",
              new Object[] {errorPriceClassName}, LocaleContextHolder.getLocale());
          errors.add(new PromoPasswordValidationError(errorPriceClassCode, errorPriceClassCode, errorMessage));
        } else {
          // there's promo pwd configured for current cart item's price class id and there is a pwd
          // in cart item
          int result =
              validatePassword(usedPromoPasswords, priceClassIdPromos, item.getPromoPassword());

          // message for different error
          switch (result) {
            case PromoConstants.PROMO_PWD_VALIDATION_INACTIVE:
              logger.error(
                  errorPriceClassName + " error.promo.password.not.active: cart_item=" + item.toString());
              errorMessage = messageSource.getMessage("error.promo.password.not.active",
                  new Object[] {errorPriceClassName}, LocaleContextHolder.getLocale());
              errors.add(new PromoPasswordValidationError(errorPriceClassCode, errorPriceClassCode, errorMessage));
              break;

            case PromoConstants.PROMO_PWD_VALIDATION_USAGE_EXCEEDED:
              logger.error(
                  errorPriceClassName + " error.promo.password.exceed.limit: cart_item=" + item.toString());
              errorMessage = messageSource.getMessage("error.promo.password.exceed.limit",
                  new Object[] {errorPriceClassName}, LocaleContextHolder.getLocale());
              errors.add(new PromoPasswordValidationError(errorPriceClassCode, errorPriceClassCode, errorMessage));
              break;

            case PromoConstants.PROMO_PWD_VALIDATION_INVALID:
              logger.error(
                  errorPriceClassName + " error.promo.password.not.correct: cart_item=" + item.toString());
              errorMessage = messageSource.getMessage("error.promo.password.not.correct",
                  new Object[] {errorPriceClassName}, LocaleContextHolder.getLocale());
              errors.add(new PromoPasswordValidationError(errorPriceClassCode, errorPriceClassCode, errorMessage));
              break;
          }
        }
      }
    }
    
    if (!errors.isEmpty()) {
      throw new CartException(errors.toString());
    }
    logger.info("Rule 'PromoRule' for PromoPassword performed successfully");
  }

  private int validatePassword(List<Long> usedPassword, List<PromoPassword> promoPwds, String passwordExpression) {
    promoPwds = promoPwds.stream().filter(p -> (
        Utils.validateRegularExpressions(passwordExpression, p.getRegexPattern())
        )).collect(Collectors.toList());
    if(promoPwds == null || promoPwds.isEmpty()){
      return PromoConstants.PROMO_PWD_VALIDATION_INVALID;
    }
    
    promoPwds = promoPwds.stream().filter(p -> (
        PromoConstants.PROMO_PWD_STATE_ACTIVE == p.getStatus()
        )).collect(Collectors.toList());
    
    if(promoPwds == null || promoPwds.isEmpty()){
      return PromoConstants.PROMO_PWD_VALIDATION_INACTIVE;
    }
    
    for (PromoPassword promoPwd : promoPwds) {
      int maxUsage = promoPwd.getMaxUsageFrequency();
      if(maxUsage > PromoConstants.PROMO_PWD_UNLIMITED_USAGE){// because 0 means unlimited use
        int totalUsage =
            promoPwd.getNumberUsage() == null ? 0 : promoPwd.getNumberUsage().intValue();
        long tempUsage =
            usedPassword.stream().filter(p -> p.equals(promoPwd.getPasswordRegexId())).count();
        totalUsage += tempUsage;
        // Check promotion password is exceed or not
        if (totalUsage >= maxUsage) {
          return PromoConstants.PROMO_PWD_VALIDATION_USAGE_EXCEEDED;
        }
      }
      usedPassword.add(promoPwd.getPasswordRegexId());
    }
    return PromoConstants.PROMO_PWD_VALIDATION_VALID;
  }
  
  @Action
  public void validatePromoCode() throws CartException{
    if(cartItems != null && cartItems.size() > 0){
      Set<Long> productIds = new HashSet<>();
      Set<Long> priceClassIds = new HashSet<>();
      Set<String> promoCodes = new HashSet<>();
      for (CartItem cartItem : cartItems) {
        productIds.add(cartItem.getProductId());
        priceClassIds.add(cartItem.getPriceClassId());
        promoCodes.add(cartItem.getPromoCode());
      }
      // get list of configured and usable promo for shopping cart items 
      List<PromoCodeDTO> configuredPromos = promoCodeRepo.getConfiguredPromos(productIds, priceClassIds, promoCodes, null);
      for (CartItem cartItem : cartItems) {
        if(StringUtils.isEmpty(cartItem.getPromoCode())){
          // item's price class id is configured for promo but there is no promo code provided
          if (configuredPromos.stream().filter(p -> (
                 cartItem.getProductId().equals(p.getProductId()) 
                 && cartItem.getPriceClassId().equals(p.getPriceClassId())
                 )
              ).count() > 0 ){
            String error = messageSource.getMessage(
                "error.promo.code.invalid.price.class", null, LocaleContextHolder.getLocale());
            logger.error(error + ": cart_item=" + cartItem.toString());
            throw new CartException(error);
          }
          continue; // dont need to check when promo code is empty and there is no promo configuration for productid/priceclassid
        }
        // there is promo code in cart item
        if (configuredPromos.stream().filter(p -> (
            cartItem.getProductId().equals(p.getProductId()) 
            && cartItem.getPriceClassId().equals(p.getPriceClassId())
            && cartItem.getPromoCode().equals(p.getPromoCode())
            )
         ).count() == 0 ){
          // but there is no configured promo for product id/price class
          String error = messageSource.getMessage(
              "error.promo.code.wrong.price.class", null, LocaleContextHolder.getLocale());
          logger.error(error + ": cart_item=" + cartItem.toString());
          throw new CartException(error);
        }
      }
    }
    logger.info("Rule 'PromoRule' for PromoCode performed successfully");
   // when promo code is valid, @InventoryCheckRule should add an action for hold code
  }
  
  public List<CartItem> getCartItems() {
    return cartItems;
  }

  public void setCartItems(List<CartItem> cartItems) {
    this.cartItems = cartItems;
  }
}

class PromoPasswordValidationError{
  private String priceClassCode;
  private String priceClassName;
  private String errorMessage;
  
  public PromoPasswordValidationError(String priceClassCode, String priceClassName, String errorMessage){
    this.priceClassCode = priceClassCode;
    this.priceClassName = priceClassName;
    this.errorMessage = errorMessage;
  }
  
  public String getPriceClassCode() {
    return priceClassCode;
  }
  public void setPriceClassCode(String priceClassCode) {
    this.priceClassCode = priceClassCode;
  }
  public String getPriceClassName() {
    return priceClassName;
  }
  public void setPriceClassName(String priceClassName) {
    this.priceClassName = priceClassName;
  }
  public String getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof PromoPasswordValidationError) {
      PromoPasswordValidationError that = (PromoPasswordValidationError) obj;
      return this.priceClassCode.equals(that.getPriceClassCode())
          && this.priceClassName.equals(that.getPriceClassName())
          && this.errorMessage.equals(that.getErrorMessage());
    }
    return false;
  }
  @Override
  public int hashCode() {
    return Objects.hash(priceClassCode, priceClassName, errorMessage);
  }
  
  public String toString(){
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("priceClassCode", priceClassCode)
        .append("priceClassName", priceClassName)
        .append("errorMessage", errorMessage)
        .toString();
  }
}
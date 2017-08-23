package com.stixcloud.cart.rules.precommit;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.api.PreCommitRequest;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.rules.CartRulesListener;
import com.stixcloud.cart.rules.ICartRulesEngine;
import com.stixcloud.cart.rules.PromoRule;
import com.stixcloud.cart.rules.add.InventoryCheckRule;
import com.stixcloud.cart.rules.add.QuantityRestrictionCheckRule;
import com.stixcloud.cart.rules.add.SharedQuotaCheckRule;
import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chongye on 24/11/2016.
 */
@Component
public class PreCommitRulesEngine extends ICartRulesEngine {
  private PaymentMethodRule            paymentMethodRule;
  private DeliveryMethodRule           deliveryMethodRule;
  private CreditCardRangeRule          creditCardRangeRule;
  private EVoucherRule                 eVoucherRule;
  private AddressRule                  addressRule;
  private ProductRule                  productRule;
  private InventoryCheckRule           inventoryCheckRule;
  private PatronSolicitationRule       patronSolicitationRule;
  private QuantityRestrictionCheckRule quantityRestrictionCheckRule;
  private SharedQuotaCheckRule         sharedQuotaCheckRule;
  private PreCommitRequest             preCommitRequest;
  private Long                         patronProfileId;
  private Long                         updatedBy;
  private ShoppingCartJson             shoppingCartJson;
  private PromoRule                    promoRule;
  
  @Autowired
  public PreCommitRulesEngine(PaymentMethodRule paymentMethodRule,
                              DeliveryMethodRule deliveryMethodRule,
                              CreditCardRangeRule creditCardRangeRule,
                              EVoucherRule eVoucherRule,
                              AddressRule addressRule,
                              ProductRule productRule,
                              InventoryCheckRule inventoryCheckRule,
                              PatronSolicitationRule patronSolicitationRule,
                              QuantityRestrictionCheckRule quantityRestrictionCheckRule,
                              SharedQuotaCheckRule sharedQuotaCheckRule,
                              PromoRule     promoRule) {
    this.paymentMethodRule = paymentMethodRule;
    this.deliveryMethodRule = deliveryMethodRule;
    this.creditCardRangeRule = creditCardRangeRule;
    this.eVoucherRule = eVoucherRule;
    this.addressRule = addressRule;
    this.productRule = productRule;
    this.inventoryCheckRule = inventoryCheckRule;
    this.patronSolicitationRule = patronSolicitationRule;
    this.quantityRestrictionCheckRule = quantityRestrictionCheckRule;
    this.sharedQuotaCheckRule = sharedQuotaCheckRule;
    this.promoRule = promoRule;
  }

  public void setPreCommitRequest(PreCommitRequest preCommitRequest) {
    this.preCommitRequest = preCommitRequest;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  public void setShoppingCartJson(ShoppingCartJson shoppingCartJson) {
    this.shoppingCartJson = shoppingCartJson;
  }

  @Override
  public void fireRules() throws CartException {
    this.setCartRulesListener(new CartRulesListener());

    RulesEngine rulesEngine =
        RulesEngineBuilder.aNewRulesEngine().withSilentMode(isSilentMode)
            .withRuleListener(cartRulesListener)
            .withSkipOnFirstFailedRule(true).build();

    paymentMethodRule.setShoppingCartJson(shoppingCartJson);
    paymentMethodRule.setShoppingCart(shoppingCart);
    paymentMethodRule.setPaymentMethodCode(preCommitRequest.getPaymentMethodCode());
    paymentMethodRule.setCreditCardNo(preCommitRequest.getCreditCardNo());
    deliveryMethodRule.setShoppingCart(shoppingCart);
    deliveryMethodRule.setDeliveryMethodCode(preCommitRequest.getDeliveryMethodCode());
    creditCardRangeRule.setShoppingCart(shoppingCart);
    creditCardRangeRule.setCreditCard(preCommitRequest.getCreditCardNo());
    creditCardRangeRule.setDeliveryMethodCode(preCommitRequest.getDeliveryMethodCode());
    promoRule.setShoppingCart(shoppingCart);
    promoRule.setCartItems(shoppingCart.getCartItems());
    rulesEngine.registerRule(paymentMethodRule);
    rulesEngine.registerRule(creditCardRangeRule);
    rulesEngine.registerRule(deliveryMethodRule);
    rulesEngine.registerRule(promoRule);

    if (preCommitRequest.getEvoucherIdList() != null && !preCommitRequest.getEvoucherIdList()
        .isEmpty()) {
      eVoucherRule.setShoppingCart(shoppingCart);
      eVoucherRule.setEvoucherIdList(preCommitRequest.getEvoucherIdList());
      eVoucherRule.setCartGuid(shoppingCart.getCartGuid());
      eVoucherRule.setPatronProfileInfoId(patronProfileId);
      rulesEngine.registerRule(eVoucherRule);
    }
    if (preCommitRequest.getAddress() != null) {
      addressRule.setShoppingCart(shoppingCart);
      addressRule.setDeliveryMethodCode(preCommitRequest.getDeliveryMethodCode());
      addressRule.setBillingMailingAddressSame(preCommitRequest.getSameAsMailingAddrFlag());
      addressRule.setAddressList(preCommitRequest.getAddress());
      addressRule.setPatronProfileId(patronProfileId);
      rulesEngine.registerRule(addressRule);
    }

    if (preCommitRequest.getPatronSolicitation() != null && (
        preCommitRequest.getPatronSolicitation().getSolicitationList() != null
            && !preCommitRequest.getPatronSolicitation().getSolicitationList().isEmpty())) {
      patronSolicitationRule.setShoppingCart(shoppingCart);
      patronSolicitationRule.setPatronProfileId(patronProfileId);
      patronSolicitationRule.setPatronSolicitation(preCommitRequest.getPatronSolicitation());
      patronSolicitationRule.setAddressJsonList(preCommitRequest.getAddress());
      patronSolicitationRule.setUpdatedBy(updatedBy);
      rulesEngine.registerRule(patronSolicitationRule);
    }

    productRule.setShoppingCart(shoppingCart);
    rulesEngine.registerRule(productRule);

    inventoryCheckRule.setShoppingCart(shoppingCart);
    rulesEngine.registerRule(inventoryCheckRule);

    quantityRestrictionCheckRule.setPatronProfileId(patronProfileId);
    quantityRestrictionCheckRule.setShoppingCart(shoppingCart);
    rulesEngine.registerRule(quantityRestrictionCheckRule);

    sharedQuotaCheckRule.setPatronProfileId(patronProfileId);
    sharedQuotaCheckRule.setShoppingCart(shoppingCart);
    rulesEngine.registerRule(sharedQuotaCheckRule);

    rulesEngine.fireRules();
    this.handleException();
  }
}

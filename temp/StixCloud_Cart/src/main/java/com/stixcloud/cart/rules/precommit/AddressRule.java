package com.stixcloud.cart.rules.precommit;

import com.stixcloud.cart.ShoppingCartService;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.api.AddressJson;
import com.stixcloud.cart.repo.AddressRepository;
import com.stixcloud.cart.repo.DeliveryMethodRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.Address;
import com.stixcloud.domain.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sengkai on 11/30/2016.
 */
@SpringRule
public class AddressRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(AddressRule.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private ShoppingCartService shoppingCartService;

  @Autowired
  private DeliveryMethodRepository deliveryMethodRepository;

  @Autowired
  private AddressRepository addressRepository;

  private String deliveryMethodCode;
  private Long patronProfileId;
  private boolean isBillingMailingAddressSame;
  private List<AddressJson> addressList;
  private List<Address> patronAddresses;

  public void setDeliveryMethodCode(String deliveryMethodCode) {
    this.deliveryMethodCode = deliveryMethodCode;
  }

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  public void setBillingMailingAddressSame(boolean billingMailingAddressSame) {
    isBillingMailingAddressSame = billingMailingAddressSame;
  }

  public void setAddressList(List<AddressJson> addressList) {
    this.addressList = addressList;
  }

  @Priority
  public int getPriority() {
    return Integer.MIN_VALUE;
  }

  @Condition
  public boolean when() {
    Boolean isAddressRequired =
        deliveryMethodRepository.getDeliveryMethodIsAddressRequired(deliveryMethodCode);
    return isCartNotEmpty() && isAddressRequired;
  }

  /*@Action
  public void checkIsAddressRequired() throws ValidateCartException {
    this.setExecuted(true);

    Boolean isAddressRequired =
        deliveryMethodRepository.getDeliveryMethodIsAddressRequired(deliveryMethodCode)
            .booleanValue();

    if (isAddressRequired == null) {
      throw new ValidateCartException(
          messageSource
              .getMessage("precommit.error.address.deliveryMethodCode", null, LocaleContextHolder
                  .getLocale()));
    }
  }*/

  /*@Action
  public void checkPatronAddressIsNull() throws ValidateCartException {
    this.setExecuted(true);

    List<Address> patronAddresses = addressRepository.findPatronAddresses(patronProfileId);
    Address patronAddress = patronAddresses.stream()
        .filter(address -> address.getAddresstype().equalsIgnoreCase("Mailing Address"))
        .findAny()
        .orElse(null);

    if (patronAddress == null) {
      throw new ValidateCartException(
          messageSource
              .getMessage("precommit.error.address.patronAddress", null, LocaleContextHolder
                  .getLocale()));
    }

    if (checkPatronAddressLocale(patronAddress)) {
      throw new ValidateCartException(
          messageSource
              .getMessage("precommit.error.address.patronCountry", null, LocaleContextHolder
                  .getLocale()));
    }
  }*/

  private boolean checkPatronAddressLocale(Address patronAddress) throws ValidateCartException {
    ShoppingCart cart = this.getShoppingCart();

    return patronAddress.getCountry().equalsIgnoreCase(LocaleContextHolder
        .getLocale().getCountry());
  }

  @Action(order = 1)
  public void checkBillingMailingAddressSame() throws ValidateCartException {
    this.setExecuted(true);
    ShoppingCart cart = this.getShoppingCart();

    patronAddresses = addressRepository.findPatronAddresses(patronProfileId);
    Address patronAddress = patronAddresses.stream()
        .filter(address -> address.getAddresstype().equalsIgnoreCase("Mailing Address"))
        .findAny()
        .orElse(null);

    boolean isSameAsMailingAddr = false;
    if (patronAddress != null && isBillingMailingAddressSame) {

      isSameAsMailingAddr = addressList.stream().allMatch(
          address ->
              address.getCountry().equalsIgnoreCase(patronAddress.getCountry()) &&
                  address.getCity().equalsIgnoreCase(patronAddress.getCity()) &&
                  address.getLine1().equalsIgnoreCase(patronAddress.getLineone()) &&
                  address.getLine2().equalsIgnoreCase(patronAddress.getLinetwo()) &&
                  address.getLine3().equalsIgnoreCase(patronAddress.getLinethree()) &&
                  address.getPostalCode().equalsIgnoreCase(patronAddress.getPostcode()) &&
                  address.getCountrySubdivision().equalsIgnoreCase(patronAddress.getState())

      );

      if (!isSameAsMailingAddr) {
        throw new ValidateCartException(
            messageSource
                .getMessage("precommit.error.address.patronMailingBilling", null,
                    LocaleContextHolder
                        .getLocale()));
      }
    }
  }

  @Action(order = 2)
  public void updatePatronAddress() throws ValidateCartException {

    /**
     * Overwrite the address in patronAddresses
     * with Json list. Both do not have the same no. of attribute
     */
    List<Address> updatedPatronAddresses = new ArrayList<Address>();

    this.addressList.stream().forEach(addressJson -> {

      Address
          addressEntity =
          this.patronAddresses.stream()
              .filter(address -> address.getAddresstype()
                  //.equalsIgnoreCase(AddressEnum.valueOf(addressJson.getType()).getDescription()))
                  .equalsIgnoreCase(addressJson.getType()))
              .findAny().orElse(null);

      if (addressEntity != null) {
        addressEntity.setCountry(addressJson.getCountry());
        addressEntity.setState(addressJson.getCountrySubdivision());
        addressEntity.setCity(addressJson.getCity());
        addressEntity.setLineone(addressJson.getLine1());
        addressEntity.setLinetwo(addressJson.getLine2());
        addressEntity.setLinethree(addressJson.getLine3());
        addressEntity.setPostcode(addressJson.getPostalCode());

        updatedPatronAddresses.add(addressEntity);
      }
    });

    addressRepository.save(updatedPatronAddresses);
  }
}

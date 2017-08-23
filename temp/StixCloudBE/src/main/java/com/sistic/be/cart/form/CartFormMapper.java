package com.sistic.be.cart.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.PatronUtil;
import com.sistic.be.cart.api.model.PatronSolicitationUpdateRequest;
import com.sistic.be.cart.api.model.PrecommitOrderRequest;
import com.sistic.be.cart.model.CommonPaymentMethod;
import com.sistic.be.cart.model.PaymentMethod;
import com.sistic.be.patron.api.model.PatronProfile;
import com.sistic.be.patron.model.Address;

@Service
public class CartFormMapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6956284822011178853L;
	
	@Autowired
	private ObjectMapper mapper;
	
	/**
	 * Change to use PatronUtil?
	 */
	private final String MailingAddressType = "Mailing Address";
	private final String BillingAddressType = "Billing Address";
	private final String TenantSolicitation = "Tenant";
	private final String PromoterSolicitation = "Promoter";
	private final String VenueSolicitation = "Venue";
	
	public ConfirmOrderForm toConfirmOrderFormFromPatronProfile(PatronProfile patronProfile) {
		
		/**
		 * temporary nullpointer handling. review
		 */
		if (patronProfile == null) {
			return null;
		}
		
		ConfirmOrderForm confirmOrderForm = new ConfirmOrderForm();
		
		confirmOrderForm.setBillingBlockHouseStreet(PatronUtil.findBillingAddress(patronProfile.getAddresses()).getAddressLineOne());
		confirmOrderForm.setBillingBuildingName(PatronUtil.findBillingAddress(patronProfile.getAddresses()).getAddressLineThree());
		confirmOrderForm.setBillingCountry(PatronUtil.findBillingAddress(patronProfile.getAddresses()).getCountryCode());
		confirmOrderForm.setBillingPostalCode(PatronUtil.findBillingAddress(patronProfile.getAddresses()).getPostalCode());
		confirmOrderForm.setBillingUnitNo(PatronUtil.findBillingAddress(patronProfile.getAddresses()).getAddressLineTwo());
		
		confirmOrderForm.setMailingBlockHouseStreet(PatronUtil.findMailingAddress(patronProfile.getAddresses()).getAddressLineOne());
		confirmOrderForm.setMailingBuildingName(PatronUtil.findMailingAddress(patronProfile.getAddresses()).getAddressLineThree());
		confirmOrderForm.setMailingCountry(PatronUtil.findMailingAddress(patronProfile.getAddresses()).getCountryCode());
		confirmOrderForm.setMailingPostalCode(PatronUtil.findMailingAddress(patronProfile.getAddresses()).getPostalCode());
		confirmOrderForm.setMailingUnitNo(PatronUtil.findMailingAddress(patronProfile.getAddresses()).getAddressLineTwo());
		
		return confirmOrderForm;
	}
	
	public PrecommitOrderRequest toPrecommitOrderRequest(ConfirmOrderForm confirmOrderForm, CommonPaymentMethod commonPaymentMethod, PatronSolicitationUpdateRequest patronSolicitationUpdateRequest) {
		
		/**
		 * temporary nullpointer handling. review
		 */
		if (confirmOrderForm == null) {
			return null;
		}
		
		PrecommitOrderRequest precommitOrderRequest = new PrecommitOrderRequest();
		
		precommitOrderRequest.setDeliveryMethodCode(confirmOrderForm.getDeliveryMethodCode());
		String userPaymentMethod = this.findPaymentMethodMapping(confirmOrderForm.getCardType());
		String paymentMethodCode = this.findPaymentMethodCode(userPaymentMethod, commonPaymentMethod);
		precommitOrderRequest.setPaymentMethodCode(paymentMethodCode);	// Mark, put confirmOrderForm.getCardType here
		
		precommitOrderRequest.setCreditCardNo(confirmOrderForm.getCreditCardNumber());
		String expiryMonthStr = StringUtils.leftPad(confirmOrderForm.getCardExpiryMonth(), 2, "0");
		String expiryYearStr = StringUtils.substring(confirmOrderForm.getCardExpiryYear(), -2);
		precommitOrderRequest.setCreditCardMonth(expiryMonthStr);
		precommitOrderRequest.setCreditCardYear(expiryYearStr);
		precommitOrderRequest.setCreditCardExpiry(expiryMonthStr + expiryYearStr);
		precommitOrderRequest.setCreditCardCSC(confirmOrderForm.getCardSecurityCode());

		precommitOrderRequest.setSameAsMailingAddrFlag(Boolean.parseBoolean(confirmOrderForm.getSameAsMailingAddrFlag()));
		
		precommitOrderRequest.setPurchaseTp(Boolean.parseBoolean(confirmOrderForm.getPurchaseTp()));
		
		List<Address> addresses = new ArrayList<Address>();
		Address billingAddress = new Address();
		billingAddress.setCity(confirmOrderForm.getBillingCountry());
		billingAddress.setCountry(confirmOrderForm.getBillingCountry());
		billingAddress.setCountrySubdivision(null);
		billingAddress.setLine1(confirmOrderForm.getBillingBlockHouseStreet());
		billingAddress.setLine2(confirmOrderForm.getBillingBuildingName());
		billingAddress.setLine3(confirmOrderForm.getBillingUnitNo());
		billingAddress.setPostalCode(confirmOrderForm.getBillingPostalCode());
		billingAddress.setType(BillingAddressType);
		billingAddress.setPrimary(null);
		addresses.add(billingAddress);
		Address mailingAddress = new Address();
		mailingAddress.setCity(confirmOrderForm.getMailingCountry());
		mailingAddress.setCountry(confirmOrderForm.getMailingCountry());
		mailingAddress.setCountrySubdivision(null);
		mailingAddress.setLine1(confirmOrderForm.getMailingBlockHouseStreet());
		mailingAddress.setLine2(confirmOrderForm.getMailingBuildingName());
		mailingAddress.setLine3(confirmOrderForm.getMailingUnitNo());
		mailingAddress.setPostalCode(confirmOrderForm.getMailingPostalCode());
		mailingAddress.setType(MailingAddressType);
		mailingAddress.setPrimary(null);
		addresses.add(mailingAddress);
		
		precommitOrderRequest.setAddress(addresses);
		
		/**
		 * Set patron solicitation
		 */
		precommitOrderRequest.setPatronSolicitation(patronSolicitationUpdateRequest);
		
		/**
		 * This portion is to not send empty string over to API
		 */
		if (confirmOrderForm.getEvoucherIds() != null && !confirmOrderForm.getEvoucherIds().isEmpty()) {
			List<String> evoucherIds = new ArrayList<String>();
			for (String evoucherId : confirmOrderForm.getEvoucherIds()) {
				if (!evoucherId.isEmpty()) {
					evoucherIds.add(evoucherId);
				}
			}
			
			precommitOrderRequest.setEvoucherIdList(evoucherIds);
		}
		
		return precommitOrderRequest;
	}
	
	private String findPaymentMethodCode(String paymentCode, CommonPaymentMethod commonPaymentMethod) {
		
		List<PaymentMethod> commonPaymentMethodSet = commonPaymentMethod.getPaymentMethodList();
		
//		Collections.sort(commonPaymentMethodCodes); // sort ascending (choose MASTER over MASTER_RWS?)
	
		for (PaymentMethod paymentMethod : commonPaymentMethodSet) {
			if (paymentMethod == null) {continue;}
			String paymentMethodCode = paymentMethod.getCode();
			if (paymentMethodCode.contains("VOUCHER")) {
				continue;	// VOUCHER is not valid paymentMethod selection
			}
			String paymentMethodCodeSubStr = paymentMethodCode.split("_")[0];	// MASTER if MASTER_RWS for example. Problem with MASTER_E_VOUCHER!!!
			if (paymentCode.equals(paymentMethodCodeSubStr)) {
				return paymentMethodCode;
			}
		}
		return null;
	}
	
	private String findPaymentMethodMapping (String cardType) {
		
		switch (cardType) {
			case "visa":
				return "VISA";
			case "mastercard":
				return "MASTER";
			case "jcb":
				return "JCB";
			case "unionpay":
				return "CUP";
			case "amex":
				return "AMEX";
			case "diners":
				return "DINERS";
			default:
				return null;
		}
	}

}

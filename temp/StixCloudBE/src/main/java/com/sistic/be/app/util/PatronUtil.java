package com.sistic.be.app.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sistic.be.patron.api.model.Address;
import com.sistic.be.patron.api.model.PatronPhone;

public final class PatronUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * TODO: Constants to refactor to Constants class
	 */
	private final static String MobileType = "MOBILE";
	private final static String HomeType = "HOME";
	private final static String OtherType = "OFFICE";
	private final static String MailingAddressType = "Mailing Address";
	private final static String BillingAddressType = "Billing Address";
	
	public static PatronPhone findMobilePhone(List<PatronPhone> phoneNumbers) {
		for (PatronPhone phone : phoneNumbers) {
			if (MobileType.equals(phone.getContactType())) {
				return phone;
			}
		}
		return new PatronPhone();
	}
	
	public static PatronPhone findHomePhone(List<PatronPhone> phoneNumbers) {
		for (PatronPhone phone : phoneNumbers) {
			if (HomeType.equals(phone.getContactType())) {
				return phone;
			}
		}
		return new PatronPhone();
	}
	
	public static PatronPhone findOtherPhone(List<PatronPhone> phoneNumbers) {
		for (PatronPhone phone : phoneNumbers) {
			if (OtherType.equals(phone.getContactType())) {
				return phone;
			}
		}
		return new PatronPhone();
	}
	
	public static Address findMailingAddress(List<Address> addresses) {
		for (Address address : addresses) {
			if (MailingAddressType.equals(address.getType())) {
				return address;
			}
		}
		return new Address();
	}
	
	public static Address findBillingAddress(List<Address> addresses) {
		for (Address address : addresses) {
			if (BillingAddressType.equals(address.getType())) {
				return address;
			}
		}
		return new Address();
	}
	
}

package com.sistic.be.patron.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.cart.api.model.PatronSolicitationUpdateRequest;
import com.sistic.be.cart.api.model.SolicitationUpdate;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.countries.Country;
import com.sistic.be.countries.ListCountry;
import com.sistic.be.patron.api.model.Address;
import com.sistic.be.patron.api.model.CountryCode;
import com.sistic.be.patron.api.model.PatronIdentification;
import com.sistic.be.patron.api.model.PatronPhone;
import com.sistic.be.patron.api.model.PatronProfile;
import com.sistic.be.patron.api.model.PatronSolicitation;
import com.sistic.be.patron.api.model.Solicitation;

/**
 * This is an adapter class to convert flat form objects into request object to the API
 * @author jianhong
 *
 */
@Service
public class PatronFormMapper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ObjectMapper mapper;
	

	/**
	 * TODO: Constants to refactor to Constants class
	 */
	private final String MobileType = "MOBILE";
	private final String HomeType = "HOME";
	private final String OtherType = "OFFICE";
	private final String MailingAddressType = "Mailing Address";
	private final String BillingAddressType = "Billing Address";
	
	public PatronForm toPatronForm(PatronProfile patronProfile, ListCountry countryList) throws JsonProcessingException {
		
		PatronForm patronForm = new PatronForm();
		
		patronForm.setEmailAddress(patronProfile.getEmail());
		patronForm.setTitle(patronProfile.getTitle());
		patronForm.setFirstName(patronProfile.getFirstName());
		patronForm.setLastName(patronProfile.getLastName());
		
		
		try {
			PatronPhone mobilePatronPhone = findMobilePhone(patronProfile.getContacts());
			patronForm.setMobileNo(mobilePatronPhone.getPhoneNumber());
			String mobileCallingCode = mobilePatronPhone.getCountry().getCallingCode().toString() + "," + mobilePatronPhone.getCountry().getCode();
			patronForm.setMobileCallingCode(mobileCallingCode);
		} catch (NullPointerException e) {
			logger.debug("Patron has no mobile number");
		}
		
		try {
			PatronPhone homePatronPhone = findHomePhone(patronProfile.getContacts());
			patronForm.setHomeNo(homePatronPhone.getPhoneNumber());
			String homeCallingCode = homePatronPhone.getCountry().getCallingCode().toString() + "," + homePatronPhone.getCountry().getCode();
			patronForm.setHomeCallingCode(homeCallingCode);
		} catch (NullPointerException e) {
			logger.debug("Patron has no home number");
		}
		
		try {
			PatronPhone otherPatronPhone = findOtherPhone(patronProfile.getContacts());
			patronForm.setOtherNo(otherPatronPhone.getPhoneNumber());
			String otherCallingCode = otherPatronPhone.getCountry().getCallingCode().toString() + "," + otherPatronPhone.getCountry().getCode();
			patronForm.setOtherCallingCode(otherCallingCode);
		} catch (NullPointerException e) {
			logger.debug("Patron has no other number");
		}
		
//		patronForm.setMobileCountryCode(findMobilePhone(patronProfile.getContactNos()).getCountry().getCode());
//		patronForm.setHomeCountryCode(findHomePhone(patronProfile.getContactNos()).getCountry().getCode());
//		patronForm.setOtherCountryCode(findOtherPhone(patronProfile.getContactNos()).getCountry().getCode());
		
		patronForm.setBirthYear((patronProfile.getYearOfBirth() == null) ? "" : patronProfile.getYearOfBirth().toString());
		patronForm.setNationality(patronProfile.getNationality());
		patronForm.setCountry(patronProfile.getCountryOfResidence());
		patronForm.setIdType(patronProfile.getIdentification().getType());
		patronForm.setIdentityNo(patronProfile.getIdentification().getNo());
		
		patronForm.setBillingCountry(findBillingAddress(patronProfile.getAddresses()).getCountryCode());
		patronForm.setBillingBlockHouseStreet(findBillingAddress(patronProfile.getAddresses()).getAddressLineOne());
		patronForm.setBillingUnitNo(findBillingAddress(patronProfile.getAddresses()).getAddressLineTwo());
		patronForm.setBillingBuildingName(findBillingAddress(patronProfile.getAddresses()).getAddressLineThree());
		patronForm.setBillingPostalCode(findBillingAddress(patronProfile.getAddresses()).getPostalCode());
		
		patronForm.setMailingCountry(findMailingAddress(patronProfile.getAddresses()).getCountryCode());
		patronForm.setMailingBlockHouseStreet(findMailingAddress(patronProfile.getAddresses()).getAddressLineOne());
		patronForm.setMailingUnitNo(findMailingAddress(patronProfile.getAddresses()).getAddressLineTwo());
		patronForm.setMailingBuildingName(findMailingAddress(patronProfile.getAddresses()).getAddressLineThree());
		patronForm.setMailingPostalCode(findMailingAddress(patronProfile.getAddresses()).getPostalCode());
		
		return patronForm;
	}
	
	public PatronProfile toPatronProfile(PatronForm patronForm, ListCountry countryList, String requestType) throws JsonParseException, JsonMappingException, IOException {
		
		PatronProfile patronProfile = new PatronProfile();
		
		if (patronForm != null && patronProfile != null) {
			
			List<Country> countries = countryList.getCountries();
			
			patronProfile.setEmail(patronForm.getEmailAddress());
			patronProfile.setTitle(patronForm.getTitle());
			patronProfile.setFirstName(patronForm.getFirstName());
			patronProfile.setLastName(patronForm.getLastName());
			
			List<PatronPhone> contactNos = new ArrayList<PatronPhone>();
			
			try {
				String mobileInfo[] = patronForm.getMobileCallingCode().split(",");
				Short mobileCallingCode = Short.valueOf(mobileInfo[0]);
				String mobileCountryCode = mobileInfo[1];
				CountryCode mobileCountry = new CountryCode(mobileCountryCode, mobileCallingCode);
				
				contactNos.add(new PatronPhone(MobileType, mobileCountry, patronForm.getMobileNo()));
			} catch (NumberFormatException | NullPointerException e) {
				logger.error("Did not input valid mobile calling code: " + patronForm.getMobileCallingCode());
			}
			
			try {
				String homeInfo[] = patronForm.getHomeCallingCode().split(",");
				Short homeCallingCode = Short.valueOf(homeInfo[0]);
				String homeCountryCode = homeInfo[1];
				CountryCode homeCountry = new CountryCode(homeCountryCode, homeCallingCode);
				
				contactNos.add(new PatronPhone(HomeType, homeCountry, patronForm.getHomeNo()));
			} catch (NumberFormatException | NullPointerException e) {
				logger.error("Did not input valid home calling code: " + patronForm.getHomeCallingCode());
			}
			
			try {
				String otherInfo[] = patronForm.getOtherCallingCode().split(",");
				Short otherCallingCode = Short.valueOf(otherInfo[0]);
				String otherCountryCode = otherInfo[1];
				CountryCode otherCountry = new CountryCode(otherCountryCode, otherCallingCode);	
				
				contactNos.add(new PatronPhone(OtherType, otherCountry, patronForm.getOtherNo()));
			} catch (NumberFormatException | NullPointerException e) {
				logger.error("Did not input valid other calling code: " + patronForm.getOtherCallingCode());
			}
			
			
			/**
			 * Refactor these code later, Inefficient
			 */
			if (requestType.equals("REGISTER")) {
				List<PatronPhone> copyList = new ArrayList<PatronPhone>(contactNos);
				for (PatronPhone patronPhone : copyList) {
					if (patronPhone.getPhoneNumber().isEmpty()) {
						contactNos.remove(patronPhone);
					}
				}
			}
			
			patronProfile.setContacts(contactNos);
			
			patronProfile.setYearOfBirth(Integer.valueOf(patronForm.getBirthYear()));
			patronProfile.setNationality(patronForm.getNationality());
			patronProfile.setCountryOfResidence(patronForm.getCountry());
			
			PatronIdentification identification = new PatronIdentification(patronForm.getIdType(), patronForm.getIdentityNo());
			patronProfile.setIdentification(identification);
			
//			patronProfile.setBillingAddressSameAsMailing(null);
			
			List<Address> addresses = new ArrayList<Address>();
			
			Address billingAddress = new Address();
			billingAddress.setAddressLineOne(patronForm.getBillingBlockHouseStreet());
			billingAddress.setAddressLineThree(patronForm.getBillingBuildingName());
			billingAddress.setCountryCode(patronForm.getBillingCountry());
			billingAddress.setPostalCode(patronForm.getBillingPostalCode());
			billingAddress.setType(BillingAddressType);
			billingAddress.setAddressLineTwo(patronForm.getBillingUnitNo());
			addresses.add(billingAddress);
			
			Address mailingAddress = new Address();
			mailingAddress.setAddressLineOne(patronForm.getMailingBlockHouseStreet());
			mailingAddress.setAddressLineThree(patronForm.getMailingBuildingName());
			mailingAddress.setCountryCode(patronForm.getMailingCountry());
			mailingAddress.setPostalCode(patronForm.getMailingPostalCode());
			mailingAddress.setType(MailingAddressType);
			mailingAddress.setAddressLineTwo(patronForm.getMailingUnitNo());
			addresses.add(mailingAddress);
			
			patronProfile.setAddresses(addresses);
			
			if (requestType.equals("REGISTER")) {
				if (!patronForm.getPassword().isEmpty()) {
					patronProfile.setPassword(patronForm.getPassword());
				}
				
				// set organizationId
				patronProfile.setOrganizationId(TenantContextHolder.getTenant().getOrganizationId());
				// set preferLanguage
				patronProfile.setPreferLanguage(TenantContextHolder.getTenant().getPreferLanguage());
				// set patron type
				patronProfile.setPatronType("I");
				
				try {
					if (patronForm.getReceiveVenue() != null && "true".equals(patronForm.getReceiveVenue().toLowerCase())) {
						patronProfile.setReceiveVenue(true);
					}
					if (patronForm.getReceiveTicketingAgent() != null && "true".equals(patronForm.getReceiveTicketingAgent().toLowerCase())) {
						patronProfile.setReceiveTicketingAgent(true);
					}
					if (patronForm.getReceivePromoter() != null && "true".equals(patronForm.getReceivePromoter().toLowerCase())) {
						patronProfile.setReceivePromoter(true);
					}
				} catch (NullPointerException e) {
					logger.error("Could not map form flags to solicitation flags");
				}
			}
			
			if (requestType.equals("MANAGE")) {
				if (!patronForm.getPassword().isEmpty()) {
					patronProfile.setPassword(patronForm.getPassword());
				}
				if (!patronForm.getNewPassword().isEmpty()) {
					patronProfile.setNewPassword(patronForm.getNewPassword());
				}
			}
			
		}
		
		return patronProfile;
		
	}
	
	/**
	 * Get the submitted subscription organization Ids
	 * Compare to the current patron subscription list
	 * If current patron subscription list has organizationId but not submitted, subscribed => false
	 * If current patron subscription list does not have organizationId, but submitted, subscribed => true
	 */
	public PatronSolicitationUpdateRequest createPatronSolicitationUpdate(PatronSubscriptionsForm patronSubscriptionsForm, PatronSolicitation patronSolicitation) {
		
		PatronSolicitationUpdateRequest patronSolicitationUpdateRequest = new PatronSolicitationUpdateRequest();
		
		if (patronSolicitation == null) {
			patronSolicitation = new PatronSolicitation();
		}
		
		List<String> formSolicitationList = null;
		if (patronSubscriptionsForm != null && patronSubscriptionsForm.getSolicitationList() != null) {
			formSolicitationList = patronSubscriptionsForm.getSolicitationList();
		}
			
		/**
		 * @TODO
		 * Refactor this chunk to be efficient
		 */
		for (Solicitation solicitation : patronSolicitation.getSolicitationList()) {
			boolean skip = false;
			if (formSolicitationList != null) {
				for (String formOrganization : formSolicitationList) {
					String[] splitOutput = formOrganization.split(":");		// e.g. Tenant:1 or Promoter:123
					String formSolicitationType = splitOutput[0];
					Long formOrganizationId = Long.valueOf(splitOutput[1]);
					if (solicitation.getSolicitationType().equals(formSolicitationType) && solicitation.getOrganizationID().equals(formOrganizationId)) {
						// There was a match, subscribed (continue)
						SolicitationUpdate solicitationUpdate = new SolicitationUpdate();
						solicitationUpdate.setOrganizationID(solicitation.getOrganizationID());
						solicitationUpdate.setSolicitationType(solicitation.getSolicitationType());
						solicitationUpdate.setSubscribed(true);
						patronSolicitationUpdateRequest.addSolicitation(solicitationUpdate);
						skip = true;
						continue;
					}
				}	// reached end of inner loop
			}
			if (skip == true) {	// if continue invoked in inner loop
				continue;
			}
			// not inside the form subscriptiones, means want to unsubscribe
			SolicitationUpdate solicitationUpdate = new SolicitationUpdate();
			solicitationUpdate.setOrganizationID(solicitation.getOrganizationID());
			solicitationUpdate.setSolicitationType(solicitation.getSolicitationType());
			solicitationUpdate.setSubscribed(false);
			patronSolicitationUpdateRequest.addSolicitation(solicitationUpdate);
		}
		
		return patronSolicitationUpdateRequest;
	}
	
	/**
	 * Caters for subscriptions through shopping cart form
	 * User registers based on Organization Type instead of submitting individual organizationIds to subscribe to
	 */
	public PatronSolicitationUpdateRequest createPatronSolicitationByOrganizationTypes(List<String> subscribedOrganizationTypes, PatronSolicitation patronSolicitation) {
		
		if (subscribedOrganizationTypes != null) {
			PatronSolicitationUpdateRequest patronSolicitationUpdateRequest = new PatronSolicitationUpdateRequest();
			Map<String, Collection<Solicitation>> solicitationsGroupByType = patronSolicitation.getSolicitationListGroupByType();
			List<SolicitationUpdate> solicitationList = new ArrayList<SolicitationUpdate>();

			for (String organizationType : subscribedOrganizationTypes) {
				if (solicitationsGroupByType.containsKey(organizationType)) {
					Collection<Solicitation> solicitationsForType = solicitationsGroupByType.get(organizationType);
					List<SolicitationUpdate> solicitationUpdateForTypeList = solicitationsForType.stream()
							.map(s -> new SolicitationUpdate(s.getSolicitationType(), s.getOrganizationID(), true))
							.collect(Collectors.toList());
					solicitationList.addAll(solicitationUpdateForTypeList);
				}
			}
			patronSolicitationUpdateRequest.setSolicitationList(solicitationList);
			
			return patronSolicitationUpdateRequest;
		}
		else {	// subscribedOrganizationTypes == null
			return null;
		}
	}
	
	/**
	 * New password and Confirm password needs to match
	 * @param patronForm
	 * @return
	 */
	public Boolean crossCheckPasswords(PatronForm patronForm, String requestType) {
		
		String newPassword = patronForm.getNewPassword();
		String confirmNewPassword = patronForm.getConfirmPassword();
		
		if (requestType.equals("MANAGE")) {
			if (newPassword != null && confirmNewPassword != null) {
				return newPassword.equals(confirmNewPassword);
			}
		}
		
		return null;		
		
	}
	
	// TODO: Review this. How to manage defaults for different whitelabels.
	public PatronForm setPatronFormDefaults(PatronForm patronForm) {
		
		String defaultCallingCode = TenantContextHolder.getTenant().getDefaultCallingCode();
		String defaultCountryCode = TenantContextHolder.getTenant().getDefaultCountryCode();
		String defaultIdType = TenantContextHolder.getTenant().getDefaultIdType();
		
		if (patronForm.getMobileCallingCode() == null || patronForm.getMobileCallingCode().isEmpty()) {
			patronForm.setMobileCallingCode(defaultCallingCode + "," + defaultCountryCode);
		}
		if (patronForm.getHomeCallingCode() == null || patronForm.getHomeCallingCode().isEmpty()) {
			patronForm.setHomeCallingCode(defaultCallingCode + "," + defaultCountryCode);
		}
		if (patronForm.getOtherCallingCode() == null || patronForm.getOtherCallingCode().isEmpty()) {
			patronForm.setOtherCallingCode(defaultCallingCode + "," + defaultCountryCode);
		}
		if (patronForm.getNationality() == null || patronForm.getNationality().isEmpty()) {
			patronForm.setNationality(defaultCountryCode);
		}
		if (patronForm.getCountry() == null || patronForm.getCountry().isEmpty()) {
			patronForm.setCountry(defaultCountryCode);
		}
		if (patronForm.getIdType() == null || patronForm.getIdType().isEmpty()) {
			patronForm.setIdType(defaultIdType);
		}
		if (patronForm.getBillingCountry() == null || patronForm.getBillingCountry().isEmpty()) {
			patronForm.setBillingCountry(defaultCountryCode);
		}
		if (patronForm.getMailingCountry() == null || patronForm.getMailingCountry().isEmpty()) {
			patronForm.setMailingCountry(defaultCountryCode);
		}
		
		return patronForm;
	}
	
	public PatronPhone findMobilePhone(List<PatronPhone> phoneNumbers) {
		for (PatronPhone phone : phoneNumbers) {
			if (MobileType.equals(phone.getContactType())) {
				return phone;
			}
		}
		return new PatronPhone();
	}
	
	public PatronPhone findHomePhone(List<PatronPhone> phoneNumbers) {
		for (PatronPhone phone : phoneNumbers) {
			if (HomeType.equals(phone.getContactType())) {
				return phone;
			}
		}
		return new PatronPhone();
	}
	
	public PatronPhone findOtherPhone(List<PatronPhone> phoneNumbers) {
		for (PatronPhone phone : phoneNumbers) {
			if (OtherType.equals(phone.getContactType())) {
				return phone;
			}
		}
		return new PatronPhone();
	}
	
	public Address findMailingAddress(List<Address> addresses) {
		for (Address address : addresses) {
			if (MailingAddressType.equals(address.getType())) {
				return address;
			}
		}
		return new Address();
	}
	
	public Address findBillingAddress(List<Address> addresses) {
		for (Address address : addresses) {
			if (BillingAddressType.equals(address.getType())) {
				return address;
			}
		}
		return new Address();
	}
	
	public Country findCountryFromCallingCode(List<Country> countries, Short callingCode) {
		Optional<Country> country = countries.stream().filter(c -> Short.valueOf(c.getCountryCallingCode()).equals(callingCode)).findFirst();
		return country.orElse(null);
	}

}

package com.stixcloud.patron.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.domain.PatronPhone;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.constant.PatronConstant;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.Country;
import com.stixcloud.patron.repo.IPatronPhoneRepository;

@Service
public class PatronPhoneService implements IServiceUpdate {

  private static final Logger logger = LogManager.getLogger(PatronPhoneService.class);

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IPatronPhoneRepository repo;
  @Autowired
  private ICountryService countryService;

  @Override
  public boolean isChanged(PatronProfileJson request, PatronProfileJson original) {
    
    return ( (null != request.getContacts() && null == original.getContacts())
        || (null == request.getContacts() && null != original.getContacts())
        || null!=request.getContacts()&&!request.getContacts().equals(original.getContacts()));
  }

  @Override
  public void updatePatron(PatronProfileJson request, Long entityId, Long userInfoId) throws SisticApiException {
    logger.info("Update patron phone is in progress.");
    List<PatronPhone> patronPhone = repo.findByPatronProfileId(entityId);
    List<ContactJson> contactNos = request.getContacts();
    List<String> contactNoType =
        contactNos.stream().map(ContactJson::getContactType).collect(Collectors.toList());
    if (!contactNoType.contains(PatronConstant.PATRON_PHONE_MOBILE.getValue())) {
      throw new SisticApiException("patron.phone.missing.mobile.error",messageSource.getMessage("patron.phone.missing.mobile.error", null,
          LocaleContextHolder.getLocale()));
    }
    List<Country> countries = countryService.fillAllCountry();
    if (patronPhone != null && !patronPhone.isEmpty()) {
      // Case update
      for (PatronPhone patron : patronPhone) {

        ContactJson contact = request.getContacts().stream()
            .filter(p -> patron.getPhonetype().equals(p.getContactType())).findFirst().orElse(null);
        if (contact != null && null != contact.getCountry()) {
          String phoneNumber =
              contact.getPhoneNumber() == null || contact.getPhoneNumber().equals("null")
                  || StringUtils.isEmpty(contact.getPhoneNumber()) ? " " : contact.getPhoneNumber();
          patron.setPhonenumber(phoneNumber);
          Country country = countries.stream()
              .filter(c -> c.getCountryCode().equals(contact.getCountry().getCode())).findFirst()
              .orElse(null);
          if (country == null) {
            logger.error("Can't get country for phone number");
            throw new SisticApiException("patron.profile.invalid.contact.error", messageSource.getMessage(
                "patron.profile.invalid.contact.error", null, LocaleContextHolder.getLocale()));
          }
          patron.setCountryId(country.getCountryID());
        }

      } ;
      repo.save(patronPhone);
      // Case add new patron phone
      List<String> patronPhoneType =
          patronPhone.stream().map(PatronPhone::getPhonetype).collect(Collectors.toList());
      List<ContactJson> newContactNos = contactNos.stream()
          .filter(p -> !patronPhoneType.contains(p.getContactType())).collect(Collectors.toList());
      if (newContactNos != null && !newContactNos.isEmpty()) {
        List<PatronPhone> patronPhoneInsert = new ArrayList<PatronPhone>();

        newContactNos.forEach(p -> {
          Country country =
              countries.stream().filter(c -> c.getCountryCode().equals(p.getCountry().getCode()))
                  .findFirst().orElse(null);
          if (country != null) {
            String phoneNumber = p.getPhoneNumber() == null || p.getPhoneNumber().equals("null")
                || StringUtils.isEmpty(p.getPhoneNumber()) ? " " : p.getPhoneNumber();
            PatronPhone phone = new PatronPhone(false, entityId, p.getContactType(),
                country.getCountryID(), null, phoneNumber);
            patronPhoneInsert.add(phone);
          }

        });
        if (!patronPhoneInsert.isEmpty()) {
          repo.save(patronPhoneInsert);
        }

      }
      // Case delete patron phone
      List<String> contactNosType =
          contactNos.stream().map(ContactJson::getContactType).collect(Collectors.toList());
      List<PatronPhone> deletePatronPhone = patronPhone.stream()
          .filter(p -> !contactNosType.contains(p.getPhonetype())).collect(Collectors.toList());
      if (deletePatronPhone != null && !deletePatronPhone.isEmpty()) {
        repo.delete(deletePatronPhone);
      }

      logger.info("Update patron phone was successful.");
    }
  }

  public void insertPatronPhone(List<PatronPhone> patron) {
    repo.save(patron);
  }
  
  /**
   * insert patron contact
   * @param patronProfileId
   * @param contactNos
   * @throws SisticApiException
   */
  public void insertPatronContact(Long patronProfileId, List<ContactJson> contactNos) throws SisticApiException{
	  
	  if(null == contactNos || contactNos.isEmpty()){
		  return;
	  }
	  
		List<Country> countries = countryService.fillAllCountry();
		List<PatronPhone> patronPhones = new ArrayList<PatronPhone>();
		for (ContactJson contact : contactNos) {
			Country country = null;
			if(null != contact.getCountry()){
				country = countries.stream().filter(p -> p.getCountryCode().equals( contact.getCountry().getCode()))
						.findFirst().orElse(null);
				if (country == null) {
					logger.error("Can't get country for phone number");
					throw new SisticApiException("patron.profile.invalid.contact.error", messageSource.getMessage("patron.profile.invalid.contact.error", null,
							LocaleContextHolder.getLocale()));
				}
			}

			String phoneNumber = null != contact && StringUtils.isNotBlank(contact.getPhoneNumber()) ? contact.getPhoneNumber() : " ";
			Long countryId = null != country ? country.getCountryID() : null;
			PatronPhone patronPhone = new PatronPhone(
					PatronConstant.PATRON_PHONE_MOBILE.getValue().equals(contact.getContactType()), patronProfileId,
					contact.getContactType(), countryId, null, phoneNumber);
			patronPhones.add(patronPhone);
		}

		insertPatronPhone(patronPhones);
		logger.info("Insert patron phone was successful");
  }

}

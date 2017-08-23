package com.stixcloud.patron.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.CountryPhoneCodeJson;
import com.stixcloud.patron.api.json.IdentificationJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.constant.PatronConstant;
import com.stixcloud.patron.util.PatronProfileUtil;

public class BasicPatronValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return PatronProfileJson.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors e) {
    ValidationUtils.rejectIfEmptyOrWhitespace(e, "firstName", "patron.profile.firstname.error");
    ValidationUtils.rejectIfEmptyOrWhitespace(e, "lastName", "patron.profile.lastname.error");
    ValidationUtils.rejectIfEmptyOrWhitespace(e, "nationality", "patron.profile.nationality.error");
    ValidationUtils.rejectIfEmptyOrWhitespace(e, "countryOfResidence",
        "patron.profile.country.residence.error");

    PatronProfileJson request = (PatronProfileJson) target;
    List<ContactJson> contactNos = request.getContacts();
    IdentificationJson identification = request.getIdentification();

    // Validate contactNos is empty
    if (contactNos == null || contactNos.isEmpty()) {
      e.rejectValue("contactNos", "patron.profile.contact.error");
    }

    // Validate identification
    if (identification == null || StringUtils.isBlank(identification.getType())) {
      e.rejectValue("identification", "patron.profile.empty.identification.error");
    }

    // Validate email format
    if (!StringUtils.isBlank(request.getEmail())
        && !EmailValidator.getInstance().isValid(request.getEmail())) {
      e.rejectValue("email", "patron.email.wrong.format.error");
    }

    // Validate contactNos
    if (contactNos != null && !contactNos.isEmpty()) {
      for (ContactJson contact : contactNos) {
        if (!StringUtils.isBlank(contact.getPhoneNumber())
            && !PatronProfileUtil.isValidPhoneNumber(contact.getPhoneNumber())) {
          e.rejectValue("contactNos", "patron.phone.number.invalid.error");
        }

        if (StringUtils.isBlank(contact.getContactType())) {
          e.rejectValue("contactNos", "patron.profile.contact.error");
        }

        CountryPhoneCodeJson country = contact.getCountry();
        if (country == null || StringUtils.isBlank(country.getCode())
            || country.getCallingCode() == null) {
          e.rejectValue("contactNos", "patron.profile.invalid.contact.error");
        }
      }
    }

    if (identification != null
        && PatronConstant.PATRON_IDENTIFICATION_NRIC.getValue().equals(identification.getType())
        && StringUtils.isBlank(identification.getNo())) {
      e.rejectValue("identification", "patron.profile.missing.nric.infor.error");
    }

  }
}

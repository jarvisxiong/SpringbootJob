package com.stixcloud.patron.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.Errors;

import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.constant.PatronConstant;


public class PatronCreationRequestValidator extends BasicPatronValidator {
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PatronRequest.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors e) {
    super.validate(target, e);

    PatronRequest request = (PatronRequest) target;

    List<ContactJson> contactNos = request.getContacts();
    if (contactNos != null && !contactNos.isEmpty()) {
      List<String> contactNoType =
          contactNos.stream().map(ContactJson::getContactType).collect(Collectors.toList());
      if (!contactNoType.contains(PatronConstant.PATRON_PHONE_MOBILE.getValue())) {
        e.rejectValue("contactNos", "patron.phone.missing.mobile.error");
      }
    }
  }
}

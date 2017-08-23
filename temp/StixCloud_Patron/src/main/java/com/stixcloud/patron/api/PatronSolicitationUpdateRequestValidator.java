package com.stixcloud.patron.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.stixcloud.patron.api.json.SolicitationJson;

public class PatronSolicitationUpdateRequestValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return PatronSolicitationUpdateRequest.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors e) {

    PatronSolicitationUpdateRequest request = (PatronSolicitationUpdateRequest) target;
    List<SolicitationJson> solicitationList = request.getSolicitationList();
    if (solicitationList == null || solicitationList.isEmpty()) {
      e.rejectValue("solicitationList", "patron.subsciptions.invalid.request.error");
    }

    if (solicitationList != null && !solicitationList.isEmpty()) {
      solicitationList.forEach(p -> {
        if (StringUtils.isBlank(p.getSolicitationType())) {
          e.rejectValue("solicitationList", "patron.subsciptions.missing.mandatory.field.error");
        }
        
        if (p.getOrganizationID() == null) {
          e.rejectValue("solicitationList", "patron.subsciptions.missing.mandatory.field.error");
        }

        if (p.getSubscribed() == null) {
          e.rejectValue("solicitationList", "patron.subsciptions.missing.mandatory.field.error");
        }
      });
    }
  }

}

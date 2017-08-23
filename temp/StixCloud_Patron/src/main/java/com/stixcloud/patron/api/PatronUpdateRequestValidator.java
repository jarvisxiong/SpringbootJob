package com.stixcloud.patron.api;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class PatronUpdateRequestValidator extends BasicPatronValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return PatronUpdateRequest.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors e) {
    //all fields is nullable
  }
}

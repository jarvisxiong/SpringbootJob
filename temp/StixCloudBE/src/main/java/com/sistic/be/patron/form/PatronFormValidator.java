package com.sistic.be.patron.form;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PatronFormValidator implements Validator {

	// which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> clazz) {
		return PatronForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "emailAddress", "emailAddress.required");
	}

}

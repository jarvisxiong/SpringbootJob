package com.stixcloud.cart;

import static org.apache.commons.validator.routines.CreditCardValidator.AMEX_VALIDATOR;
import static org.apache.commons.validator.routines.CreditCardValidator.DINERS_VALIDATOR;
import static org.apache.commons.validator.routines.CreditCardValidator.MASTERCARD_VALIDATOR;
import static org.apache.commons.validator.routines.CreditCardValidator.VISA_VALIDATOR;
import static org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit.LUHN_CHECK_DIGIT;

import org.apache.commons.validator.routines.CodeValidator;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.util.Arrays;

/**
 * Created by chongye on 30/11/2016.
 */
public enum CreditCardType {
  UNKNOWN,
  VISA {
    public CodeValidator getCodeValidator() {
      return VISA_VALIDATOR;
    }
  },
  MASTER {
    public CodeValidator getCodeValidator() {
      return MASTERCARD_VALIDATOR;
    }
  },
  DINERS {
    public CodeValidator getCodeValidator() {
      return DINERS_VALIDATOR;
    }
  },
  JCB {
    public CodeValidator getCodeValidator() {
      RegexValidator regexValidator = new RegexValidator(new String[]{
          "^(352[89]\\d{12})$", "^(35[3-8][0-9]\\d{12})$"
      });
      return new CodeValidator(regexValidator, LUHN_CHECK_DIGIT);
    }
  },
  AMEX {
    public CodeValidator getCodeValidator() {
      return AMEX_VALIDATOR;
    }
  },
  CUP {
    public CodeValidator getCodeValidator() {
      RegexValidator regexValidator = new RegexValidator(new String[]{
          "^62[0-5]\\d{13,16}$"
      });
      return new CodeValidator(regexValidator, null);
    }
  };

  public CodeValidator getCodeValidator() {
    throw new AbstractMethodError();
  }

  public boolean isValid(String creditCardNumber) {
    CreditCardValidator validator =
        new CreditCardValidator(new CodeValidator[]{this.getCodeValidator()});
    return validator.isValid(creditCardNumber);
  }

  public static CreditCardType getCreditCardPattern(String paymentMethodCode) {
    return Arrays.stream(CreditCardType.values())
        .filter(pattern -> paymentMethodCode.contains(pattern.toString()))
        .findFirst().orElse(UNKNOWN);
  }
}

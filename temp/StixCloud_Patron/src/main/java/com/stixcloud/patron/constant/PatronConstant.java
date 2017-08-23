package com.stixcloud.patron.constant;

public enum PatronConstant {

  PATRON_PHONE_MOBILE("MOBILE"),
  PATRON_ADDRESS_PRIMARY("Mailing Address"),
  PATRON_IDENTIFICATION_NRIC("NRIC/FIN"),
  MASTER_CODE_TITLE_CATEGORY("Title"),
  MASTER_CODE_GENDER_CATEGORY("Gender"),
  MASTER_CODE_GENDER_MALE("M"),
  MASTER_CODE_GENDER_FEMALE("F"),
  MASTER_CODE_TITLE_MR("MR"),
  MASTER_CODE_TITLE_MS("MS"),
  MASTER_CODE_TITLE_MRS("MRS"),
  MASTER_CODE_TITLE_MDM("MDM"),
  MASTER_CODE_TITLE_DR("DR"),
  MASTER_CODE_TITLE_PROF("PROF"),
  MASTER_CODE_CATEGORY_IDENTITY_NO("Identification Number Type"),
  MASTER_CODE_CATEGORY_GENDER("Gender"),
  MASTER_CODE_CATEGORY_TITLE("Title"),
  MASTER_CODE_CATEGORY_PATRON_ACCOUNT_TYPE("Patron Account Type");
  String value;
  
  PatronConstant(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return this.value;
  }
}

package com.stixcloud.patron.constant;

public class EmailConstants {

  public static final String FORGOT_USER_PASSWORD = "forgotPassword.vm";

  public static final String NEW_INTERNET_ACCOUNT_EMAIL_TEMPLATE_LOCATION =
      "newPatronInternetAccount.vm";

  public static final String RESEND_PASSWORD_EMAIL_TEMPLATE_LOCATION = "resendPatronPassword.vm";

  public static final String RESET_PASSWORD_EMAIL_TEMPLATE_LOCATION = "resetPatronPassword.vm";

  public static final String NEW_USER_PASSWORD = "newPassword.vm";

  public static final String EMAIL_CONFIRMATION = "emailConfirmation.vm";

  /**
   * @author devin For 0010798 - Enhancement to new account creation - removal of password in email
   *         content Issue 10759
   */
  public static final String SEND_TOKEN = "sendToken.vm";

  /**
   * @author devin for 0011934 - To include reset password link in account creation email for
   *         account created via mobile app Related to 0010798
   */
  public static final String SEND_TOKEN_NEW_PATRON_INTERNET_ACCOUNT_MOBILE =
      "sendTokenNewPatronInternetAccount.vm";

  public static final String EMAIL_SENDER = "customerservice@sistic.com.sg";

  public static final String NEW_USER_ACCOUNT_CREATION_SUBJECT =
      "New User Creation - login details";
  public static final String PASSWORD_RESET_SUBJECT = "SISTIC Account password reset link";

  // add for season parking
  public static final String EMAIL_UPDATE_SEASON_PARKING = "updateSeasonParking.vm";
  public static final String TENANT_BASED_FROM_ADDRESS = "TENANT_BASED_FROM_ADDRESS";
  public static final String EMAIL_SUBJECT_PASSWORD_RESET = "EMAIL_SUBJECT_PASSWORD_RESET";
  public static final String EMAIL_TEMPLATE_REGISTRATION_TYPE = "Registration";
  public static final String EMAIL_TEMPLATE_FORGOT_PASSWORD_TYPE = "ForgotPassword";
  public static final String NEW_INTERNET_ACCOUNT_EMAIL_TITLE = "NEW_INTERNET_ACCOUNT_EMAIL_TITLE";
}

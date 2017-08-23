	/**
	 * @author devin
	 * For 0010798 - Enhancement to new account creation - removal of password in email content Issue 10759
	 */

package com.stixcloud.patron.constant;

public enum TokenConstants {

	//StixCloud stuff
	//24 hours timeout
	//currently not in use, gets the expiry param (check the config name below) from database instead
	//check the SQL script provided
	DEFAULT_EXPIRYTIME_IN_MS("86400000"),
	DEFAULT_EXPIRYTIME_IN_SECONDS("86400"),
	DEFAULT_EXPIRYTIME_IN_MINUTES("1440"),
	DEFAULT_EXPIRYTIME_IN_HOURS("24"),
	
	//Date Format
	DATE_FORMAT("DD-MON-RR HH:MI:SS AM TZR"),
	TIMEZONE_SINGAPORE("Asia/Singapore"),
	
	//Origin
	STIX("Stix"),
	STIX_CLOUD("StixCloud"),
	BOOKING_ENGINE("Booking Engine"),
	MOBILE_APP("Mobile App"),
	
	//Active status
    INACTIVE("0"),
    ACTIVE("1"),
	
	//request user type
    //patron is customer, user is well.. stix cloud users.
	PATRON("Patron"),
	USER("User"),
	
	//config name
	DB_TOKEN_EXPIRY_TIME("TOKEN_EXPIRY_TIME"),
	DB_ACTIVATION_LINK("ACTIVATION_LINK"),
	
	//Booking engine stuff
	//booking engine forward URLs
	URL_RESEND_TOKEN("resendToken"),
	URL_RESET_PASSWORD("resetPassword"),
	URL_INVALID("invalidURL"),
	URL_ERROR("error"),
	URL_SUCCESS("success"),
	
	//E-mail subject
	EMAIL_SUBJECT("SISTIC Account password reset link"),
	EMAIL_SUBJECT_MOBILE_APP("Your new SISTIC account has been created!"),
	EMAIL_UPDATE_SEASONPARKING("You have update season parking successfully!"),
	//email activation link URL
	//local and dev might be based on your HOST file.
	URL_LOCAL("http://sistic.localhost.com:8090/SisticWebApp/ResetPassword.do?"),
	URL_DEV02("http://sistic.stixclouddev02.com/SisticWebApp/ResetPassword.do?"),
	URL_UAT("http://booking.sistic.com.sg/SisticWebApp/ResetPassword.do?"),
	
	TOKEN_REDIRECT_REQUEST("TOKEN_REDIRECT_REQUEST"),
	ACTIVATION_LINK("ACTIVATION_LINK"),
	SUFFIX_ACTIVATION_LINK("template_path"),
	//error messages
	
	//MSG_TOKEN_NOT_FOUND("Error: The URL entered is incorrect. Please refer to the URL link sent to your email again"),
	MSG_TOKEN_NOT_FOUND("0"),
	//MSG_HASH_EMAIL_NOT_MATCH("Your E-mail address and Token does not match. Please request for another Token to be sent to you."),
	MSG_HASH_EMAIL_NOT_MATCH("1"),
	//MSG_TOKEN_EXPIRED("Your Token has already Expired. Please request for another Token to be sent to you."),
	MSG_TOKEN_EXPIRED("2"),
	//MSG_TOKEN_INVALIDATED("Token Verification was a success, token has been invalidated.");
	MSG_TOKEN_INVALIDATED("3"),
	//Error: This URL has been used/expired. Please enter your email address below and we will send a new link for you to change your password.
	MSG_TOKEN_INVALIDATED_EXPIRED("4");

	
	private String value;
	
	/**
	 * Instantiates a new Token constants.
	 *
	 * @param value the value
	 */
	TokenConstants(String value) {
	this.value=value;
	}

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value(){
		return value;
	}

}

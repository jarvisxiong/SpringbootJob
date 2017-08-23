package com.stixcloud.patron.constant;

/**
 * Created by sengkai on 12/23/2016.
 */
public class PatronSubscriptionEnum {

  //private final String test = "";

  PatronSubscriptionEnum() {
  }

  public enum MasterTableCategoryCode {
    SOLICITATION_TYPE("Solicitation Type"),
    SOLICITATION_LABEL("Solicitation Label");

    String masterCodeTable_CategoryCode;

    MasterTableCategoryCode(String masterCodeTable_CategoryCode) {
      this.masterCodeTable_CategoryCode = masterCodeTable_CategoryCode;
    }

    public String masterCodeTable_CategoryCode() {
      return masterCodeTable_CategoryCode;
    }
  }

  public enum SubscriptionType {

    TENANT("Tenant"),
    PROMOTER("Promoter"),
    VENUE("Venue"),
    UNSUBSCRIBE("Unsubscribe"),
    PROMOTER_SMS("Promoter SMS"),
    PROMOTER_SUB_EMAIL("PromoterSubEmail"),
    PROMOTER_DIRECT_MAIL_LOCAL("PromoterDirectMailLocal"),
    ORGANIZER_PHONE("PromoterDirectMail"),
    ORGANIZER_POST("PromoterDirectMail");

    String mctDescription;

    private SubscriptionType(String mctDescription) {
      this.mctDescription = mctDescription;
    }

    public String getMctDescription() {
      return mctDescription;
    }
  }

  public enum SubscriptionLabel {
    TENANT("TenantLabel"), UNSUBSCRIBE("UnsubscribeLabel");

    String mctLabelDescription;

    private SubscriptionLabel(String mctLabelDescription) {
      this.mctLabelDescription = mctLabelDescription;
    }

    public String getMctLabelDescription() {
      return mctLabelDescription;
    }
  }
}

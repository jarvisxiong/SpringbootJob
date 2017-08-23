package com.stixcloud.cart.rules.precommit;

import com.stixcloud.cart.AddressEnum;
import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.PatronSubscriptionEnum;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.api.AddressJson;
import com.stixcloud.cart.api.Flags;
import com.stixcloud.cart.repo.MasterCodeTableRepository;
import com.stixcloud.cart.repo.PatronProfileRepository;
import com.stixcloud.cart.repo.PatronSolicitationRepository;
import com.stixcloud.cart.rules.BaseCartRule;
import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.PatronSolicitation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by sengkai on 12/23/2016.
 */
@SpringRule
public class PatronSolicitationRule extends BaseCartRule {
  private static final Logger logger = LogManager.getLogger(AddressRule.class);

  List<PatronSolicitation>
      updatedPatronSolicitationList;
  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IShoppingCartService shoppingCartService;
  @Autowired
  private PatronProfileRepository patronProfileRepository;
  @Autowired
  private PatronSolicitationRepository patronSolicitationRepository;
  @Autowired
  private MasterCodeTableRepository masterCodeTableRepository;

  private Long patronProfileId;
  private com.stixcloud.cart.api.PatronSolicitation patronSolicitation;
  private List<AddressJson> addressJsonList;

  private Long updatedBy;

  public void setPatronProfileId(Long patronProfileId) {
    this.patronProfileId = patronProfileId;
  }

  public void setPatronSolicitation(com.stixcloud.cart.api.PatronSolicitation patronSolicitation) {
    this.patronSolicitation = patronSolicitation;
  }

  public void setAddressJsonList(List<AddressJson> addressJsonList) {
    this.addressJsonList = addressJsonList;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty()
        && this.patronSolicitation != null;
  }

  @Action(order = 1)
  public void updatePatronSubscriptionFlags() {
    this.setExecuted(true);

    PatronProfile patronProfile = patronProfileRepository.findOne(this.patronProfileId);
    Flags flags = null;

    if (this.patronSolicitation.getFlags() != null) {

      flags = this.patronSolicitation.getFlags();
      patronProfile.setIsdonotsignup(flags.getDoNotSignUp());
      patronProfile.setIsreceivepromoter(flags.getReceivePromoter());
      patronProfile.setIsreceiveticketingagent(flags.getReceiveSistic());
      patronProfile.setIsreceivevenue(flags.getReceiveVenue());

      patronProfileRepository.save(patronProfile);
    } else {
      flags = new Flags();
      flags.setDoNotSignUp(patronProfile.getIsdonotsignup());
      flags.setReceivePromoter(patronProfile.getIsreceivepromoter());
      flags.setReceiveSistic(patronProfile.getIsreceiveticketingagent());
      flags.setReceiveVenue(patronProfile.getIsreceivevenue());

      this.patronSolicitation.setFlags(flags);
    }
  }

  @Action(order = 2)
  public void decodePatronSolicitation() {
    this.setExecuted(true);
    //Update patron_solicitation
    List<MasterCodeTable>
        solicitationTypeList =
        masterCodeTableRepository.retrieveCategoryList(
            PatronSubscriptionEnum.MasterTableCategoryCode.SOLICITATION_TYPE
                .masterCodeTable_CategoryCode());

    List<MasterCodeTable>
        solicitationLabelList =
        masterCodeTableRepository.retrieveCategoryList(
            PatronSubscriptionEnum.MasterTableCategoryCode.SOLICITATION_LABEL
                .masterCodeTable_CategoryCode());

    //Retrieve DB Patron Solicitation List
    updatedPatronSolicitationList =
        patronSolicitationRepository.getPatronSolicitationList(this.patronProfileId);
    logger.debug("Current Time :: " + OffsetDateTime.now().toString());
    //logger.debug("Size of PatronSolicitationList :: " + updatedPatronSolicitationList.size());
    //Construct the Json Object to Domain Object
    this.patronSolicitation.getSolicitationList().stream()
        .forEach(solicitation -> {
          //Decode Type & Label
          MasterCodeTable
              solicitationType =
              solicitationTypeList.stream().filter(
                  masterCodeTable -> masterCodeTable.getDescription1()
                      .equalsIgnoreCase(solicitation.getSolicitationType()))
                  .findAny().orElse(null);

          MasterCodeTable
              solicitationLabel =
              solicitationLabelList.stream().filter(
                  masterCodeTable -> masterCodeTable.getDescription1()
                      .equalsIgnoreCase((this.patronSolicitation.getFlags()
                          .getReceiveSistic() == true
                          ? PatronSubscriptionEnum.SubscriptionLabel.TENANT.getMctLabelDescription()
                          : null)))
                  .findAny().orElse(null);

          PatronSolicitation patronSolicitationDomain = new PatronSolicitation(
              this.patronProfileId, solicitationType.getMastercodeid(),
              (solicitationLabel != null ? solicitationLabel.getMastercodeid()
                  : null), solicitation.getOrganizationID(),
              solicitation.getSubscribed(), OffsetDateTime.now(), this.updatedBy
          );

          /**
           * Update existing field or to create if it does not exist
           */
          boolean
              addDomain =
              updatedPatronSolicitationList.stream().noneMatch(patronSolicitation -> {
                if (patronSolicitation.getSolicitationTypeMctId()
                    .equals(patronSolicitationDomain.getSolicitationTypeMctId())
                    && patronSolicitation.getOrganizationId()
                    .equals(patronSolicitationDomain.getOrganizationId())) {
                  patronSolicitation
                      .setSubscriptionstatus(patronSolicitationDomain.getSubscriptionstatus());
                  patronSolicitation.setUpdatedBy(this.updatedBy);
                  patronSolicitation.setUpdateddate(OffsetDateTime.now());
                  return true;
                }
                return false;
              });

          logger.debug("Results [Add Domain]:: " + addDomain);
          if (addDomain) {
            updatedPatronSolicitationList.add(patronSolicitationDomain);
          }
        });
  }

  @Action(order = 3)
  public void validatePatronSubscriptionType() throws ValidateCartException {
    this.setExecuted(true);
    if (this.getShoppingCart().getAddressList() != null
        && this.updatedPatronSolicitationList != null) {
      boolean
          isTypeFound =
          this.patronSolicitation.getSolicitationList().stream()
              .anyMatch(patronSolicitation -> patronSolicitation.getSolicitationType()
                  .equalsIgnoreCase(
                      PatronSubscriptionEnum.SubscriptionType.PROMOTER_DIRECT_MAIL_LOCAL
                          .getMctDescription()));

      if (isTypeFound) {

        boolean noMatch = this.addressJsonList.stream()
            .noneMatch(addressJson -> addressJson.getType().equalsIgnoreCase(
                AddressEnum.MAILING.getBEDescription())
                && addressJson.getCountry()
                .equalsIgnoreCase(AddressEnum.SINGAPORE.getBEDescription()));

        if (noMatch) {
          throw new ValidateCartException(
              messageSource
                  .getMessage("precommit.error.patronSolicitation.invalidMailingCountry", null,
                      LocaleContextHolder
                          .getLocale()));
        }
      }

      logger.debug(this.updatedPatronSolicitationList.toString());

      patronSolicitationRepository.save(this.updatedPatronSolicitationList);
    }
  }
}

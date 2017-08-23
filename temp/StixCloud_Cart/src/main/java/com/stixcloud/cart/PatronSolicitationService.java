package com.stixcloud.cart;

import com.stixcloud.cart.api.Flags;
import com.stixcloud.cart.api.PatronSolicitation;
import com.stixcloud.cart.api.Solicitation;
import com.stixcloud.cart.repo.MasterCodeTableRepository;
import com.stixcloud.cart.repo.PatronProfileRepository;
import com.stixcloud.cart.repo.PatronSolicitationRepository;
import com.stixcloud.cart.repo.ProductLiveRepository;
import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.domain.Organization;
import com.stixcloud.domain.PatronProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sengkai on 12/6/2016.
 */
@Service
public class PatronSolicitationService implements IPatronSolicitationService {
  public static final Logger logger = LogManager.getLogger(PatronSolicitationService.class);

  @Autowired
  private PatronSolicitationRepository patronSolicitationRepository;
  @Autowired
  private PatronProfileRepository patronProfileRepository;
  @Autowired
  private MasterCodeTableRepository masterCodeTableRepository;
  @Autowired
  private ProductLiveRepository productLiveRepository;

  @Override
  public PatronSolicitation retrievePatronSolicitation(Long patronprofileid) {
    List<SolicitationDto>
        solicitationList =
        patronSolicitationRepository.retrievePatronSolicitation(patronprofileid);

    PatronSolicitation patronSolicitationJson = null;
    Flags patronSolicitationFlags = new Flags.Builder().build();
    List<Solicitation> solicitationListJsonList = new ArrayList<Solicitation>();

    solicitationList.stream().forEach(
        solicitationDto -> {
          Solicitation s = new Solicitation.Builder()
              .solicitationType(solicitationDto.getSolicitationType())
              .organizationID(solicitationDto.getOrganizationid())
              .subscribed(solicitationDto.getSubscriptionstatus())
              .build();

          patronSolicitationFlags
              .setDoNotSignUp(solicitationDto.getPatronProfile().getIsdonotsignup());
          patronSolicitationFlags
              .setReceiveSistic(solicitationDto.getPatronProfile().getIsreceiveticketingagent());
          patronSolicitationFlags
              .setReceiveVenue(solicitationDto.getPatronProfile().getIsreceivevenue());
          patronSolicitationFlags
              .setReceivePromoter(solicitationDto.getPatronProfile().getIsreceivepromoter());

          solicitationListJsonList.add(s);
        }
    );

    patronSolicitationJson = new PatronSolicitation.Builder()
        .flags(patronSolicitationFlags)
        .solicitationList(solicitationListJsonList)
        .build();

    return patronSolicitationJson;
  }

  @Override
  public PatronSolicitation retrievePatronSolicitation(Long patronprofileid,
                                                       List<Long> productIds) {
    List<SolicitationDto>
        solicitationList =
        patronSolicitationRepository.retrievePatronSolicitation(patronprofileid);

    List<Organization>
        promoterSubscriptionList =
        productLiveRepository.retrieveAvailPromoterList(productIds);

    List<Organization>
        venueSubscriptionList =
        productLiveRepository.retrieveAvailVenueList(productIds);

    PatronSolicitation patronSolicitationJson = null;
    Flags patronSolicitationFlags = new Flags.Builder().build();
    List<Solicitation> solicitationListJsonList = new ArrayList<Solicitation>();

    solicitationList.stream().forEach(
        solicitationDto -> {
          Solicitation s = new Solicitation.Builder()
              .solicitationType(solicitationDto.getSolicitationType())
              .organizationID(solicitationDto.getOrganizationid())
              .organizationName(solicitationDto.getOrganizationname())
              .organizationUrl(solicitationDto.getOrganizationurl())
              .subscribed(solicitationDto.getSubscriptionstatus())
              .build();

          solicitationListJsonList.add(s);
        }
    );

    promoterSubscriptionList.stream().forEach(organization -> {
      Solicitation s = new Solicitation.Builder()
          .solicitationType(PatronSubscriptionEnum.SubscriptionType.PROMOTER.getMctDescription())
          .organizationID(organization.getOrganizationid())
          .organizationName(organization.getOrganizationname())
          .organizationUrl(organization.getOrganizationurl())
          .subscribed(false)
          .build();

      solicitationListJsonList.add(s);
    });

    venueSubscriptionList.stream().forEach(organization -> {
      Solicitation s = new Solicitation.Builder()
          .solicitationType(PatronSubscriptionEnum.SubscriptionType.VENUE.getMctDescription())
          .organizationID(organization.getOrganizationid())
          .organizationName(organization.getOrganizationname())
          .organizationUrl(organization.getOrganizationurl())
          .subscribed(false)
          .build();

      solicitationListJsonList.add(s);
    });

    //Remove duplicates if there is any
    List<Solicitation> uniqueSolicitationListJsonList = solicitationListJsonList.stream()
        .filter(solicitation -> solicitation.getSubscribed() == false)
        .distinct().collect(
            Collectors.toList());

    patronSolicitationJson = new PatronSolicitation.Builder()
        //.flags(patronSolicitationFlags)
        .solicitationList(uniqueSolicitationListJsonList)
        .build();

    return patronSolicitationJson;
  }

  @Override
  public void updatePatronSolicitation(Long patronprofileid,
                                       PatronSolicitation patronSolicitation) {

    //Update patronProfile first
    //Decode the values for the following fields.
    PatronProfile patronProfile = patronProfileRepository.findOne(patronprofileid);
    Flags flags = patronSolicitation.getFlags();
    patronProfile.setIsdonotsignup(flags.getDoNotSignUp());
    patronProfile.setIsreceivepromoter(flags.getReceivePromoter());
    patronProfile.setIsreceiveticketingagent(flags.getReceiveSistic());
    patronProfile.setIsreceivevenue(flags.getReceiveVenue());

    patronProfileRepository.save(patronProfile);

    //Update patron_solicitation
    List<MasterCodeTable>
        masterCodeTableList =
        masterCodeTableRepository.retrieveCategoryList(
            PatronSubscriptionEnum.MasterTableCategoryCode.SOLICITATION_TYPE
                .masterCodeTable_CategoryCode());

    List<com.stixcloud.domain.PatronSolicitation> patronSolicitationList =
        new ArrayList<com.stixcloud.domain.PatronSolicitation>();

    /**
     * TODO:: This is assume the solicitationList contains the IDs to overwrite
     *  and any records that does not exist will be created.
     */
    patronSolicitation.getSolicitationList().stream().forEach(solicitation -> {
      Long
          solicitationTypeMctId =
          masterCodeTableList.stream().filter(masterCodeTable -> masterCodeTable.getDescription1()
              .equalsIgnoreCase(solicitation.getSolicitationType())).findAny().get()
              .getMastercodeid();

    });
  }
}

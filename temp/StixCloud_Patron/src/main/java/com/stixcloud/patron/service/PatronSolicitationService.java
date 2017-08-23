package com.stixcloud.patron.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.domain.PatronSolicitation;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.MasterTableCategoryCode;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.SubscriptionLabel;
import com.stixcloud.patron.constant.PatronSubscriptionEnum.SubscriptionType;
import com.stixcloud.patron.domain.SolicitationDto;
import com.stixcloud.patron.repo.IPatronSolicitationRepository;

@Service
public class PatronSolicitationService implements IPatronSolicitationService {
  private static final Logger logger = LogManager.getLogger(PatronService.class);
  
  @Autowired
  private IPatronSolicitationRepository repo;
  @Autowired
  private MasterCodeTableService masterCodeTableService;
  @Autowired
  private MessageSource messageSource;

  @Override
  public List<PatronSolicitation> findByPatronProfileId(Long patronId, Long userInfoId) {
    return repo.findByPatronProfileId(patronId, userInfoId);
  }

  @Override
  public void save(PatronSolicitation patronSolicitation) {
    repo.save(patronSolicitation);
  }

  @Override
  public void update(List<PatronSolicitation> pUpdate) {
    repo.save(pUpdate);
  }

  @Override
  public void delete(List<PatronSolicitation> pDelete) {
    repo.delete(pDelete);
  }

  @Override
  public List<SolicitationDto> retrievePatronSolicitation(Long patronProfileId, Long userInfoId) {
    return repo.retrievePatronSolicitation(patronProfileId, userInfoId);
  }

  /**
   * insert patron solicitation
   * @param userInfoId
   * @param patronProfileId
   * @param organizationId
   * @throws SisticApiException
   */
  @Override
  public void save(Long userInfoId, Long patronProfileId, Long organizationId) throws SisticApiException{
		List<MasterCodeTable> mctTypeList = masterCodeTableService
				.findByCategory(MasterTableCategoryCode.SOLICITATION_TYPE.masterCodeTable_CategoryCode());
		MasterCodeTable mctType = mctTypeList.stream()
				.filter(p -> SubscriptionType.TENANT.getMctDescription().equals(p.getDescription1())).findFirst()
				.orElse(null);
		if (mctType == null) {
			logger.error("Can't get solicitation type");
			throw new SisticApiException("patron.sucscriptions.invalid.solicitation.type.error", messageSource.getMessage("patron.sucscriptions.invalid.solicitation.type.error",
					null, LocaleContextHolder.getLocale()));
		}

		List<MasterCodeTable> mctLabelList = masterCodeTableService
				.findByCategory(MasterTableCategoryCode.SOLICITATION_LABEL.masterCodeTable_CategoryCode());
		MasterCodeTable mctLabel = mctLabelList.stream()
				.filter(p -> SubscriptionLabel.TENANT.getMctLabelDescription().equals(p.getDescription1())).findFirst()
				.orElse(null);
		PatronSolicitation patronSolicitation = new PatronSolicitation(patronProfileId, mctType.getMastercodeid(),
				mctLabel == null ? null : mctLabel.getMastercodeid(), organizationId, true, OffsetDateTime.now(), userInfoId);
		save(patronSolicitation);
  }
}

package com.stixcloud.patron.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronAdvanceProfile;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.repo.IPatronAdvanceProfileRepository;

@Service
public class PatronAdvanceProfileService implements IServiceUpdate {

  private static final Logger logger = LogManager.getLogger(PatronAdvanceProfileService.class);

  @Autowired
  private IPatronAdvanceProfileRepository repo;
  @Autowired
  private MessageSource messageSource;

  @Override
  public boolean isChanged(PatronProfileJson request, PatronProfileJson original) {
    if (request.getYearOfBirth() != null) {
      return !request.getYearOfBirth().equals(original.getYearOfBirth());
    } else if (original.getYearOfBirth() != null) {
      return true;
    }
    return false;
  }

  @Override
  public void updatePatron(PatronProfileJson request, Long entityId, Long userInfoId) throws SisticApiException {
    try {
      logger.info("Update patron advance profile is in progress.");
      PatronAdvanceProfile patronAdv = repo.findByPatronProfileId(entityId);

      if (request.getYearOfBirth() == null) {
        patronAdv.setDob(null);
      } else {
        OffsetDateTime dob =
            OffsetDateTime.of(request.getYearOfBirth(), 01, 01, 00, 00, 00, 0, ZoneOffset.UTC);
        patronAdv.setDob(dob);
      }

      repo.save(patronAdv);
      logger.info("Update patron advance profile was successful.");
    } catch (Exception e) {
      logger.error("Can't update patron advance profile", e);
      throw new SisticApiException("patron.advance.profile.update.error",messageSource.getMessage("patron.advance.profile.update.error",
          null, LocaleContextHolder.getLocale()));
    }
  }

  /**
   * insert patron advance profile
   * @param patron
   */
  public void insertPatronAdvanceProfile(PatronAdvanceProfile patron) {
    logger.info("Start inserting patron advance profile");
    repo.save(patron);
    logger.info("End inserting patron advance profile");
  }
  
  /**
   * insert patron advance profile
   * @param yearOfBirth
   * @param designation
   * @param patronProfileId
   * @throws Exception
   */
  public void insertPatronAdvanceProfile(Long patronProfileId, Integer yearOfBirth, String designation) throws SisticApiException{
		try {
			OffsetDateTime dob = null;
			if (yearOfBirth != null) {
				dob = OffsetDateTime.of(yearOfBirth, 01, 01, 00, 00, 00, 0, ZoneOffset.UTC);
			}

			PatronAdvanceProfile insertPatronAdv = new PatronAdvanceProfile(patronProfileId, dob, designation);
			insertPatronAdvanceProfile(insertPatronAdv);
			logger.info("Insert patron advance profile was successful");
		} catch (Exception e) {
			logger.error("Can't parse date of birth", e);
			throw new SisticApiException("patron.profile.parse.date.error",
					messageSource.getMessage("patron.profile.parse.date.error", null, LocaleContextHolder.getLocale()));
		}
  }
}

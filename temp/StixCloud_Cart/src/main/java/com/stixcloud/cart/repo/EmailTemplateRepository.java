package com.stixcloud.cart.repo;

import com.stixcloud.domain.EmailTemplate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface EmailTemplateRepository extends CrudRepository<EmailTemplate, Long> {

  @Query("SELECT e "
      + "FROM EmailTemplate e JOIN EmailTemplateMapping em ON e.emailTemplateId=em.emailTemplateId "
      + "where  e.type = :templateType "
      + "and em.entityId= :entityId and em.type= :type ")
  public EmailTemplate retrieveEmailTemplatesByCriterias(@Param("templateType") String templateType,
                                                         @Param("entityId") Long entityId,
                                                         @Param("type") Long type);

  @Query("Select e From EmailTemplate e where e.isDefault='Y' and e.type='EmailConfirmation' and e.languageCode='en_SG'")
  public EmailTemplate getDefaultEmailTemplate();
  
}

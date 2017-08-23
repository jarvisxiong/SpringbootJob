package com.stixcloud.cart.repo;

import com.stixcloud.cart.SolicitationDto;
import com.stixcloud.domain.PatronSolicitation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sengkai on 12/6/2016.
 */
public interface PatronSolicitationRepository extends CrudRepository<PatronSolicitation, Long> {

  @Query("SELECT new com.stixcloud.cart.SolicitationDto(pp, mct.description1, o.organizationid, "
      + "               o.organizationname, o.organizationurl, ps.subscriptionstatus) "
      + "FROM PatronSolicitation ps "
      + "JOIN PatronProfile pp ON ps.patronProfileId = pp.patronprofileid "
      + "JOIN Organization o ON o.organizationid = ps.organizationId "
      + "JOIN MasterCodeTable mct ON mct.mastercodeid = ps.solicitationTypeMctId "
      + "WHERE pp.patronprofileid = :patronprofileid ")
  List<SolicitationDto> retrievePatronSolicitation(@Param("patronprofileid") Long patronprofileid);

  @Query("SELECT ps "
      + "FROM PatronSolicitation ps "
      + "JOIN PatronProfile pp ON ps.patronProfileId = pp.patronprofileid "
      + "JOIN Organization o ON o.organizationid = ps.organizationId "
      + "JOIN MasterCodeTable mct ON mct.mastercodeid = ps.solicitationTypeMctId "
      + "WHERE pp.patronprofileid = :patronprofileid ")
  List<PatronSolicitation> getPatronSolicitationList(
      @Param("patronprofileid") Long patronprofileid);
}

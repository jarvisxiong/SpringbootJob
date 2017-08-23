package com.stixcloud.patron.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PatronSolicitation;
import com.stixcloud.patron.domain.SolicitationDto;

@Repository
public interface IPatronSolicitationRepository extends CrudRepository<PatronSolicitation, Long> {

  @Query("select p from PatronSolicitation p where p.patronProfileId = :patronProfileId "
      + "AND p.updatedBy = :userInfoId")
  List<PatronSolicitation> findByPatronProfileId(@Param("patronProfileId") Long patronProfileId,
      @Param("userInfoId") Long userInfoId);
  
  @Query("SELECT new com.stixcloud.patron.domain.SolicitationDto(pp, mct.description1, o.organizationid, "
      + "               o.organizationname, o.organizationurl, ps.subscriptionstatus) "
      + "FROM PatronSolicitation ps "
      + "JOIN PatronProfile pp ON ps.patronProfileId = pp.patronprofileid "
      + "JOIN Organization o ON o.organizationid = ps.organizationId "
      + "JOIN MasterCodeTable mct ON mct.mastercodeid = ps.solicitationTypeMctId "
      + "WHERE pp.patronprofileid = :patronProfileId "
      + "AND ps.updatedBy = :userInfoId")
  List<SolicitationDto> retrievePatronSolicitation(@Param("patronProfileId") Long patronProfileId,
      @Param("userInfoId") Long userInfoId);
  
  @Query("SELECT ps "
      + "FROM PatronSolicitation ps "
      + "JOIN PatronProfile pp ON ps.patronProfileId = pp.patronprofileid "
      + "JOIN Organization o ON o.organizationid = ps.organizationId "
      + "JOIN MasterCodeTable mct ON mct.mastercodeid = ps.solicitationTypeMctId "
      + "WHERE pp.patronprofileid = :patronProfileId ")
  List<PatronSolicitation> getPatronSolicitationList(
      @Param("patronProfileId") Long patronProfileId);
}

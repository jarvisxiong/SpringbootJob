package com.stixcloud.cart.repo;

import com.stixcloud.domain.PatronAdvanceProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PatronAdvanceProfileRepository extends CrudRepository<PatronAdvanceProfile, Long> {

  @Query("SELECT pap "
      + "FROM PatronAdvanceProfile pap  "
      + "  JOIN PatronProfile profile ON profile.patronprofileid = pap.patronProfileId  "
      + "WHERE profile.patronprofileid = ?1")
  PatronAdvanceProfile getPatronAdvanceProfileByPatronProfileId(Long patronProfileId);

}

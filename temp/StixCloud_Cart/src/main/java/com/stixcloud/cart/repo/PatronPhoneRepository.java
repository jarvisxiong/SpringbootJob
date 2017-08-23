package com.stixcloud.cart.repo;

import com.stixcloud.domain.PatronPhone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatronPhoneRepository extends CrudRepository<PatronPhone, Long> {
  @Query("SELECT phone "
      + "FROM PatronPhone phone  "
      + "  JOIN PatronProfile profile ON profile.patronprofileid = phone.patronProfileId  "
      + "WHERE profile.patronprofileid = ?1 and phone.phonetype='MOBILE'")
  PatronPhone getMobileNumberByPatronProfileId(Long patronProfileId);

  @Query("SELECT phone "
      + "FROM PatronPhone phone  "
      + "  JOIN PatronProfile profile ON profile.patronprofileid = phone.patronProfileId  "
      + "WHERE profile.patronprofileid = ?1")
  List<PatronPhone> getNumbersByPatronProfileId(Long patronProfileId);
}

package com.stixcloud.cart;

import static org.junit.Assert.assertTrue;

import com.stixcloud.cart.repo.PatronSolicitationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by sengkai on 12/7/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class PatronSolicitationRepositoryTest {
  private final Logger logger = LogManager.getLogger(PatronSolicitationRepositoryTest.class);

  @Autowired
  PatronSolicitationRepository patronSolicitationRepository;

  @Test
  @Sql("/cart/sql/patronSolicitation.sql")
  public void retrievePatronSolicitation() {
    List<SolicitationDto>
        solicitationList =
        patronSolicitationRepository.retrievePatronSolicitation(1921979L);

    solicitationList.forEach(System.out::println);

    assertTrue(!solicitationList.isEmpty());
    assertTrue(solicitationList.size() == 8);
  }
}

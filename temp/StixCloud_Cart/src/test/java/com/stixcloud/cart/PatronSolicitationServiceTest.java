package com.stixcloud.cart;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.api.PatronSolicitation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sengkai on 12/7/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatronSolicitationServiceTest {
  private static final Logger logger = LogManager.getLogger(PatronSolicitationServiceTest.class);

  @Autowired
  IPatronSolicitationService iPatronSolicitationService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @Sql("/cart/sql/patronSolicitation.sql")
  public void retrievePatronSolicitationList() throws Exception {
    /*PatronSolicitation
        patronSolicitation =
        iPatronSolicitationService.retrievePatronSolicitation(540711L);*/
    List<Long> prodIds = new ArrayList<Long>();
    prodIds.add(264172L);
    PatronSolicitation
        patronSolicitation =
        iPatronSolicitationService.retrievePatronSolicitation(1921979L, prodIds);

    assertThat(patronSolicitation.getSolicitationList()).isNotNull();
    assertThat(patronSolicitation.getSolicitationList()).hasSize(8);

    String
        jsonMapperOutput =
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(patronSolicitation);
    assertThat(jsonMapperOutput).isNotNull();

    logger.debug("Json Object : " + jsonMapperOutput);
  }
}

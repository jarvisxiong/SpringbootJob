package com.stixcloud.cart.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

/**
 * Created by sengkai on 12/8/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatronSolicitationControllerTest {
  private static RedisServer redisServer;
  private final Logger logger = LogManager.getLogger(PatronSolicitationControllerTest.class);
  private final String URI = "/SISTIC/patron/1921979/products";
  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private TestRestTemplate restTemplate;

  private JacksonTester<PatronSolicitation> patronSolicitationJacksonTester;

  @BeforeClass
  public static void setUp() throws Exception {

    RedisExecProvider
        customProvider =
        RedisExecProvider.defaultProvider()
            .override(OS.WINDOWS, Architecture.x86, "redis/redis-server-3.2.1.exe")
            .override(OS.WINDOWS, Architecture.x86_64, "redis/redis-server-3.2.1.exe");
    redisServer = new RedisServerBuilder().redisExecProvider(customProvider).build();
    redisServer.start();
  }

  @AfterClass
  public static void tearDown() {
    redisServer.stop();
  }

  @Before
  public void setup() {
    JacksonTester.initFields(this, objectMapper);
  }

  @Test
  @Sql({"/cart/sql/patronSolicitation.sql"})
  public void retrievePatronSolicitation() throws Exception {

    List<Long> requestByPrdtIds = new ArrayList<Long>();
    requestByPrdtIds.add(264172L);
    requestByPrdtIds.add(22713L);

    ResponseEntity<PatronSolicitation> entity = restTemplate
        .postForEntity(URI, requestByPrdtIds, PatronSolicitation.class);
    PatronSolicitation restResult = entity.getBody();

    assertEquals(entity.getStatusCode(), HttpStatus.OK);

    assertThat(patronSolicitationJacksonTester.write(restResult))
        .isEqualToJson("/cart/json/getPatronSolicitationByProdtIds.json");
  }
}

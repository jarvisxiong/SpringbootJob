package com.stixcloud.evoucher.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

public class BaseEVoucherRuleTest {

  private RulesEngine rulesEngine;
  private EVoucherRulesListener eVoucherRulesListener;
  private static RedisServer redisServer;

  @BeforeClass
  public static void setUpRedisServer() throws Exception {
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
  public void setUp() throws Exception {
    eVoucherRulesListener = new EVoucherRulesListener();
    rulesEngine = RulesEngineBuilder.aNewRulesEngine().withRuleListener(eVoucherRulesListener)
        .withSkipOnFirstFailedRule(true).build();
  }

  public RulesEngine getRulesEngine() {
    return rulesEngine;
  }

  public EVoucherRulesListener getEVoucherRulesListener() {
    return eVoucherRulesListener;
  }

  public void fireRuleAndAssertExecuted(BaseEVoucherRule eVoucherRule) {
    rulesEngine.registerRule(eVoucherRule);
    rulesEngine.fireRules();
    assertTrue(eVoucherRule.isExecuted());
  }

  public void fireRulesAndAssertError(BaseEVoucherRule rule,
                                      String message) {
    fireRuleAndAssertExecuted(rule);
    EVoucherValidationException exception = assertErrorAndGetException();
    assertThat(exception.getMessage()).isNotEmpty().isEqualTo(message);
  }

  public EVoucherValidationException assertErrorAndGetException() {
    assertTrue(eVoucherRulesListener.hasError());
    assertThat(eVoucherRulesListener.geteVoucherException()).isNotNull();

    return eVoucherRulesListener.geteVoucherException();
  }

}

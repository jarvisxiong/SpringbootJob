package com.stixcloud.cart.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;
import static org.junit.Assert.assertTrue;

import com.stixcloud.cart.CartException;
import com.stixcloud.domain.ShoppingCart;
import org.easyrules.api.Rule;
import org.easyrules.api.RulesEngine;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.Map;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

/**
 * Created by chongye on 13/10/2016.
 */
public class BaseCartRuleTest {
  private RulesEngine rulesEngine;
  private CartRulesListener cartRulesListener;
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
    cartRulesListener = new CartRulesListener();
    rulesEngine =
        aNewRulesEngine().withRuleListener(cartRulesListener).withSkipOnFirstFailedRule(true)
            .build();
  }

  public RulesEngine getRulesEngine() {
    return rulesEngine;
  }

  public CartRulesListener getCartRulesListener() {
    return cartRulesListener;
  }

  public BaseCartRule fireRulesAndAssertExecuted(BaseCartRule rule, ShoppingCart shoppingCart) {
    rule.setShoppingCart(shoppingCart);

    rulesEngine.registerRule(rule);
    rulesEngine.fireRules();

    assertTrue(rule.isExecuted());
    return rule;
  }

  public void fireRulesAndAssertException(BaseCartRule rule, ShoppingCart shoppingCart,
                                          String message) {
    fireRulesAndAssertExecuted(rule, shoppingCart);
    Map.Entry<Rule, CartException> exceptionEntry = assertListenerAndGetExceptionEntry();
    assertThat(exceptionEntry.getValue().getMessage()).isNotEmpty().isEqualTo(message);
  }

  public Map.Entry<Rule, CartException> assertListenerAndGetExceptionEntry() {
    assertTrue(cartRulesListener.hasError());
    assertThat(cartRulesListener.getFailureMessageMap()).isNotEmpty().hasSize(1);

    Map.Entry<Rule, ? super CartException> exceptionEntry =
        cartRulesListener.getFailureMessageMap().entrySet().stream().findFirst().orElse(null);
    assertThat(exceptionEntry.getValue()).isNotNull();
    return (Map.Entry<Rule, CartException>) exceptionEntry;
  }
}

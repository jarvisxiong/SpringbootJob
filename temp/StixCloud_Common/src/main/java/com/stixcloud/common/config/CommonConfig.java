package com.stixcloud.common.config;

import com.fasterxml.jackson.databind.Module;
import com.stixcloud.common.filter.Log4JFilter;
import org.javamoney.moneta.format.CurrencyStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.zalando.jackson.datatype.money.MoneyModule;

import java.util.Locale;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryFormats;
import javax.servlet.Filter;

@Configuration
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
public class CommonConfig {
  @Bean
  public Module moneyModule() {
    return new MoneyModule().withFormatFactory((final Locale locale) ->
        MonetaryFormats.getAmountFormat(
            AmountFormatQueryBuilder.of(LocaleContextHolder.getLocale()).set(CurrencyStyle.SYMBOL)
                .build()));
  }

  @Bean
  public Filter log4JFilter() {
    return new Log4JFilter();
  }
}

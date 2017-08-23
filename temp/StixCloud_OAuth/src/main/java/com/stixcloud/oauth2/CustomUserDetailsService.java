package com.stixcloud.oauth2;

import javax.sql.DataSource;

import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService extends JdbcDaoImpl {

  public static final String USERS_BY_USERNAME_QUERY =
      "SELECT userid, password, 1 AS enabled FROM patron_internet_account WHERE lower(userid) = lower(?)";
  public static final String AUTHORITIES_BY_USERNAME_QUERY =
      "SELECT userid, 'ROLE_PATRON' AS role FROM patron_internet_account WHERE lower(userid) = lower(?)";

  public CustomUserDetailsService(DataSource dataSource) {
    setJdbcTemplate(super.createJdbcTemplate(dataSource));
    setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
    setUsersByUsernameQuery(USERS_BY_USERNAME_QUERY);
  }
}

package com.stixcloud.oauth2.repo;

import com.stixcloud.oauth2.domain.PatronLogin;

public interface IPatronLoginRepository {
  public PatronLogin retrievePatronLogin(String patronEmail);
}

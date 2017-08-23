package com.stixcloud.policyagent.repo;

import com.stixcloud.policyagent.model.SessionTokenModel;

public interface SessionTokenRepository {
  void saveSessionToken(SessionTokenModel sessionToken);

  void updateSessionToken(SessionTokenModel sessionToken);

  void deleteSessionToken(String jSessionId);

  SessionTokenModel findSessionToken(String jSessionId);
}

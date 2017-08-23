package com.stixcloud.policyagent.repo;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;

import com.stixcloud.policyagent.model.SessionTokenModel;

@Repository
public class SessionTokenRepositoryImpl implements SessionTokenRepository {

  private static final Logger logger = LogManager.getLogger(SessionTokenRepositoryImpl.class);

  private RedisTemplate<String, SessionTokenModel> redisTemplate;
  private ValueOperations<String, SessionTokenModel> valueOps;

  @Autowired
  public SessionTokenRepositoryImpl(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  private void init() {
    valueOps = redisTemplate.opsForValue();
  }

  @Override
  public void saveSessionToken(SessionTokenModel sessionToken) {
    // token will be expired after a ttl milliseconds
    valueOps.set(RequestContextHolder.getRequestAttributes().getSessionId(), sessionToken, sessionToken.getTtl(), TimeUnit.MILLISECONDS);
  }

  @Override
  public void updateSessionToken(SessionTokenModel sessionToken) {
    valueOps.set(sessionToken.getJSessionId(), sessionToken);
  }

  @Override
  public SessionTokenModel findSessionToken(String jSessionId) {
    SessionTokenModel session = null;
    try {
      session = (SessionTokenModel) valueOps.get(jSessionId);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return session;
  }

  @Override
  public void deleteSessionToken(String jSessionId) {
    valueOps.set(jSessionId, null);
  }
}

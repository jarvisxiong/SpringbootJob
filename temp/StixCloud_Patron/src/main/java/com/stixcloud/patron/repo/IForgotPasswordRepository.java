package com.stixcloud.patron.repo;

import com.stixcloud.domain.TokenForgotPwd;

public interface IForgotPasswordRepository {
  void invalidateAllActiveTokens(String email);
  void createNewToken(TokenForgotPwd token);
  boolean validateToken(String token, String email);
  boolean updatePassword(String email, String newPassword);
  String getTokenExpiryTime();
  String getActivationLink();
}

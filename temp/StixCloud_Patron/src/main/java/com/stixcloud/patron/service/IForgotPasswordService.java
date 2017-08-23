package com.stixcloud.patron.service;

import com.stixcloud.common.exception.SisticApiException;

public interface IForgotPasswordService {
  public boolean handleForgotPassword(String email, String languageCode) throws SisticApiException;

  public boolean validateToken(String token, String email);

  public boolean updatePassword(String token, String email, String newPassword);
}

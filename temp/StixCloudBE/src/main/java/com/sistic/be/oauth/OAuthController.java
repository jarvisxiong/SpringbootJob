package com.sistic.be.oauth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistic.be.patron.api.auth.service.AuthorisationService;

@RestController
@RequestMapping(value = "/{tenant}")
public class OAuthController {

  @Autowired
  AuthorisationService authService;

  @RequestMapping(value = "/oauth/refreshAccessToken", method = RequestMethod.GET)
  public String getNewAccessToken(HttpSession session, HttpServletResponse response) {
    Cookie cookie =authService.createTokenCookie();
    response.addCookie(cookie);
    return cookie.getValue();
  }
}

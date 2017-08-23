package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.TokenForgotPwd;
import com.stixcloud.patron.repo.IEmailTemplateRepository;
import com.stixcloud.patron.repo.IForgotPasswordRepository;
import com.stixcloud.patron.repo.IPatronRepository;
import com.stixcloud.patron.repo.ITenantConfigRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class IForgotPasswordServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IForgotPasswordServiceTest.class);

  @InjectMocks
  private ForgotPasswordService service;
  @Mock
  private IForgotPasswordRepository repo;
  @Mock
  private IPatronService patronService;
  @Mock
  private IPatronRepository patronRepo;
  @Mock
  private IEmailTemplateRepository emailTemplateRepo;

  @Mock
  private ITenantConfigRepository tenantConfigRepo;

  @Mock
  private VelocityEngine velocityEngine;
  @Mock
  IEmailService emailService;

  @Test
  public void handleForgotPasswordCaseTrue() throws SisticApiException {
    Mockito.doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString(),
        anyString(), (HashMap<String, byte[]>) anyMapOf(String.class, byte[].class));
    when(patronService.checkExistEmailAddress(anyString())).thenReturn(true);
    when(patronRepo.getPatronProfileByEmail(anyString())).thenReturn(new PatronProfile());
    when(repo.getTokenExpiryTime()).thenReturn("5");
    doNothing().when(repo).invalidateAllActiveTokens(anyString());
    when(emailTemplateRepo.getEmailTemplatePath(anyString(), anyString()))
        .thenReturn("/SISTIC/velocity/en/channel/45/sendToken.vm");
    when(repo.getActivationLink())
        .thenReturn("http://stagings.nationalgallery.com.sg/web/ResetPassword.do");
    when(repo.getTokenExpiryTime()).thenReturn("1");
    when(tenantConfigRepo.findAll()).thenReturn(new ArrayList<>());
    ArgumentCaptor<TokenForgotPwd> argumentToken = ArgumentCaptor.forClass(TokenForgotPwd.class);
    long startTime = System.nanoTime();
    service.handleForgotPassword("dbthan@cmc.com.vn", "");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).createNewToken(argumentToken.capture());
  }

  @Test
  public void handleForgotPasswordCaseFalse() throws SisticApiException {
    when(patronService.checkExistEmailAddress(anyString())).thenReturn(false);
    long startTime = System.nanoTime();
    service.handleForgotPassword("dbthan@cmc.com.vn", "");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<TokenForgotPwd> argumentToken = ArgumentCaptor.forClass(TokenForgotPwd.class);
    ArgumentCaptor<String> argumentEmail = ArgumentCaptor.forClass(String.class);
    verify(repo, times(0)).invalidateAllActiveTokens(argumentEmail.capture());
    verify(repo, times(0)).createNewToken(argumentToken.capture());
  }

  @Test
  public void handleForgotPasswordCaseNotExistEmailAddress() throws SisticApiException {
    when(patronService.checkExistEmailAddress(anyString())).thenReturn(false);
    long startTime = System.nanoTime();
    boolean expected = false;
    boolean actual = service.handleForgotPassword("dbthan@cmc.com.vn", "");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(expected, actual);
  }

  @Test
  public void validateToken() {
    long startTime = System.nanoTime();
    service.validateToken("testToken", "testEmail");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
    verify(repo, times(1)).validateToken(argumentString.capture(), argumentString.capture());
  }

  @Test
  public void updatePasswordCaseTrue() {
    when(repo.validateToken(anyString(), anyString())).thenReturn(true);
    doNothing().when(repo).invalidateAllActiveTokens(anyString());
    long startTime = System.nanoTime();
    service.updatePassword("testToken", "dbthan@cmc.com.vn", "newpassword");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
    verify(repo, times(1)).updatePassword(argumentString.capture(), argumentString.capture());
  }

  @Test
  public void updatePasswordCaseFalse() {
    when(repo.validateToken(anyString(), anyString())).thenReturn(false);
    long startTime = System.nanoTime();
    service.updatePassword("testToken", "dbthan@cmc.com.vn", "newpassword");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<String> argumentString = ArgumentCaptor.forClass(String.class);
    verify(repo, times(0)).updatePassword(argumentString.capture(), argumentString.capture());
  }

}

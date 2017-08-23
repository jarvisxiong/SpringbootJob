package com.stixcloud.policyagent.helper;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("oauth2")
public class PAConfigProperties {
  private String server;

  public String getServer() {
    return server;
  }

  public void setServer(String server) {
    this.server = server;
  }

  private Map<String, Client> clients;

  public Map<String, Client> getClients() {
    return clients;
  }

  public void setClients(Map<String, Client> clients) {
    this.clients = clients;
  }

  public static class Client {
    private String clientId;
    private String secret;

    public String getClientId() {
      return clientId;
    }

    public void setClientId(String clientId) {
      this.clientId = clientId;
    }

    public String getSecret() {
      return secret;
    }

    public void setSecret(String secret) {
      this.secret = secret;
    }

  }

}

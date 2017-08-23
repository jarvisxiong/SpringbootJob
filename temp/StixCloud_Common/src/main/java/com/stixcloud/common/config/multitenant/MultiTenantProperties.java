package com.stixcloud.common.config.multitenant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * Created by chongye on 17/11/2016.
 */
@ConfigurationProperties(prefix = "multitenant")
public class MultiTenantProperties {

  public static final String DEFAULT_TENANT = "DEFAULT";

  public ClassLoader getBeanClassLoader() {
     return beanClassLoader;
  }

  public void setBeanClassLoader(ClassLoader beanClassLoader) {
     this.beanClassLoader = beanClassLoader;
  }

  private ClassLoader beanClassLoader;

  private Map<String, Tenant> tenants;

  public Map<String, Tenant> getTenants() {
    return tenants;
  }

  public void setTenants(Map<String, Tenant> tenants) {
    this.tenants = tenants;
  }

  @PostConstruct
  public void init() {
    for (Map.Entry<String, Tenant> entry : this.tenants.entrySet()) {
      Tenant value = entry.getValue();
      if (!StringUtils.hasText(value.getName())) {
        value.setName(entry.getKey());
      }
    }
  }

  public static class Tenant extends DataSourceProperties{
    private String name;
    private String url;
    private String username;
    private String password;
    private Long profileInfoId;
    private Long userInfoId;
    private Long seatInventoryExpiry;
    private Integer cartMaxTickets;
    private Long cartTimeout;

    public PoolProperties getTomcat() {
        return tomcat;
    }

    public void setTomcat(PoolProperties tomcat) {
        this.tomcat = tomcat;
    }

      private PoolProperties tomcat;
/*    private String defaultEmailTemplate;
    
    public String getDefaultEmailTemplate() {
		return defaultEmailTemplate;
	}

	public void setDefaultEmailTemplate(String defaultEmailTemplate) {
		this.defaultEmailTemplate = defaultEmailTemplate;
	}
*/
	public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public Long getProfileInfoId() {
      return profileInfoId;
    }

    public void setProfileInfoId(Long profileInfoId) {
      this.profileInfoId = profileInfoId;
    }

    public Long getUserInfoId() {
      return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
      this.userInfoId = userInfoId;
    }

    public Long getSeatInventoryExpiry() {
      return seatInventoryExpiry;
    }

    public void setSeatInventoryExpiry(Long seatInventoryExpiry) {
      this.seatInventoryExpiry = seatInventoryExpiry;
    }

    public void setCartMaxTickets(Integer cartMaxTickets) {
      this.cartMaxTickets = cartMaxTickets;
    }

    public Integer getCartMaxTickets() {
      return cartMaxTickets;
    }

    public Long getCartTimeout() {
      return cartTimeout;
    }

    public void setCartTimeout(Long cartTimeout) {
      this.cartTimeout = cartTimeout;
    }
  }
}

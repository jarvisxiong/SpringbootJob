package com.stixcloud.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TENANT_CONFIG")
public class TenantConfig {
  private Long tenantConfigID;
  private String type;
  private String key;
  private String value;
  private String description;

  /**
   * Instantiates a new Tenant config.
   */
  public TenantConfig() {}

  /**
   * Instantiates a new Tenant config.
   *
   * @param tenantConfigID the tenant config type
   */
  public TenantConfig(String tenantConfigID) {
    setType(tenantConfigID);
  }

  /**
   * Gets the tenant config id.
   *
   * @return the tenant config id
   */
  @Id
  @Column(name = "TENANTCONFIGID", unique = true, nullable = false, precision = 10, scale = 0)
  public Long getTenantConfigID() {
    return tenantConfigID;
  }

  /**
   * Sets thetenantConfigID.
   *
   * @param tenantConfigID the new app config id
   */
  public void setTenantConfigID(Long tenantConfigID) {
    this.tenantConfigID = tenantConfigID;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  @Column(name = "TYPE", nullable = false)
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the new type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the param1.
   *
   * @return the param1
   */
  @Column(name = "KEY", nullable = true)
  public String getKey() {
    return key;
  }

  /**
   * Sets the key.
   *
   * @param key the new key
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  @Column(name = "VALUE", nullable = true)
  public String getValue() {
    return value;
  }

  /**
   * Sets the value.
   *
   * @param value the new value
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  @Column(name = "DESCRIPTION", nullable = true)
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }
}

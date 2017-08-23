package com.sistic.be.patron.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "loginStatus",
})

/**
 * Created by User on 28/3/2017.
 */
public class PatronJsonpCheckCartResponse  extends PatronCheckCartResponse implements Serializable{

    private static final long serialVersionUID = -7863803527988488515L;

    @JsonProperty("loginStatus")
    private Integer loginStatus;

    public PatronJsonpCheckCartResponse(Integer loginStatus){
       this.loginStatus = loginStatus;
    }

    public PatronJsonpCheckCartResponse(Integer loginStatus, PatronCheckCartResponse cartResponse){
        super(cartResponse);
        this.loginStatus = loginStatus;
    }

    @JsonProperty("loginStatus")
    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }
}

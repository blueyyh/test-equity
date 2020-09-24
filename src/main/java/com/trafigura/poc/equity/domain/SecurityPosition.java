package com.trafigura.poc.equity.domain;

/**
 * Created by JACK YANG on 2020/9/24.
 */
public class SecurityPosition {
    private String securityIdentifier;
    private Integer position;

    public SecurityPosition(String securityIdentifier, Integer position) {
        this.securityIdentifier = securityIdentifier;
        this.position = position;
    }

    public String getSecurityIdentifier() {
        return securityIdentifier;
    }

    public void setSecurityIdentifier(String securityIdentifier) {
        this.securityIdentifier = securityIdentifier;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

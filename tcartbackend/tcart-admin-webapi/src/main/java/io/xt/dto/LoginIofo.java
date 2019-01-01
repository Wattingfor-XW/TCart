package io.xt.dto;

import java.util.Date;
public class LoginIofo {
    private String username;
    private String roles;
    private Date issuedAt;
    private Date expirationTime;


    public LoginIofo(String username, String roles, Date issuedAt) {
        this.username = username;
        this.roles = roles;
        this.issuedAt = issuedAt;
        Long issuedAtTimetamp = issuedAt.getTime();
        Long expirationTimetemp = issuedAtTimetamp+86400000;
        this.expirationTime = new Date(expirationTimetemp);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }
}

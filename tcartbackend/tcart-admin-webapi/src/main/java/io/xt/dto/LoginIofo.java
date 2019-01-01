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
}

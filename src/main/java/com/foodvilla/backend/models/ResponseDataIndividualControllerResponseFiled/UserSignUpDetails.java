package com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserSignUpDetails {

    public String username;

    public String emailId;

    public String token;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate = new Date();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserSignIn {

    @Schema
    @JsonProperty("code")
    public String code;

    @Schema
    @JsonProperty("message")
    public String message;

    @Schema
    @JsonProperty("token")
    public String token;

    @Schema
    @JsonProperty("limit")
    public String validLimit;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate = new Date();

    public UserSignIn(String code, String message, String token, String validLimit) {
        this.code = code;
        this.message = message;
        this.token = token;
        this.validLimit = validLimit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidLimit() {
        return validLimit;
    }

    public void setValidLimit(String validLimit) {
        this.validLimit = validLimit;
    }
}

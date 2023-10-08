package com.foodvilla.backend.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignIn;
import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignUpDetails;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseData {

    @JsonProperty("productDetails")
    public String isProductAdded;

    @JsonProperty("signUpDetails")
    public UserSignUpDetails userSignUpDetails;

    @JsonProperty("signInDetails")
    public UserSignIn userSignInDetails;

    public UserSignUpDetails getUserSignUpDetails() {
        return userSignUpDetails;
    }

    public void setUserSignUpDetails(UserSignUpDetails userSignUpDetails) {
        this.userSignUpDetails = userSignUpDetails;
    }

    public String getIsProductAdded() {
        return isProductAdded;
    }

    public void setIsProductAdded(String isProductAdded) {
        this.isProductAdded = isProductAdded;
    }

    public UserSignIn getUserSignInDetails() {
        return userSignInDetails;
    }

    public void setUserSignInDetails(UserSignIn userSignInDetails) {
        this.userSignInDetails = userSignInDetails;
    }
}
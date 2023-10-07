package com.foodvilla.backend.models;

import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignUpDetails;

public class ResponseData {

    public String isProductAdded;

    public UserSignUpDetails userSignUpDetails;

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
}

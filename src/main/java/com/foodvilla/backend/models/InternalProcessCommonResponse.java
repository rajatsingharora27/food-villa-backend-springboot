package com.foodvilla.backend.models;

import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.ProductCreatedResponse;

import java.util.List;

public class InternalProcessCommonResponse {


    public Boolean isValid;

    public List<String> errorMessageList;

    public ProductCreatedResponse productCreatedResponse;

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public List<String> getErrorMessageList() {
        return errorMessageList;
    }

    public void setErrorMessageList(List<String> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public ProductCreatedResponse getProductCreatedResponse() {
        return productCreatedResponse;
    }

    public void setProductCreatedResponse(ProductCreatedResponse productCreatedResponse) {
        this.productCreatedResponse = productCreatedResponse;
    }
}

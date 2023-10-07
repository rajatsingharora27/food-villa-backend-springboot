package com.foodvilla.backend.models;

import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.ProductAvaiableInStore;

import java.util.List;

public class InternalProcessCommonResponse {


    public Boolean isValid;

    public List<String> errorMessageList;

    public ProductAvaiableInStore productAvaiableInStore;

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

    public ProductAvaiableInStore getProductCreatedResponse() {
        return productAvaiableInStore;
    }

    public void setProductCreatedResponse(ProductAvaiableInStore productAvaiableInStore) {
        this.productAvaiableInStore = productAvaiableInStore;
    }
}

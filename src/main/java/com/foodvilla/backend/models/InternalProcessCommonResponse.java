package com.foodvilla.backend.models;

import java.util.List;

public class InternalProcessCommonResponse {


    public Boolean isValid;

    public List<String> errorMessageList;

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
}

package com.foodvilla.backend.validation;

import com.foodvilla.backend.models.InternalProcessCommonResponse;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import com.foodvilla.backend.models.RegisterUserInputBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class Validation {

    Logger log = LoggerFactory.getLogger(Validation.class);
    @Autowired
    private AsyncMethod asyncMethod;


    public void validateProductDetails(InputRequestCreateProduct inputRequestCreateProduct, InternalProcessCommonResponse internalProcessCommonResponse) {
        List<String> errorMessage = new ArrayList<>();
        try {
            CompletableFuture<Boolean> isValidName = asyncMethod.validateProductName(inputRequestCreateProduct.productName, internalProcessCommonResponse, errorMessage);
            CompletableFuture<Boolean> isValidProductDetails = asyncMethod.validateProductDetail(inputRequestCreateProduct.productDetails, internalProcessCommonResponse, errorMessage);
            CompletableFuture<Boolean> isValidProductCategory = asyncMethod.validateProductCategory(inputRequestCreateProduct.productCategory, internalProcessCommonResponse, errorMessage);
            CompletableFuture<Boolean> isValidPrice = asyncMethod.validateProductPrice(inputRequestCreateProduct.productPrice, internalProcessCommonResponse, errorMessage);
            CompletableFuture<Boolean> isValidInventory = asyncMethod.validateProductInventory(inputRequestCreateProduct.inventory, internalProcessCommonResponse, errorMessage);
            CompletableFuture.allOf(isValidName, isValidProductDetails, isValidProductCategory, isValidPrice, isValidInventory).join();
            if (isValidName.get() && isValidProductDetails.get() && isValidProductCategory.get() && isValidPrice.get() && isValidInventory.get()) {
                internalProcessCommonResponse.setValid(Boolean.TRUE);
            } else {
                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                internalProcessCommonResponse.setValid(Boolean.FALSE);
            }
        } catch (Exception ex) {
            log.error("Exception occurred {{validateProductDetails()}} method ex ->, {}", ex);
        }
    }


    public void validateUserDetailsInPut(RegisterUserInputBody userDetailsInput, InternalProcessCommonResponse internalProcessCommonResponse) {
        List<String> errorMessage = new ArrayList<>();
        try {
            CompletableFuture<Boolean> validateUserName = asyncMethod.validateUserName(userDetailsInput, internalProcessCommonResponse, errorMessage);
            CompletableFuture<Boolean> validatePhoneNumber = asyncMethod.validatePhoneNumber(userDetailsInput, internalProcessCommonResponse, errorMessage);
            CompletableFuture<Boolean> validatePassword = asyncMethod.validatePassword(userDetailsInput, internalProcessCommonResponse, errorMessage);
            CompletableFuture<Boolean> validateEmailId = asyncMethod.validateEmail(userDetailsInput, internalProcessCommonResponse, errorMessage);
            CompletableFuture.allOf(validateUserName, validateEmailId, validatePassword, validatePhoneNumber).join();
            if (validateUserName.get() && validateEmailId.get() && validatePassword.get() && validatePhoneNumber.get()){
                internalProcessCommonResponse.setValid(Boolean.TRUE);
            }else{
                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                internalProcessCommonResponse.setValid(Boolean.FALSE);
            }
        } catch (Exception ex) {
            log.error("Exception occurred in method {{validateUserDetailsInPut()}} ex-> {} , ",ex);
        }
    }

}

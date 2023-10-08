package com.foodvilla.backend.validation;

import com.foodvilla.backend.constants.ErrorMessage;
import com.foodvilla.backend.models.InternalProcessCommonResponse;
import com.foodvilla.backend.models.ProductDetails;
import com.foodvilla.backend.models.SignUpUserInputBody;
import com.foodvilla.backend.repository.ProductCreateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AsyncMethod {

    Logger log = LoggerFactory.getLogger(AsyncMethod.class);
    @Autowired
    private ProductCreateRepository productCreateRepository;

    @Async
    public CompletableFuture<Boolean> validateProductName(String productName, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage) {
        try {

            if(productCreateRepository.findByName(productName)!=null){
                String errMsg=ErrorMessage.PRODUCT_WITH_THE_SAME_NAME_1+ productName +ErrorMessage.PRODUCT_WITH_THE_SAME_NAME_2;
                errorMessage.add(errMsg);
//                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                return CompletableFuture.completedFuture(Boolean.FALSE);
            }
            if (productName == null) {
                errorMessage.add(ErrorMessage.PRODUCT_FILED_NULL);
//                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                return CompletableFuture.completedFuture(Boolean.FALSE);
            } else if (productName.isEmpty()) {
                errorMessage.add(ErrorMessage.EMPTY_PRODUCT_NAME);
//                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                return CompletableFuture.completedFuture(Boolean.FALSE);
            }
        }catch (Exception ex){
            log.info("Exception Occurred in {{validateProductName()}} , {} " , ex);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async
    public CompletableFuture<Boolean> validateProductDetail(ProductDetails productDetails, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage) {
        if (productDetails == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_DETAILS_NULL);
//            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


    @Async
    public CompletableFuture<Boolean> validateProductPrice(Integer productPrice, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage) {
        if (productPrice == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_PRICE_NULL);
//            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        } else if (productPrice == null) {
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_PRICE);
//            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


    @Async
    public CompletableFuture<Boolean> validateProductCategory(String productCategory, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage) {

        if (productCategory == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_CATEGORY_NULL);
//            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        } else if (productCategory == null) {
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_CATEGORY);
//            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async
    public CompletableFuture<Boolean> validateProductInventory(Integer inventory, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage) {

        if (inventory == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_CATEGORY_NULL);
//            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        } else if (inventory == null) {
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_INVENTORY);
//            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async
    public CompletableFuture<Boolean> validateUserName(SignUpUserInputBody userDetailsInput, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage) {
        if(isEmptyOrNull(userDetailsInput.getUserName())){
            errorMessage.add(ErrorMessage.USER_NAME_CANNOT_BE_EMPTY_OR_BLANK);
            internalProcessCommonResponse.setValid(Boolean.FALSE);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    public CompletableFuture<Boolean> validatePhoneNumber(SignUpUserInputBody userDetailsInput, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage){
        if(isEmptyOrNull(userDetailsInput.getPhoneNumber())){
            errorMessage.add(ErrorMessage.PHONE_NUMBER_CANNOT_BE_EMPTY_OR_BLANK);
            internalProcessCommonResponse.setValid(Boolean.FALSE);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        else if(!isValidPhoneNumberPassed(userDetailsInput.getPhoneNumber())){
            errorMessage.add(ErrorMessage.PHONE_NUMBER_MUST_HAVE_10_DIGITS);
            internalProcessCommonResponse.setValid(Boolean.FALSE);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    public CompletableFuture<Boolean> validatePassword(SignUpUserInputBody userDetailsInput, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage){
        if(isEmptyOrNull(userDetailsInput.getPassword())){
            errorMessage.add(ErrorMessage.PASSWORD_CANNOT_BE_EMPTY_OR_BLANK);
            internalProcessCommonResponse.setValid(Boolean.FALSE);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    public CompletableFuture<Boolean> validateEmail(SignUpUserInputBody userDetailsInput, InternalProcessCommonResponse internalProcessCommonResponse, List<String> errorMessage){
        if(isEmptyOrNull(userDetailsInput.getEmailId())){
            errorMessage.add(ErrorMessage.EMAIL_CANNOT_BE_EMPTY_OR_BLANK);
            internalProcessCommonResponse.setValid(Boolean.FALSE);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        else if(validateEmailID(userDetailsInput.getEmailId())){

        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    private boolean validateEmailID(String emailId) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailId == null)
            return false;
        return pat.matcher(emailId).matches();
    }


    private boolean isValidPhoneNumberPassed(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        System.out.println(matcher.matches());
        return matcher.matches();
    }


    private Boolean isEmptyOrNull(String value){
        if(value==null || value.isEmpty()){
            return  true;
        }
        return false;
    }


}

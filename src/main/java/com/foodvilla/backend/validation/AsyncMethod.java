package com.foodvilla.backend.validation;

import com.foodvilla.backend.constants.ErrorMessage;
import com.foodvilla.backend.models.InternalProcessCommonResponse;
import com.foodvilla.backend.models.ProductDetails;
import com.foodvilla.backend.repository.ProductCreateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncMethod {

    Logger log = LoggerFactory.getLogger(AsyncMethod.class);
    @Autowired
    private ProductCreateRepository productCreateRepository;

    @Async
    public CompletableFuture<Boolean> validateProductName(String productName, InternalProcessCommonResponse internalProcessCommonResponse) {
        try {
            List<String> errorMessage = new ArrayList<>();
            if(productCreateRepository.findByName(productName)!=null){
                String errMsg=ErrorMessage.PRODUCT_WITH_THE_SAME_NAME_1+ productName +ErrorMessage.PRODUCT_WITH_THE_SAME_NAME_2;
                errorMessage.add(errMsg);
                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                return CompletableFuture.completedFuture(Boolean.FALSE);
            }
            if (productName == null) {
                errorMessage.add(ErrorMessage.PRODUCT_FILED_NULL);
                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                return CompletableFuture.completedFuture(Boolean.FALSE);
            } else if (productName.isEmpty()) {
                errorMessage.add(ErrorMessage.EMPTY_PRODUCT_NAME);
                internalProcessCommonResponse.setErrorMessageList(errorMessage);
                return CompletableFuture.completedFuture(Boolean.FALSE);
            }
        }catch (Exception ex){
            log.info("Exception Occurred in {{validateProductName()}} , {} " , ex);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async
    public CompletableFuture<Boolean> validateProductDetail(ProductDetails productDetails, InternalProcessCommonResponse internalProcessCommonResponse) {
        List<String> errorMessage = new ArrayList<>();
        if (productDetails == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_DETAILS_NULL);
            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


    @Async
    public CompletableFuture<Boolean> validateProductPrice(Integer productPrice, InternalProcessCommonResponse internalProcessCommonResponse) {
        List<String> errorMessage = new ArrayList<>();
        if (productPrice == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_PRICE_NULL);
            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        } else if (productPrice == null) {
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_PRICE);
            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


    @Async
    public CompletableFuture<Boolean> validateProductCategory(String productCategory, InternalProcessCommonResponse internalProcessCommonResponse) {
        List<String> errorMessage = new ArrayList<>();
        if (productCategory == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_CATEGORY_NULL);
            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        } else if (productCategory == null) {
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_CATEGORY);
            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async
    public CompletableFuture<Boolean> validateProductInventory(Integer inventory, InternalProcessCommonResponse internalProcessCommonResponse) {
        List<String> errorMessage = new ArrayList<>();
        if (inventory == null) {
            errorMessage.add(ErrorMessage.PRODUCT_FILED_CATEGORY_NULL);
            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        } else if (inventory == null) {
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_INVENTORY);
            internalProcessCommonResponse.setErrorMessageList(errorMessage);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


}

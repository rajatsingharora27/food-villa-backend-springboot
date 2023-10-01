package com.foodvilla.backend.validation;

import com.foodvilla.backend.constants.ErrorMessage;
import com.foodvilla.backend.models.ProductDetails;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncMethod {

    @Async
    public CompletableFuture<Boolean> validateProductName(String productName, List<String> errorMessage){

        if(productName==null){
            errorMessage.add(ErrorMessage.PRODUCT_FILED_NULL);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }else if(productName.isEmpty()){
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_NAME);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async
    public CompletableFuture<Boolean> validateProductDetail(ProductDetails productDetails, List<String> errorMessage){

        if(productDetails==null){
            errorMessage.add(ErrorMessage.PRODUCT_FILED_DETAILS_NULL);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


    @Async
    public CompletableFuture<Boolean> validateProductPrice(Integer productPrice, List<String> errorMessage){

        if(productPrice==null){
            errorMessage.add(ErrorMessage.PRODUCT_FILED_PRICE_NULL);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }else if(productPrice==null){
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_PRICE);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


    @Async
    public CompletableFuture<Boolean> validateProductCategory(String productCategory, List<String> errorMessage){

        if(productCategory==null){
            errorMessage.add(ErrorMessage.PRODUCT_FILED_CATEGORY_NULL);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }else if(productCategory==null){
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_CATEGORY);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

    @Async
    public CompletableFuture<Boolean> validateProductInventory(Integer inventory, List<String> errorMessage){

        if(inventory==null){
            errorMessage.add(ErrorMessage.PRODUCT_FILED_CATEGORY_NULL);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }else if(inventory==null){
            errorMessage.add(ErrorMessage.EMPTY_PRODUCT_INVENTORY);
            return CompletableFuture.completedFuture(Boolean.FALSE);
        }
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }


}

package com.foodvilla.backend.validation;

import com.foodvilla.backend.models.InternalProcessCommonResponse;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductCreationValidate {


    @Autowired
    private AsyncMethod asyncMethod;


    public void validateProductDetails(InputRequestCreateProduct inputRequestCreateProduct , InternalProcessCommonResponse internalProcessCommonResponse){

        try{
            CompletableFuture<Boolean> isValidName = asyncMethod.validateProductName(inputRequestCreateProduct.productName, internalProcessCommonResponse);
            CompletableFuture<Boolean> isValidProductDetails = asyncMethod.validateProductDetail(inputRequestCreateProduct.productDetails, internalProcessCommonResponse);
            CompletableFuture<Boolean> isValidProductCategory = asyncMethod.validateProductCategory(inputRequestCreateProduct.productCategory, internalProcessCommonResponse);
            CompletableFuture<Boolean> isValidPrice = asyncMethod.validateProductPrice(inputRequestCreateProduct.productPrice, internalProcessCommonResponse);
            CompletableFuture<Boolean> isValidInventory = asyncMethod.validateProductInventory(inputRequestCreateProduct.inventory, internalProcessCommonResponse);
            CompletableFuture.allOf(isValidName,isValidProductDetails,isValidProductCategory,isValidPrice,isValidInventory).join();
            if(isValidName.get() &&isValidProductDetails.get() && isValidProductCategory.get() && isValidPrice.get() && isValidInventory.get()){
                internalProcessCommonResponse.setValid(Boolean.TRUE);
            }else {
                internalProcessCommonResponse.setValid(Boolean.FALSE);
                return;
            }
        }catch(Exception ex){

        }
    }




}

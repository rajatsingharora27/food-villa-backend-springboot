package com.foodvilla.backend.validation;

import com.foodvilla.backend.models.CommonResponse;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductCreationValidate {


    @Autowired
    private AsyncMethod asyncMethod;


    public void validateProductDetails(InputRequestCreateProduct inputRequestCreateProduct , CommonResponse commonResponse){

        List<String> errorMessage=new ArrayList<>();

        try{
            CompletableFuture<Boolean> isValidName = asyncMethod.validateProductName(inputRequestCreateProduct.productName,errorMessage);
            CompletableFuture<Boolean> isValidProductDetails = asyncMethod.validateProductDetail(inputRequestCreateProduct.productDetails,errorMessage);
            CompletableFuture<Boolean> isValidProductCategory = asyncMethod.validateProductCategory(inputRequestCreateProduct.productCategory,errorMessage);
            CompletableFuture<Boolean> isValidPrice = asyncMethod.validateProductPrice(inputRequestCreateProduct.productPrice,errorMessage);
            CompletableFuture<Boolean> isValidInventory = asyncMethod.validateProductInventory(inputRequestCreateProduct.inventory,errorMessage);
            CompletableFuture.allOf(isValidName,isValidProductDetails,isValidProductCategory,isValidPrice,isValidInventory).join();
            if(isValidName.get() &&isValidProductDetails.get() && isValidProductCategory.get() && isValidPrice.get() && isValidInventory.get()){
                commonResponse.setValid(Boolean.TRUE);
            }else {
                commonResponse.setValid(Boolean.FALSE);
                return;
            }
        }catch(Exception ex){

        }
    }




}

package com.foodvilla.backend.controller;

import com.foodvilla.backend.models.InternalProcessCommonResponse;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import com.foodvilla.backend.models.ProductInfoWithImageResult;
import com.foodvilla.backend.models.Response;
import com.foodvilla.backend.service.ProductService;
import com.foodvilla.backend.validation.ProductCreationValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
//@RequestMapping("/api")
public class FoodVillaController {


     Logger log = LoggerFactory.getLogger(FoodVillaController.class);
    @Autowired
    private ProductCreationValidate productCreationValidate;


    @Value("${spring.profiles.active}")
    private String currentActiveEnv;

    @Autowired
    private ProductService productService;




    @PostMapping(value = "/v1/add-product" )
    public ResponseEntity<Response> createProduct(@RequestBody InputRequestCreateProduct inputRequest){

        InternalProcessCommonResponse internalProcessCommonResponse =new InternalProcessCommonResponse();
        Response response=new Response();
        log.info("{{createProduct()}} controller stated");

        productCreationValidate.validateProductDetails(inputRequest, internalProcessCommonResponse);
        if(internalProcessCommonResponse.isValid){
            productService.addNewProduct(inputRequest);
        }
        if(internalProcessCommonResponse.getErrorMessageList()!=null && !internalProcessCommonResponse.getErrorMessageList().isEmpty()){
            List<String> errorMessage =new ArrayList<>();
            for(String err : internalProcessCommonResponse.getErrorMessageList()){
                errorMessage.add(err);
            }
            response.setMessage(errorMessage);
            response.setProductAddMessage("Product cannot be added");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
        response.setProductAddMessage("data Saved");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/upload-image")
    public void uploadImage(@RequestParam("imageFile")MultipartFile file ,@RequestParam("productName") String productName) throws IOException {
        InternalProcessCommonResponse internalProcessCommonResponse =new InternalProcessCommonResponse();
        productService.addImageToRespectiveProduct(file,productName , internalProcessCommonResponse);
    }

    // This api will get all the product mapped with the image from productImage table , productInfo
    @GetMapping("/v1/get-products/")
    public ResponseEntity<List<ProductInfoWithImageResult>> getAllTheQueryParamRelatedProduct(@RequestParam(name="productCategory") String productCategory){

        List<ProductInfoWithImageResult> response= productService.getQueryPassedProduct(productCategory);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }



}

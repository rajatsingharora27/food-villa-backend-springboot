package com.foodvilla.backend.controller;

import com.foodvilla.backend.models.*;
import com.foodvilla.backend.service.ProductService;
import com.foodvilla.backend.utils.UtilityMethods;
import com.foodvilla.backend.validation.Validation;
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
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class FoodVillaController {


    Logger log = LoggerFactory.getLogger(FoodVillaController.class);
    @Autowired
    private Validation validation;


    @Value("${spring.profiles.active}")
    private String currentActiveEnv;

    @Autowired
    private ProductService productService;

    @Autowired
    private UtilityMethods utilityMethods;



//   ************************************ Route Available to Admin only*******************************



    @PostMapping(value = "/v1/add-product" )
    public ResponseEntity<Response> createProduct(@RequestBody InputRequestCreateProduct inputRequest){

        String refId=utilityMethods.uniqueRefIdGenerate();
        InternalProcessCommonResponse internalProcessCommonResponse =new InternalProcessCommonResponse();
        Response response=new Response();
        ResponseData responseData=new ResponseData();
        response.setRefId(refId);
        log.info("{{createProduct()}} controller stated for the redId :{} " , refId);
        validation.validateProductDetails(inputRequest, internalProcessCommonResponse);
        if(internalProcessCommonResponse.isValid){
            productService.addNewProduct(inputRequest);
        }
        if(internalProcessCommonResponse.getErrorMessageList()!=null && !internalProcessCommonResponse.getErrorMessageList().isEmpty()){
            List<ErrorMessageListWithCode> errorMessage =utilityMethods.criticalErrorMessageList(internalProcessCommonResponse);
            response.setMessage(errorMessage);
            responseData.setIsProductAdded("Product Cannot be added");
            response.setResponseData(responseData);
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
        responseData.setIsProductAdded("Product Added successfully");
        response.setResponseData(responseData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/upload-image")
    public void uploadImage(@RequestParam("imageFile")MultipartFile file ,@RequestParam("productName") String productName) throws IOException {
        InternalProcessCommonResponse internalProcessCommonResponse =new InternalProcessCommonResponse();
        productService.addImageToRespectiveProduct(file,productName , internalProcessCommonResponse);
    }

    @GetMapping("/v1/get-products-dropdown")
    public List<String> getAllProductName(){
        productService.getAllTheDropDownProductName();
        return null;
    }

    //   ************************************ Route Available to Admin only*******************************





//   ************************************ Route Available to Public *******************************

    // This api will get all the product mapped with the image from productImage table , productInfo
    @GetMapping("/v1/get-products/")
    public ResponseEntity<List<ProductInfoWithImageResult>> getAllTheQueryParamRelatedProduct(@RequestParam(name="productCategory") String productCategory){
        List<ProductInfoWithImageResult> response= productService.getQueryPassedProduct(productCategory);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

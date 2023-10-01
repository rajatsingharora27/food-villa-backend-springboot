package com.foodvilla.backend.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.foodvilla.backend.models.CommonResponse;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import com.foodvilla.backend.service.CreateProduct;
import com.foodvilla.backend.validation.ProductCreationValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

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
    private CreateProduct createProduct;

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping(value = "/v1/add-product" )
    public String createProduct(@RequestBody InputRequestCreateProduct inputRequest){

        log.info("{{createProduct()}} controller stated");
        CommonResponse commonResponse=new CommonResponse();
        productCreationValidate.validateProductDetails(inputRequest,commonResponse);
        if(commonResponse.isValid){
            createProduct.addNewProduct(inputRequest);
        }
        return "data saved";
    }

    @PostMapping("/v1/upload-image")
    public void uploadImage(@RequestParam("imageFile")MultipartFile file) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//        cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        System.out.println(result);

    }



}

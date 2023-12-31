package com.foodvilla.backend.service;

import com.cloudinary.Cloudinary;
import com.foodvilla.backend.constants.ErrorMessage;
import com.foodvilla.backend.dao.ProductDetailsDao;
import com.foodvilla.backend.dao.ProductImageDao;
import com.foodvilla.backend.mapper.AddNewProductMapper;
import com.foodvilla.backend.models.InternalProcessCommonResponse;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import com.foodvilla.backend.models.ProductInfoWithImageResult;
import com.foodvilla.backend.repository.ProductCreateRepository;
import com.foodvilla.backend.repository.ProductImageRepository;
import com.foodvilla.backend.utils.ComplexQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {


    Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private AddNewProductMapper addNewProductMapper;

    @Autowired
    private ProductCreateRepository productCreateRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ComplexQueryService complexQueryService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private MongoTemplate mongoTemplate;


    public void addNewProduct(InputRequestCreateProduct inputRequest) {
        ProductDetailsDao productDetailsDao = addNewProductMapper.addNewProductMapper(inputRequest);
        productCreateRepository.save(productDetailsDao);

    }

    public void addImageToRespectiveProduct(MultipartFile file, String productName, InternalProcessCommonResponse internalProcessCommonResponse) {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), Map.of());
            ProductDetailsDao productDetailsDao = productCreateRepository.findByName(productName);
            List<String> errMsg = new ArrayList<>();

            //Product is present in productInfo table so image can be added in productImage table
            if (productDetailsDao != null) {
                ProductImageDao productImageDao = productImageRepository.findByName(productName);
                if (productImageDao == null) {
                    ProductImageDao productImageObject = new ProductImageDao();
                    List<String> imageUrl = new ArrayList<>();
                    imageUrl.add((String) result.get("secure_url"));
                    productImageObject.setProductImageList(imageUrl);
                    productImageObject.setProductName(productName);
                    productImageRepository.save(productImageObject);
                } else {
                    List<String> imageUrlList = productImageDao.getProductImageList();
                    imageUrlList.add((String) result.get("secure_url"));
                    productImageDao.setProductImageList(imageUrlList);
                    Query query = new Query();
                    Update update = new Update();
                    query.addCriteria(Criteria.where("productName").is(productName));
                    update.set("productImageList", imageUrlList);
                    mongoTemplate.upsert(query, update, ProductImageDao.class);
                }

            } else {
                errMsg.add(ErrorMessage.PRODUCT_NOT_PRESENT_1 + productName + ErrorMessage.PRODUCT_NOT_PRESENT_2);
                internalProcessCommonResponse.setErrorMessageList(errMsg);
                return;
            }
        } catch (Exception ex) {
            log.error("Exception Occurred in {{addImageToRespectiveProduct()}} ex->", ex);
        }
    }

    public List<ProductInfoWithImageResult> getQueryPassedProduct(String productCategory){

        List<ProductInfoWithImageResult>result=new ArrayList<>();
        try{
            if(productCategory.isEmpty()){
            }
            result=complexQueryService.getListOfProductInfoWithTheProductImage(productCategory);
        }catch(Exception ex){
            log.error("Exception");
        }
        return result;

    }

    public List<String> getAllTheDropDownProductName(){
        Query query=new Query();
        query.fields().include("productName");
        List<String>ans=mongoTemplate.find(query,String.class);
        for(String ele:ans){
            System.out.println(ele);
        }
        return null;
    }



}

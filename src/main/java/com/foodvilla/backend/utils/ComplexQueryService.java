package com.foodvilla.backend.utils;

import com.foodvilla.backend.models.ProductInfoWithImageResult;
import com.foodvilla.backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplexQueryService {


    Logger log = LoggerFactory.getLogger(ComplexQueryService.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ProductInfoWithImageResult> getListOfProductInfoWithTheProductImage(String productCategory){
        List<ProductInfoWithImageResult>result=new ArrayList<>();
        try {
            LookupOperation lookupOperation = LookupOperation.newLookup()
                    .from("productImage")
                    .localField("productName")
                    .foreignField("productName")
                    .as("productImageListDetails");

            Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("productCategory").is(productCategory)) , lookupOperation);
            result=mongoTemplate.aggregate(aggregation, "productInfo",ProductInfoWithImageResult.class).getMappedResults();
            log.info("result ->{}" ,result);
        }catch (Exception ex){
            log.info("Exception occurred in {{getListOfProductInfoWithTheProductImage}} , ex -> {} ",ex);

        }
        return result;
    }
}

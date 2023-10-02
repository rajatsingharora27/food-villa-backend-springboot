package com.foodvilla.backend.repository;


import com.foodvilla.backend.dao.ProductImageDao;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends MongoRepository<ProductImageDao,String> {

    @Query("{productName:?0}")
    public ProductImageDao findByName(String productName);

}

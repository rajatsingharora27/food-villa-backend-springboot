package com.foodvilla.backend.repository;

import com.foodvilla.backend.dao.ProductDetailsDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCreateRepository extends MongoRepository<ProductDetailsDao,String> {

    @Query("{productName:?0}")
    public ProductDetailsDao findByName(String productName);


}

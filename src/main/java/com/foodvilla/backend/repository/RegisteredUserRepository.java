package com.foodvilla.backend.repository;

import com.foodvilla.backend.dao.RegisterUserDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RegisteredUserRepository extends MongoRepository<RegisterUserDao,String> {
    @Query("{email:?0}")
    public RegisterUserDao findByEmailId(String emailId);

}

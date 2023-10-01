package com.foodvilla.backend.service;

import com.foodvilla.backend.dao.ProductDetailsDao;
import com.foodvilla.backend.mapper.AddNewProductMapper;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import com.foodvilla.backend.repository.ProductCreateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {

    @Autowired
    private AddNewProductMapper addNewProductMapper;

    @Autowired
    private ProductCreateRepository productCreateRepository;


    public void addNewProduct(InputRequestCreateProduct inputRequest){
        ProductDetailsDao productDetailsDao= addNewProductMapper.addNewProductMapper(inputRequest);
        productCreateRepository.save(productDetailsDao);

    }




}

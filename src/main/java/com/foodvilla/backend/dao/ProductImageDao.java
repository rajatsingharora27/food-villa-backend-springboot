package com.foodvilla.backend.dao;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "productImage")
public class ProductImageDao {

    public String productName;

    public List<String> productImageList;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<String> productImageList) {
        this.productImageList = productImageList;
    }
}

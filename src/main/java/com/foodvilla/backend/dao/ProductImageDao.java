package com.foodvilla.backend.dao;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "productImage")
public class ProductImageDao {

    public String productName;

    public List<String> productImageList;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate = new Date();

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

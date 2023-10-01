package com.foodvilla.backend.models;

import java.util.List;

public class InputRequestCreateProduct {

    public String productName;

    public Integer productPrice;

    public String tagLine;

    public Integer inventory;

    public Boolean inStock;

    public List<String> productImage;

    public ProductDetails productDetails;

    public String productCategory;

    public String festiveTag;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public List<String> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<String> productImage) {
        this.productImage = productImage;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getFestiveTag() {
        return festiveTag;
    }

    public void setFestiveTag(String festiveTag) {
        this.festiveTag = festiveTag;
    }
}






//       "produtId": "string",
//               "": "abc",
//               "": 1451,
//               "": "string",
//               "": 4,
//               "": "boolean",
//               "productImage": [],
//               "productDetails": {
//               "description": "string can be a para try to convert to html format",
//               "servingInstrinctions": "string",
//               "ingredients": "string",
//               "allergens": "string"
//               },
//               "product": ["arry of festival product order"],
//               "productCategory": "string of can be cake,savory ,pastory",
//               "festiveTag": ""


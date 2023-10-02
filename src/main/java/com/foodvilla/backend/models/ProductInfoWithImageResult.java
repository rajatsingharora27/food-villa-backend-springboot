package com.foodvilla.backend.models;

import java.util.List;

public class ProductInfoWithImageResult {

    private String productName;

    private Integer productPrice;

    private String tagLine;

    private Integer inventory;

    private Boolean inStock;

    private String productCategory;

    private ProductDetails productDetails;

    public List<String> productImageListDetails;


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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public List<String> getProductImageListDetails() {
        return productImageListDetails;
    }

    public void setProductImageListDetails(List<String> productImageListDetails) {
        this.productImageListDetails = productImageListDetails;
    }
}

package com.foodvilla.backend.dao;

import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.ProductAvaiableInStore;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "userProductDetails")
public class UserProductDetailsDao extends  RegisterUserDao {


  public String userAddress;


  public List<ProductAvaiableInStore> wishList;


  public List<ProductAvaiableInStore> cartProduct;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date createdDate = new Date();

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public List<ProductAvaiableInStore> getWishList() {
    return wishList;
  }

  public void setWishList(List<ProductAvaiableInStore> wishList) {
    this.wishList = wishList;
  }

  public List<ProductAvaiableInStore> getCartProduct() {
    return cartProduct;
  }

  public void setCartProduct(List<ProductAvaiableInStore> cartProduct) {
    this.cartProduct = cartProduct;
  }
}

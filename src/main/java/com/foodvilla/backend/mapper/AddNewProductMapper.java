package com.foodvilla.backend.mapper;

import com.foodvilla.backend.dao.ProductDetailsDao;
import com.foodvilla.backend.models.InputRequestCreateProduct;
import com.foodvilla.backend.models.ProductDetails;
import org.springframework.stereotype.Service;

@Service
public class AddNewProductMapper {

    public ProductDetailsDao addNewProductMapper(InputRequestCreateProduct inputRequest){
        ProductDetailsDao productDetailsDao=new ProductDetailsDao();
        productDetailsDao.setProductPrice(inputRequest.getProductPrice());
        productDetailsDao.setProductName(inputRequest.getProductName());
        productDetailsDao.setTagLine(inputRequest.getTagLine());
        productDetailsDao.setProductDetails(getProductDetails(inputRequest.productDetails));
        productDetailsDao.setProductCategory(inputRequest.getProductCategory());
        productDetailsDao.setInStock(inputRequest.getInventory()>0? Boolean.TRUE : Boolean.FALSE);
        return productDetailsDao;
    }

    private ProductDetails getProductDetails(ProductDetails productDetails) {
        ProductDetails pd=new ProductDetails();
        pd.setAllergens(productDetails.getAllergens());
        pd.setStorageAndConsumption(productDetails.getStorageAndConsumption());
        pd.setIngredients(productDetails.getIngredients());
        pd.setIngredients(pd.getIngredients());
        return  pd;
    }


}

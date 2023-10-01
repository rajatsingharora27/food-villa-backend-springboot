package com.foodvilla.backend.constants;

import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

    static final public String EMPTY_PRODUCT_NAME="productName cannot be Empty";
    static final public String PRODUCT_FILED_NULL="productName is a required field";


    static final public String EMPTY_PRODUCT_DETAILS="productName cannot be Empty";
    static final public String PRODUCT_FILED_DETAILS_NULL="productName is a required field";


    static final public String EMPTY_PRODUCT_PRICE="productPrice cannot be Empty";
    static final public String PRODUCT_FILED_PRICE_NULL="productPrice is a required field";


    static final public String EMPTY_PRODUCT_CATEGORY="productCategory cannot be Empty";
    static final public String PRODUCT_FILED_CATEGORY_NULL="productCategory is a required field";

    static final public String EMPTY_PRODUCT_INVENTORY="inventory cannot be Empty";
    static final public String PRODUCT_FILED_INVENTORY_NULL="inventory is a required field";


}

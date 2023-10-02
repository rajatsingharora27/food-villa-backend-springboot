package com.foodvilla.backend.constants;

import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

    static final public String EMPTY_PRODUCT_NAME="productName cannot be Empty";
    static final public String PRODUCT_FILED_NULL="productName is a required field";

    static final public String PRODUCT_WITH_THE_SAME_NAME_1="Product with the name (";
    static final public String PRODUCT_WITH_THE_SAME_NAME_2=") already exist.";


    static final public String EMPTY_PRODUCT_DETAILS="productName cannot be Empty";
    static final public String PRODUCT_FILED_DETAILS_NULL="productName is a required field";


    static final public String EMPTY_PRODUCT_PRICE="productPrice cannot be Empty";
    static final public String PRODUCT_FILED_PRICE_NULL="productPrice is a required field";


    static final public String EMPTY_PRODUCT_CATEGORY="productCategory cannot be Empty";
    static final public String PRODUCT_FILED_CATEGORY_NULL="productCategory is a required field";

    static final public String EMPTY_PRODUCT_INVENTORY="inventory cannot be Empty";
    static final public String PRODUCT_FILED_INVENTORY_NULL="inventory is a required field";

    static final public String PRODUCT_NOT_PRESENT_1="Product Name ( ";
    static final public String PRODUCT_NOT_PRESENT_2=")do not exist in productInfo table hence image cannot be added";





}

package com.foodvilla.backend.constants;

import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

    static final public String EMPTY_PRODUCT_NAME="0001::Critical,productName cannot be Empty";
    static final public String PRODUCT_FILED_NULL="0002::Critical,productName is a required field";

    static final public String PRODUCT_WITH_THE_SAME_NAME_1="0003::Critical,Product with the name (";
    static final public String PRODUCT_WITH_THE_SAME_NAME_2=") already exist.";


    static final public String EMPTY_PRODUCT_DETAILS="0004::Critical,productName cannot be Empty";
    static final public String PRODUCT_FILED_DETAILS_NULL="productName is a required field";


    static final public String EMPTY_PRODUCT_PRICE="0005::Critical,productPrice cannot be Empty";
    static final public String PRODUCT_FILED_PRICE_NULL="0006::Critical,productPrice is a required field";


    static final public String EMPTY_PRODUCT_CATEGORY="0007::Critical,productCategory cannot be Empty";
    static final public String PRODUCT_FILED_CATEGORY_NULL="0008::Critical,productCategory is a required field";

    static final public String EMPTY_PRODUCT_INVENTORY="0009::Critical,inventory cannot be Empty";
    static final public String PRODUCT_FILED_INVENTORY_NULL="0010::Critical,inventory is a required field";

    static final public String PRODUCT_NOT_PRESENT_1="0011::Critical,Product Name ( ";
    static final public String PRODUCT_NOT_PRESENT_2=")do not exist in productInfo table hence image cannot be added";

    static final public String USER_ALREADY_EXIST_1= "0012::Critical,User (";
    static final public String USER_ALREADY_EXIST_2=") email already registered with us please try to log in ";

    static final public String USER_NAME_CANNOT_BE_EMPTY_OR_BLANK= "0014::Critical,User Name cannot be empty or blank";
    static final public String PHONE_NUMBER_CANNOT_BE_EMPTY_OR_BLANK= "0015::Critical,Phone number cannot be empty or blank";
    static final public String PHONE_NUMBER_MUST_HAVE_10_DIGITS= "0016::Critical,Phone number has less than 10 digit";
    static final public String PASSWORD_CANNOT_BE_EMPTY_OR_BLANK= "0017::Critical,Password cannot be empty or blank";
    static final public String EMAIL_CANNOT_BE_EMPTY_OR_BLANK= "0017::Critical,Email cannot be empty or blank";

    static final public String USER_DONT_EXIST_1= "0018::Critical,User with the emilId entered (";
    static final public String USER_DONT_EXIST_2= ") doesn't exit with us please sign up";
    static final public String INCORRECT_PASSWORD_ENTERED= "0018::Critical,Password entered is incorrect";




}

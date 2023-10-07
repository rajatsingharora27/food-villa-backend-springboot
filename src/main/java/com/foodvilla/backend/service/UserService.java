package com.foodvilla.backend.service;

import com.foodvilla.backend.constants.ErrorMessage;
import com.foodvilla.backend.dao.RegisterUserDao;
import com.foodvilla.backend.mapper.UserAuthenticationAndAuthorizationMapper;
import com.foodvilla.backend.models.*;
import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignUpDetails;
import com.foodvilla.backend.repository.RegisteredUserRepository;
import com.foodvilla.backend.utils.UtilityMethods;
import com.foodvilla.backend.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {


    Logger log= LoggerFactory.getLogger(UserService.class);
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private Validation validation;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private UserAuthenticationAndAuthorizationMapper userAuthenticationAndAuthorizationMapper;


    public void registerUser(RegisterUserInputBody registerUserInputBody, InternalProcessCommonResponse internalProcessCommonResponse, Response response, String refId) {
        response.setRefId(refId);
        List<String> messageList = new ArrayList<>();
        ResponseData responseData=new ResponseData();
        // check if user is already present or not
        RegisterUserDao userObject = registeredUserRepository.findByEmailId(registerUserInputBody.getEmailId());
        if (userObject != null) {
            messageList.add(ErrorMessage.USER_ALREADY_EXIST_1 + userObject.getEmail() + ErrorMessage.USER_ALREADY_EXIST_2);
            internalProcessCommonResponse.setErrorMessageList(messageList);
            List<ErrorMessageListWithCode>errorList=utilityMethods.criticalErrorMessageList(internalProcessCommonResponse);
            response.setMessage(errorList);
        } else {
            validation.validateUserDetailsInPut(registerUserInputBody, internalProcessCommonResponse);
            if(internalProcessCommonResponse.isValid){
                //saveUser to DB;
                RegisterUserDao userEntity=saveUser(registerUserInputBody);
                registeredUserRepository.save(userEntity);
                UserSignUpDetails userSignup=userAuthenticationAndAuthorizationMapper.mapSignUpUserData(registerUserInputBody);
                responseData.setUserSignUpDetails(userSignup);
                response.setResponseData(responseData);
            }else{
                List<ErrorMessageListWithCode>errorList=utilityMethods.criticalErrorMessageList(internalProcessCommonResponse);
                response.setMessage(errorList);
            }
            // generate jwt token for the user and sent to UI in response
            //join with the userProductSelectedTable and other information as well
        }
//        return response;
    }

    private RegisterUserDao saveUser(RegisterUserInputBody registerUserInputBody) {
        RegisterUserDao registerUserDao=new RegisterUserDao();
        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            String encryptedPassword = encoder.encode(registerUserInputBody.getPassword());
            registerUserDao.setUserName(registerUserInputBody.getUserName());
            registerUserDao.setEmail(registerUserInputBody.getEmailId());
            registerUserDao.setPassword(encryptedPassword);
            registerUserDao.setPhoneNumber(registerUserInputBody.getPhoneNumber());
            return registerUserDao;

        }catch (Exception ex){
            log.error("Exception Occurred while store user to DB ex->{}",ex);
        }
        return registerUserDao;
    }


}

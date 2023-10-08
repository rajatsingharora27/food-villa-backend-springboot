package com.foodvilla.backend.service;

import com.foodvilla.backend.constants.ErrorMessage;
import com.foodvilla.backend.dao.RegisterUserDao;
import com.foodvilla.backend.mapper.UserAuthenticationAndAuthorizationMapper;
import com.foodvilla.backend.models.*;
import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignIn;
import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignUpDetails;
import com.foodvilla.backend.repository.RegisteredUserRepository;
import com.foodvilla.backend.utils.UtilityMethods;
import com.foodvilla.backend.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private UtilityMethods utilityMethods;

    @Autowired
    private Validation validation;

    @Autowired
    private UserAuthenticationAndAuthorizationMapper userAuthenticationAndAuthorizationMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(SignUpUserInputBody signUpUserInputBody, InternalProcessCommonResponse internalProcessCommonResponse, Response response, String refId) {
        response.setRefId(refId);
        List<String> messageList = new ArrayList<>();
        ResponseData responseData = new ResponseData();
        // check if user is already present or not
        RegisterUserDao userObject = registeredUserRepository.findByEmailId(signUpUserInputBody.getEmailId());
        if (userObject != null) {
            messageList.add(ErrorMessage.USER_ALREADY_EXIST_1 + userObject.getEmail() + ErrorMessage.USER_ALREADY_EXIST_2);
            internalProcessCommonResponse.setErrorMessageList(messageList);
            List<ErrorMessageListWithCode> errorList = utilityMethods.criticalErrorMessageList(internalProcessCommonResponse);
            response.setMessage(errorList);
        } else {
            validation.validateUserDetailsInPut(signUpUserInputBody, internalProcessCommonResponse);
            if (internalProcessCommonResponse.isValid) {
                //saveUser to DB;
                RegisterUserDao userEntity = saveUser(signUpUserInputBody);
                registeredUserRepository.save(userEntity);
                UserSignUpDetails userSignup = userAuthenticationAndAuthorizationMapper.mapSignUpUserData(signUpUserInputBody);

                responseData.setUserSignUpDetails(userSignup);
                response.setResponseData(responseData);

                // generate jwt token for the user and sent to UI in response
                //join with the userProductSelectedTable and other information as well
            } else {
                List<ErrorMessageListWithCode> errorList = utilityMethods.criticalErrorMessageList(internalProcessCommonResponse);
                response.setMessage(errorList);
            }

        }
    }


    public void signIn(SignInUserInputBody signInUserInputBody, InternalProcessCommonResponse internalProcessCommonResponse, Response response, String refId) {
        List<String> messageList = new ArrayList<>();
        response.setRefId(refId);
        RegisterUserDao user = registeredUserRepository.findByEmailId(signInUserInputBody.getEmailId());
        ResponseData responseData = new ResponseData();
        if (user == null) {
            messageList.add(ErrorMessage.USER_DONT_EXIST_1 + signInUserInputBody.getEmailId() + ErrorMessage.USER_DONT_EXIST_2);
            internalProcessCommonResponse.setErrorMessageList(messageList);
            List<ErrorMessageListWithCode> errorList = utilityMethods.criticalErrorMessageList(internalProcessCommonResponse);
            response.setMessage(errorList);
        } else {
            //validate Password Entered
            if (!passwordEncoder.matches(signInUserInputBody.getPassword(), user.getPassword())) {
                messageList.add(ErrorMessage.INCORRECT_PASSWORD_ENTERED);
                internalProcessCommonResponse.setErrorMessageList(messageList);
                List<ErrorMessageListWithCode> errorList = utilityMethods.criticalErrorMessageList(internalProcessCommonResponse);
                response.setMessage(errorList);
            } else {
//               String jwt= utilityMethods.generateJwtToken(signInUserInputBody.emailId);
                UserSignIn userSignIn = new UserSignIn("success", "Login successful", "jwt", "2 Days");

                responseData.setUserSignInDetails(userSignIn);
                response.setResponseData(responseData);
            }
        }

    }


    private RegisterUserDao saveUser(SignUpUserInputBody signUpUserInputBody) {
        RegisterUserDao registerUserDao = new RegisterUserDao();
        try {
//            String encryptedPassword = passwordEncoder.encode(signUpUserInputBody.getPassword());
            registerUserDao.setUserName(signUpUserInputBody.getUserName());
            registerUserDao.setEmail(signUpUserInputBody.getEmailId());
//            registerUserDao.setPassword(encryptedPassword);
            registerUserDao.setPhoneNumber(signUpUserInputBody.getPhoneNumber());
            return registerUserDao;

        } catch (Exception ex) {
            log.error("Exception Occurred while store user to DB ex->{}", ex);
        }
        return registerUserDao;
    }


}

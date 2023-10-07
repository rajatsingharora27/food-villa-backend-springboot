package com.foodvilla.backend.mapper;

import com.foodvilla.backend.models.SignUpUserInputBody;
import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignUpDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationAndAuthorizationMapper {


   public UserSignUpDetails mapSignUpUserData(SignUpUserInputBody signUpUserInputBody){
      UserSignUpDetails userSignUpDetails=new UserSignUpDetails();
      userSignUpDetails.setEmailId(signUpUserInputBody.getEmailId());
      userSignUpDetails.setUsername(signUpUserInputBody.getUserName());
      return  userSignUpDetails;
   }
}

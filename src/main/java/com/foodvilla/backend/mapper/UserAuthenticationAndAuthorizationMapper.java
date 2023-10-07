package com.foodvilla.backend.mapper;

import com.foodvilla.backend.models.RegisterUserInputBody;
import com.foodvilla.backend.models.ResponseDataIndividualControllerResponseFiled.UserSignUpDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationAndAuthorizationMapper {


   public UserSignUpDetails mapSignUpUserData(RegisterUserInputBody registerUserInputBody){
      UserSignUpDetails userSignUpDetails=new UserSignUpDetails();
      userSignUpDetails.setEmailId(registerUserInputBody.getEmailId());
      userSignUpDetails.setUsername(registerUserInputBody.getUserName());
      return  userSignUpDetails;
   }
}

package com.foodvilla.backend.service;

import com.foodvilla.backend.dao.RegisterUserDao;
import com.foodvilla.backend.models.RegisterUserInputBody;
import com.foodvilla.backend.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public void registerUser(RegisterUserInputBody registerUserInputBody){

        // check if user is already present or not
        RegisterUserDao userObject= registeredUserRepository.findByEmailId(registerUserInputBody.getEmailId());
        if(userObject==null){
            return;
        }else {}
            //if present give message


            //else
                //add user to db
                // generate jwt token for the user and sent to UI in response
                //join with the userProductSelectedTable and other information as well



    }




}

package com.foodvilla.backend.controller;

import com.foodvilla.backend.models.InternalProcessCommonResponse;
import com.foodvilla.backend.models.Response;
import com.foodvilla.backend.models.SignInUserInputBody;
import com.foodvilla.backend.models.SignUpUserInputBody;
import com.foodvilla.backend.service.UserService;
import com.foodvilla.backend.utils.UtilityMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationAndAuthorizationController {

    Logger log = LoggerFactory.getLogger(AuthenticationAndAuthorizationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UtilityMethods utilityMethods;

    @PostMapping(value = "/v1/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> registerUserSignUP(@RequestBody SignUpUserInputBody signUpUserInputBody) {
        InternalProcessCommonResponse internalProcessCommonResponse = new InternalProcessCommonResponse();
        String refId = utilityMethods.uniqueRefIdGenerate();
        Response response = new Response();
        userService.registerUser(signUpUserInputBody, internalProcessCommonResponse, response, refId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> registerUserSignIN(@RequestBody SignInUserInputBody signInUserInputBody) {
        InternalProcessCommonResponse internalProcessCommonResponse = new InternalProcessCommonResponse();
        String refId = utilityMethods.uniqueRefIdGenerate();
        Response response = new Response();
        userService.signIn(signInUserInputBody, internalProcessCommonResponse, response, refId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void userValidation(@RequestBody SignInUserInputBody signInUserInputBody){

    }


}

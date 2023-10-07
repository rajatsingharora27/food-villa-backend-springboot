package com.foodvilla.backend.controller;

import com.foodvilla.backend.models.*;
import com.foodvilla.backend.service.UserService;
import com.foodvilla.backend.utils.UtilityMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthenticationAndAuthorizationController {

    Logger log = LoggerFactory.getLogger(AuthenticationAndAuthorizationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UtilityMethods utilityMethods;


    @PostMapping(value = "/v1/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> registerUserSignUP(@RequestBody RegisterUserInputBody registerUserInputBody) {
        InternalProcessCommonResponse internalProcessCommonResponse = new InternalProcessCommonResponse();
        String refId = utilityMethods.uniqueRefIdGenerate();
        Response response = new Response();
        userService.registerUser(registerUserInputBody, internalProcessCommonResponse, response, refId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

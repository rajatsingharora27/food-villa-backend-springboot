package com.foodvilla.backend.utils;

import com.foodvilla.backend.constants.ApplicationConstants;
import com.foodvilla.backend.models.ErrorMessageListWithCode;
import com.foodvilla.backend.models.InternalProcessCommonResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UtilityMethods {

    public List<ErrorMessageListWithCode> criticalErrorMessageList(InternalProcessCommonResponse internalProcessCommonResponse){
        List<ErrorMessageListWithCode> errorMessage=new ArrayList<>();
        for(String err : internalProcessCommonResponse.getErrorMessageList()){
            ErrorMessageListWithCode errorMessageListWithCode=new ErrorMessageListWithCode();
            String codeTypeAndMessage[]=err.split(",");
            String message=codeTypeAndMessage[1];
            String codeAndMessage[]=codeTypeAndMessage[0].split("::");
            String code=codeAndMessage[0];
            String type=codeAndMessage[1];

            errorMessageListWithCode.setCode(code);
            errorMessageListWithCode.setMessage(message);
            errorMessageListWithCode.setType(type);
            errorMessage.add(errorMessageListWithCode);
        }
        return  errorMessage;
    }

    public String uniqueRefIdGenerate(){
        String refId = "REF-ID"+UUID.randomUUID().toString().toUpperCase().replace("-", "");
        return refId;
    }

//    public String generateJwtToken(String email) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("emailId", email);
//        claims.put("created", new Date());
//        // Add additional claims if needed
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, ApplicationConstants.SECRET_KEY)
//                .compact();
//    }
}

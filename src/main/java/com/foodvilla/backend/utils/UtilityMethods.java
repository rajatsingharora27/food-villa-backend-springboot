package com.foodvilla.backend.utils;

import com.foodvilla.backend.constants.ApplicationConstants;
import com.foodvilla.backend.models.ErrorMessageListWithCode;
import com.foodvilla.backend.models.InternalProcessCommonResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.*;

@Service
public class UtilityMethods {

    @Value("${jwt.secret}")
    private String  key;


    public List<ErrorMessageListWithCode> criticalErrorMessageList(InternalProcessCommonResponse internalProcessCommonResponse) {
        List<ErrorMessageListWithCode> errorMessage = new ArrayList<>();
        for (String err : internalProcessCommonResponse.getErrorMessageList()) {
            ErrorMessageListWithCode errorMessageListWithCode = new ErrorMessageListWithCode();
            String codeTypeAndMessage[] = err.split(",");
            String message = codeTypeAndMessage[1];
            String codeAndMessage[] = codeTypeAndMessage[0].split("::");
            String code = codeAndMessage[0];
            String type = codeAndMessage[1];

            errorMessageListWithCode.setCode(code);
            errorMessageListWithCode.setMessage(message);
            errorMessageListWithCode.setType(type);
            errorMessage.add(errorMessageListWithCode);
        }
        return errorMessage;
    }

    public String uniqueRefIdGenerate() {
        String refId = "REF-ID" + UUID.randomUUID().toString().toUpperCase().replace("-", "");
        return refId;
    }

    public String generateJwtToken(String email) {
        String jwtToken = "";
        Key secretKey=getSigningKey();
        jwtToken = Jwts.builder()
                .claim("emailId", email)
                .signWith(secretKey)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(new Date(System.currentTimeMillis() + ApplicationConstants.EXPIRATION_TIME))
                .compact();
        return jwtToken;
    }


    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

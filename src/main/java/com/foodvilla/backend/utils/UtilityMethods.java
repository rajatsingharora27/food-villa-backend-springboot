package com.foodvilla.backend.utils;

import com.foodvilla.backend.models.ErrorMessageListWithCode;
import com.foodvilla.backend.models.InternalProcessCommonResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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




}

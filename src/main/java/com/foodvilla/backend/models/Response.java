package com.foodvilla.backend.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {

    @JsonProperty("refId")
    public String refId;


    @JsonProperty("messageList")
    public List<ErrorMessageListWithCode> message;

    @JsonProperty("response")
    public ResponseData responseData;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public List<ErrorMessageListWithCode> getMessage() {
        return message;
    }

    public void setMessage(List<ErrorMessageListWithCode> message) {
        this.message = message;
    }

//    public String getProductAddMessage() {
//        return productAddMessage;
//    }
//
//    public void setProductAddMessage(String productAddMessage) {
//        this.productAddMessage = productAddMessage;
//    }


    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}

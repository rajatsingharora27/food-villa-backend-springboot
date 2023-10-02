package com.foodvilla.backend.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {


    @JsonProperty("messageList")
    public List<String> message;

    @JsonProperty("response")
    public String productAddMessage;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getProductAddMessage() {
        return productAddMessage;
    }

    public void setProductAddMessage(String productAddMessage) {
        this.productAddMessage = productAddMessage;
    }
}

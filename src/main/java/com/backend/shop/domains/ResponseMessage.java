package com.backend.shop.domains;

public class ResponseMessage extends BaseResponse{

    private String message;
    public ResponseMessage(){}

    public ResponseMessage(int status, String message) {
        super(status);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.backend.shop.domains;

public class BaseResponse {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseResponse(int status) {
        this.status = status;
    }

    public BaseResponse() {
    }
}

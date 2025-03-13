package com.backend.shop.domains;

public class ResponseWithPayload<T> extends BaseResponse {
    private T payload;

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public ResponseWithPayload(int status,T payload) {
        super(status);
        this.payload = payload;
    }

    public ResponseWithPayload() {
    }
}

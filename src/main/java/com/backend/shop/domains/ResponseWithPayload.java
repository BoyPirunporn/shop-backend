package com.backend.shop.domains;

import com.backend.shop.applications.dto.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class ResponseWithPayload<T> extends BaseResponse {
    @JsonView(Views.ResponseWithPayload.class)
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

    public interface View {}
}

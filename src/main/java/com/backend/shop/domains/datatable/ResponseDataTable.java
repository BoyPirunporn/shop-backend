package com.backend.shop.domains.datatable;

import com.backend.shop.domains.BaseResponse;

import java.util.List;

public class ResponseDataTable<T> extends BaseResponse {

    private List<T> payload;
    private Long count;
    private Integer page;
    private Integer size;

    public ResponseDataTable(int status, List<T> payload, Long count, Integer page, Integer size) {
        super(status);
        this.payload = payload;
        this.count = count;
        this.page = page;
        this.size = size;
    }

    public ResponseDataTable() {
    }

    public List<T> getPayload() {
        return payload;
    }

    public void setPayload(List<T> payload) {
        this.payload = payload;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

package com.backend.shop.domains.filter;

import org.springframework.boot.context.properties.bind.DefaultValue;

public class ProductFilter {
    private int page = 0;
    private int size = 10;
    private String orderBy = "createdAt";
    private String sort = "desc";
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }

    
}

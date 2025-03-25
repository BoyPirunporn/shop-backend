package com.backend.shop.domains.datatable.product;

import com.backend.shop.domains.datatable.DataTableFilter;

public class ProductFilter extends DataTableFilter {
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

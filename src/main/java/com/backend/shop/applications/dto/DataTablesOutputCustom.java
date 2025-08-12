package com.backend.shop.applications.dto;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.SearchPanes;

import com.fasterxml.jackson.annotation.JsonView;

public class DataTablesOutputCustom<T> extends DataTablesOutput<T> {
    @Override
    @JsonView(Views.DataTable.class)
    public int getDraw() {
        return super.getDraw();
    }

    @Override
    @JsonView(Views.DataTable.class)
    public long getRecordsTotal() {
        return super.getRecordsTotal();
    }

    @Override
    @JsonView(Views.DataTable.class)
    public long getRecordsFiltered() {
        return super.getRecordsFiltered();
    }

    @Override
    @JsonView(Views.DataTable.class)
    public List<T> getData() {
        return super.getData();
    }

    @Override
    @JsonView(Views.DataTable.class)
    public SearchPanes getSearchPanes() {
        return super.getSearchPanes();
    }

    @Override
    @JsonView(Views.DataTable.class)
    public String getError() {
        return super.getError();
    }
}

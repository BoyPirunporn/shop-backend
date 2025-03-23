package com.backend.shop.applications.dto.dashboard;

import java.util.ArrayList;
import java.util.List;

import com.backend.shop.domains.models.MonthlyRevenueProjection;


public class DashBoardDTO {

    private List<MonthlyRevenueProjection> monthlyRevenues = new ArrayList<>();
    private Long subscriptions;
    private Double sales;
    private Double totalRevenues;
    public List<MonthlyRevenueProjection> getMonthlyRevenues() {
        return monthlyRevenues;
    }
    public void setMonthlyRevenues(List<MonthlyRevenueProjection> monthlyRevenues) {
        this.monthlyRevenues = monthlyRevenues;
    }
    public Long getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(Long subscriptions) {
        this.subscriptions = subscriptions;
    }
    public Double getSales() {
        return sales;
    }
    public void setSales(Double sales) {
        this.sales = sales;
    }
    public Double getTotalRevenues() {
        return totalRevenues;
    }
    public void setTotalRevenues(Double totalRevenues) {
        this.totalRevenues = totalRevenues;
    }
    

}

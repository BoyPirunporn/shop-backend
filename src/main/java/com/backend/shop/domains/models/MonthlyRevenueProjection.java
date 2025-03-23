package com.backend.shop.domains.models;

import java.util.Date;

public interface MonthlyRevenueProjection {
    Integer getYear();
    Integer getMonth();
    Double getTotalRevenue();
    Date getDate();
}
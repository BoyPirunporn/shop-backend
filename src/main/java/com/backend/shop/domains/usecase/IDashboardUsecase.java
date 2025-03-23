package com.backend.shop.domains.usecase;

import com.backend.shop.domains.models.MonthlyRevenueProjection;

import java.util.List;

public interface IDashboardUsecase {
    List<MonthlyRevenueProjection> findRevenueOfMount(Integer minMount);
    Long subscriptionCount();
}

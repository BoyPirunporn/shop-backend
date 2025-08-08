package com.backend.shop.infrastructure.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.MonthlyRevenueProjection;
import com.backend.shop.domains.usecase.IDashboardUsecase;
import com.backend.shop.infrastructure.repository.OrderJpaRepository;
import com.backend.shop.infrastructure.repository.UserJapRepoitory;

@Service
public class DashboardUsecase implements IDashboardUsecase {

    private final OrderJpaRepository orderJpaRepository;
    private final UserJapRepoitory userJapRepoitory;

    public DashboardUsecase(OrderJpaRepository orderJpaRepository,UserJapRepoitory userJapRepoitory) {
        this.orderJpaRepository = orderJpaRepository;
        this.userJapRepoitory = userJapRepoitory;
    }

    @Override
    public List<MonthlyRevenueProjection> findRevenueOfMount(Integer minMount) {
        List<MonthlyRevenueProjection> revenue = orderJpaRepository.getMonthlyRevenue(minMount);
        return revenue;
    }

    @Override
    public Long subscriptionCount() {
       return userJapRepoitory.countRoleUser();
    }
}

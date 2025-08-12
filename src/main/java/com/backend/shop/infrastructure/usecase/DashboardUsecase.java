package com.backend.shop.infrastructure.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.MonthlyRevenueProjection;
import com.backend.shop.domains.usecase.IDashboardUseCase;
import com.backend.shop.infrastructure.repository.OrderJpaRepository;
import com.backend.shop.infrastructure.repository.UserJapRepository;

@Service
public class DashboardUsecase implements IDashboardUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final UserJapRepository userJapRepoitory;

    public DashboardUsecase(OrderJpaRepository orderJpaRepository,UserJapRepository userJapRepoitory) {
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
       return userJapRepoitory.countByRoles_Name("USER");
    }
}

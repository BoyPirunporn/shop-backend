package com.backend.shop.applications.services.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.dashboard.DashBoardDTO;
import com.backend.shop.applications.interfaces.IDashboardService;
import com.backend.shop.domains.models.MonthlyRevenueProjection;
import com.backend.shop.domains.usecase.IDashboardUsecase;

@Service
public class DashboardServiceImpl implements IDashboardService {
    private final IDashboardUsecase dashboardUseCase;

    public DashboardServiceImpl(IDashboardUsecase dashboardUseCase) {
        this.dashboardUseCase = dashboardUseCase;
    }

    @Override
    public DashBoardDTO initialPage() {
        DashBoardDTO dashboard = new DashBoardDTO();

        List<MonthlyRevenueProjection> revenue = findRevenueOfMount(6);

        Double totalRevenues = revenue.stream().map(MonthlyRevenueProjection::getTotalRevenue) // ดึง totalRevenue ออกมา
                .reduce(0.0, Double::sum);

        dashboard.setTotalRevenues(totalRevenues);
        dashboard.setSales(revenue.get(revenue.size() - 1).getTotalRevenue());
        dashboard.setMonthlyRevenues(revenue);
        dashboard.setSubscriptions(subscriptionCount());
        return dashboard;
    }

    private List<MonthlyRevenueProjection> findRevenueOfMount(Integer minMount) {
        return dashboardUseCase.findRevenueOfMount(minMount);
    }

    private Long subscriptionCount() {
        return dashboardUseCase.subscriptionCount();
    }

}

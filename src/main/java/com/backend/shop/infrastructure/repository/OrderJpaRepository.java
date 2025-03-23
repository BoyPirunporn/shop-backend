package com.backend.shop.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.shop.domains.models.MonthlyRevenueProjection;
import com.backend.shop.infrastructure.entity.order.OrderEntity;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = """
                           WITH months AS (
                SELECT generate_series(
                    DATE_TRUNC('month', NOW() - INTERVAL '5 months'),
                    DATE_TRUNC('month', NOW()),
                    '1 month'::interval
                ) AS month
            )
            SELECT
                m.month AS date,  -- แปลงเป็น timestamp
                EXTRACT(YEAR FROM m.month) AS year,
                EXTRACT(MONTH FROM m.month) AS month,
                COALESCE(SUM(o.total_amount), 0) AS total_revenue
            FROM months m
            LEFT JOIN orders o ON DATE_TRUNC('month', o.order_date) = m.month
            GROUP BY year, month
            ORDER BY year, month
                                    """, nativeQuery = true)
    List<MonthlyRevenueProjection> getMonthlyRevenue(@Param("startDate") Integer startDate);
}

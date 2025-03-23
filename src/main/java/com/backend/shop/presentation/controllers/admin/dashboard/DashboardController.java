package com.backend.shop.presentation.controllers.admin.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.dashboard.DashBoardDTO;
import com.backend.shop.applications.interfaces.IDashboardService;
import com.backend.shop.domains.ResponseWithPayload;

@RestController
@RequestMapping("${application.api.prefix}/admin/dashboard")
public class DashboardController {

    private final IDashboardService dashboardService;

    public DashboardController(IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<ResponseWithPayload<DashBoardDTO>> getRevenueMountly() {
        return ResponseEntity.ok(new ResponseWithPayload<>(200,dashboardService.initialPage()));
    }

}

package com.backend.shop.presentation.controllers.activitylog;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.shop.applications.dto.activityLog.ActivityLogDTO;
import com.backend.shop.applications.interfaces.IActivityLogService;
import com.backend.shop.domains.ResponseMessage;

@RestController
@RequestMapping("${application.api.prefix}/activity-logs")
public class ActivityLogController {
    
    private final IActivityLogService activityLogService;

    public ActivityLogController(IActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> create(@RequestBody ActivityLogDTO dto){
        System.out.println(dto.toString());
        activityLogService.saveActivityLog(dto);
        return ResponseEntity.ok(new ResponseMessage(200,"ActivityLog has been created."));
    }

    @PostMapping("/datatable")
    public ResponseEntity<DataTablesOutput<ActivityLogDTO>> datatable(@RequestBody DataTablesInput input) {
        return ResponseEntity.ok(activityLogService.datatable(input));
    }
}

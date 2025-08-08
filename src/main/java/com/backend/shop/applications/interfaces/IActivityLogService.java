package com.backend.shop.applications.interfaces;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.applications.dto.activityLog.ActivityLogDTO;

public interface IActivityLogService {
    void saveActivityLog(ActivityLogDTO dto);
    DataTablesOutput<ActivityLogDTO> datatable(DataTablesInput input);
}

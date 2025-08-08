package com.backend.shop.domains.usecase;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.domains.models.ActivityLogs;

public interface IActivityLogsUseCase {
    void saveActivityLog(ActivityLogs model);
    DataTablesOutput<ActivityLogs> getDatatable(DataTablesInput input);
}

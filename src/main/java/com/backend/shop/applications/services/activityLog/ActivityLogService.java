package com.backend.shop.applications.services.activityLog;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.activityLog.ActivityLogDTO;
import com.backend.shop.applications.interfaces.IActivityLogService;
import com.backend.shop.applications.mapper.ActivityLogModelMapper;
import com.backend.shop.domains.models.ActivityLogs;
import com.backend.shop.domains.usecase.IActivityLogsUseCase;

@Service
public class ActivityLogService implements IActivityLogService {

    private final IActivityLogsUseCase activityLogsUseCase;
    private final ActivityLogModelMapper activityLogModelMapper;

    public ActivityLogService(IActivityLogsUseCase activityLogsUseCase,
            ActivityLogModelMapper activityLogModelMapper) {
        this.activityLogsUseCase = activityLogsUseCase;
        this.activityLogModelMapper = activityLogModelMapper;
    }

    @Override
    public void saveActivityLog(ActivityLogDTO dto) {
        activityLogsUseCase.saveActivityLog(activityLogModelMapper.toModel(dto));
    }

    @Override
    public DataTablesOutput<ActivityLogDTO> datatable(DataTablesInput input) {
        DataTablesOutput<ActivityLogs> output = activityLogsUseCase.getDatatable(input);

        DataTablesOutput<ActivityLogDTO> result = new DataTablesOutput<>();
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(output.getRecordsTotal());
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(activityLogModelMapper::toDTO) // <- แปลงแต่ละ entity ไป model
                        .toList());
        return result;
    }

}

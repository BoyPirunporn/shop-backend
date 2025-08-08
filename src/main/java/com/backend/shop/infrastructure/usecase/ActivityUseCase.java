package com.backend.shop.infrastructure.usecase;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.ActivityLogs;
import com.backend.shop.domains.usecase.IActivityLogsUseCase;
import com.backend.shop.infrastructure.entity.ActivityLogsEntity;
import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.mapper.ActivityLogsMapper;
import com.backend.shop.infrastructure.repository.ActivityLogsJpaRepository;
import com.backend.shop.infrastructure.utility.SecurityUtils;

@Service
public class ActivityUseCase implements IActivityLogsUseCase {

    private final ActivityLogsJpaRepository activityLogRepo;
    private final ActivityLogsMapper activityMapper;

    public ActivityUseCase(ActivityLogsJpaRepository activityLogRepo, ActivityLogsMapper activityMapper) {
        this.activityLogRepo = activityLogRepo;
        this.activityMapper = activityMapper;
    }

    @Override
    public void saveActivityLog(ActivityLogs model) {
        ActivityLogsEntity entity = activityMapper.toEntity(model);
        UsersEntity userDetails = SecurityUtils.getCurrentUserDetail();
        entity.setUser(userDetails);
        System.out.println(entity.toString());
        activityLogRepo.save(entity);
    }

    @Override
    // @Cacheable(cacheNames = "activityDataTable", keyGenerator = "customKeyGenerator")
    public DataTablesOutput<ActivityLogs> getDatatable(DataTablesInput dataTableFilter) {
        Specification<ActivityLogsEntity> spec = (root, q, cb) -> cb.equal(root.get("user").get("id"),
                SecurityUtils.getCurrentUserDetail().getId());
        DataTablesOutput<ActivityLogsEntity> output = activityLogRepo.findAll(dataTableFilter, spec);
        DataTablesOutput<ActivityLogs> result = new DataTablesOutput<>();
        result.setDraw(output.getDraw());
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(activityLogRepo.count(spec));
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(activityMapper::toModel) // <- แปลงแต่ละ entity ไป model
                        .toList());

        return result;
    }
}

package com.backend.shop.infrastructure.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.persistence.criteria.Predicate;

@Service
public class ActivityUseCase implements IActivityLogsUseCase {
    private static final Logger log = LoggerFactory.getLogger(ActivityUseCase.class);
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
        Specification<ActivityLogsEntity> spec = (root, q, cb) -> {
            Long userId = SecurityUtils.getCurrentUserDetail().getId();
            if (userId != null) {
                return cb.equal(root.get("user").get("id"), userId);
            }
            return cb.disjunction();
        };
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

package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.domains.models.ActivityLogs;
import com.backend.shop.infrastructure.entity.ActivityLogsEntity;

@Mapper(componentModel = "spring")
public interface ActivityLogsMapper {
    
    ActivityLogs toModel(ActivityLogsEntity entity);
    ActivityLogsEntity toEntity(ActivityLogs model);
    
}

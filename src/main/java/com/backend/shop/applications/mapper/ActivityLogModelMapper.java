package com.backend.shop.applications.mapper;

import org.mapstruct.Mapper;

import com.backend.shop.applications.dto.activityLog.ActivityLogDTO;
import com.backend.shop.domains.models.ActivityLogs;

@Mapper(componentModel = "spring")
public interface ActivityLogModelMapper {
    
    ActivityLogs toModel(ActivityLogDTO dto);

    ActivityLogDTO toDTO(ActivityLogs model);
}

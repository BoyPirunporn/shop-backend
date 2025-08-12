package com.backend.shop.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.backend.shop.domains.models.ActivityLogs;
import com.backend.shop.infrastructure.entity.ActivityLogsEntity;

@Mapper(componentModel = "spring")
public interface ActivityLogsMapper {
    
    @Mapping(target="user.roles", ignore = true)
    @Mapping(target="user.authProviders", ignore = true)
    ActivityLogs toModel(ActivityLogsEntity entity);

    @Mapping(target="user.roles", ignore = true)
    @Mapping(target="user.authProviders", ignore = true)
    ActivityLogsEntity toEntity(ActivityLogs model);
    
}

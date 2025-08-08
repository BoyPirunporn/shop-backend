package com.backend.shop.infrastructure.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.backend.shop.infrastructure.entity.ActivityLogsEntity;

public interface ActivityLogsJpaRepository extends DataTablesRepository<ActivityLogsEntity, Long> {

}

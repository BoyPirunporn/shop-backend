package com.backend.shop.infrastructure.specification.datatable;

import com.backend.shop.infrastructure.entity.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import com.backend.shop.domains.datatable.DataTableFilter;

public class DataTableSpecification<T extends BaseEntity> {
     public  <T> Specification<T> filterBy(DataTableFilter filter) {
        return (root, query, criteriaBuilder) -> {
            // Filter by other criteria (if any)
            
            // Default sorting: orderBy by 'createdAt' descending if not provided
            if (filter.getOrderBy() != null && filter.getSort() != null) {
                if ("desc".equalsIgnoreCase(filter.getSort())) {
                    query.orderBy(criteriaBuilder.desc(root.get(filter.getOrderBy())));
                } else {
                    query.orderBy(criteriaBuilder.asc(root.get(filter.getOrderBy())));
                }
            } else {
                // Default sorting by 'createdAt' in descending order
                query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
            }
            
            return criteriaBuilder.conjunction(); // Return true for all conditions
        };
    }
}

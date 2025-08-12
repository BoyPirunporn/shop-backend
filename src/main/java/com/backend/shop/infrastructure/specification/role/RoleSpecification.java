package com.backend.shop.infrastructure.specification.role;

import com.backend.shop.infrastructure.entity.RoleEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;

public class RoleSpecification {

    /**
     * Creates a Specification to eagerly fetch all related entities for a Role.
     * This is crucial for preventing N+1 query problems when serializing the Role DTO
     * with its full set of permissions and menu items.
     *
     * @return A Specification for RoleEntity.
     */
    public static Specification<RoleEntity> fetchFullRoleData(int currentRole) {
        return (root, query, cb) -> {
            // We only apply fetches for non-count queries to avoid issues.
            // if (query != null && query.getResultType() != Long.class && query.getResultType() != long.class) {
            //     query.distinct(true); // Essential to avoid duplicate roles from JOINs
            //     Fetch<Object, Object> roleMenuPermissions = root.fetch("roleMenuPermissions", JoinType.LEFT);
            //     roleMenuPermissions.fetch("menuItem", JoinType.LEFT);
            //     roleMenuPermissions.fetch("permission", JoinType.LEFT);
            // }
            return cb.greaterThanOrEqualTo(root.get("level"),currentRole); // Returns an empty "where" clause
        };
    }
}
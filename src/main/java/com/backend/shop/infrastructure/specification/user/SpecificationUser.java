package com.backend.shop.infrastructure.specification.user;

import org.springframework.data.jpa.domain.Specification;

import com.backend.shop.infrastructure.entity.RoleEntity;
import com.backend.shop.infrastructure.entity.UsersEntity;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class SpecificationUser {
    public static Specification<UsersEntity> fetchUserFilterLevelRole(int level) {
        return (root, query, cb) -> {
            // We only apply fetches for non-count queries to avoid issues.
            Join<UsersEntity, RoleEntity> rolesJoin = root.join("roles", JoinType.INNER);
            return cb.greaterThanOrEqualTo(rolesJoin.get("level"), level); // Returns an empty "where" clause
        };
    }
}

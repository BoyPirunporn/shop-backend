package com.backend.shop.infrastructure.specification.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.Column;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;

import com.backend.shop.infrastructure.entity.MenuItemEntity;
import com.backend.shop.infrastructure.entity.UsersEntity;

import jakarta.persistence.criteria.Predicate;

public class MenuItemSpecification {

    public static Specification<MenuItemEntity> createSpecification(DataTablesInput input, UsersEntity user) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Apply role-based filtering first (if a user is present and has roles)
            // if (user != null && user.getRoles() != null && !user.getRoles().isEmpty()) {
            //     Set<Long> roleIds = user.getRoles().stream()
            //             .map(RoleEntity::getId)
            //             .collect(Collectors.toSet());
            //     // Ensure distinct results as a user can have multiple roles pointing to the
            //     // same menu
            //     query.distinct(true);
            //     predicates.add(root.join("roleMenuPermissions").get("role").get("id").in(roleIds));
            // }

            // 2. Apply column-specific filters from DataTablesInput
            for (Column column : input.getColumns()) {
                String data = column.getData();
                String searchValue = column.getSearch().getValue();

                if (searchValue == null || searchValue.isEmpty()) {
                    continue;
                }

                switch (data) {
                    case "title":
                        // Using "contains" for a better search experience in DataTables
                        predicates.add(cb.like(cb.lower(root.get("title")), "%" + searchValue.toLowerCase() + "%"));
                        break;
                    case "parent":
                        if ("isNull".equalsIgnoreCase(searchValue)) {
                            predicates.add(cb.isNull(root.get("parent")));
                        } else {
                            try {
                                Long parentId = Long.parseLong(searchValue);
                                predicates.add(cb.equal(root.get("parent").get("id"), parentId));
                            } catch (NumberFormatException e) {
                                // Ignore if the search value is not a valid Long for parent ID
                            }
                        }
                        break;
                    // --- Example of extending with more complex searches ---
                    case "visible", "isGroup":
                        predicates.add(cb.equal(root.get(data), Boolean.parseBoolean(searchValue)));
                        break;
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

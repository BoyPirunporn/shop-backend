package com.backend.shop.infrastructure.specification.product;

import com.backend.shop.domains.datatable.product.ProductFilter;
import com.backend.shop.infrastructure.entity.ProductEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecification {

    public Specification<ProductEntity> filterProduct(ProductFilter filter){
        return (root,query,criteriaBuilder) -> {
            Predicate categoryPredicate = null;
            if(filter.getCategory() != null){
               categoryPredicate = criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("category").get("parent").get("name")),
                        filter.getCategory().toLowerCase()  // Convert the parameter to lowercase for comparison
                );
                query.where(categoryPredicate);
            }

            // Add sorting based on the provided sort field and direction
            if (filter.getOrderBy() != null && filter.getSort() != null) {
                if ("desc".equalsIgnoreCase(filter.getSort())) {
                    query.orderBy(criteriaBuilder.desc(root.get(filter.getOrderBy())));
                } else {
                    query.orderBy(criteriaBuilder.asc(root.get(filter.getOrderBy())));
                }
            } else {
                // Default sorting by 'createdAt' in descending order if no order is specified
                query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
            }
            // Add the category filter to the query
            return categoryPredicate;
        };
    }
}

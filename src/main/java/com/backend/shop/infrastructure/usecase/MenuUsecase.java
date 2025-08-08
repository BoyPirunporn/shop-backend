package com.backend.shop.infrastructure.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.Search;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.Category;
import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.usecase.IMenuUsecase;
import com.backend.shop.infrastructure.entity.CategoryEntity;
import com.backend.shop.infrastructure.entity.MenuItemEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.mapper.MenuItemMapper;
import com.backend.shop.infrastructure.repository.MenuItemJpaRepository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuUsecase implements IMenuUsecase {

    private final MenuItemJpaRepository menuItemJpaRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuUsecase(MenuItemJpaRepository menuItemJpaRepository, MenuItemMapper menuItemMapper) {
        this.menuItemJpaRepository = menuItemJpaRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    public List<MenuItem> getMenu() {
        return menuItemJpaRepository.findRootMenusWithChildren().stream().map(menuItemMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> getMenuByTitle(String title) {
        List<MenuItemEntity> menuItems = menuItemJpaRepository.findAllByTitleStartingWithIgnoreCase(title);
        return menuItems.stream().map(menuItemMapper::toModel).collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "menuDataTable", keyGenerator = "customKeyGenerator")
    public DataTablesOutput<MenuItem> getMenuDataTable(DataTablesInput dataTableFilter) {
        // log.info("GET FIRST");
        Specification<MenuItemEntity> specCriterial = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // log.info(Arrays.toString(dataTableFilter.getColumns().toArray()));

            dataTableFilter.getColumns().forEach(col -> {
                String data = col.getData();
                Search search = col.getSearch();
                if ("parent".equals(data) && search != null && !search.getValue().isEmpty()) {
                    if ("isNull".equals(search.getValue())) {
                        predicates.add(cb.isNull(root.get("parent")));
                    } else {
                        predicates.add(cb.equal(root.get("parent").get("id"), Long.parseLong(search.getValue())));
                    }
                }
                if ("title".equals(data) && search != null && !search.getValue().isEmpty()) {
                    predicates.add(cb.like(cb.lower(root.get("title")), search.getValue().toLowerCase() + "%"));
                }
            });
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        // dataTableFilter.setSearch(new Search());
        DataTablesOutput<MenuItemEntity> output = menuItemJpaRepository.findAll(dataTableFilter, specCriterial);
        DataTablesOutput<MenuItem> result = new DataTablesOutput<>();
        result.setDraw(output.getDraw());
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(menuItemJpaRepository.count(specCriterial));
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(menuItemMapper::toModel) // <- แปลงแต่ละ entity ไป model
                        .toList());

        return result;
    }

    @Override
    public DataTablesOutput<MenuItem> getMenuDataTable(DataTablesInput dataTableFilter, String parentId) {
        Specification<MenuItemEntity> spec = (root, query, cb) -> {
            Join<MenuItemEntity, MenuItemEntity> parentJoin = root.join("parent", JoinType.LEFT);
            return cb.equal(parentJoin.get("id"), parentId);
        };
        DataTablesOutput<MenuItemEntity> output = menuItemJpaRepository.findAll(dataTableFilter, spec);
        DataTablesOutput<MenuItem> result = new DataTablesOutput<>();
        result.setDraw(output.getDraw());
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(output.getRecordsTotal());
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(menuItemMapper::toModel) // <- แปลงแต่ละ entity ไป model
                        .toList());

        return result;
    }

    @Override
    public void created(MenuItem menuItem) {
        if (menuItemJpaRepository.existsByTitle(menuItem.getTitle())) {
            throw new BaseException("Title existing", HttpStatus.BAD_REQUEST);
        }
        ;
        MenuItemEntity entity = mapToMenuItemEntity(menuItem, menuItemMapper.toEntity(menuItem.getParent()));
        menuItemJpaRepository.save(entity);
    }

    private MenuItemEntity mapToMenuItemEntity(MenuItem model, MenuItemEntity parent) {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(model.getId());
        entity.setTitle(model.getTitle());
        entity.setIcon(model.getIcon());
        entity.setIsGroup(model.getIsGroup());
        entity.setVisible(model.getVisible());
        entity.setUrl(model.getUrl());
        entity.setSortOrder(model.getSortOrder());
        entity.setParent(parent);
        // ตรวจสอบ children และ map พวกมัน
        List<MenuItemEntity> children = Optional.ofNullable(model.getItems())
                .orElse(Collections.emptyList()) // ป้องกัน NPE
                .stream()
                .map(child -> {
                    return mapToMenuItemEntity(child, entity);
                })
                .collect(Collectors.toList());
        entity.setItems(children); // กำหนด children ให้ MenuItem

        return entity;
    }

}

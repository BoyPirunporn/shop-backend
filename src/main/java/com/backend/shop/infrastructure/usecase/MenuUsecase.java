package com.backend.shop.infrastructure.usecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.models.MenuItemBasic;
import com.backend.shop.domains.usecase.IMenuUseCase;
import com.backend.shop.infrastructure.entity.MenuItemEntity;
import com.backend.shop.infrastructure.entity.RoleEntity;
import com.backend.shop.infrastructure.entity.RoleMenuPermissionEntity;
import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.exceptions.BaseException;
import com.backend.shop.infrastructure.mapper.MenuItemMapper;
import com.backend.shop.infrastructure.repository.MenuItemJpaRepository;
import com.backend.shop.infrastructure.specification.menu.MenuItemSpecification;
import com.backend.shop.infrastructure.utility.SecurityUtils;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

@Service
public class MenuUseCase implements IMenuUseCase {
    private static final Logger log = LoggerFactory.getLogger(MenuUseCase.class);

    private final MenuItemJpaRepository menuItemJpaRepository;
    private final MenuItemMapper menuItemMapper;

    public MenuUseCase(MenuItemJpaRepository menuItemJpaRepository, MenuItemMapper menuItemMapper) {
        this.menuItemJpaRepository = menuItemJpaRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    public List<MenuItem> getAllMenu() {
        return menuItemJpaRepository.findAllByParentIsNull().stream()
                .map(menuItemMapper::toModelWithOutRoleMenuPermission)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> getMenuByRole() {
        UsersEntity user = SecurityUtils.getCurrentUserDetail();
        if (user == null || user.getRoles().isEmpty()) {
            return Collections.emptyList();
        }

        Set<Long> userRoleIds = user.getRoles().stream()
                .map(RoleEntity::getId)
                .collect(Collectors.toSet());

        // ดึง menu item ทั้งหมดที่ user สามารถเข้าถึงได้
        List<MenuItemEntity> allAccessibleItems = menuItemJpaRepository
                .findDistinctByRoleMenuPermissions_Role_IdInOrderBySortOrderAsc(userRoleIds);

        // กรอง roleMenuPermissions ให้เหลือเฉพาะของเมนูนั้น และ role ของ user
        allAccessibleItems.forEach(item -> {
            if (item.getRoleMenuPermissions() != null) {
                Set<RoleMenuPermissionEntity> filtered = item.getRoleMenuPermissions().stream()
                        .filter(rmp -> rmp.getMenuItem() != null
                                && rmp.getMenuItem().getId().equals(item.getId())
                                && rmp.getRole() != null
                                && userRoleIds.contains(rmp.getRole().getId())).map(e -> {
                                    e.setMenuItem(null);
                                    return e;
                                })
                        .collect(Collectors.toSet());
                item.setRoleMenuPermissions(filtered);
            } else {
                item.setRoleMenuPermissions(new HashSet<>());
            }
            item.setItems(new ArrayList<>()); // เตรียม children
        });

        // สร้าง menu tree
        Map<Long, MenuItemEntity> menuMap = allAccessibleItems.stream()
                .collect(Collectors.toMap(MenuItemEntity::getId, item -> item));

        List<MenuItemEntity> rootNodes = new ArrayList<>();
        allAccessibleItems.forEach(item -> {
            MenuItemEntity parent = item.getParent();
            if (parent != null && menuMap.containsKey(parent.getId())) {
                menuMap.get(parent.getId()).getItems().add(item);
            } else {
                rootNodes.add(item);
            }
        });

        // แปลงเป็น model สำหรับ UI
        return rootNodes.stream()
                .map(menuItemMapper::toModel)
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
        UsersEntity user = SecurityUtils.getCurrentUserDetail();
        Specification<MenuItemEntity> spec = MenuItemSpecification.createSpecification(dataTableFilter, user);

        DataTablesOutput<MenuItemEntity> output = menuItemJpaRepository.findAll(dataTableFilter, spec);
        DataTablesOutput<MenuItem> result = new DataTablesOutput<>();
        result.setDraw(output.getDraw());
        result.setRecordsFiltered(output.getRecordsFiltered());
        // Use count from the output to avoid an extra query
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

    @Override
    public DataTablesOutput<MenuItemBasic> getMenuDataTableBasic(DataTablesInput dataTableFilter) {
        // log.info("GET FIRST");
        UsersEntity user = SecurityUtils.getCurrentUserDetail();
        Specification<MenuItemEntity> spec = MenuItemSpecification.createSpecification(dataTableFilter, user);

        DataTablesOutput<MenuItemEntity> output = menuItemJpaRepository.findAll(dataTableFilter, spec);
        DataTablesOutput<MenuItemBasic> result = new DataTablesOutput<>();
        result.setDraw(output.getDraw());
        result.setRecordsFiltered(output.getRecordsFiltered());
        // Use count from the output to avoid an extra query
        result.setRecordsTotal(output.getRecordsTotal());
        result.setError(output.getError());

        // map entities to models
        result.setData(
                output.getData()
                        .stream()
                        .map(menuItemMapper::toModelBasic) // <- แปลงแต่ละ entity ไป model
                        .toList());

        return result;
    }

}

package com.backend.shop.applications.services.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.shop.applications.dto.user.UserRequest;
import com.backend.shop.applications.interfaces.IUserService;
import com.backend.shop.applications.mapper.MenuItemModelMapper;
import com.backend.shop.applications.mapper.UserModelMapper;
import com.backend.shop.domains.models.MenuItem;
import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.Role;
import com.backend.shop.domains.models.RoleMenuPermission;
import com.backend.shop.domains.models.User;
import com.backend.shop.domains.usecase.IUserUseCase;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserModelMapper userMapper;
    private final MenuItemModelMapper menuItemModelMapper;
    private final IUserUseCase userUseCase;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserModelMapper userMapper, MenuItemModelMapper menuItemModelMapper,
            IUserUseCase userUseCase, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.menuItemModelMapper = menuItemModelMapper;
        this.userUseCase = userUseCase;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserRequest request) {
        User user = userMapper.mapUserRequestToUserModel(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<MenuItem> menuItems = request.getMenuItems().stream()
                .map(menuItemModelMapper::mapMenuItemRequestToMenuItemModel).collect(Collectors.toList());

        Permissions viewPermission = userUseCase.findPermissionByName("VIEW");

        // assign role/menu to all existing permissions
        for (Role role : user.getRoles()) {
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getRoleMenuPermissions() != null) {
                    for (RoleMenuPermission rmp : menuItem.getRoleMenuPermissions()) {
                        rmp.setRole(role);
                        rmp.setMenuItem(menuItem);
                    }
                }

                // assign VIEW to parent if missing
                MenuItem parent = menuItem.getParent();
                if (parent != null) {
                    boolean hasView = parent.getRoleMenuPermissions().stream()
                            .anyMatch(p -> "VIEW".equalsIgnoreCase(p.getPermission().getName())
                                    && p.getRole().equals(role));
                    if (!hasView) {
                        RoleMenuPermission viewPerm = new RoleMenuPermission();
                        viewPerm.setRole(role);
                        viewPerm.setMenuItem(parent);
                        viewPerm.setPermission(viewPermission);
                        parent.getRoleMenuPermissions().add(viewPerm);
                    }
                    menuItem.setParent(parent);
                }
            }
        }

        Set<RoleMenuPermission> roleMenuPermissions = collectRoleMenuPermissions(menuItems, user.getRoles(),
                viewPermission);

        userUseCase.createUser(user, roleMenuPermissions);

    }

    private Set<RoleMenuPermission> collectRoleMenuPermissions(List<MenuItem> menuItems, Set<Role> roles,
            Permissions viewPermission) {
        Set<RoleMenuPermission> allPermissions = new HashSet<>();

        for (Role role : roles) {
            Set<Long> processedParentIds = new HashSet<>();

            for (MenuItem menuItem : menuItems) {
                collectMenuPermissionsRecursive(menuItem, role, allPermissions, viewPermission, processedParentIds);
            }
        }

        return allPermissions;
    }

    private void collectMenuPermissionsRecursive(MenuItem menuItem, Role role, Set<RoleMenuPermission> collector,
            Permissions viewPermission, Set<Long> processedParentIds) {
        if (menuItem.getRoleMenuPermissions() != null) {
            // assign role/menu ให้ permission ที่มีอยู่แล้ว
            for (RoleMenuPermission rmp : menuItem.getRoleMenuPermissions()) {
                System.out.println("TO -> " + rmp.getPermission().toString());
                rmp.setRole(role);
                rmp.setMenuItem(menuItem);
                collector.add(rmp);
            }
        } else {
            menuItem.setRoleMenuPermissions(new HashSet<>());
        }

        // assign VIEW ให้ parent ถ้ายังไม่มี
        MenuItem parent = menuItem.getParent();
        if (parent != null && !processedParentIds.contains(parent.getId())) {
            boolean hasView = parent.getRoleMenuPermissions().stream()
                    .anyMatch(p -> "VIEW".equalsIgnoreCase(p.getPermission().getName()) && p.getRole().equals(role));
            if (!hasView) {
                RoleMenuPermission viewPerm = new RoleMenuPermission();
                viewPerm.setRole(role);
                viewPerm.setMenuItem(parent);
                viewPerm.setPermission(viewPermission);
                parent.getRoleMenuPermissions().add(viewPerm);
                collector.add(viewPerm);
            }
            // mark parent ว่า processed แล้ว
            processedParentIds.add(parent.getId());
            // recursive call
            collectMenuPermissionsRecursive(parent, role, collector, viewPermission,processedParentIds);
        }
    }

    @Override
    public void updateUser(UserRequest user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}

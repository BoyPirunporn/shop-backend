package com.backend.shop.applications.mapper;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.backend.shop.applications.dto.auth.AuthDTO;
import com.backend.shop.applications.dto.auth.UserAuthProviderDTO;
import com.backend.shop.applications.dto.roleAndPermission.PermissionsDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleBasicDTO;
import com.backend.shop.applications.dto.roleAndPermission.RoleDTO;
import com.backend.shop.applications.dto.user.UserRequest;
import com.backend.shop.domains.models.Permissions;
import com.backend.shop.domains.models.Role;
import com.backend.shop.domains.models.RoleBasic;
import com.backend.shop.domains.models.User;
import com.backend.shop.domains.models.UserAuthProvider;

@Mapper(componentModel = "spring",uses = {RoleMenuPermissionModelMapper.class})
public interface UserModelMapper {

    User toModel(AuthDTO dto);

    AuthDTO toDTO(User user);


    RoleDTO toDTO(Role model);

    RoleBasicDTO toBasicDTO(RoleBasic model);

    Role toModel(RoleDTO dto);

    PermissionsDTO toDTO(Permissions model);

    Permissions toModel(PermissionsDTO dto);

    @Mapping(target = "user.authProviders", ignore = true)
    @Mapping(target = "user.roles", ignore = true)
    UserAuthProvider toModel(UserAuthProviderDTO dto);

    @Mapping(target = "user.authProviders", ignore = true)
    @Mapping(target = "user.roles", ignore = true)
    UserAuthProviderDTO toDTO(UserAuthProvider model);


    @Named("mapUserRequestToUserModel")
    @Mapping(source = "roles",target = "roles", qualifiedByName="mapRoleRequestToRoleModel")
    User mapUserRequestToUserModel(UserRequest userRequest);
    
    @Named("mapRoleRequestToRoleModel")
    default Set<Role> mapRoleRequestToRoleModel(List<UserRequest.RoleRequest> userRequest){
        if (userRequest == null) {
        return Collections.emptySet();
    }
        return userRequest.stream().map(role -> {

            Role model = new Role();
            model.setId(role.getId());
            return model;
        }).collect(Collectors.toSet());
    }
}

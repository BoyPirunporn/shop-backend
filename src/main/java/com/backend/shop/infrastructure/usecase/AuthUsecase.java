package com.backend.shop.infrastructure.usecase;

import java.beans.Transient;
import java.util.stream.Collectors;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.backend.shop.domains.models.User;
import com.backend.shop.domains.usecase.IAuthUseCase;
import com.backend.shop.infrastructure.entity.RoleEntity;
import com.backend.shop.infrastructure.entity.UsersEntity;
import com.backend.shop.infrastructure.mapper.AuthMapper;
import com.backend.shop.infrastructure.repository.UserJapRepository;
import com.backend.shop.infrastructure.specification.user.SpecificationUser;
import com.backend.shop.infrastructure.utility.SecurityUtils;

@Service
public class AuthUseCase implements IAuthUseCase {

    private final AuthMapper authMapper;
    private final UserJapRepository userJapRepository;

    public AuthUseCase(UserJapRepository userJapRepository, AuthMapper authMapper) {
        this.userJapRepository = userJapRepository;
        this.authMapper = authMapper;
    }

    @Override
    @Transient
    public User createUser(User user) {
        // UsersEntity usersEntity = new UsersEntity();
        // usersEntity.setEmail(user.getEmail());
        // usersEntity.setPassword(user.getPassword());
        // usersEntity.setRoles(user.getRoles());
        UsersEntity saveUser = userJapRepository.save(authMapper.toEntity(user));
        return authMapper.toModel(saveUser);
    }

    @Override
    public boolean existingByEmail(String email) {
        return userJapRepository.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return authMapper.toModel(userJapRepository.findByEmail(email).orElse(null));
    }

    @Override
    public DataTablesOutput<User> dataTable(DataTablesInput input) {
        UsersEntity user = SecurityUtils.getCurrentUserDetail();
        int currentRole = user.getRoles().stream().mapToInt(RoleEntity::getLevel).min().orElse(Integer.MAX_VALUE);
        DataTablesOutput<UsersEntity> output = userJapRepository.findAll(input,
                SpecificationUser.fetchUserFilterLevelRole(currentRole));

        DataTablesOutput<User> result = new DataTablesOutput<>();
        result.setData(output.getData().stream().map(authMapper::toModelWithOutRole).collect(Collectors.toList()));
        result.setDraw(output.getDraw());
        result.setError(output.getError());
        result.setRecordsFiltered(output.getRecordsFiltered());
        result.setRecordsTotal(output.getRecordsTotal());
        result.setSearchPanes(output.getSearchPanes());

        return result;
    }

}

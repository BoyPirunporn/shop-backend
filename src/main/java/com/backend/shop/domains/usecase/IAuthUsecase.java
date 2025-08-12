package com.backend.shop.domains.usecase;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.backend.shop.domains.models.User;

public interface IAuthUseCase {
    public User createUser(User user);
    public boolean existingByEmail(String email);
    public User findByEmail(String email);
    public DataTablesOutput<User> dataTable(DataTablesInput input);
}

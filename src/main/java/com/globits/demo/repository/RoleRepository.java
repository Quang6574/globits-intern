package com.globits.demo.repository;

import com.globits.demo.model.Role;

import java.util.List;

public interface RoleRepository {
    Role create(Role role);

    List<Role> getAll(int page, int pageSize);
    Role get(String id);

    Role save(Role role);
    void delete(String id);
}

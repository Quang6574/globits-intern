package com.globits.demo.dao;

import com.globits.demo.model.Role;

import java.util.List;

public interface RoleDAO {
    Role create(Role role);

    List<Role> getAll();
    Role get(String id);

    Role save(Role role);
    void delete(String id);
}

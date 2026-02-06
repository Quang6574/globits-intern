package com.globits.demo.service;

import com.globits.demo.dto.RoleCreateDTO;
import com.globits.demo.dto.RoleViewDTO;

import java.util.List;

public interface RoleService {
    RoleCreateDTO create(RoleCreateDTO roleDTO);

    List<RoleCreateDTO> getAll();
    RoleViewDTO get(String role);

    RoleCreateDTO save(String code, RoleCreateDTO roleDTO);
    void delete(String code);

}

package com.globits.demo.service;

import com.globits.demo.dto.DepartmentCreateDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentCreateDTO create(DepartmentCreateDTO country);

    List<DepartmentCreateDTO> getAll();
    DepartmentCreateDTO get(int id);

    DepartmentCreateDTO save(int id, DepartmentCreateDTO country);
    void delete(int id);
}

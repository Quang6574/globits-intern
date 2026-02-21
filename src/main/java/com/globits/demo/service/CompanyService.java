package com.globits.demo.service;

import com.globits.demo.dto.CompanyDTO;
import com.globits.demo.dto.DepartmentCreateDTO;

import java.util.List;

public interface CompanyService {


    CompanyDTO create(CompanyDTO country);

    List<CompanyDTO> getAll();
    CompanyDTO get(String id);

    CompanyDTO save(String code, CompanyDTO country);
    void delete(String id);

    List<DepartmentCreateDTO> getAllDepartment(String id);
}

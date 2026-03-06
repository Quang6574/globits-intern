package com.globits.demo.service;

import com.globits.demo.dto.CompanyDTO;
import com.globits.demo.dto.DepartmentDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getAll(int page, int pageSize);
    CompanyDTO get(String id);

    void delete(String id);

    List<DepartmentDTO> getAllDepartment(String id);

    CompanyDTO createOrUpdate(CompanyDTO companyDTO);
}

package com.globits.demo.service;

import com.globits.demo.dto.DepartmentCompanyDTO;
import com.globits.demo.dto.DepartmentDTO;
import com.globits.demo.dto.DepartmentParentDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDTO> getAll(int page, int pageSize);
    DepartmentDTO get(int id);

    void delete(int id);

    DepartmentDTO editCompany(int id, DepartmentCompanyDTO dto);
    DepartmentDTO editParent(int id, DepartmentParentDTO dto);

    DepartmentDTO createOrUpdate(DepartmentDTO countryDTO);
}

package com.globits.demo.repository;

import com.globits.demo.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department create(Department department);

    List<Department> getAll(int page, int pageSize);
    Department get(int id);

    Department save(Department department);
    void delete(int id);

    List<Department> getByCompanyCode(String companyCode);

    Department createOrUpdate(Department department);

}

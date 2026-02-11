package com.globits.demo.dao;

import com.globits.demo.model.Department;

import java.util.List;

public interface DepartmentDAO {
    Department create(Department department);

    List<Department> getAll();
    Department get(int id);

    Department save(Department department);
    void delete(int id);

}

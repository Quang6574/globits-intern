package com.globits.demo.dto;

import com.globits.demo.model.Department;

public class DepartmentParentDTO {

    private Integer id;

    public DepartmentParentDTO() {
    }
    public DepartmentParentDTO(Department entity) {
        if (entity != null) {
            this.id = entity.getId();
        }
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}

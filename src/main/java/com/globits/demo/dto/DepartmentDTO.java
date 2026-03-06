package com.globits.demo.dto;

import com.globits.demo.model.Department;

public class DepartmentDTO {
    private Integer id;
    private String name;
    private String code;

    public DepartmentDTO() {
    }
    public DepartmentDTO(Department entity) {
        if (entity == null) return;
        this.name = entity.getName();
        this.code = entity.getCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

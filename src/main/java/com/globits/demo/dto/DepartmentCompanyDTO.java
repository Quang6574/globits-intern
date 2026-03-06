package com.globits.demo.dto;

import com.globits.demo.model.Department;

public class DepartmentCompanyDTO {
    private String code;

    public DepartmentCompanyDTO() {
    }
    public DepartmentCompanyDTO(Department department) {
        if (department != null && department.getCompany() != null) {
            this.code = department.getCompany().getCode();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

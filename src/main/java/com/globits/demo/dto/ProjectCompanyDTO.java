package com.globits.demo.dto;

public class ProjectCompanyDTO {
    private String companyCode;

    public ProjectCompanyDTO() {}
    public ProjectCompanyDTO(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}

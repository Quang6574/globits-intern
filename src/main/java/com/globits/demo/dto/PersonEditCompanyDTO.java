package com.globits.demo.dto;

import com.globits.demo.model.Company;

public class PersonEditCompanyDTO {

    private String companyCode;

    public PersonEditCompanyDTO() {
    }
    public PersonEditCompanyDTO(Company company) {
        if (company != null) {
            this.companyCode = company.getCode();
        }
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}

package com.globits.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.globits.demo.model.Company;

import java.util.List;

public class CompanyDTO {

    private String code;
    private String name;
    private String address;

    public CompanyDTO() {}

    public CompanyDTO(Company company) {
        if (company != null) {
            this.code = company.getCode();
            this.name = company.getName();
            this.address = company.getAddress();
        }
    }

    @JsonProperty("persons")
    private List<PersonViewDTO> persons;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonViewDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonViewDTO> persons) {
        this.persons = persons;
    }
}

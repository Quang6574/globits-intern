package com.globits.demo.model;

import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

public class Project {
    private Integer id;
    private String code;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "projects")
    private Set<Company> company = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Company> getCompany() {
        return company;
    }

    public void setCompany(Set<Company> company) {
        this.company = company;
    }
}

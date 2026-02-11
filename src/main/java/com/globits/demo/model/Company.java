package com.globits.demo.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_company")
public class Company {

    @Id
    private String code;
    private String name;
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Person> person;

    @ManyToMany
    @JoinTable(
            name = "tbl_company_project",
            joinColumns = @JoinColumn(name = "company_code", referencedColumnName = "code"),
            inverseJoinColumns = @JoinColumn(name = "project_code", referencedColumnName = "code")
    )
    private Set<Project> projects = new HashSet<>();

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

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }
}

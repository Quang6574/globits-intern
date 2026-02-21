package com.globits.demo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_company")
public class Company {

    @Id
    private String code;
    @Column
    private String name;
    @Column
    private String address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Person> person;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Project> project;

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

    public List<Project> getProject() {
        return project;
    }
    public void setProject(List<Project> project) {
        this.project = project;
    }

}

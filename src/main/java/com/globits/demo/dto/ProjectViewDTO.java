package com.globits.demo.dto;

import java.util.List;

public class ProjectViewDTO {
    private String name;
    private String code;

    private List<PersonViewDTO> persons;

    public List<PersonViewDTO> getPersons() {
        return persons;
    }
    public void setPersons(List<PersonViewDTO> persons) {
        this.persons = persons;
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

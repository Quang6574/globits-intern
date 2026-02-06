package com.globits.demo.dto;

import java.util.List;

public class RoleViewDTO {
    private String role;
    private String description;

    private List<PersonViewDTO> persons;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PersonViewDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonViewDTO> persons) {
        this.persons = persons;
    }
}

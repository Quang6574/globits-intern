package com.globits.demo.dto;

public class PersonRoleDTO {
    private String role;
    public PersonRoleDTO() {
    }
    public PersonRoleDTO(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}

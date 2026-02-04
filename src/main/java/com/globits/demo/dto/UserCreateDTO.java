package com.globits.demo.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateDTO {
    private String email;
    @JsonProperty("is_active")
    private Boolean isActive;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.globits.demo.dto;

import com.globits.demo.model.Person;

public class PersonAddUserDTO {
    private Integer userId;

    public PersonAddUserDTO() {
    }
    public PersonAddUserDTO(Person person) {
        if (person != null && person.getUser() != null)
            this.userId = person.getUser().getId();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

package com.globits.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PersonViewDTO {
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("phone_num")
    private String phoneNum;

    private String email;



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

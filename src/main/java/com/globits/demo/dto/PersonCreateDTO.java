package com.globits.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class PersonCreateDTO {
    @JsonProperty("full_name")
    private String fullName;
    private String gender;
    private Date dob;
    private String address;

    @JsonProperty("phone_num")
    private String phoneNum;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}

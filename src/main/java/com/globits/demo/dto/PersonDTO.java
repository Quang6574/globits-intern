package com.globits.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.globits.demo.model.Person;

import java.sql.Date;

public class PersonDTO {

    private Integer id;
    @JsonProperty("full_name")
    private String fullName;
    private String gender;
    private Date dob;
    private String address;

    @JsonProperty("phone_num")
    private String phoneNum;

    public PersonDTO() {
    }
    public PersonDTO(Person person) {
        if (person != null) {
            this.fullName = person.getFullName();
            this.gender = person.getGender();
            this.dob = person.getDob();
            this.address = person.getAddress();
            this.phoneNum = person.getPhoneNum();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

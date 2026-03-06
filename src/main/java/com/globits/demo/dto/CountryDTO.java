package com.globits.demo.dto;

import com.globits.demo.model.Country;

public class CountryDTO {
    private Integer id;
    private String name;
    private String code;
    private String description;

    public CountryDTO() {}

    public CountryDTO(Country country) {
        if (country != null) {
            this.name = country.getName();
            this.code = country.getCode();
            this.description = country.getDescription();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

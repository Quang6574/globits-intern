package com.globits.demo.service;

import com.globits.demo.model.Country;

import java.util.List;

public interface CountryService {

    Country create(Country country);

    List<Country> getAll();
    Country get(int id);

    Country save(Country country);
    void delete(int id);

}

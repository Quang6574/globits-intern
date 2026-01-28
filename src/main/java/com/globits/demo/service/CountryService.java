package com.globits.demo.service;

import com.globits.demo.model.Country;

import java.util.List;

public interface CountryService {
    void create(Country country);

    List<Country> getAll();
    Country get(int id);

    void save(Country country);
    void delete(int id);
}

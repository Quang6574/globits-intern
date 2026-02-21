package com.globits.demo.dao;

import com.globits.demo.model.Country;

import java.util.List;

public interface CountryDAO {

    Country create(Country country);

    List<Country> getAll(int page, int pageSize);
    Country get(int id);

    Country save(Country country);
    void delete(int id);

}

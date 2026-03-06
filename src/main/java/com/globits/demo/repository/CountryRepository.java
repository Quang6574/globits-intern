package com.globits.demo.repository;

import com.globits.demo.model.Country;

import java.util.List;

public interface CountryRepository {

    Country create(Country country);

    List<Country> getAll(int page, int pageSize);
    Country get(int id);

    Country save(Country country);
    void delete(int id);

    Country  createOrUpdate(Country country);

}

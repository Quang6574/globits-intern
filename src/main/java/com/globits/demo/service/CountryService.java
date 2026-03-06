package com.globits.demo.service;

import com.globits.demo.dto.CountryDTO;

import java.util.List;

public interface CountryService {

    CountryDTO create(CountryDTO country);

    List<CountryDTO> getAll(int page, int pageSize);
    CountryDTO get(int id);

    CountryDTO save(int id, CountryDTO country);
    void delete(int id);

    CountryDTO createOrUpdate(CountryDTO countryDTO);

}

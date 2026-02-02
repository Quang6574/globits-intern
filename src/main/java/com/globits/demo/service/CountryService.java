package com.globits.demo.service;

import com.globits.demo.dto.CountryCreateDTO;

import java.util.List;

public interface CountryService {

    CountryCreateDTO create(CountryCreateDTO country);

    List<CountryCreateDTO> getAll();
    CountryCreateDTO get(int id);

    CountryCreateDTO save(int id, CountryCreateDTO country);
    void delete(int id);

}

package com.globits.demo.service;

import com.globits.demo.dto.CountryCreateDTO;

import java.util.List;

public interface CountryService {

    CountryCreateDTO create(CountryCreateDTO country);

    List<CountryCreateDTO> getAll(int page, int pageSize);
    CountryCreateDTO get(int id);

    CountryCreateDTO save(int id, CountryCreateDTO country);
    void delete(int id);

}

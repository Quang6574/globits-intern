package com.globits.demo.service;

import com.globits.demo.dao.CountryDAO;
import com.globits.demo.model.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImplement implements CountryService {

    @Autowired
    private CountryDAO countryDAO;


    @Transactional
    @Override
    public Country create(Country country) {
        return countryDAO.create(country);
    }

    @Transactional
    @Override
    public List<Country> getAll() {
        return countryDAO.getAll();
    }

    @Transactional
    @Override
    public Country get(int id) {
        return countryDAO.get(id);
    }

    @Transactional
    @Override
    public Country save(Country country) {
        return countryDAO.save(country);
    }

    @Transactional
    @Override
    public void delete(int id) {
        countryDAO.delete(id);
    }
}

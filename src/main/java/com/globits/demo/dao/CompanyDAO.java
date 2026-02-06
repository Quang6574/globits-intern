package com.globits.demo.dao;

import com.globits.demo.model.Company;

import java.util.List;

public interface CompanyDAO {

    Company create(Company company);

    List<Company> getAll();
    Company get(String code);

    Company save(Company company);
    void delete(String code);

}

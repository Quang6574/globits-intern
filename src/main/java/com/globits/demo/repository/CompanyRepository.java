package com.globits.demo.repository;

import com.globits.demo.model.Company;

import java.util.List;

public interface CompanyRepository {

    Company create(Company company);

    List<Company> getAll(int page, int pageSize);
    Company get(String code);

    Company save(Company company);
    void delete(String code);

    Company createOrUpdate(Company company);

}

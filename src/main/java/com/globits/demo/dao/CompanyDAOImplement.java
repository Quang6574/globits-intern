package com.globits.demo.dao;

import com.globits.demo.model.Company;
import jakarta.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import java.util.List;

@Repository
public class CompanyDAOImplement implements CompanyDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Company create(Company company) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(company);
        return company;
    }

    @Override
    public List<Company> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Company> query = currentSession.createQuery("from Company", Company.class);
        //List<Company> list = query.getResultList();
        return query.getResultList();
    }

    @Override
    public Company get(String code) {
        Session currentSession = entityManager.unwrap(Session.class);
        //Company company = currentSession.find(Company.class, id);
        return currentSession.find(Company.class, code);
    }

    @Override
    public Company save(Company company) {
        Session currentSession = entityManager.unwrap(Session.class);
        return (Company) currentSession.merge(company);
    }

    @Override
    public void delete(String code) {
        Session currentSession = entityManager.unwrap(Session.class);
        Company company = currentSession.find(Company.class, code);
        if (company != null){
            currentSession.remove(company);
        } else {
            System.out.println("Company with code " + code + " not found.");
        }
    }
}

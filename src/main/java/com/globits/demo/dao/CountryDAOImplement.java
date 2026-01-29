package com.globits.demo.dao;
import com.globits.demo.model.Country;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDAOImplement implements CountryDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public void create(Country country) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(country);
    }

    @Override
    public List<Country> getAll() {
        return null;
    }

    @Override
    public Country get(int id) {
        return null;
    }

    @Override
    public void save(Country country) {

    }

    @Override
    public void delete(int id) {

    }
}

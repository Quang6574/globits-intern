package com.globits.demo.dao;

import com.globits.demo.model.Country;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDAOImplement implements CountryDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Country create(Country country) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(country);
        return country;
    }

    @Override
    public List<Country> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 1;

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Country> query = currentSession.createQuery("from Country", Country.class);

        int firstResult = (page - 1) * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        //List<Country> list = query.getResultList();
        return query.getResultList();
    }

    @Override
    public Country get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        //Country country = currentSession.find(Country.class, id);
        return currentSession.find(Country.class, id);
    }

    @Override
    public Country save(Country country) {
        Session currentSession = entityManager.unwrap(Session.class);
        return (Country) currentSession.merge(country);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Country country = currentSession.find(Country.class, id);
        if (country != null){
            currentSession.remove(country);
        } else {
            System.out.println("Country with id " + id + " not found.");
        }

    }
}

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
    public List<Country> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Country> query = currentSession.createQuery("from Country", Country.class);

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

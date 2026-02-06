package com.globits.demo.dao;

import com.globits.demo.model.Person;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOImplement implements PersonDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Person create(Person person) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(person);
        return person;
    }

    @Override
    public List<Person> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Person> query = currentSession.createQuery("from Person", Person.class);

        //List<Person> list = query.getResultList();
        return query.getResultList();
    }

    @Override
    public Person get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        //User user = currentSession.find(User.class, id);
        return currentSession.find(Person.class, id);
    }

    @Override
    public Person save(Person person) {
        Session currentSession = entityManager.unwrap(Session.class);
        return (Person) currentSession.merge(person);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Person person = currentSession.find(Person.class, id);
        if (person != null){
            currentSession.remove(person);
        } else {
            System.out.println("Person with id " + id + " not found.");
        }
    }
}

package com.globits.demo.repository;

import com.globits.demo.model.Person;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImplement implements PersonRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Person create(Person person) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(person);
        return person;
    }

    @Override
    public List<Person> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 1;

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Person> query = currentSession.createQuery("from Person", Person.class);

        int firstResult = (page - 1) * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

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

    @Override
    public Person createOrUpdate(Person person) {
        if (person == null) return null;
        if (person.getId() == null) return create(person);
        if (get(person.getId()) == null) return create(person);

        return save(person);
    }


}

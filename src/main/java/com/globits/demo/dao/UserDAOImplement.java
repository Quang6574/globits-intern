package com.globits.demo.dao;

import com.globits.demo.model.User;
import com.globits.demo.model.Person;
import org.hibernate.Session;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImplement implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User create(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User", User.class);

        //List<User> list = query.getResultList();
        return query.getResultList();
    }

    @Override
    public User get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        //User user = currentSession.find(User.class, id);
        return currentSession.find(User.class, id);
    }

    @Override
    public User save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        return (User) currentSession.merge(user);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = currentSession.find(User.class, id);
        if (user != null){
            currentSession.remove(user);
        } else {
            System.out.println("User with id " + id + " not found.");
        }
    }
}

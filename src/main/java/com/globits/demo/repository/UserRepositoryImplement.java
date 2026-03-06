package com.globits.demo.repository;

import com.globits.demo.model.User;
import org.hibernate.Session;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImplement implements UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User create(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(user);
        return user;
    }

    @Override
    public List<User> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 1;

        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User", User.class);

        int firstResult = (page - 1) * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

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

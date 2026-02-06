package com.globits.demo.dao;

import com.globits.demo.model.Role;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImplement implements RoleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role create(Role role) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(role);
        return role;
    }

    @Override
    public List<Role> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Role> query = currentSession.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role get(String role) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.find(Role.class, role);
    }

    @Override
    public Role save(Role role) {
        Session currentSession = entityManager.unwrap(Session.class);
        return (Role) currentSession.merge(role);
    }

    @Override
    public void delete(String role) {
        Session currentSession = entityManager.unwrap(Session.class);
        Role existing = currentSession.find(Role.class, role);
        if (existing != null) {
            currentSession.remove(existing);
        } else {
            System.out.println("Role with name " + role + " not found.");
        }
    }
}

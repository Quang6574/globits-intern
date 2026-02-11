package com.globits.demo.dao;

import com.globits.demo.model.Project;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import java.util.List;

@Repository
public class ProjectDAOImplement implements ProjectDAO {

    @Autowired
    private EntityManager entityManager
            ;
    @Override
    public Project create(Project project) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(project);
        return project;
    }

    @Override
    public Project get(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.find(Project.class, id);
    }

    @Override
    public List<Project> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Project> query = session.createQuery("from Project", Project.class);
        return query.getResultList();
    }

    @Override
    public Project save(Project project) {
        Session session = entityManager.unwrap(Session.class);
        return (Project) session.merge(project);
    }

    @Override
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        Project project = session.find(Project.class, id);
        if (project != null) {
            session.remove(project);
        } else {
            System.out.println("Project with id " + id + " not found.");
        }
    }
}

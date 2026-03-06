package com.globits.demo.repository;

import com.globits.demo.model.Project;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import java.util.List;

@Repository
public class ProjectRepositoryImplement implements ProjectRepository {

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
    public List<Project> getAll(int page, int pageSize) {
        if (page < 1) page = 1;
        if (pageSize < 1) pageSize = 1;

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Project> query = currentSession.createQuery("from Project", Project.class);

        int firstResult = (page - 1) * pageSize;
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        //List<Project> list = query.getResultList();
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

    @Override
    public Project createOrUpdate(Project project) {
        if (project == null) return null;
        if (project.getId() == null) return create(project);
        if (get(project.getId()) == null) return create(project);

        return save(project);
    }

}

package com.globits.demo.dao;


import com.globits.demo.model.Department;
import org.hibernate.Session;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAOImplement implements  DepartmentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Department create(Department department) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(department);
        return department;
    }

    @Override
    public List<Department> getAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query = currentSession.createQuery("from Department", Department.class);
        //List<Department> list = query.getResultList();
        return query.getResultList();
    }

    @Override
    public Department get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        //Department department = currentSession.find(Department.class, id);
        return currentSession.find(Department.class, id);
    }

    @Override
    public Department save(Department department) {
        Session currentSession = entityManager.unwrap(Session.class);
        return (Department) currentSession.merge(department);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Department department = currentSession.find(Department.class, id);
        if (department != null){
            currentSession.remove(department);
        } else {
            System.out.println("Department with id " + id + " not found.");
        }
    }

    public List<Department> getByCompanyCode(String companyCode) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Department> query = currentSession.createQuery("from Department d where d.company.code = :code", Department.class);
        query.setParameter("code", companyCode);
        return query.getResultList();
    }
}

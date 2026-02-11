package com.globits.demo.dao;

import com.globits.demo.model.Project;

import java.util.List;

public interface ProjectDAO {
    Project create(Project project);
    Project get(int id);
    List<Project> getAll();
    Project save(Project project);
    void delete(int id);
}

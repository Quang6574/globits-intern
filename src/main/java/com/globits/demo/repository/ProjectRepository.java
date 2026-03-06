package com.globits.demo.repository;

import com.globits.demo.model.Project;

import java.util.List;

public interface ProjectRepository {
    Project create(Project project);
    Project get(int id);
    List<Project> getAll(int page, int pageSize);
    Project save(Project project);
    void delete(int id);

    Project createOrUpdate(Project project);
}

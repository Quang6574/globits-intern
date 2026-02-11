package com.globits.demo.service;

import com.globits.demo.dto.ProjectCreateDTO;

import java.util.List;

public interface ProjectService {

    ProjectCreateDTO create(ProjectCreateDTO projectCreateDTO);
    List<ProjectCreateDTO> getAll();
    ProjectCreateDTO get(int id);
    ProjectCreateDTO save(int id, ProjectCreateDTO projectCreateDTO);
    void delete(int id);
}

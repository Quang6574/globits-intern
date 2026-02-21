package com.globits.demo.service;

import com.globits.demo.dto.ProjectCompanyDTO;
import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.dto.ProjectPersonDTO;
import com.globits.demo.dto.ProjectViewDTO;

import java.util.List;

public interface ProjectService {

    ProjectCreateDTO create(ProjectCreateDTO projectCreateDTO);
    List<ProjectCreateDTO> getAll();
    ProjectCreateDTO get(int id);
    ProjectCreateDTO save(int id, ProjectCreateDTO projectCreateDTO);
    void delete(int id);

    ProjectCreateDTO editCompany(int id, ProjectCompanyDTO dto);
    ProjectViewDTO addPerson(int projectId, ProjectPersonDTO personDTO);
    ProjectViewDTO removePerson(int projectId, ProjectPersonDTO personDTO);
}

package com.globits.demo.service;

import com.globits.demo.dto.ProjectCompanyDTO;
import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.dto.ProjectPersonDTO;
import com.globits.demo.dto.ProjectViewDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectCreateDTO> getAll(int page, int pageSize);
    ProjectCreateDTO get(int id);
    void delete(int id);

    ProjectCreateDTO editCompany(int id, ProjectCompanyDTO dto);
    ProjectViewDTO addPerson(int projectId, ProjectPersonDTO personDTO);
    ProjectViewDTO removePerson(int projectId, ProjectPersonDTO personDTO);

    ProjectCreateDTO createOrUpdate(ProjectCreateDTO projectCreateDTO);
}

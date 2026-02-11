package com.globits.demo.service;

import com.globits.demo.dao.ProjectDAO;
import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.mapper.ProjectMapper;
import com.globits.demo.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImplement implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private ProjectMapper projectMapper;

    @Transactional
    @Override
    public ProjectCreateDTO create(ProjectCreateDTO projectCreateDTO) {
        Project entity = projectMapper.toEntity(projectCreateDTO);
        Project saved = projectDAO.create(entity);
        return projectMapper.toCreateDTO(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProjectCreateDTO> getAll() {
        List<Project> project = projectDAO.getAll();
        return projectMapper.toCreateDTOList(project);
    }

    @Transactional
    @Override
    public ProjectCreateDTO get(int id) {
        Project project = projectDAO.get(id);
        return projectMapper.toCreateDTO(project);
    }

    @Transactional
    @Override
    public ProjectCreateDTO save(int id, ProjectCreateDTO projectCreateDTO) {
        Project existing = projectDAO.get(id);
        if (existing == null) return null;

        existing.setCode(projectCreateDTO.getCode());
        existing.setName(projectCreateDTO.getName());
        existing.setDescription(projectCreateDTO.getDescription());

        Project saved = projectDAO.save(existing);
        return projectMapper.toCreateDTO(saved);
    }

    @Transactional
    @Override
    public void delete(int id) {
        projectDAO.delete(id);
    }
}

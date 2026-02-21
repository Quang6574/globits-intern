package com.globits.demo.service;

import com.globits.demo.dao.CompanyDAO;
import com.globits.demo.dao.PersonDAO;
import com.globits.demo.dao.ProjectDAO;
import com.globits.demo.dto.ProjectCompanyDTO;
import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.dto.ProjectPersonDTO;
import com.globits.demo.dto.ProjectViewDTO;
import com.globits.demo.mapper.ProjectMapper;
import com.globits.demo.model.Company;
import com.globits.demo.model.Person;
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
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private PersonDAO personDAO;

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

    @Transactional
    @Override
    public ProjectCreateDTO editCompany(int id, ProjectCompanyDTO dto) {
        Project entity = projectDAO.get(id);
        //check if project exists
        if (entity == null) return null;

        //check if company exists
        //if not, set company field to null
        Company company = companyDAO.get(dto.getCompanyCode());
        if (company == null) {
            entity.setCompany(null);
            Project saved = projectDAO.save(entity);
            return projectMapper.toCreateDTO(saved);
        }

        //add company
        entity.setCompany(company);
        Project saved = projectDAO.save(entity);
        return projectMapper.toCreateDTO(saved);
    }

    @Transactional
    @Override
    public ProjectViewDTO addPerson(int projectId, ProjectPersonDTO personDTO) {
        //get project entity
        //check if project exists
        Project entityProject = projectDAO.get(projectId);
        if (entityProject == null) return null;

        //get person entity
        //check if person exists
        Person person = personDAO.get(personDTO.getPersonId());
        if (person == null) return null;

        //get company of project entity and (parameter) person
        Company projectCompany = entityProject.getCompany();
        Company personCompany = person.getCompany();

        //after getting comapny from 2 variables above:
        //
        // check if both companies are not null
        if (projectCompany == null || personCompany == null) return null;
        //check if company codes are not null
        if (projectCompany .getCode() == null || personCompany.getCode() == null)
            return null;

        //check if company codes are matching
        if (!projectCompany.getCode().equals(personCompany.getCode())) return null;

        person.getProjects().add(entityProject);
        entityProject.getPerson().add(person);

        //save the person
        personDAO.save(person);
        //Project saved = projectDAO.save(entityProject);

        return projectMapper.toViewDTO(entityProject);
    }
    @Transactional
    @Override
    public ProjectViewDTO removePerson(int projectId, ProjectPersonDTO personDTO) {
        //get project entity
        Project entityProject = projectDAO.get(projectId);

        //if project not found
        //if personDTO or personId is null
        //=> return null
        if (entityProject == null
                || personDTO == null
                || personDTO.getPersonId() == null)
            return null;

        //get person entity
        //and check if person exists
        Person person = personDAO.get(personDTO.getPersonId());
        if (person == null) return null;

        boolean existInPerson = person.getProjects().contains(entityProject);
        boolean existInProject = entityProject.getPerson().contains(person);
        //check if both person and project have each other
        if (!existInPerson && !existInProject)
            return null; //if both is false, return null

        //delete based on differences:
        // if only exist in person
        if (existInPerson ) person.getProjects().remove(entityProject);
        // if only exist in project
        if (existInProject) entityProject.getPerson().remove(person);

        //save the person
        personDAO.save(person);
        //Project saved = projectDAO.save(entityProject);
        return projectMapper.toViewDTO(entityProject);
    }



}

package com.globits.demo.service.implement;

import com.globits.demo.repository.CompanyRepository;
import com.globits.demo.repository.PersonRepository;
import com.globits.demo.repository.ProjectRepository;
import com.globits.demo.dto.ProjectCompanyDTO;
import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.dto.ProjectPersonDTO;
import com.globits.demo.dto.ProjectViewDTO;
import com.globits.demo.mapper.ProjectMapper;
import com.globits.demo.model.Company;
import com.globits.demo.model.Person;
import com.globits.demo.model.Project;
import com.globits.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImplement implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ProjectCreateDTO> getAll(int page, int pageSize) {
        List<Project> project = projectRepository.getAll(page, pageSize);
        return projectMapper.toCreateDTOList(project);
    }
    @Transactional
    @Override
    public ProjectCreateDTO get(int id) {
        Project project = projectRepository.get(id);
        return projectMapper.toCreateDTO(project);
    }

    @Transactional
    @Override
    public void delete(int id) {
        projectRepository.delete(id);
    }

    @Transactional
    @Override
    public ProjectCreateDTO editCompany(int id, ProjectCompanyDTO dto) {
        Project entity = projectRepository.get(id);
        //check if project exists
        if (entity == null) return null;

        //check if company exists
        //if not, set company field to null
        Company company = companyRepository.get(dto.getCompanyCode());
        if (company == null) {
            entity.setCompany(null);
            Project saved = projectRepository.save(entity);
            return projectMapper.toCreateDTO(saved);
        }

        //add company
        entity.setCompany(company);
        Project saved = projectRepository.save(entity);
        return projectMapper.toCreateDTO(saved);
    }

    @Transactional
    @Override
    public ProjectViewDTO addPerson(int projectId, ProjectPersonDTO personDTO) {
        //get project entity
        //check if project exists
        Project entityProject = projectRepository.get(projectId);
        if (entityProject == null) return null;

        //get person entity
        //check if person exists
        Person person = personRepository.get(personDTO.getPersonId());
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
        personRepository.save(person);
        //Project saved = projectRepository.save(entityProject);

        return projectMapper.toViewDTO(entityProject);
    }
    @Transactional
    @Override
    public ProjectViewDTO removePerson(int projectId, ProjectPersonDTO personDTO) {
        //get project entity
        Project entityProject = projectRepository.get(projectId);

        //if project not found
        //if personDTO or personId is null
        //=> return null
        if (entityProject == null
                || personDTO == null
                || personDTO.getPersonId() == null)
            return null;

        //get person entity
        //and check if person exists
        Person person = personRepository.get(personDTO.getPersonId());
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
        personRepository.save(person);
        //Project saved = projectRepository.save(entityProject);
        return projectMapper.toViewDTO(entityProject);
    }

    @Transactional
    @Override
    public ProjectCreateDTO createOrUpdate(ProjectCreateDTO projectCreateDTO) {
        if (projectCreateDTO == null) return null;
        if (projectCreateDTO.getId() == null) {
            Project entity = projectMapper.toEntity(projectCreateDTO);
            Project saved = projectRepository.create(entity);
            return projectMapper.toCreateDTO(saved);
        }
        if (projectCreateDTO.getId() != null) {
            Project entity = projectRepository.get(projectCreateDTO.getId());
            if (entity == null) return null;

            entity.setCode(projectCreateDTO.getCode());
            entity.setName(projectCreateDTO.getName());
            entity.setDescription(projectCreateDTO.getDescription());

            Project saved = projectRepository.save(entity);
            return projectMapper.toCreateDTO(saved);
        }
        return null;
    }



}

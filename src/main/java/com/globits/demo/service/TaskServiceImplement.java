package com.globits.demo.service;

import com.globits.demo.dao.PersonDAO;
import com.globits.demo.dao.ProjectDAO;
import com.globits.demo.model.Person;
import com.globits.demo.model.Project;

import com.globits.demo.mapper.TaskMapper;
import com.globits.demo.model.Task;
import com.globits.demo.dao.TaskDAO;
import com.globits.demo.dto.TaskCreateDTO;
import com.globits.demo.dto.TaskEditFkDTO;

import java.util.List;
import java.util.ArrayList;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImplement implements TaskService {

    private final TaskDAO taskDAO;
    private final TaskMapper taskMapper;

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private ProjectDAO projectDAO;

    public TaskServiceImplement(TaskDAO taskDAO, TaskMapper taskMapper) {
        this.taskDAO = taskDAO;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskCreateDTO create(TaskCreateDTO dto) {
        Task entity = taskMapper.toEntity(dto);

        Task saved = taskDAO.save(entity);
        return taskMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskCreateDTO> getAll() {
        List<Task> entities = taskDAO.findAll();
        return taskMapper.toDtoList(entities);
    }
    @Override
    @Transactional(readOnly = true)
    public TaskCreateDTO get(Integer id) {
        return taskDAO.findById(id)
                .map(taskMapper::toDto)
                .orElse(null);
    }

    @Override
    public TaskCreateDTO save(Integer id, TaskCreateDTO dto) {
        Task entity = taskDAO.findById(id).orElse(null);
        if (entity == null) return null;

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        entity.setPriority(dto.getPriority());
        entity.setEndDate(dto.getEndDate());
        entity.setStartDate(dto.getStartDate());

        Task saved = taskDAO.save(entity);
        return taskMapper.toDto(saved);
    }

    @Override
    public void delete(Integer id) {if (taskDAO.existsById(id)) taskDAO.deleteById(id);}

    @Override
    @Transactional
    public TaskCreateDTO editPerson(Integer taskId, TaskEditFkDTO personDTO) {
        Task task = taskDAO.findById(taskId).orElse(null);
        if (task == null) return null;

        Integer personId = personDTO.getId();
        if (personId == null) {
            task.setPerson(null);
            Task saved = taskDAO.save(task);
            return taskMapper.toDto(saved);
        }

        Person person = personDAO.get(personId);
        if (person == null) return null;

        task.setPerson(person);
        Task saved = taskDAO.save(task);
        return taskMapper.toDto(saved);
    }
    @Override
    @Transactional
    public TaskCreateDTO editProject(Integer taskId, TaskEditFkDTO projectDTO) {
        Task task = taskDAO.findById(taskId).orElse(null);
        if (task == null) return null;

        Integer projectId = projectDTO.getId();
        if (projectId == null) {
            task.setProject(null);
            Task saved = taskDAO.save(task);
            return taskMapper.toDto(saved);
        }

        Project project = projectDAO.get(projectId);
        if (project == null) return null;

        task.setProject(project);
        Task saved = taskDAO.save(task);
        return taskMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskCreateDTO> search(Integer projectId,
                                      Integer personId,
                                      String companyCode,
                                      Integer status,
                                      Integer priority,
                                      String name) {

        final String normalizedCompanyCode =
                (companyCode != null && companyCode.trim().isEmpty()) ? null : companyCode;

        final String normalizedName =
                (name != null && name.trim().isEmpty()) ? null : name;

        Specification<Task> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (projectId != null) {
                predicates.add(cb.equal(root.get("project").get("id"), projectId));
            }
            if (personId != null) {
                predicates.add(cb.equal(root.get("person").get("id"), personId));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (priority != null) {
                predicates.add(cb.equal(root.get("priority"), priority));
            }

            if (normalizedCompanyCode != null) {
                predicates.add(
                        cb.equal(
                                root.get("project").get("company").get("code"),
                                normalizedCompanyCode
                        )
                );
            }
            if (normalizedName != null) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + normalizedName.toLowerCase() + "%"
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<Task> entities = taskDAO.findAll(spec);
        return taskMapper.toDtoList(entities);
    }

}

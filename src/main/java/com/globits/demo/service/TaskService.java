package com.globits.demo.service;

import com.globits.demo.dto.TaskCreateDTO;
import com.globits.demo.dto.TaskEditFkDTO;

import java.util.List;

public interface TaskService {

    TaskCreateDTO create(TaskCreateDTO taskCreateDTO);
    List<TaskCreateDTO> getAll();
    TaskCreateDTO get(Integer id);
    TaskCreateDTO save(Integer id, TaskCreateDTO taskCreateDTO);
    void delete(Integer id);

    TaskCreateDTO editPerson(Integer id, TaskEditFkDTO dto);
    TaskCreateDTO editProject(Integer id, TaskEditFkDTO dto);

    List<TaskCreateDTO> search(Integer projectId,
                               Integer personId,
                               String companyCode,
                               Integer status,
                               Integer priority,
                               String name);
}

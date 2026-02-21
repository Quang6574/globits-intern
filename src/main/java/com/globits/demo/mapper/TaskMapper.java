package com.globits.demo.mapper;

import com.globits.demo.dto.TaskCreateDTO;
import com.globits.demo.dto.TaskExcelDTO;
import com.globits.demo.model.Task;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskCreateDTO toDto(Task entity);
    List<TaskCreateDTO> toDtoList(List<Task> entities);

    Task toEntity(TaskCreateDTO dto);

    List<TaskExcelDTO> toExcelDtoList(List<Task> entities);

}

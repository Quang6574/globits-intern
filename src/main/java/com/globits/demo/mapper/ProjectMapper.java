package com.globits.demo.mapper;

import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.model.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectCreateDTO toCreateDTO(Project project);
    List<ProjectCreateDTO> toCreateDTOList(List<Project> projects);

    Project toEntity(ProjectCreateDTO dto);
}

package com.globits.demo.mapper;

import com.globits.demo.dto.DepartmentCreateDTO;
import com.globits.demo.model.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentCreateDTO toDto(Department entity);

    Department toEntity(DepartmentCreateDTO dto);

    List<DepartmentCreateDTO> toDtoList(List<Department> entities);
}

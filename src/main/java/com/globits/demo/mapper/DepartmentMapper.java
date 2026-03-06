package com.globits.demo.mapper;

import com.globits.demo.dto.DepartmentDTO;
import com.globits.demo.model.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDTO toDto(Department entity);

    Department toEntity(DepartmentDTO dto);

    List<DepartmentDTO> toDtoList(List<Department> entities);
}

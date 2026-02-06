package com.globits.demo.mapper;

import com.globits.demo.dto.CompanyDTO;
import com.globits.demo.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { PersonMapper.class }
)
public interface CompanyMapper {

    @Mapping(source = "person", target = "persons")
    CompanyDTO toDto(Company user);

    List<CompanyDTO> toDtoList(List<Company> users);


    @Mapping(source = "persons", target = "person")
    Company toEntity(CompanyDTO dto);
}

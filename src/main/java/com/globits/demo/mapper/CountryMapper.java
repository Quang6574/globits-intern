package com.globits.demo.mapper;

import com.globits.demo.dto.CountryCreateDTO;
import com.globits.demo.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryCreateDTO toDto(Country entity);

    Country toEntity(CountryCreateDTO dto);

    List<CountryCreateDTO> toDtoList(List<Country> entities);
}

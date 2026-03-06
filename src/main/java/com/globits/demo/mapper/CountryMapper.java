package com.globits.demo.mapper;

import com.globits.demo.dto.CountryDTO;
import com.globits.demo.model.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO toDto(Country entity);

    Country toEntity(CountryDTO dto);

    List<CountryDTO> toDtoList(List<Country> entities);
}

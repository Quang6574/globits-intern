package com.globits.demo.mapper;

import com.globits.demo.dto.PersonCreateDTO;
import com.globits.demo.dto.PersonViewDTO;
import com.globits.demo.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source = "user.email", target = "email")
    PersonViewDTO toViewDto(Person person);

    List<PersonViewDTO> toViewDtoList(List<Person> persons);


    @Mapping(target = "id", ignore = true)
    Person toCreateDTO(PersonCreateDTO dto);
}

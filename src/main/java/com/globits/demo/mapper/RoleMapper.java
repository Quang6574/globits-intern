package com.globits.demo.mapper;

import com.globits.demo.dto.PersonViewDTO;
import com.globits.demo.dto.RoleCreateDTO;
import com.globits.demo.dto.RoleViewDTO;
import com.globits.demo.model.Person;
import com.globits.demo.model.Role;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    // CREATE DTO
    //to dto
    RoleCreateDTO toCreateDto(Role entity);
    //to entity
    Role toEntity(RoleCreateDTO dto);
    // to dto list
    List<RoleCreateDTO> toCreateDtoList(List<Role> entities);

    // VIEW DTO
    // to dto
    RoleViewDTO toViewDto(Role entity);
    // to dto list
    List<RoleViewDTO> toViewDtoList(List<Role> entities);

    // helper: map Person -> PersonViewDTO
    default PersonViewDTO personToDto(Person person) {
        //if there is no person found in role
        if (person == null) return null;

        //if there is at least 1 person found in role
        PersonViewDTO dto = new PersonViewDTO();
        //
        dto.setFullName(person.getFullName());
        dto.setPhoneNum(person.getPhoneNum());
        return dto;
    }

    //fill person view dto list to role view dto
    @AfterMapping
    default void fillPersons(Role roleEntity, @MappingTarget RoleViewDTO roleDTO) {
        //if role is null or role dto is null
        if (roleEntity == null || roleDTO == null) return;

        //get person set from role entity
        Set<Person> persons = roleEntity.getPersons();
        //if set is empty/null
        if (persons == null || persons.isEmpty()) {
            //return empty list
            roleDTO.setPersons(new ArrayList<>());
            return;
        }

        //if set is not empty/null
        List<PersonViewDTO> personDTO = persons.stream()
                .map(this::personToDto)
                .collect(Collectors.toList());

        roleDTO.setPersons(personDTO);
    }
}

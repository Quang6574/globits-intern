package com.globits.demo.mapper;

import com.globits.demo.dto.PersonViewDTO;
import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.dto.ProjectViewDTO;
import com.globits.demo.dto.RoleViewDTO;
import com.globits.demo.model.Person;
import com.globits.demo.model.Project;
import com.globits.demo.model.Role;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    //CREATE  DTO
    ProjectCreateDTO toCreateDTO(Project project);
    List<ProjectCreateDTO> toCreateDTOList(List<Project> projects);

    Project toEntity(ProjectCreateDTO dto);
    //VIEW DTO (with person)
    ProjectViewDTO toViewDTO(Project entity);


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
    default void fillPersons(Role roleEntity,
                             @MappingTarget ProjectViewDTO projectViewDTO) {
        //if role is null or role dto is null
        if (roleEntity == null || projectViewDTO == null) return;

        //get person set from role entity
        Set<Person> persons = roleEntity.getPersons();
        //if set is empty/null
        if (persons == null || persons.isEmpty()) {
            //return empty list
            projectViewDTO.setPersons(new ArrayList<>());
            return;
        }

        //if set is not empty/null
        List<PersonViewDTO> personDTO = persons.stream()
                .map(this::personToDto)
                .collect(Collectors.toList());

        projectViewDTO.setPersons(personDTO);
    }


}

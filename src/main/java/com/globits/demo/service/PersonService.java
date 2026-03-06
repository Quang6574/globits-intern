package com.globits.demo.service;

import com.globits.demo.dto.*;

import java.util.List;

public interface PersonService {
    PersonViewDTO create(PersonDTO person);

    List<PersonViewDTO> getAll(int page, int pageSize);
    PersonViewDTO get(int id);

    PersonViewDTO save(int id, PersonDTO person);
    PersonViewDTO addUser(int personId, PersonAddUserDTO dto);
    PersonViewDTO removeUser(int personId, PersonDTO dto);

    PersonViewDTO editCompany(int personId, PersonEditCompanyDTO dto);
    PersonViewDTO addRole(int personId, PersonRoleDTO dto) ;
    PersonViewDTO removeRole(int personId, PersonRoleDTO dto);

    void delete(int id);

    PersonDTO createOrUpdate(PersonDTO personDTO);


}

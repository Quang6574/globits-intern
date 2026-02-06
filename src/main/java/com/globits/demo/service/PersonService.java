package com.globits.demo.service;

import com.globits.demo.dto.*;

import java.util.List;

public interface PersonService {
    PersonViewDTO create(PersonCreateDTO person);

    List<PersonViewDTO> getAll();
    PersonViewDTO get(int id);

    PersonViewDTO save(int id, PersonCreateDTO person);
    PersonViewDTO addUser(int personId, PersonAddUserDTO dto);
    PersonViewDTO removeUser(int personId, PersonCreateDTO dto);

    PersonViewDTO editCompany(int personId, PersonEditCompanyDTO dto);
    public PersonViewDTO addRole(int personId, PersonRoleDTO dto) ;
    public PersonViewDTO removeRole(int personId, PersonRoleDTO dto);


    void delete(int id);


}

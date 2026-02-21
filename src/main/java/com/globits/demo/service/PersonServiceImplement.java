package com.globits.demo.service;

import com.globits.demo.dao.CompanyDAO;
import com.globits.demo.dao.PersonDAO;
import com.globits.demo.dao.RoleDAO;
import com.globits.demo.dto.*;
import com.globits.demo.dao.UserDAO;

import com.globits.demo.mapper.PersonMapper;
import com.globits.demo.model.Company;
import com.globits.demo.model.Person;
import com.globits.demo.model.Role;
import com.globits.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImplement implements PersonService {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private RoleDAO roleDAO; // add this

    @Override
    @Transactional
    public PersonViewDTO create(PersonCreateDTO dto) {
        Person entity = personMapper.toCreateDTO(dto);
        Person saved = personDAO.create(entity);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonViewDTO> getAll() {
        List<Person> list = personDAO.getAll();
        return personMapper.toViewDtoList(list);
    }
    @Override
    @Transactional(readOnly = true)
    public PersonViewDTO get(int id) {
        Person person = personDAO.get(id);
        return personMapper.toViewDto(person);
    }

    @Override
    @Transactional
    public PersonViewDTO save(int id, PersonCreateDTO dto) {
        Person existing = personDAO.get(id);
        if (existing == null) return null;

        // update fields from DTO
        existing.setFullName(dto.getFullName());
        existing.setPhoneNum(dto.getPhoneNum());
        existing.setGender(dto.getGender());
        existing.setAddress(dto.getAddress());
        existing.setDob(dto.getDob());

        Person saved = personDAO.save(existing);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional
    public PersonViewDTO addUser(int personId, PersonAddUserDTO dto) {
        Person person = personDAO.get(personId);
        if (person == null) return null;


        if (dto.getUserId() == null) return null;


        User user = userDAO.get(dto.getUserId());
        if (user == null) return null;
        System.out.println("Adding user: " + user.getId());


        person.setUser(user);
        Person saved = personDAO.save(person);
        return personMapper.toViewDto(saved);
    }
    @Override
    @Transactional
    public PersonViewDTO removeUser(int personId, PersonCreateDTO dto) {
        Person person = personDAO.get(personId);
        person.setUser(null);

        Person saved = personDAO.save(person);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional
    public PersonViewDTO editCompany(int personId, PersonEditCompanyDTO dto) {
        //check if person id is valid
        Person person = personDAO.get(personId);
        if (person == null) return null;

        String companyCode = dto.getCompanyCode();

        //if companyCode is empty/null
        if (companyCode == null || companyCode.isBlank()) {
            person.setCompany(null);
            //Person saved = personDAO.save(person);
            return personMapper.toViewDto(personDAO.save(person));
        }

        //find the company with the provided code
        Company company = companyDAO.get(companyCode);
        if (company == null) return null;

        //if found the company, set it to person
        person.setCompany(company);
        Person saved = personDAO.save(person);
        return personMapper.toViewDto(saved);
    }

    //add role to person
    @Override
    @Transactional
    public PersonViewDTO addRole(int personId, PersonRoleDTO dto) {
        //get person entity by id
        Person person = personDAO.get(personId);
        //check if person exists
        if (person == null) return null;
        //and if role json is blank/null
        if (dto.getRole() == null || dto.getRole().isBlank()) return null;

        //get role entity by name
        Role role = roleDAO.get(dto.getRole());
        //check if role exists
        if (role == null) return null;

        //update the join table of role + person
        person.getRoles().add(role);
        Person saved = personDAO.save(person);
        return personMapper.toViewDto(saved);
    }
    // delete role
    @Override
    @Transactional
    public PersonViewDTO removeRole(int personId, PersonRoleDTO dto) {
        Person person = personDAO.get(personId);
        if (person == null) return null;

        if (dto.getRole() == null || dto.getRole().isBlank()) return null;

        Role role = roleDAO.get(dto.getRole());
        if (role == null) return null;

        person.getRoles().remove(role);
        Person saved = personDAO.save(person);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional
    public void delete(int id) {
        personDAO.delete(id);
    }

    @Override
    @Transactional
    public PersonRoleDTO addAvatar(int personId, PersonRoleDTO avatarDTO) {

        Person person = personDAO.get(personId);
        if (person == null) return null;

        if(person.getAvatar() != null) return null;

        String path = avatarDTO.getRole();
        if (path == null || path.isBlank()) return null;

        boolean isImage = path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png");
        if (!isImage) return null;

        person.setAvatar(path);
        personDAO.save(person);

        return avatarDTO;
    }

    @Override
    @Transactional
    public PersonViewDTO removeAvatar(int personId) {
        Person person = personDAO.get(personId);
        if (person == null) return null;

        person.setAvatar(null);
        Person saved = personDAO.save(person);
        return personMapper.toViewDto(saved);


    }

}

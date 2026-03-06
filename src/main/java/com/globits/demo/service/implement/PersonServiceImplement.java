package com.globits.demo.service.implement;

import com.globits.demo.repository.CompanyRepository;
import com.globits.demo.repository.PersonRepository;
import com.globits.demo.repository.RoleRepository;
import com.globits.demo.dto.*;
import com.globits.demo.repository.UserRepository;

import com.globits.demo.mapper.PersonMapper;
import com.globits.demo.model.Company;
import com.globits.demo.model.Person;
import com.globits.demo.model.Role;
import com.globits.demo.model.User;
import com.globits.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImplement implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RoleRepository roleRepository; // add this

    @Override
    @Transactional
    public PersonViewDTO create(PersonDTO dto) {
        Person entity = personMapper.toCreateDTO(dto);
        Person saved = personRepository.create(entity);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonViewDTO> getAll(int page, int pageSize) {
        List<Person> list = personRepository.getAll(page, pageSize);
        return personMapper.toViewDtoList(list);
    }
    @Override
    @Transactional(readOnly = true)
    public PersonViewDTO get(int id) {
        Person person = personRepository.get(id);
        return personMapper.toViewDto(person);
    }

    @Override
    @Transactional
    public PersonViewDTO save(int id, PersonDTO dto) {
        Person existing = personRepository.get(id);
        if (existing == null) return null;

        // update fields from DTO
        existing.setFullName(dto.getFullName());
        existing.setPhoneNum(dto.getPhoneNum());
        existing.setGender(dto.getGender());
        existing.setAddress(dto.getAddress());
        existing.setDob(dto.getDob());

        Person saved = personRepository.save(existing);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional
    public PersonViewDTO addUser(int personId, PersonAddUserDTO dto) {
        Person person = personRepository.get(personId);
        if (person == null) return null;


        if (dto.getUserId() == null) return null;


        User user = userRepository.get(dto.getUserId());
        if (user == null) return null;
        System.out.println("Adding user: " + user.getId());


        person.setUser(user);
        Person saved = personRepository.save(person);
        return personMapper.toViewDto(saved);
    }
    @Override
    @Transactional
    public PersonViewDTO removeUser(int personId, PersonDTO dto) {
        Person person = personRepository.get(personId);
        person.setUser(null);

        Person saved = personRepository.save(person);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional
    public PersonViewDTO editCompany(int personId, PersonEditCompanyDTO dto) {
        //check if person id is valid
        Person person = personRepository.get(personId);
        if (person == null) return null;

        String companyCode = dto.getCompanyCode();

        //if companyCode is empty/null
        if (companyCode == null || companyCode.isBlank()) {
            person.setCompany(null);
            //Person saved = personRepository.save(person);
            return personMapper.toViewDto(personRepository.save(person));
        }

        //find the company with the provided code
        Company company = companyRepository.get(companyCode);
        if (company == null) return null;

        //if found the company, set it to person
        person.setCompany(company);
        Person saved = personRepository.save(person);
        return personMapper.toViewDto(saved);
    }

    //add role to person
    @Override
    @Transactional
    public PersonViewDTO addRole(int personId, PersonRoleDTO dto) {
        //get person entity by id
        Person person = personRepository.get(personId);
        //check if person exists
        if (person == null) return null;
        //and if role json is blank/null
        if (dto.getRole() == null || dto.getRole().isBlank()) return null;

        //get role entity by name
        Role role = roleRepository.get(dto.getRole());
        //check if role exists
        if (role == null) return null;

        //update the join table of role + person
        person.getRoles().add(role);
        Person saved = personRepository.save(person);
        return personMapper.toViewDto(saved);
    }
    // delete role
    @Override
    @Transactional
    public PersonViewDTO removeRole(int personId, PersonRoleDTO dto) {
        Person person = personRepository.get(personId);
        if (person == null) return null;

        if (dto.getRole() == null || dto.getRole().isBlank()) return null;

        Role role = roleRepository.get(dto.getRole());
        if (role == null) return null;

        person.getRoles().remove(role);
        Person saved = personRepository.save(person);
        return personMapper.toViewDto(saved);
    }

    @Override
    @Transactional
    public void delete(int id) {
        personRepository.delete(id);
    }

    @Override
    @Transactional
    public PersonDTO createOrUpdate(PersonDTO personDTO) {
        if (personDTO == null) return null;
        if (personDTO.getId() == null) {
            Person entity = personMapper.toCreateDTO(personDTO);
            Person saved = personRepository.create(entity);
            return personMapper.toEntityDTO(saved);
        }
        if (personDTO.getId() != null) {
            Person entity = personRepository.get(personDTO.getId());
            if (entity == null) return null;

            entity.setFullName(personDTO.getFullName());
            entity.setPhoneNum(personDTO.getPhoneNum());
            entity.setGender(personDTO.getGender());
            entity.setAddress(personDTO.getAddress());
            entity.setDob(personDTO.getDob());

            Person updated = personRepository.save(entity);
            return personMapper.toEntityDTO(updated);
        }
        return null;
    }

}

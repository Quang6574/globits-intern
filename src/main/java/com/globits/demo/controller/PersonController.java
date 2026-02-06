package com.globits.demo.controller;

import com.globits.demo.dto.*;
import com.globits.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonViewDTO> create(@RequestBody PersonCreateDTO createDTO) {
        PersonViewDTO person = personService.create(createDTO);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonViewDTO>> getAll() {
        return ResponseEntity.ok(personService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonViewDTO> get(@PathVariable int id) {
        PersonViewDTO person = personService.get(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }

    //update user info
    @PutMapping("/{id}")
    public ResponseEntity<PersonViewDTO> update(@PathVariable int id,
                                                @RequestBody PersonCreateDTO createDTO) {

        PersonViewDTO updated = personService.save(id, createDTO);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }
    //add user to person
    @PutMapping("/{id}/addUser")
    public ResponseEntity<PersonViewDTO> addUser(@PathVariable int id,
                                                @RequestBody PersonAddUserDTO dto) {

        PersonViewDTO updated = personService.addUser(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }
    //remove user from person
    @PutMapping("/{id}/removeUser")
    public ResponseEntity<PersonViewDTO> removeUser(@PathVariable int id,
                                                 @RequestBody PersonCreateDTO dto) {

        PersonViewDTO updated = personService.removeUser(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }

    //add user to person
    @PutMapping("/{id}/editCompany")
    public ResponseEntity<PersonViewDTO> editCompany(@PathVariable int id,
                                                 @RequestBody PersonEditCompanyDTO dto) {

        PersonViewDTO updated = personService.editCompany(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }

    //add role
    @PutMapping("/{id}/addRole")
    public ResponseEntity<PersonViewDTO> addRole(@PathVariable int id,
                                                 @RequestBody PersonRoleDTO dto) {
        PersonViewDTO updated = personService.addRole(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // remove role
    @PutMapping("/{id}/removeRole")
    public ResponseEntity<PersonViewDTO> removeRole(@PathVariable int id,
                                                    @RequestBody PersonRoleDTO dto) {
        PersonViewDTO updated = personService.removeRole(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        PersonViewDTO existing = personService.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

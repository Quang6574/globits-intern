package com.globits.demo.controller;

import com.globits.demo.dto.*;
import com.globits.demo.service.PersonService;
import com.globits.demo.service.implement.PersonAvatar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonAvatar personAvatar;

    @PostMapping
    public ResponseEntity<PersonViewDTO> create(@RequestBody PersonDTO createDTO) {
        PersonViewDTO person = personService.create(createDTO);
        return ResponseEntity.ok(person);
    }

    @GetMapping
    public ResponseEntity<List<PersonViewDTO>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(personService.getAll(page, pageSize));
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
                                                @RequestBody PersonDTO createDTO) {

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
                                                 @RequestBody PersonDTO dto) {

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

    @PutMapping("/{id}/avatar")
    public ResponseEntity<PersonViewDTO> personAvatar(@PathVariable("id") int personId,
                                                   @RequestParam("file") MultipartFile img) {
        PersonViewDTO updated = personAvatar.saveAvatar(personId, img);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        PersonViewDTO existing = personService.get(id);
        if (existing == null) return ResponseEntity.notFound().build();

        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<PersonDTO> createOrUpdate(@RequestBody PersonDTO personDTO) {
        PersonDTO saved = personService.createOrUpdate(personDTO);
        if (saved == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saved);
    }
}

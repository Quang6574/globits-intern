package com.globits.demo.controller;

import java.util.List;

import com.globits.demo.dto.UserCreateDTO;
import com.globits.demo.dto.UserViewDTO;
import com.globits.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserViewDTO> create(@RequestBody UserCreateDTO createDTO) {
        UserViewDTO user = userService.create(createDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserViewDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserViewDTO> get(@PathVariable int id) {
        UserViewDTO user = userService.get(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserViewDTO> update(@PathVariable int id,
                                              @RequestBody UserCreateDTO createDTO) {

        UserViewDTO updated = userService.save(id, createDTO);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        UserViewDTO existing = userService.get(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

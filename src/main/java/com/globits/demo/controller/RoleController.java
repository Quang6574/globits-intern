package com.globits.demo.controller;

import com.globits.demo.dto.RoleCreateDTO;
import com.globits.demo.dto.RoleViewDTO;
import com.globits.demo.service.RoleService;
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
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    // CREATE
    @PostMapping
    public ResponseEntity<RoleCreateDTO> create(@RequestBody RoleCreateDTO dto) {
        RoleCreateDTO created = roleService.create(dto);
        return ResponseEntity.ok(created);
    }

    // READ ALL (without persons)
    @GetMapping
    public ResponseEntity<List<RoleCreateDTO>> getAll() {
        List<RoleCreateDTO> roles = roleService.getAll();
        return ResponseEntity.ok(roles);
    }

    // READ ONE (with persons)
    @GetMapping("/{role}")
    public ResponseEntity<RoleViewDTO> get(@PathVariable("role") String role) {
        RoleViewDTO dto = roleService.get(role);
        if (dto == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(dto);
    }

    // UPDATE (by primary key `role`)
    @PutMapping("/{role}")
    public ResponseEntity<RoleCreateDTO> update(
            @PathVariable("role") String role,
            @RequestBody RoleCreateDTO dto) {

        RoleCreateDTO updated = roleService.save(role, dto);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{role}")
    public ResponseEntity<Void> delete(@PathVariable("role") String role) {
        roleService.delete(role);
        return ResponseEntity.noContent().build();
    }
}

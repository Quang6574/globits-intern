package com.globits.demo.controller;

import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectCreateDTO> create(@RequestBody ProjectCreateDTO projectCreateDTO) {
        ProjectCreateDTO created = projectService.create(projectCreateDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<ProjectCreateDTO>> getAll() {
        //List<ProjectCreateDTO> projects = projectService.getAll();
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectCreateDTO> get(@PathVariable int id) {
        ProjectCreateDTO project = projectService.get(id);
        if (project == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(project);
    }
}

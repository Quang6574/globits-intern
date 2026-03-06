package com.globits.demo.controller;

import com.globits.demo.dto.ProjectCompanyDTO;
import com.globits.demo.dto.ProjectCreateDTO;
import com.globits.demo.dto.ProjectPersonDTO;
import com.globits.demo.dto.ProjectViewDTO;
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

    @GetMapping
    public ResponseEntity<List<ProjectCreateDTO>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1", name = "pageSize") int pageSize) {
        //List<ProjectCreateDTO> projects = projectService.getAll();
        return ResponseEntity.ok(projectService.getAll(page, pageSize));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProjectCreateDTO> get(@PathVariable int id) {
        ProjectCreateDTO project = projectService.get(id);
        if (project == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/editCompany")
    public ResponseEntity<ProjectCreateDTO> editCompany(@PathVariable int id,
                                                        @RequestBody ProjectCompanyDTO dto) {
        ProjectCreateDTO updated = projectService.editCompany(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/addPerson")
    public ResponseEntity<ProjectViewDTO> addPerson(@PathVariable int id,
                                                    @RequestBody ProjectPersonDTO dto) {
        ProjectViewDTO updated = projectService.addPerson(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/removePerson")
    public ResponseEntity<ProjectViewDTO> removePerson(@PathVariable int id,
                                                       @RequestBody ProjectPersonDTO dto) {
        ProjectViewDTO updated = projectService.removePerson(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping
    public ResponseEntity<ProjectCreateDTO> createOrUpdate(@RequestBody ProjectCreateDTO dto) {
        ProjectCreateDTO saved = projectService.createOrUpdate(dto);
        if (saved == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saved);
    }

}

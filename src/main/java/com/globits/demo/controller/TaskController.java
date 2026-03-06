package com.globits.demo.controller;

import com.globits.demo.dto.TaskCreateDTO;

import com.globits.demo.dto.TaskEditFkDTO;
import com.globits.demo.service.implement.TaskExport;
import com.globits.demo.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskExport taskExport;

    // CREATE
    @PostMapping
    public ResponseEntity<TaskCreateDTO> create(@RequestBody TaskCreateDTO dto) {
        TaskCreateDTO created = taskService.create(dto);
        return ResponseEntity.ok(created);
    }

    // READ ALL (without persons)
    @GetMapping
    public ResponseEntity<List<TaskCreateDTO>> getAll() {
        List<TaskCreateDTO> roles = taskService.getAll();
        return ResponseEntity.ok(roles);
    }

    // READ ONE (with persons)
    @GetMapping("/{id}")
    public ResponseEntity<TaskCreateDTO> get(@PathVariable("id") Integer id) {
        TaskCreateDTO dto = taskService.get(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    // UPDATE (by primary key `role`)
    @PutMapping("/{id}")
    public ResponseEntity<TaskCreateDTO> update(
            @PathVariable("id") Integer id,
            @RequestBody TaskCreateDTO dto) {

        TaskCreateDTO updated = taskService.save(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/editProject")
    public ResponseEntity<TaskCreateDTO> editProject(
            @PathVariable("id") Integer id,
            @RequestBody TaskEditFkDTO dto) {
        TaskCreateDTO updated = taskService.editProject(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/editPerson")
    public ResponseEntity<TaskCreateDTO> editPerson(
            @PathVariable("id") Integer id,
            @RequestBody TaskEditFkDTO dto) {
        TaskCreateDTO updated = taskService.editPerson(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<TaskCreateDTO>> search(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "projectId", required = false) Integer projectId,
            @RequestParam(name = "personId", required = false) Integer personId,
            @RequestParam(name = "companyCode", required = false) String companyCode,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "priority", required = false) Integer priority,
            @RequestParam(name = "name", required = false) String name) {

        Page<TaskCreateDTO> result =
                taskService.search(page - 1, pageSize, projectId, personId, companyCode, status, priority, name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/export")
    public ResponseEntity<Void> export(HttpServletResponse response) throws IOException {
        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"globitsProject.xlsx\"");

        // write directly to response output stream
        taskExport.exportToExcel(response.getOutputStream());
        response.flushBuffer();
        return ResponseEntity.ok().build();
    }

}

package com.globits.demo.controller;

import com.globits.demo.dto.DepartmentCompanyDTO;
import com.globits.demo.dto.DepartmentDTO;
import com.globits.demo.dto.DepartmentParentDTO;
import com.globits.demo.service.DepartmentService;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1", name = "pageSize") int pageSize) {
        return  ResponseEntity.ok(departmentService.getAll(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> get(@PathVariable Integer id) {
        DepartmentDTO department = departmentService.get(id);
        //nếu ko tìm thấy department vx ID đã cho, trả về not found
        //nếu tìm thấy, trả về department dưới dạng dto
        if (department == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        //lấy thông tin department bằng id
        // dưới dạng dto
        DepartmentDTO existing = departmentService.get(id);
        //nếu trả về null => ko có department vx ID đã cho => trả về not found
        if (existing == null) return ResponseEntity.notFound().build();
        //nếu tìm thấy, gọi service để xóa
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/editCompany")
    public ResponseEntity<DepartmentDTO> editCompany(@PathVariable Integer id,
                                                     @RequestBody DepartmentCompanyDTO dto) {
        DepartmentDTO updated = departmentService.editCompany(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/editParent")
    public ResponseEntity<DepartmentDTO> editParent(@PathVariable Integer id,
                                                    @RequestBody DepartmentParentDTO dto) {
        DepartmentDTO updated = departmentService.editParent(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping
    public ResponseEntity<DepartmentDTO> createOrUpdate(@RequestBody DepartmentDTO dto) {
        DepartmentDTO saved = departmentService.createOrUpdate(dto);
        if (saved == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saved);
    }

}

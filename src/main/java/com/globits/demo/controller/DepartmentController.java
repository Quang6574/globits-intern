package com.globits.demo.controller;

import com.globits.demo.dto.DepartmentCompanyDTO;
import com.globits.demo.dto.DepartmentCreateDTO;
import com.globits.demo.dto.DepartmentParentDTO;
import com.globits.demo.service.DepartmentService;

import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentCreateDTO>  create(@RequestBody DepartmentCreateDTO dto) {
        return ResponseEntity.ok(departmentService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentCreateDTO>> getAll() {
        return  ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentCreateDTO> get(@PathVariable Integer id) {
        DepartmentCreateDTO department = departmentService.get(id);
        //nếu ko tìm thấy department vx ID đã cho, trả về not found
        //nếu tìm thấy, trả về department dưới dạng dto
        if (department == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentCreateDTO> update(@PathVariable Integer id,
                                                      @RequestBody DepartmentCreateDTO dto) {
        //tạo biến mới, gọi service trùng tên, pass id và dto từ client
        //service pass thông tin cho dao, rồi trả ngược kết quả
        DepartmentCreateDTO updated = departmentService.save(id, dto);
        //nếu ko tìm thấy department vx ID đã cho, trả về not found
        if (updated == null) return ResponseEntity.notFound().build();
        //nếu tìm thấy, trả về dto thông tin đã cập nhật
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        //lấy thông tin department bằng id
        // dưới dạng dto
        DepartmentCreateDTO existing = departmentService.get(id);
        //nếu trả về null => ko có department vx ID đã cho => trả về not found
        if (existing == null) return ResponseEntity.notFound().build();
        //nếu tìm thấy, gọi service để xóa
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/editCompany")
    public ResponseEntity<DepartmentCreateDTO> editCompany(@PathVariable Integer id,
                                                           @RequestBody DepartmentCompanyDTO dto) {
        DepartmentCreateDTO updated = departmentService.editCompany(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/editParent")
    public ResponseEntity<DepartmentCreateDTO> editParent(@PathVariable Integer id,
                                                          @RequestBody DepartmentParentDTO dto) {
        DepartmentCreateDTO updated = departmentService.editParent(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

}

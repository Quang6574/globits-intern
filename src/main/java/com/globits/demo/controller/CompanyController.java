package com.globits.demo.controller;

import java.util.List;

import com.globits.demo.dto.DepartmentDTO;
import com.globits.demo.service.CompanyService;

import com.globits.demo.dto.CompanyDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //READ ALL
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(companyService.getAll(page, pageSize));
    }

    //GET by id
    @GetMapping("/{code}")
    public ResponseEntity<CompanyDTO> get(@PathVariable String code) {
        CompanyDTO company = companyService.get(code);
        if (company == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(company);
    }

    //DELETE
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        CompanyDTO deleted = companyService.get(code);
        if (deleted == null) return ResponseEntity.notFound().build();
        companyService.delete(code);
        return ResponseEntity.noContent().build();
    }

    //GET DEPARTMENTS BY COMPANY CODE
    @GetMapping("/{code}/departments")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments(@PathVariable String code) {
        //check if the company with the given code exists
        if (companyService.get(code) == null) return ResponseEntity.notFound().build();

        List<DepartmentDTO> departments = companyService.getAllDepartment(code);
        return ResponseEntity.ok(departments);
    }

    @PutMapping
    public ResponseEntity<CompanyDTO> createOrUpdate(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO saved = companyService.createOrUpdate(companyDTO);
        if (saved == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saved);
    }

}

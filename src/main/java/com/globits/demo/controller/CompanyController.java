package com.globits.demo.controller;

import java.util.List;

import com.globits.demo.service.CompanyService;

import com.globits.demo.dto.CompanyDTO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //CREATE
    @PostMapping
    public ResponseEntity<CompanyDTO> create(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.ok(companyService.create(companyDTO));
    }

    //READ ALL
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    //GET by id
    @GetMapping("/{code}")
    public ResponseEntity<CompanyDTO> get(@PathVariable String code) {
        CompanyDTO company = companyService.get(code);
        if (company == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(company);
    }

    //UPDATE
    @PutMapping("/{code}")
    public ResponseEntity<CompanyDTO> update(@PathVariable String code,
                                          @RequestBody CompanyDTO companyDTO) {
        CompanyDTO updated = companyService.save(code, companyDTO);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    //DELETE
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        CompanyDTO deleted = companyService.get(code);
        if (deleted == null) return ResponseEntity.notFound().build();
        companyService.delete(code);
        return ResponseEntity.noContent().build();
    }
}

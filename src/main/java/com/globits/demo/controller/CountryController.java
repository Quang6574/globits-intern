package com.globits.demo.controller;

import java.util.List;

import com.globits.demo.dto.CountryDTO;
import com.globits.demo.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    //READ ALL
    //gọi service trùng tên
    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAll
            (@RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "1", name = "pageSize") int pageSize) {
        return ResponseEntity.ok(countryService.getAll(page, pageSize));
    }

    //READ by id
    //gọi service trùng tên, pass id từ client.
    //id pass từ controller->service->repository; rồi dữ liệu được gửi ngược lại
    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> get(@PathVariable int id) {
        CountryDTO country = countryService.get(id);
        //nếu ko tìm thấy country vx ID đã cho, trả về not found
        //nếu tìm thấy, trả về country dưới dạng dto
        if (country == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(country);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        //lấy thông tin country bằng id
        // dưới dạng dto
        CountryDTO existing = countryService.get(id);
        //nếu trả về null => ko có country vx ID đã cho => trả về not found
        if (existing == null) return ResponseEntity.notFound().build();

        //nếu tìm thấy, gọi service để xóa
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<CountryDTO> createOrUpdate(@RequestBody CountryDTO countryDTO) {
        CountryDTO saved = countryService.createOrUpdate(countryDTO);
        if (saved == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saved);
    }

}

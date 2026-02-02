package com.globits.demo.controller;

import java.util.List;

import com.globits.demo.service.CountryService;

import com.globits.demo.dto.CountryCreateDTO;

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
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    //CREATE
    //tạo biến Mới = gọi service trùng tên, pass dto từ client
    //trả về biến mới đã được service xử lý dưới dạng dto
    @PostMapping
    public ResponseEntity<CountryCreateDTO> create(@RequestBody CountryCreateDTO countryCreateDTO){
        //CountryDTO created = countryService.create(CountryDTO);
        //return ResponseEntity.ok(created);
        return ResponseEntity.ok(countryService.create(countryCreateDTO));
    }

    //READ ALL
    //gọi service trùng tên
    @GetMapping
    public ResponseEntity<List<CountryCreateDTO>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    //READ by id
    //gọi service trùng tên, pass id từ client.
    //id pass từ controller->service->dao; rồi dữ liệu được gửi ngược lại
    @GetMapping("/{id}")
    public ResponseEntity<CountryCreateDTO> get(@PathVariable int id) {
        CountryCreateDTO country = countryService.get(id);
        //nếu ko tìm thấy country vx ID đã cho, trả về not found
        //nếu tìm thấy, trả về country dưới dạng dto
        if (country == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(country);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CountryCreateDTO> update(@PathVariable int id,
                                                   @RequestBody CountryCreateDTO countryCreateDTO) {
        //tạo biến mới, gọi service trùng tên, pass id và dto từ client
        //service pass thông tin cho dao, rồi trả ngược kết quả
        CountryCreateDTO updated = countryService.save(id, countryCreateDTO);
        //nếu ko tìm thấy country vx ID đã cho, trả về not found
        if (updated == null) return ResponseEntity.notFound().build();
        //nếu tìm thấy, trả về dto thông tin đã cập nhật
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        //lấy thông tin country bằng id
        // dưới dạng dto
        CountryCreateDTO existing = countryService.get(id);
        //nếu trả về null => ko có country vx ID đã cho => trả về not found
        if (existing == null) return ResponseEntity.notFound().build();

        //nếu tìm thấy, gọi service để xóa
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

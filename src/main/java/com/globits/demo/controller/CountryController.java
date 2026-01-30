package com.globits.demo.controller;

import java.util.List;

import com.globits.demo.model.Country;
import com.globits.demo.service.CountryService;

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

    @PostMapping
    public ResponseEntity<Country> create(@RequestBody Country country){
        //Country created = countryService.create(country);
        //return ResponseEntity.ok(created);

        return ResponseEntity.ok(countryService.create(country));
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        //List<Country> created = countryService.create(country);
        //return ResponseEntity.ok(created);
        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> get(@PathVariable int id) {
        Country country = countryService.get(id);
        if (country == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(@PathVariable int id,
                                          @RequestBody Country country) {
        if (countryService.get(id) == null) return ResponseEntity.notFound().build();

        country.setId(id);
        //Country updated = countryService.save(country);
        //return ResponseEntity.ok(updated);

        return ResponseEntity.ok(countryService.save(country));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Country existing = countryService.get(id);
        if (existing == null) return ResponseEntity.notFound().build();

        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.globits.demo.controller;

import com.globits.demo.model.Country;
import com.globits.demo.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/create")
    public Country create(@RequestBody Country country){
        countryService.create(country);
        return country;
    }

}

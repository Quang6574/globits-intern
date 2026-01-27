package com.globits.demo.controller;
import com.globits.demo.dto.NewDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @PostMapping("/new")
    public NewDTO createNew(@RequestBody NewDTO model) {
        return model;
    }

}

package com.example.springtestingdemo.controller;

import com.example.springtestingdemo.dto.CatFactDTO;
import com.example.springtestingdemo.service.CatFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final CatFactService catFactService;

    @Autowired
    public DemoController(CatFactService catFactService) {
        this.catFactService = catFactService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/cat-fact")
    public CatFactDTO getCatFact() {
       return catFactService.getFact();
    }
}

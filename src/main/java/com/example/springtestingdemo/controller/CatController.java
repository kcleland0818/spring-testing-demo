package com.example.springtestingdemo.controller;

import com.example.springtestingdemo.dto.CatDTO;
import com.example.springtestingdemo.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")
public class CatController {

    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public String saveCat(@RequestBody CatDTO cat) {
        return catService.saveCat(cat);
    }

    @GetMapping("/{id}")
    public CatDTO getCat(@PathVariable Integer id) {
        return catService.getCat(id);
    }
}

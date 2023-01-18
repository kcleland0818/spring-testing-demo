package com.example.springtestingdemo.controller;

import com.example.springtestingdemo.dto.RescueDTO;
import com.example.springtestingdemo.service.RescueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rescue")
public class RescueController {

    private final RescueService rescueService;

    @Autowired
    public RescueController(RescueService rescueService) {
        this.rescueService = rescueService;
    }

    @PostMapping
    public String addRescue(@RequestBody RescueDTO rescue) {
        return rescueService.addRescue(rescue);
    }

    @GetMapping("/{name}")
    public RescueDTO getRescue(@PathVariable String name) {
        return rescueService.getRescue(name);
    }
}

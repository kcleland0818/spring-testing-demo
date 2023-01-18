package com.example.springtestingdemo.controller;

import com.example.springtestingdemo.dto.CatDTO;
import com.example.springtestingdemo.dto.CatFactDTO;
import com.example.springtestingdemo.service.CatFactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DemoControllerUnitTest {

    private DemoController demoController;

    private CatFactService catFactService;

    @BeforeEach
    void setUp() {
        catFactService = Mockito.mock(CatFactService.class);
        demoController = new DemoController(catFactService);
    }

    @Test
    void hello() {
        assertEquals("Hello World", demoController.hello());
    }

    @Test
    void getCatFact() {
        CatFactDTO catFact = new CatFactDTO();
        catFact.setFact("In ancient Egypt, when a family cat died," +
                "all family members would shave their eyebrows as a sign of mourning.");
        when(catFactService.getFact()).thenReturn(catFact);
        assertEquals(catFact, demoController.getCatFact());
    }
}
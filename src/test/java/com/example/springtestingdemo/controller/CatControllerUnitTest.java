package com.example.springtestingdemo.controller;

import com.example.springtestingdemo.dto.CatDTO;
import com.example.springtestingdemo.service.CatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CatControllerUnitTest {

    private CatController catController;
    private CatService catService;
    private CatDTO testCat;

    @BeforeEach
    void setUp() {
        catService = Mockito.mock(CatService.class);
        catController = new CatController(catService);

        testCat = new CatDTO();
        testCat.setName("Grumpy Cat");
        testCat.setBreed("DSH");
        testCat.setAge(7);
        testCat.setRescueId(1);
    }

    @Test
    void saveCat() {
        String success = "The cat, Grumpy Cat, has been added!";
        when(catService.saveCat(testCat)).thenReturn(success);
        assertEquals(success, catController.saveCat(testCat));
    }

    @Test
    void getCat() {
        when(catService.getCat(1)).thenReturn(testCat);
        assertEquals(testCat, catController.getCat(1));
    }
}
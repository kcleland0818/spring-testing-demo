package com.example.springtestingdemo.controller;

import com.example.springtestingdemo.dto.RescueDTO;
import com.example.springtestingdemo.service.RescueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RescueControllerUnitTest {

    private RescueController rescueController;
    private RescueService rescueService;
    private RescueDTO testRescue;

    @BeforeEach
    void setUp() {
        rescueService = Mockito.mock(RescueService.class);
        rescueController = new RescueController(rescueService);

        testRescue = new RescueDTO();
        testRescue.setName("Humane-Society");
        testRescue.setAddress("42 Wallaby Way");
        testRescue.setCity("Morristown");
        testRescue.setState("AZ");
        testRescue.setWebsiteUrl("www.humanesociety.com");
        testRescue.setPhoneNumber("555-123-4567");
    }

    @Test
    void addRescue() {
        String status = "The rescue, Humane-Society, has been added!";
        when(rescueService.addRescue(testRescue)).thenReturn(status);
        assertEquals(status, rescueController.addRescue(testRescue));
    }

    @Test
    void getRescue() {
        when(rescueService.getRescue("Humane-Society")).thenReturn(testRescue);
        assertEquals(testRescue, rescueController.getRescue("Humane-Society"));
    }
}
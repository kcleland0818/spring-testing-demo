package com.example.springtestingdemo.controller;

import com.example.springtestingdemo.dto.CatFactDTO;
import com.example.springtestingdemo.service.CatFactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoController.class)
class DemoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatFactService catFactService;

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    void getCatFact() throws Exception {
        CatFactDTO catFact = new CatFactDTO();
        String fact = "In ancient Egypt, when a family cat died, " +
                "all family members would shave their eyebrows as a sign of mourning.";
        catFact.setFact(fact);
        when(catFactService.getFact()).thenReturn(catFact);

        String expected = "{\"fact\":\"" + fact + "\"}";

        MvcResult response = mockMvc.perform(get("/cat-fact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expected, response.getResponse().getContentAsString());
    }
}
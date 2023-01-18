package com.example.springtestingdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    }

    @Test
    void getCatFact() throws Exception{

        // Get one cat fact from the external API
        MvcResult response = mockMvc.perform(get("/cat-fact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String firstFact = response.getResponse().getContentAsString();
        assertNotNull(firstFact);

        // Get a second cat fact from the external API
        response = mockMvc.perform(get("/cat-fact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String secondFact  = response.getResponse().getContentAsString();
        assertNotNull(secondFact);
        assertNotEquals(firstFact, secondFact);
    }
}
package com.example.springtestingdemo;

import com.example.springtestingdemo.dto.CatDTO;
import com.example.springtestingdemo.dto.RescueDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CatRescueAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    private RescueDTO testRescue;
    private CatDTO testCat;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        testRescue = new RescueDTO();
        testRescue.setName("Humane-Society");
        testRescue.setAddress("42 Wallaby Way");
        testRescue.setCity("Morristown");
        testRescue.setState("AZ");
        testRescue.setWebsiteUrl("www.humanesociety.com");
        testRescue.setPhoneNumber("555-123-4567");

        testCat = new CatDTO();
        testCat.setName("Grumpy Cat");
        testCat.setBreed("DSH");
        testCat.setAge(7);
        testCat.setRescueName(testRescue.getName());

        objectMapper = new ObjectMapper();

    }

    @Test
    @Order(1)    // Run this test first
    void addRescue() throws Exception {

        // Serialize the testRescue into a JSON as a String
        String rescueJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(testRescue);

        MvcResult response =
                mockMvc.perform(post("/rescue").contentType(MediaType.APPLICATION_JSON).content(rescueJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        String status = response.getResponse().getContentAsString();
        assertNotNull(status);
        assertEquals("The rescue, Humane-Society, has been added!", status);
    }

    @Test
    @Order(2)    // run this test second
    void getRescue() throws Exception {

        MvcResult response = mockMvc.perform(get("/rescue/Humane-Society"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String rescueJson = response.getResponse().getContentAsString();
        assertNotNull(rescueJson);
        RescueDTO rescueDTO = objectMapper.readValue(rescueJson, RescueDTO.class);
        assertEquals(testRescue, rescueDTO);
    }

    @Test
    @Order(3)    // run this test third
    void saveCat() throws Exception{

        String catJson = "{\n" +
                "  \"name\" : \"Grumpy Cat\",\n" +
                "  \"breed\" : \"DSH\",\n" +
                "  \"age\" : 7,\n" +
                "  \"rescueId\" : 1\n" +
                "}";

        MvcResult response =
                mockMvc.perform(post("/cat").contentType(MediaType.APPLICATION_JSON).content(catJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();
        String status = response.getResponse().getContentAsString();
        assertNotNull(status);
        assertEquals("The cat, Grumpy Cat, has been added!", status);
    }

    @Test
    @Order(4)    // run this test fourth
    void getCat() throws Exception {

        MvcResult response = mockMvc.perform(get("/cat/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String catJson = response.getResponse().getContentAsString();
        assertNotNull(catJson);
        CatDTO catDTO = objectMapper.readValue(catJson, CatDTO.class);
        catDTO.setRescueName(testRescue.getName());
        assertEquals(testCat, catDTO);
    }
}
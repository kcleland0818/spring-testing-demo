package com.example.springtestingdemo.service;

import com.example.springtestingdemo.dto.CatFactDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class CatFactServiceIntegrationTest {

    @Test
    void getFactFromExternalApi() {
        CatFactService catFactService = new CatFactService(new RestTemplate());
        CatFactDTO firstCatFact = catFactService.getFact();
        assertNotNull(firstCatFact);
        CatFactDTO secondCatFact = catFactService.getFact();
        assertNotNull(secondCatFact);
        assertNotEquals(firstCatFact, secondCatFact);
    }
}
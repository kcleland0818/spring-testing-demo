package com.example.springtestingdemo.service;

import com.example.springtestingdemo.dto.CatFactDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CatFactService {

    private static final String FACT_URI = "https://catfact.ninja/fact";
    private final RestTemplate restTemplate;

    @Autowired
    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CatFactDTO getFact() {
        CatFactDTO catFact = restTemplate.getForObject(FACT_URI, CatFactDTO.class);
        log.info("Retrieved cat fact from external source. Returning the DTO");
        return catFact;
    }
}

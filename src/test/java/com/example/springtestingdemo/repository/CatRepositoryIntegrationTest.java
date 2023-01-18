package com.example.springtestingdemo.repository;

import com.example.springtestingdemo.entity.Cat;
import com.example.springtestingdemo.entity.Rescue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class CatRepositoryIntegrationTest {

    @Autowired
    private CatRepository catRepository;

    private Cat testCat;

    @BeforeEach
    void setUp() {

        // Create a rescue to test with
        Rescue testRescue = new Rescue();
        testRescue.setName("Humane-Society");
        testRescue.setAddress("42 Wallaby Way");
        testRescue.setCity("Morristown");
        testRescue.setState("AZ");
        testRescue.setWebsiteUrl("www.humanesociety.com");
        testRescue.setPhoneNumber("555-123-4567");

        // Create a cat to test with
        testCat = new Cat();
        testCat.setName("Grumpy Cat");
        testCat.setBreed("DSH");
        testCat.setAge(7);
        testCat.setRescue(testRescue);
    }

    @AfterEach
    void tearDown() {
        catRepository.delete(testCat);
    }


    @Test
    void save() {
        assertEquals(testCat, catRepository.save(testCat));
    }

    @Test
    void findById() {

        // First save the cat in the repository
        catRepository.save(testCat);

        // Now test to see if we can retrieve the cat by its ID
        Optional<Cat> optionalCat = catRepository.findById(1);
        assertTrue(optionalCat.isPresent());
        assertEquals(testCat, optionalCat.get());

    }
}
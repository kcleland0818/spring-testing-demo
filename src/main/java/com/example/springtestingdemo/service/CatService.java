package com.example.springtestingdemo.service;

import com.example.springtestingdemo.dto.CatDTO;
import com.example.springtestingdemo.entity.Cat;
import com.example.springtestingdemo.repository.CatRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CatService {

    private final ModelMapper modelMapper;
    private final CatRepository catRepository;

    @Autowired
    public CatService(ModelMapper modelMapper, CatRepository catRepository) {
        this.modelMapper = modelMapper;
        this.catRepository = catRepository;
    }

    public String saveCat(CatDTO cat) {
        Cat catEntity = modelMapper.map(cat, Cat.class);
        catRepository.save(catEntity);
        String status = String.format("The cat, %s, has been added!", cat.getName());
        log.info(status);
        return status;
    }

    public CatDTO getCat(Integer id) {
        Optional<Cat> optionalCat = catRepository.findById(id);
        Cat catEntity = optionalCat.orElseThrow();
        log.info(String.format("Retrieved cat with ID %d from the database.", id));
        return modelMapper.map(catEntity, CatDTO.class);
    }
}

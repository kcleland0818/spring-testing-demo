package com.example.springtestingdemo.service;

import com.example.springtestingdemo.dto.RescueDTO;
import com.example.springtestingdemo.entity.Rescue;
import com.example.springtestingdemo.repository.RescueRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RescueService {

    private final ModelMapper modelMapper;
    private final RescueRepository rescueRepository;

    @Autowired
    public RescueService(ModelMapper modelMapper, RescueRepository rescueRepository) {
        this.modelMapper = modelMapper;
        this.rescueRepository = rescueRepository;
    }

    public String addRescue(RescueDTO rescue) {
        Rescue rescueEntity = modelMapper.map(rescue, Rescue.class);
        rescueRepository.save(rescueEntity);
        String status = String.format("The rescue, %s, has been added!", rescue.getName());
        log.info(status);
        return status;
    }

    public RescueDTO getRescue(String name) {
        Optional<Rescue> optionalRescue = rescueRepository.findRescueByName(name);
        Rescue rescue = optionalRescue.orElseThrow();
        log.info(String.format("Retrieved %s from the database", name));
        return modelMapper.map(rescue, RescueDTO.class);
    }
}
